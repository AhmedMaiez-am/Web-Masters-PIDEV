<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ModifType;
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
use Snipe\BanBuilder\CensorWords;



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
     * @Route ("modifier/{idr}", name="modifier")
     */
    function modifier(ReclamationRepository $repository,Request $request,$idr)
    {
        $reclamation = $repository->find($idr);
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
        $reclamation->setEtat("En attente");
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();//recupuration entity manager

            $reclamation1 = $form->get('reclamation')->getData();
            $censor = new CensorWords;
            $censor->setDictionary('BadWords','fr');
            $rec = $censor->censorString($reclamation1);
            $reclamation->setReclamation($rec['clean']);
            $em->persist($reclamation);//l'ajout de l'objet cree
            $em->flush();
            return $this->redirectToRoute('afficher');//redirecter la pagee la page dafichage
        }
        return $this->render('reclamation\ajouter.html.twig', [
            'form' => $form->createview()
        ]);
    }

    /**
     * @Route("/supprimerreclfront/{idr}", name="supprimerfront")
     */
    public function supprimerfront ($idr)
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->find($idr);
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
     * @Route ("modifierback/{idr}", name="modifierback")
     */
    function modifierr(ReclamationRepository $repository,Request $request,$idr,\Swift_Mailer $mailer)
    {
        $reclamation = $repository->find($idr);
        $form = $this->createForm(ModifType::class, $reclamation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();

            $mail = $form->get('mail')->getData();

            // On crée le message
            $message = (new \Swift_Message('RECLAMATION_EN_COURS'))
                // On attribue l'expéditeur
                ->setFrom('travelbios@gmail.com')
                // On attribue le destinataire

                ->setTo($mail)
                // On crée le texte avec la vue
                ->setBody(
                    $this->renderView(
                        'mail/afficherec.html.twig', compact('reclamation')
                    ),
                    'text/html'
                )
            ;
            $mailer->send($message);

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
        $reclamation->setEtat("En attente");
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
     * @Route("/supprimerreclback/{idr}", name="supprimerback")
     */
    public function supprimerback($idr)
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->find($idr);
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
