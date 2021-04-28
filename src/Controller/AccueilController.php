<?php

namespace App\Controller;


use App\Entity\Directeur;
use App\Entity\Enfant;
use App\Entity\Maitresse;
use App\Entity\Validation;
use App\Form\DemandeType;
use App\Form\DirecType;
use App\Form\EnfantType;
use App\Form\ValidationType;
use App\Repository\DemandeRep;
use App\Repository\DirecteurRep;
use App\Repository\EnfantRep;
use App\Repository\ParentRep;
use App\Repository\TuteurRep;
use App\Repository\ValidationRep;
use App\Security\DirecteurAuthenticator;
use Doctrine\ORM\Repository\RepositoryFactory;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\FormTypeInterface;
use App\Entity\Parents;
use App\Form\ParentsType;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\Session\Session ;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;
use Symfony\Component\HttpFoundation\JsonResponse;
use Dompdf\Dompdf;
use Dompdf\Options;
use \Twilio\Rest\Client;
//use MercurySeries\FlashyBundle\FlashyNotifier;
use BotMan\BotMan\BotMan;




class AccueilController extends AbstractController
{ private $twilio;

    public function __construct(Client $twilio) {
        $this->twilio = $twilio;

    }
    /**
     * @Route("/accueil", name="accueil7")
     */
    public function index(): Response
    {
        return $this->render('accueil/AccueilFront.html.twig', [

        ]);
    }
    /**
     * @Route("/register", name="security")
     */
    public function addParents (Request $request)
    {
        $Parents = new Parents();
        $Form =$this->createForm(ParentsType :: class , $Parents );

        $Form->handleRequest($request);
        if ($Form ->isSubmitted() && $Form->isValid() ) {
            $var = 'non' ;
            $Parents->setBlock($var);
            $em =$this->getDoctrine()->getManager();
            $em->persist($Parents);
            $em->flush();
            return $this->redirectToRoute("pageConnexion");
        }
        return $this->render('accueil/Ajout.html.twig',[
            'f' =>$Form->createView(),
        ]);
    }

    /**
     * @Route("/update/{id}", name="pere")
     */
    public function UpdateParents (ParentRep $rep , Request $request , $id)
    {
        $Directeur = $rep->find($id);
        $Form =$this->createForm(ParentsType :: class , $Directeur);

        $Form->handleRequest($request);
        if ($Form ->isSubmitted() && $Form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();

            $user=$rep->findAll();

            return $this->render("accueil/parent.html.twig",
                ['user'=>$user]);

        }
        return $this->render('accueil/update.html.twig',[
            'f' =>$Form->createView(),
        ]);
    }

