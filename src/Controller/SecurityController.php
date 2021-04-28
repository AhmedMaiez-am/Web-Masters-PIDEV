<?php

namespace App\Controller;

use App\Entity\Directeur;
use App\Entity\Parents;
use App\Form\DirecType;
use App\Form\ParentsType;
use App\Repository\ValidationRep;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;


class SecurityController extends AbstractController
{

    /**
     * @Route ("/mailling", name="mailler")
     */
    function mot () {
        return $this->render("accueil/motdepasse.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/Motpasse", name="oubliepasse")
     */
    function genererChaineAleatoire(\Swift_Mailer $mailer , $longueur = 10 , Request $request  ,ValidationRep  $repository )
    {
        $data=$request->get('mail');

        $caracteres = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $longueurMax = strlen($caracteres);
        $chaineAleatoire = '';
        for ($i = 0; $i < $longueur; $i++)
        {
            $chaineAleatoire .= $caracteres[rand(0, $longueurMax - 1)];
        }
        $var = $chaineAleatoire ;

        $message = (new \Swift_Message('Validation'))
            ->setFrom('directeurkidzy@gmail.com','Administration')
            ->setTo($data)
            ->setBody($var);

        $mailer->send($message);

        // $tuteur = new Validation() ;
        $tuteur = $repository->findOneBy(['loginm'=>$data]);
        $tuteur->setCodem($var);
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        $session = $request->getSession();
        $session->start();
        $var=$tuteur->getIdm();
        $session->set('name', $var);

        return $this->render("security/Code.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/verr", name="cod")
     */
    function Codev () {
        return $this->render("security/Code.html.twig",
            ['user'=>'pageConnexionAdmin']);
    }
    /**
     * @Route ("/verifier", name="coder")
     */

    function code (Request  $request , ValidationRep  $repository)  {

        $data=$request->get('mail');

        $tuteur = $repository->findOneBy(['codem'=>$data]);

        if (($tuteur->getcodem()) ==($data )){
            $session = $request->getSession();
            $session->start();
            $var=$tuteur->getIdm();
            $session->set('name', $var);

            return $this->render("security/Password.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }  else
            return $this->render("security/Code.html.twig",
                ['user'=>'pageConnexionAdmin']);

    }
    /**
     * @Route ("/passwordj", name="passsw")
     */

    function password (Request  $request , ValidationRep  $repository)  {
        $data=$request->get('pass1');
        $data1=$request->get('pass2');
        $session = $request->getSession();
        $session->start();
        $var = $session->get('name');
        if ($data == $data1) {
            $tuteur = $repository->findOneBy(['idm'=>$var]);

            $tuteur->setPasswordm($data);

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->render("accueil/loginTuteur.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }

        else  {
            return $this->render("security/Password.html.twig",
                ['user'=>'pageConnexionAdmin']);
        }


    }
}
