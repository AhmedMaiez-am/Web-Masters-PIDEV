<?php

namespace App\Controller;

use App\Form\MailType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
class MailController extends AbstractController
{

    /**
     * @Route("/email")
     */
    public function sendEmail(Request $request,MailerInterface  $mailer): Response
    {
        $form=$this->createForm(MailType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
            $mail = $form->getData();
            $email = (new Email())
                ->from('maitressecole13@gmail.com')
                ->to($mail['emailp'])
                ->subject('Felicitation!')
                ->Text("Bonjour")
                ->html('Felicitation votre enfant a gagné un recompense')
                ->attach($this->renderView(
                    'mail/contact.html.twig', compact('mail')
                ))

            ;
            $mailer->send($email);
            $this->addFlash('message',"votre message a été bien envoyé");
            return  $this->redirectToRoute('listerecuperation');//routename

        }
        // ...
        return $this->render('mail/index.html.twig', [
            'MailForm' => $form->createView()
        ]);
    }

}
