<?php

namespace App\Controller;

use App\Repository\RecuperationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FrontRecuperationController extends AbstractController
{

    /**
     * @param RecuperationRepository $em
     * @return Response
     * @Route("/afficheRecup",name="afficheRecup")
     */
    public function listeRecup(RecuperationRepository  $em):Response{
        $Recuperations=$em->findAll();
        return  $this->render('recuperation_recompense/Front_recuperation_recompense/AfficheRecuperation.html.twig'
            ,["Recuperations"=>$Recuperations
        ]);

    }

}
