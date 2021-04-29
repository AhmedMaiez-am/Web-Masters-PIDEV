<?php

namespace App\Controller;

use App\Entity\Reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mailer\Bridge\Google\Transport\GmailSmtpTransport;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;

class MailController1Controller extends AbstractController
{
    /**
     * @Route("/mail", name="mail")
     */
    public function index(): Response
    {
        return $this->render('mail/index.html.twig', [
            'controller_name' => 'MailController',
        ]);
    }
    /**
     * @Route("/EmailRecl/{idr}", name="EmailRecl" )
     */

    public function Email(\Swift_Mailer  $mailer,$idr)
    {
        $recl= $this->getDoctrine()->getRepository(Reclamation::class)->find($idr);

        $message = (new \Swift_Message('[ Reclamation en cours de traitement ]'))
            ->setFrom('directeurkidzy@gmail.com')
            ->setTo($recl->getEmail())
            ->setBody("Bonjour Mr/Mme ,
    Déja bienvenue dans notre site web , on espére qu'il vous plait et qu'il répond à tous nos besoins .
    Merci pour votre comphrension et si vous avez une autre demande ou un conseil ca sera avec grand plaisir.
    Cordialement."

            );
        $mailer->send($message);
        if($mailer->send($message))
            return new Response('Email sent');
        else return  new Response('failed');

    }

}
