<?php

namespace App\Controller;

use App\Entity\Contes;
use App\Form\ContesType;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\File;
use App\Service\FileUploader;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/contes")
 */
class ContesController extends AbstractController
{
    /**
     * @Route("/", name="contes_index", methods={"GET"})
     */
    public function index(): Response
    {
        $contes = $this->getDoctrine()
            ->getRepository(Contes::class)
            ->findAll();

        return $this->render('contes/index.html.twig', [
            'contes' => $contes,
        ]);
    }
    /**
     * @Route("/searchStudent ", name="searchStudent")
     */
    public function searchStudentx(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Contes::class);
        $requestString = $request->get('searchValue');
        $contes = $repository->findStudentByT($requestString);
        $jsonContent = $Normalizer->normalize($contes, 'json', ['groups' => 'c:read']);
        $re = json_encode($jsonContent);
        return new Response($re);
    }
    /**
     * @Route("/new", name="contes_new", methods={"GET","POST"})
     */
    public function new(Request $request,FileUploader $fileUploader): Response
    {
        $conte = new Contes();
        $form = $this->createForm(ContesType::class, $conte);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
                // $file stores the uploaded PDF file
                /** @var Symfony\Component\HttpFoundation\File\UploadedFile $file */
                $file = $form->get("contes")->getData();
                //   dump($cour->getCours());
                $fileName = $this->generateUniqueFileName() . '.' . $file->guessExtension();


                // Move the file to the directory where brochures are stored
                try {
                    $file->move(
                        $this->getParameter('cours_directory'),
                        $fileName
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'brochure' property to store the PDF file name
                //   $fileName = $fileUploader->upload($file);

                //  $cour->setCours($fileName);
                // instead of its contents
                $conte->setContes($fileName);
                // ... persist the $product variable or any other work

                //   return $this->redirect($this->generateUrl('home'));


                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($conte);
                $entityManager->flush();

                return $this->redirectToRoute('contes_index');
            }

            return $this->render('contes/new.html.twig', [
                'conte' => $conte,
                'form' => $form->createView(),
            ]);
        }
        /**
         * @return string
         */
        private function generateUniqueFileName()
    {
        // md5() reduces the similarity of the file names generated by
        // uniqid(), which is based on timestamps
        return md5(uniqid());
    }


        /**
     * @Route("/{idconte}", name="contes_show", methods={"GET"})
     */
    public function show(Contes $conte): Response
    {
        return $this->render('contes/show.html.twig', [
            'conte' => $conte,
        ]);
    }

    /**
     * @Route("/{idconte}/edit", name="contes_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Contes $conte): Response
    {
        $form = $this->createForm(ContesType::class, $conte);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // $file stores the uploaded PDF file
            /** @var Symfony\Component\HttpFoundation\File\UploadedFile $file */
            $file = $form->get("contes")->getData();
            //   dump($cour->getCours());
            $fileName = $this->generateUniqueFileName().'.'.$file->guessExtension();



            // Move the file to the directory where brochures are stored
            try {
                $file->move(
                    $this->getParameter('cours_directory'),
                    $fileName
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            // updates the 'brochure' property to store the PDF file name
            //   $fileName = $fileUploader->upload($file);

            //  $cour->setCours($fileName);
            // instead of its contents
            $conte->setContes ($fileName);
            // ... persist the $product variable or any other work

            //   return $this->redirect($this->generateUrl('home'));

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('contes_index');
        }

        return $this->render('contes/edit.html.twig', [
            'conte' => $conte,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idconte}", name="contes_delete", methods={"POST"})
     */
    public function delete(Request $request, Contes $conte): Response
    {
        if ($this->isCsrfTokenValid('delete'.$conte->getIdconte(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($conte);
            $entityManager->flush();
        }

        return $this->redirectToRoute('contes_index');
    }

    /**
     * @Route(" /_wdt", name="exporte")
     */
    public function export()
    {
        $contes=$this->getDoctrine()->getRepository(Contes::class)->findAll();


        $spreadsheet = new Spreadsheet();

        /* @var $sheet \PhpOffice\PhpSpreadsheet\Writer\Xlsx\Worksheet */


        $sheet = $spreadsheet->getActiveSheet();
        $sheet->setCellValue('A1', 'nom');
        $sheet->setCellValue('B1', 'auteur');
        $sheet->setTitle("Mes contes");
        $rowCount=2;
        foreach ($contes as $con) {
            $sheet->setCellValue('A'.$rowCount, $con->getTitre());
            $sheet->setCellValue('B'.$rowCount, $con->getAuteur());
            $rowCount++;
        }
        // Create your Office 2007 Excel (XLSX Format)
        $writer = new Xlsx($spreadsheet);

        // Create a Temporary file in the system
        $fileName = 'my_first_excel_symfony4.xlsx';
        $temp_file = tempnam(sys_get_temp_dir(), $fileName);

        // Create the excel file in the tmp directory of the system
        $writer->save($temp_file);

        // Return the excel file as an attachment
        return $this->file($temp_file, $fileName, ResponseHeaderBag::DISPOSITION_INLINE);
    }
}
