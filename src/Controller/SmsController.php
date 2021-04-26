<?php

namespace App\Controller;

use App\Entity\Parents;
use App\Form\SmsType;
use Doctrine\DBAL\Types\TextType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;

class SmsController extends AbstractController
{
    /**
     * @Route("/sms", name="sms")
     */
    public function index(): Response
    {
        return $this->render('sms/index.html.twig', [
            'controller_name' => 'SmsController',
        ]);
    }

    /**
     * @param Parents $parents
     * @return Response
     * @Route ("/sms",name="sms")
     */
    public function sms(Request $request):Response{


        $form=$this->createForm(SmsType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
         $sms=$form->getData();

// Your Account SID and Auth Token from twilio.com/console
            $account_sid = 'ACfeaf963c34a2bbd21d41a370d1011ce1';
            $auth_token = 'cda9cf0f2617cf5cea40122fe8ca03e7';
// In production, these should be environment variables. E.g.:
// $auth_token = $_ENV["TWILIO_AUTH_TOKEN"]

// A Twilio number you own with SMS capabilities
            $twilio_number = "+13346058453";

            $client = new Client($account_sid, $auth_token);
            $client->messages->create(
            // Where to send a text message (your cell phone?)
                $sms['telp'],
                array(
                    'from' => $twilio_number,
                    'body' => 'Felicitation  votre fils a gagné une recompense'
                )
            );
            $this->addFlash('message',"votre mail a été bien envoyé");
            return  $this->redirectToRoute('listerecuperation');//routename
        }
        return $this->render('sms/index.html.twig',['form'=>$form->createView()]);
    }


}
