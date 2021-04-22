<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\search;
use App\Form\ReclamationType;
use App\Form\SearchReclType;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;

class ReclamationController extends AbstractController
{
    /**
     * @Route("/home", name="home")
     */
    public function index(): Response
    {
        return $this->render('reclamation/home.html.twig', [
            'controller_name' => 'ReclamationController',
        ]);
    }
    /**
     * @Route("/afficher", name="afficher")
     */
    public function af(ReclamationRepository $repository)
    {

        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->findAll();

        return $this->render('reclamation/index.html.twig', [
            'reclamation' => $reclamation,
        ]);

    }

    /**
     * @Route ("modifier/{id}", name="modifier")
     */
    function modifier(ReclamationRepository $repository,Request $request,$id)
    {
        $reclamation = $repository->find($id);
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficher');
        }
        return $this->render('reclamation/modifier.html.twig', [
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/Ajoutrec", name="Ajoutrec")
     */
    public function ajouter (Request $request)
    {

        $reclamation = new Reclamation();//creation instance

        $form = $this->createForm(ReclamationType::class, $reclamation);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($reclamation);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('afficher');//redirecter la pagee la page dafichage
        }
        return $this->render('reclamation\ajouter.html.twig', [
            'form' => $form->createview()
        ]);
    }

/**
 * @Route("/supprimerreclfront/{id}", name="supprimerfront")
 */
public function supprimerfront ($id)
{
    $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->find($id);
    $em=$this->getDoctrine()->getManager();
    $em->remove($reclamation);//suprrimer lobjet dans le parametre
    $em->flush();
    return $this->redirectToRoute('afficher');
}

    /**
     * @Route("/afficherback", name="afficherback")
     */
    public function afr (Request $request , PaginatorInterface $paginator)
    {
        $repository = $this->getDoctrine()->getrepository(Reclamation::Class);//recuperer repisotory
        $allrec = $repository->findAll();
        $rec = $paginator->paginate(
        // Doctrine Query, not results
            $allrec,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            5
        );
        return $this->render('reclamation/afficherec.html.twig', [
            'reclamation' => $rec,
        ]);
    }


    /**
     * @Route ("modifierback/{id}", name="modifierback")
     */
    function modifierr(ReclamationRepository $repository,Request $request,$id)
    {
        $reclamation = $repository->find($id);
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficherback');
        }
        return $this->render('reclamation/modifierback.html.twig', [
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/Ajoutrecback", name="Ajoutrecback")
     */
    public function ajouterr (Request $request)
    {

        $reclamation = new Reclamation();//creation instance

        $form = $this->createForm(ReclamationType::class, $reclamation);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($reclamation);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('afficherback');//redirecter la pagee la page dafichage
        }
        return $this->render('reclamation\ajouterback.html.twig', [
            'form' => $form->createview()
        ]);
    }

    /**
     * @Route("/supprimerreclback/{id}", name="supprimerback")
     */
    public function supprimerback($id)
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reclamation);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('afficherback');
    }

    /**
     * @Route("/admin/searchOffreajax ", name="ajaxsearch")
     */
    public function searchOffreajax(Request $request,ReclamationRepository $repository)
    {
        $repository = $this->getDoctrine()->getRepository(Reclamation::class);
        $requestString=$request->get('searchValue');
        $reclamations = $repository->findReclamationByNom($requestString);

        return $this->render('reclamation/reclamationajax.html.twig', [
            "reclamation"=>$reclamations
        ]);
    }
    /**
     * @Route("/triparprenom", name="triparprenom")
     */

    public function Triprenom(ReclamationRepository $repo)
    {
        $donnees=$repo->listOrderByPren();
        return $this->render("reclamation/afficherec.html.twig",['reclamation'=>$donnees]);

    }
    /**
     * @Route("/triparnom", name="triparnom")
     */

    public function TriNom(ReclamationRepository $repo)
    {
        $donnees=$repo->listOrderByName();
        return $this->render("reclamation/afficherec.html.twig",['reclamation'=>$donnees]);

    }

    /**
     * @route ("/recpdf", name="/recpdf")
     */
    public function imprimerpdf()
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('reclamation/mypdf.html.twig', [
            'reclamation' => $reclamation
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("list_reclamation.pdf", [
            "Attachment" => true
        ]);
    }





}
