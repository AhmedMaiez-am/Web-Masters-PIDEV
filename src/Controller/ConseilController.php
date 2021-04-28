<?php

namespace App\Controller;

use App\Entity\Conseil;
use App\Entity\Reclamation;
use App\Form\ConseilType;
use App\Form\ReclamationType;
use App\Repository\ConseilRepository;
use App\Repository\ReclamationRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ConseilController extends AbstractController
{
    /**
     * @Route("/conseil", name="conseil")
     */
    public function index(): Response
    {
        return $this->render('conseil/index.html.twig', [
            'controller_name' => 'ConseilController',
        ]);
    }
    /**
     * @Route("/afficherconseil", name="afficherconseil")
     */
    public function af()
    {

        $conseil=$this->getDoctrine()->getRepository(Conseil::class)->findAll();

        return $this->render('conseil/index.html.twig', [
            'conseil' => $conseil,
        ]);

    }

    /**
     * @Route ("modifierconseil/{idc}", name="modifierconseil")
     */
    function modifier(ConseilRepository $repository,Request $request,$idc)
    {
        $conseil = $repository->find($idc);
        $form = $this->createForm(ConseilType::class, $conseil);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficherconseil');
        }
        return $this->render('conseil/modifier.html.twig', [
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/Ajouterconseil", name="Ajouterconseil")
     */
    public function ajouter (Request $request)
    {

        $conseil = new Conseil();//creation instance

        $form = $this->createForm(ConseilType::class, $conseil);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($conseil);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('afficherconseil');//redirecter la pagee la page dafichage
        }
        return $this->render('conseil\ajouter.html.twig', [
            'form' => $form->createview()
        ]);
    }

    /**
     * @Route("/supprimerconseil/{idc}", name="supprimerconseil")
     */
    public function supprimerfront ($idc)
    {
        $conseil=$this->getDoctrine()->getRepository(Conseil::class)->find($idc);
        $em=$this->getDoctrine()->getManager();
        $em->remove($conseil);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('afficherconseil');
    }



    /**
     * @Route("/afficherconseilback", name="afficherconseilback")
     */
    public function afc()
    {

        $conseil=$this->getDoctrine()->getRepository(Conseil::class)->findAll();

        return $this->render('conseil/afficherconback.html.twig', [
            'conseil' => $conseil,
        ]);

    }

    /**
     * @Route ("modifierconseilback/{idc}", name="modifierconseilback")
     */
    function modifierc(ConseilRepository $repository,Request $request,$idc)
    {
        $conseil = $repository->find($idc);
        $form = $this->createForm(ConseilType::class, $conseil);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute('afficherconseilback');
        }
        return $this->render('conseil/modifierconback.html.twig', [
            'form' => $form->createView()
        ]);
    }
    /**
     * @Route("/Ajouterconseilback", name="Ajouterconseilback")
     */
    public function ajouterc (Request $request)
    {

        $conseil = new Conseil();//creation instance

        $form = $this->createForm(ConseilType::class, $conseil);//Récupération du formulaire dans le contrôleur:
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();//recupuration entity manager
            $em->persist($conseil);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('afficherconseilback');//redirecter la pagee la page dafichage
        }
        return $this->render('conseil\ajouterconback.html.twig', [
            'form' => $form->createview()
        ]);
    }

    /**
     * @Route("/supprimerconseilback/{idc}", name="supprimerconseilback")
     */
    public function supprimerback ($idc)
    {
        $conseil=$this->getDoctrine()->getRepository(Conseil::class)->find($idc);
        $em=$this->getDoctrine()->getManager();
        $em->remove($conseil);//suprrimer lobjet dans le parametre
        $em->flush();
        return $this->redirectToRoute('afficherconseilback');
    }

    /**
     * @route ("/conpdf", name="/conpdf")
     */
    public function imprimerpdf()
    {
        $conseil=$this->getDoctrine()->getRepository(Conseil::class)->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('conseil/mypdfc.html.twig', [
            'conseil' => $conseil
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("list_conseil.pdf", [
            "Attachment" => true
        ]);
    }
}