    /**
     * @Route("/demande", name="dem")
     */
    public function addDemande (Request $request)
    {
        $Maitresse = new Maitresse();
        $Form =$this->createForm(DemandeType :: class , $Maitresse);

        $Form->handleRequest($request);
        if ($Form ->isSubmitted() && $Form->isValid() ) {

            $file1 = $Maitresse->getCv();
            $file= $Maitresse->getPath();

            $fileName1 = $this->generateUniqueFileName().'.'.$file1->guessExtension();

            $file1->move(
                $this->getParameter('pdf_directory'),
                $fileName1
            );
            $fileName = $this->generateUniqueFileName().'.'.$file->guessExtension();

            $file->move(
                $this->getParameter('img_directory'),
                $fileName
            );
            $Maitresse->setCv($fileName1);
            $Maitresse->setPath($fileName);



            $em =$this->getDoctrine()->getManager();
            $em->persist($Maitresse);
            $em->flush();

        }
        return $this->render('accueil/index.html.twig',[
            'form' =>$Form->createView(),
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
     * @Route("/affiche", name="aff")
     */
    function affichemedForAdmin(DemandeRep $repo){

        $user=$repo->findAll();

        return $this->render("accueil/Demande.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/supp/{id}", name="d")
     */
    function deleteDemande(DemandeRep $repo , $id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        $user=$repo->findAll();

        return $this->render("accueil/Demande.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/ajoutdemande/{id}", name="ajouterTuteur")
     * @param Maitresse $demande
     * @return RedirectResponse | Response
     */
    public function addValidation (Maitresse $demande , Request $request , \Swift_Mailer $mailer1)
    {
        $Mai = new Validation();
        $Mai->setLoginm($demande->getEmailmaitresse());
        $Mai->setPasswordm($demande->getCin());
        $Mai->setImg($demande->getPath());
        $em = $this->getDoctrine()->getManager();
        $em->persist($Mai);
        $em->remove($demande);
        $em->flush();

        $message = (new \Swift_Message('Validation'))
            ->setFrom('directeurkidzy@gmail.com','Administration')
            ->setTo($Mai->getLoginm())
            ->setBody('Votre Demande a été validé avec success.
Merci pour votre confiance.');

        $mailer1->send($message);

        return $this->render("accueil/Demande.html.twig",
            ['user'=>$demande]);

    }
    /**
     * @Route("/afficheParent", name="afficher")
     */
    function affichePrents (ParentRep $repo){

        $user=$repo->findAll();

        return $this->render("accueil/parent.html.twig",
            ['user'=>$user]);
    }



    /**
     * @Route("/affidire", name="direc")
     */
    function afficheDirec (DirecteurRep  $repo){

        $user=$repo->findAll();

        return $this->render("security/seconn.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/suppi/{id1}/", name="dp")
     */
    function deleteParent (ParentRep $repo , $id1) {
        $user=$repo->find($id1);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        $user=$repo->findAll();

        return $this->render("accueil/parent.html.twig",
            ['user'=>$user]);

    }
    /**
     * @Route("/affEnfant", name="EnfantA")
     */
    function afficheEnf (EnfantRep $repo){

        $user=$repo->findAll();

        return $this->render("registration/listeEnfant.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/supprimerEnfant/{iden}", name="enfant")
     */
    function deleteEnfant (EnfantRep $repo , $iden) {
        $user=$repo->find($iden);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        $user=$repo->findAll();

        return $this->render("registration/listeEnfant.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/updateEnf/{iden}", name="upenfant")
     */
    public function UpdatEnfant (EnfantRep $rep , Request $request , $iden)
    {
        $Di = $rep->find($iden);
        $Form =$this->createForm(EnfantType :: class , $Di);

        $Form->handleRequest($request);
        if ($Form ->isSubmitted() && $Form->isValid() ) {
            $file = $Di->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $fileName
                );
            }
            catch (FileException $e){

            }
            $Di->setImage($fileName);
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            $user=$rep->findAll();

            return $this->render("registration/listeEnfant.html.twig",
                ['user'=>$user]);

        }
        return $this->render('registration/updateEnfant.html.twig',[
            'f' =>$Form->createView(),
        ]);
    }
    /**
     * @Route("/Rech", name="rechEnf")
     */
    function EnfantRech (EnfantRep $repo , Request $request) {

        $data =$request->get('search');
        $user=$repo->findBy(['nomenfant'=>$data]);

        return $this->render("registration/listeEnfant.html.twig",
            ['user'=>$user]);
    }

    /**
     * @Route ("/tri",name="triEnfant")
     */
    public function tri(EnfantRep $repository , Request $request)
    {

        if (isset($_POST['tri']))
        {
            $choix = $_POST['tri'];
            if ($choix=='nom')
            {
                $produit=$repository->OrderByNomE();
                return $this->render("registration/listeEnfant.html.twig",['user'=>$produit]);
            }
            elseif ($choix=='prenom')
            {
                $produit=$repository->OrderByPrenomE();
                return $this->render("registration/listeEnfant.html.twig",['user'=>$produit]);
            }
            elseif ($choix=='age')
            {
                $produit=$repository->OrderByAgeE();
                return $this->render("registration/listeEnfant.html.twig",['user'=>$produit]);
            }

        }

    }
    /**
     * @Route ("/connexionAdmin", name="pageConnexionAdmin")
     */
    public function PageConnexion2 (DirecteurRep $repository, Request $request)
    {

        return $this->render("accueil/login.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }

    /**
     * @Route ("/CompteAdmin", name="ConnexionAdmin")
     */
    public function Connexion (DirecteurRep $repository, Request $request , EnfantRep $enfantRep , ParentRep $parentRep )
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['login'=>$data]);

        if(($admin->getPassword())==($request->get('mdp')))
        {
            $user=$repository->findAll();
            $session = $request->getSession();
            $session->start();
            $var=$admin->getIdd();
            $session->set('name', $var);
            $enf = $enfantRep->countEnfant();
            $count = [];
            $ageEnfant = [];
            $parent =$parentRep->countParent();
            $parentColor= [];
            $enfColor = [];
            $countParent = [];
            $identifiant = [];
            foreach($parent as $parent ){

                if ($parent['identifiant'] == 'non')
                    $parentColor[] = '#7FB3D5' ;
                elseif ($parent['identifiant'] == 'oui')
                    $parentColor[] = '#F5B7B1 ';
                $countParent [] = $parent['countParent'];
                $identifiant [] = $parent['identifiant'];
            }
            foreach($enf as $enf){
                $count[] = $enf['count'];
                $ageEnfant[] = $enf['ageEnfant'];

            }

            return $this->render("registration/chartjs.html.twig",
                ['user'=>$user ,
                    'ageEnfant' => json_encode($ageEnfant),
                    'count' => json_encode($count),
                    'countParent' => json_encode($countParent),
                    'identifiant' => json_encode($identifiant),
                    'parentColor' => json_encode($parentColor),
                ] );

        }
        else{return $this->redirectToRoute("pageConnexionAdmin");}
    }
    /**
     * @Route ("/connexionParent", name="pageConnexion")
     */
    public function PageConnexionParent (ParentRep $repository, Request $request)
    {

        return $this->render("accueil/loginParent.html.twig",
            ['user'=>'pageConnexion']);
    }

    /**
     * @Route ("/CompteParent", name="ConnexionParent")
     */
    public function ConnexionParent (ParentRep $repository, Request $request)
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['emailp'=>$data]);
        if(($admin->getBlock()==null)||($admin->getBlock()=='non')){
            if(($admin->getPasswordp())==($request->get('mdp')))
            {
                $user=$repository->findAll();
                $session = $request->getSession();
                $session->start();
                $var=$admin->getIdp();
                $session->set('name', $var);

                return $this->render("accueil/AccueilParents.html.twig",
                    ['user'=>$user]);

            }
            else{
                return $this->redirectToRoute("pageConnexion");}
        }
        else{
            return $this->redirectToRoute("pageConnexion");}
    }
    /**
     * @Route ("/Bloquer/{id}", name="Bloquer")
     */
    public function Bloquer (ParentRep $repository, Request $request,$id )
    {
        $user=$repository->find($id);
        $user->setBlock('oui');
        $em =$this->getDoctrine()->getManager();
        $em->flush();
        $user=$repository->findAll();
        return $this->render("accueil/parent.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route ("/DBloquer/{id}", name="DBloquer")
     */
    public function DBloquer (ParentRep $repository, Request $request,$id)
    {
        $user=$repository->find($id);
        $user->setBlock('non');
        $em =$this->getDoctrine()->getManager();
        $em->flush();
        $user=$repository->findAll();

        return $this->render("accueil/parent.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route ("/connexionTuteur", name="pageConnexionTuteur")
     */
    public function pageConnxTuteur (TuteurRep  $repository, Request $request)
    {

        return $this->render("accueil/loginTuteur.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }

    /**
     * @Route ("/CompteTuteur", name="ConnexionTuteur")
     */
    public function ConnexionTuteur (TuteurRep $repository, Request $request)
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['loginm'=>$data]);

        if(($admin->getPasswordm())==($request->get('mdp')))
        {
            $user=$repository->findAll();

            return $this->render("cours/conn.html.twig",
                ['user'=>$user]);

        }
        else{return $this->redirectToRoute("pageConnexionTuteur");}
    }
    /**
     * @Route("/update", name="pereup")
     */
    public function UpdateP (ParentRep $rep , Request $request )
    {

        $session = $request->getSession();
        $session->start();
        $session->get('name');
        $var =  $session->get('name');
        $Directeur = $rep->findOneBy(['idp'=>$var]);
        $Form =$this->createForm(ParentsType :: class , $Directeur);

        $Form->handleRequest($request);
        if ($Form ->isSubmitted() && $Form->isValid() ) {
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("accueilParent");

        }
        return $this->render('accueil/updateFrontP.html.twig',[
            'f' =>$Form->createView(),
        ]);
    }
    /**
     * @Route ("/accuParent", name="accueilParent")
     */
    public function PageCon (ParentRep $repository, Request $request)
    {

        return $this->render("accueil/AccueilParents.html.twig",
            ['user'=>'pageConnexion']);
    }
    /**
     * @Route("/supprimerParent", name="supParent")
     */
    function deleteP (ParentRep $repo , Request $request ) {
        $session = $request->getSession();
        $session->start();
        $session->get('name');
        $var =  $session->get('name');

        $user = $repo->findOneBy(['idp'=>$var]);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("pageConnexion");
    }
    /**
     * @Route ("/connexionEnf", name="pageConnexionEnfant")
     */
    public function pageConnxEnfant (EnfantRep  $repository, Request $request)
    {

        return $this->render("accueil/loginEnfant.html.twig",
            ['user'=>'pageConnexionEnfant']);
    }

    /**
     * @Route ("/CompteEnfant", name="ConnexionEnfantp")
     */
    public function ConnexionEnfant (EnfantRep $repository, Request $request)
    {
        $data=$request->get('mail');
        $admin=$repository->findOneBy(['nomenfant'=>$data]);

        if(($admin->getPassword())==($request->get('mdp')))
        {
            $user=$repository->findAll();

            return $this->render("accueil/AccueilFront.html.twig",
                ['user'=>$user]);

        }
        else{return $this->redirectToRoute("pageConnexionEnfant");}
    }
    /**
     * @Route ("/DonsEnfant", name="Donner")
     */
    public function DonnerParent (ParentRep $repository, Request $request)
    {
        $session = $request->getSession();
        $session->start();
        $session->get('name');
        $var =  $session->get('name');

        $user = $repository->findOneBy(['idp'=>$var]);
        $carte= $user->getNumcarte();
        $mdp = $user->getPasscarte();
        $data=$request->get('numcarte');
        $data1 =$request->get('mdpcarte');
        $pr =$request->get('montant');
        (int)$pt = $user->getPortefeuille();
        $num = $user->getTelp();
        if ($mdp == $data1 && $carte==$data) {
            $newPt = (int)$pt-(int)$pr;

            $user->setPortefeuille($newPt);
            $em1 = $this->getDoctrine()->getManager();
            $em1->flush($user);
            $message = $this->twilio->messages->create(
                $num , // Send text to this number
                array(
                    'from' => '+15124003293', // My Twilio phone number
                    'body' => 'Hello from Awesome Massages. Thank your for being KIND '  . ' KIDZY.'
                )
            );
            $this->addFlash('success', $message);

            return $this->render("accueil/AccueilParents.html.twig",
                ['user'=>$user]);
        }else {
            $this->addFlash('payInvalide','Votre crédit du portefeuille est insuffisant !');
        }

        return $this->render("accueil/Dons.html.twig",
            ['user'=>$user]);
    }
    /**
     * @Route("/imprimerRes", name="imprimerRes")
     */
    function ImprimerRes(ParentRep $repo, Request $request){

        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);


        $user=$repo->findAll();


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView("accueil/PDF.html.twig",
            ['user'=>$user]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);


    }
    /**
     * @Route("/event/sms",name="sendSms")
     */
    public function sendSMS()
    {

        $message = $this->twilio->messages->create(
            '+21653227764', // Send text to this number
            array(
                'from' => '+15124003293', // My Twilio phone number
                'body' => 'Hello from Awesome Massages. A reminder that your massage appointment is for today at '  . ' for any questions.'
            )
        );
        $this->addFlash('success', $message);


        return $this->redirectToRoute('accueil7');
    }
    /**
     * @Route("/chatbot", name="chatbot")
     */
    function chatbotAction(Request $request)
    {
        // get a BotMan instance from Symfony's service container
        $botman = $this->container->get('gas_botman.botman');

        //your logic here, for e.g the following statements
        $botman->hears('(hello|hi|hey)', function (BotMan $bot) {
            $bot->reply('Hello');
        });

        $botman->fallback(function (BotMan $bot) {
            $bot->typesAndWaits(2);
            $bot->reply("Sorry I dd not understand your request");
        });

        $botman->listen();

        return new Response();
    }
    /**
     * @Route("/listeContes", name="redirection_contes")
     */
    public function redirection():Response
    {
        return $this->render('liste_contes/index.html.twig');
    }
    /**
     * @Route("/listeCours", name="redirection_cours")
     */
    public function redirectionC():Response
    {
        return $this->render('liste_cours/index.html.twig');
    }
}

