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
            ->setFrom('travelbios@gmail.com')
            ->setTo('mehdi.benabdallah.1@esprit.tn')
            ->setBody("helloo world!!!"

            );
        $mailer->send($message);
        if($mailer->send($message))
            return new Response('Email sent');
        else return  new Response('failed');

    }

}
