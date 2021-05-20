<?php

namespace App\Controller;
use App\Form\FilterType;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use App\Entity\Cours;
use App\Form\Cours1Type;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\File;
use App\Service\FileUploader;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
/**
 * @Route("/cours")
 */
class CoursController extends AbstractController
{
    /**
     * @Route("/", name="cours_index", methods={"GET","POST"})
     */
    public function index(Request $request): Response
    {
        $cours = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->findAll();
        $form = $this->createForm(FilterType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $cours=$this->getDoctrine()->getRepository(Cours::class)->filterByPriceType($request->get('filter')['prix'],$request->get('filter')['type'],$request->get('filter')['rate']);
            return $this->render('cours/index.html.twig', [
                'cours' => $cours,
                'form' => $form->createView(),

            ]);
//dump($request->get('filter')['type']);
//die;
        }

        return $this->render('cours/index.html.twig', [
            'cours' => $cours,
            'form' => $form->createView(),

        ]);

    }

    /**
     * @Route("/searchStudentx ", name="searchStudentx")
     */
    public function searchStudentx(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Cours::class);
        $requestString = $request->get('searchValue');
        $cour = $repository->findStudentByNsc($requestString);
        $jsonContent = $Normalizer->normalize($cour, 'json', ['groups' => 'c:read']);
        $re = json_encode($jsonContent);
        return new Response($re);
    }

    /**
     * @Route("/new", name="cours_new", methods={"GET","POST"})
     */
    public function new(Request $request, FileUploader $fileUploader): Response
    {
        $cour = new Cours();
        $form = $this->createForm(Cours1Type::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // $file stores the uploaded PDF file
            /** @var Symfony\Component\HttpFoundation\File\UploadedFile $file */
            $file = $form->get("cours")->getData();
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
            $cour->setCours($fileName);
            // ... persist the $product variable or any other work

            //   return $this->redirect($this->generateUrl('home'));

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($cour);
            $entityManager->flush();

            return $this->redirectToRoute('cours_index');
        }

        return $this->render('cours/new.html.twig', [
            'cour' => $cour,
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
     * @Route("/{idc}", name="cours_show", methods={"GET"})
     */
    public function show(Cours $cour): Response
    {
        return $this->render('cours/show.html.twig', [
            'cour' => $cour,
        ]);
    }

    /**
     * @Route("/{idc}/edit", name="cours_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Cours $cour): Response
    {
        $form = $this->createForm(Cours1Type::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // $file stores the uploaded PDF file
            /** @var Symfony\Component\HttpFoundation\File\UploadedFile $file */
            $file = $form->get("cours")->getData();
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
            $cour->setCours($fileName);
            // ... persist the $product variable or any other work

            //   return $this->redirect($this->generateUrl('home'));

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('cours_index');

        }

        return $this->render('cours/edit.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idc}", name="cours_delete", methods={"POST"})
     */
    public function delete(Request $request, Cours $cour): Response
    {
        if ($this->isCsrfTokenValid('delete' . $cour->getIdc(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('cours_index');
    }

    /**
     * @Route(" /_wdt", name="export")
     */
    public function export()
    {
        $cours=$this->getDoctrine()->getRepository(Cours::class)->findAll();


        $spreadsheet = new Spreadsheet();

        /* @var $sheet \PhpOffice\PhpSpreadsheet\Writer\Xlsx\Worksheet */


        $sheet = $spreadsheet->getActiveSheet();
        $sheet->setCellValue('A1', 'nom');
        $sheet->setCellValue('B1', 'type!');
        $sheet->setCellValue('C1', 'description');
        $sheet->setCellValue('D1', 'prix');
        $sheet->setTitle("Mes cours");
        $rowCount=2;
        foreach ($cours as $cour) {
            $sheet->setCellValue('A'.$rowCount, $cour->getNom());
            $sheet->setCellValue('B'.$rowCount, $cour->getType());
            $sheet->setCellValue('C'.$rowCount, $cour->getDescription());
            $sheet->setCellValue('D'.$rowCount, $cour->getPrix());
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

    /**
     * @Route("/rate/{idc}", name="cours_rate", methods={"POST"})
     */
    public function rate (Request $request, Cours $cour)
    {
        $cour->setRate(intval($request->get('ratedIndex'))+1);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($cour);
        $entityManager->flush();
        return new Response('success');
    }
    /**
     * @Route("/listeCours", name="espace_inventaire")
     */
    public function redirectionC():Response
    {
        return $this->render('liste_cours/index.html.twig');
    }

}