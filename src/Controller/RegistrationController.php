<?php

namespace App\Controller;

use App\Entity\Directeur;
use App\Entity\Enfant;
use App\Form\DirecType;
use App\Form\EnfantType;
use App\Repository\DirecteurRep;
use App\Repository\EnfantRep;
use App\Repository\ParentRep;
use App\Repository\TuteurRep;
use App\Security\DirecteurAuthenticator;
use Doctrine\ORM\Repository\RepositoryFactory;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Validator\Constraints as Assert;

use Symfony\Component\HttpFoundation\File\Exception\FileException;


use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Guard\GuardAuthenticatorHandler;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\Session\Session ;

use Symfony\Component\Form\FormTypeInterface;

class RegistrationController extends AbstractController
{
    /**
     * @Route("/inscription", name="app_register")
     */
    public function register(DirecteurRep $repo, Request $request, UserPasswordEncoderInterface $passwordEncoder, GuardAuthenticatorHandler $guardHandler, DirecteurAuthenticator $authenticator): Response
    {
        $Directeur = new Directeur();
        $form = $this->createForm(DirecType::class, $Directeur);
        $form->add('Ajouter' , SubmitType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password
            //  $Directeur->setPassword(
            //    $passwordEncoder->encodePassword(
            //   $Directeur,
            //$form->get('password')->getData()
            //)
            //);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Directeur);
            $entityManager->flush();

        }

        return $this->render('security/seconn.html.twig' , [
            'reg' => $form->createView()
        ]);

    }
    /**
     * @Route("/inscEnfant", name="apEnfant")
     */
    public function addEnfant (Request $request)
    {    $session = $request->getSession();
        $session->start();
        $id=$session->get('name');

        $Mait = new Enfant();
        $Mait->setIdparent($id);
        $Form =$this->createForm(EnfantType :: class , $Mait);


        $Form->handleRequest($request);
        (int)  $request->query->get("age") ;
        if ($Form ->isSubmitted() && $Form->isValid() ) {



            $file = $Mait->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $fileName
                );
            }
            catch (FileException $e){
            }

            $Mait->setImage($fileName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($Mait);
            $em->flush() ;
            return $this->render("accueil/loginEnfant.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }
        return $this->render('registration/Enfant.html.twig',[
            'form' =>$Form->createView(),
        ]);

    }
    /**
     * @Route("/stat", name="statiq")
     */

    public function statistiques(EnfantRep $enfantRep , ParentRep $parentRep ) {
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
        return $this->render('registration/chartjs.html.twig', [

            'ageEnfant' => json_encode($ageEnfant),
            'count' => json_encode($count),
            'countParent' => json_encode($countParent),
            'identifiant' => json_encode($identifiant),
            'parentColor' => json_encode($parentColor),
        ]);
    }
}
