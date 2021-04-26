<?php

namespace App\Controller;

use App\Entity\Recuperation;
use App\Form\RecompenseType;
use App\Form\RecuperationType;
use App\Repository\EnfantRepository;
use App\Repository\ParentsRepository;
use App\Repository\RecompenseRepository;
use App\Repository\RecuperationRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class RecuperationRecompenseController extends AbstractController
{
    /**
     * @Route("/recuperation", name="recuperation_recompense")
     */
    public function index(): Response
    {
        return $this->render('recuperation_recompense/index.html.twig', [
            'controller_name' => 'RecuperationRecompenseController',
        ]);
    }

    /**
     * @Route ("/listeenfant",name="listeenfant")
     */
    public function listEnf(EnfantRepository $em):Response{
        $Enfants=$em->findAll();
        return $this->render('recuperation_recompense/listEnfant.html.twig',
        ['Enfants'=>$Enfants]);
    }
    /**
     * @Route ("/listeparent",name="listeparent")
     */

    public function listparent(ParentsRepository $em):Response{
        $Parents=$em->findAll();
        return $this->render('recuperation_recompense/listparents.html.twig',[
            'Parents'=>$Parents
        ]);
    }

    /**
     * @Route ("/listerecompense",name="recompense")
     */
    public function list1(RecompenseRepository $em):Response{
        $Recompenses=$em->findAll();
        return $this->render('recuperation_recompense/listRec.html.twig',
            ['Recompenses'=>$Recompenses]);

    }

    /**
     * @Route("/listerecuperation",name="listerecuperation")
     */
    public function listRecup(RecuperationRepository $em):Response{
        $Recuperations=$em->findAll();
        return $this->render('recuperation_recompense/listRecuperation.html.twig',
            ['Recuperations'=>$Recuperations]);
    }



    /**
     * @Route ("/ajoutrecuperation",name="ajoutrecuperation")
     */
    public function add(Request $request):Response{
        $Recuperation=new Recuperation();
        $form=$this->createForm(RecuperationType::class,$Recuperation);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($Recuperation);
            $em->flush();
            return $this->redirectToRoute("listerecuperation");//route name
        }
        return $this->render('recuperation_recompense/AddRecuperation.html.twig',
            ['form'=>$form->createView()]);

    }
      /**
       * @Route("/supprimerRecuperation/{id}",name="supprimerRecuperation")
       *
       */
    public function delete(Recuperation $r):Response{
        $em=$this->getDoctrine()->getManager();
        $em->remove($r);
        $em->flush();
        return $this->redirectToRoute('listerecuperation');
    }

    /**
     * @Route("/updateRecuperation/{id"),name="updateRecuperation")
     *
     */
    public function update(RecuperationRepository $rp,Request $rq,$id):Response{
        $Recuperation=$rp->find($id);
        $form=$this->createForm(RecuperationType::class,$Recuperation);
        $form->handleRequest($rq);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($Recuperation);
            $em->flush();
            $this->addFlash('success', "recompense mis à jour");
            return  $this->redirectToRoute('listrecuperation');
        }
        return  $this->render('recuperation_recompense/modifierRecuperation.html.twig',['form'=>$form->createView()]);

    }

    /**
     * @param Request $request
     * @param NormalizerInterface $normalizer
     * @return JsonResponse
     * @throws ExceptionInterface
     * @Route("/search",name="search")
     */

    public function search(Request $request,NormalizerInterface $normalizer){
        $repository = $this->getDoctrine()->getRepository(Recuperation::class);
        $requestString = $request->get('searchValue');
        $Recuperation = $repository->findBynomrec($requestString);
        $jsonContent = $normalizer->normalize($Recuperation, 'json', ['groups' => 'Recuperation:read']);
        $re = json_encode($jsonContent);
        return new Response($re);

    }

    /**
     * @param RecuperationRepository $repository
     * @return Response
     * @Route("Recuperation/tri")
     */
     public function orderByMailSQL(RecuperationRepository $repository){
          $Recuperations=$repository->OrderByMail();
         return $this->render('recuperation_recompense/listRecuperation.html.twig',
             ['Recuperations'=>$Recuperations]);
     }

    /**
     * @param RecuperationRepository $repository
     * @return Response
     * @Route ("/trinom",name="tri")
     */
public function orederByNom(RecuperationRepository $repository){
         $Recuperations=$repository->OrderBynom();
         return $this->render('recuperation_recompense/listRecuperation.html.twig',
         ['Recuperations'=>$Recuperations]);
}

    /**
     * @param RecuperationRepository $repository
     * @return Response
     * @Route("/pdf",name="pdf",methods={"GET"})
     */
public function pdf(RecuperationRepository $repository):Response{
    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');

    // Instantiate Dompdf with our options
    $dompdf = new Dompdf($pdfOptions);
    $Recuperations=$repository->findAll();
    // Retrieve the HTML generated in our twig file
    $html=$this->renderView('recuperation_recompense/pdf.html.twig',[
        'Recuperations'=>$Recuperations
    ]);

    // Load HTML to Dompdf
    $dompdf->loadHtml($html);
// (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
    $dompdf->setPaper('A4', 'portrait');

    // Render the HTML as PDF
    $dompdf->render();

    // Output the generated PDF to Browser (force download)
    $dompdf->stream("mypdf.pdf", [
        "Attachment" => true
    ]);

}


}
