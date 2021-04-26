<?php

namespace App\Controller;

use App\Entity\Contes;
use App\Entity\Inventairecontes;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

class ListeContesController extends AbstractController
{
    /**
     * @Route("/ajout/{idconte}", name="ajouter")
     * @param Contes $contes
     * @return RedirectResponse
     */
    public function ajouter(Contes $contes)
    {
        $lstContes1 = new Inventairecontes();
        $lstContes1->setTitrec($contes->getTitre());
        $lstContes1->setAuteurc($contes->getAuteur());
        $em = $this->getDoctrine()->getManager();
        $em->persist($lstContes1);
        $em->flush();

        $this->addFlash('successC','La conte a été bien ajouté à votre inventaire !');

        return $this->redirectToRoute('redirection_inv_contes');
    }
    /**
     * @Route("/listeContes", name="liste_contes")
     */
    public function index()
    {
        $lstContes = $this->getDoctrine()
            ->getRepository('App:Contes')
            ->findAll();

        return $this->render('liste_contes/index.html.twig', ['lstContes' => $lstContes]);
    }
    /**
     * @Route("/listCours", name="redirection_cours")
     */
    public function redirectionC():Response
    {
        return $this->render('liste_cours/index.html.twig');
    }
    /**
     * @Route("/invCours", name="redirection_inv_cours")
     */
    public function redirectionInvCours():Response
    {
        return $this->render('inventaire_cours/index.html.twig');
    }
    /**
     * @Route("/invContes", name="redirection_inv_contes")
     */
    public function redirectionInvContes():Response
    {
        return $this->render('inventaire_contes/index.html.twig');
    }


}