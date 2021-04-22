<?php

namespace App\Controller;

use App\Entity\Inventairecontes;
use App\Repository\InventaireContesRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use ProxyManager\Exception\ExceptionInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class InventaireContesController extends AbstractController
{
    /**
     * @Route("/invContes", name="inventaire_contes")
     */
    public function index()
    {
        $invContes = $this->getDoctrine()
            ->getRepository('App:Inventairecontes')
            ->findAll();
        return $this->render('inventaire_contes/index.html.twig', ['invContes' => $invContes]);
    }

    /**
     * @Route("/invContes/supp/{idcontesc}", name ="supp")
     * @param $idcontesc
     * @return RedirectResponse;
     */
    public function SupprimerConte($idcontesc , FlashyNotifier $flashy)
    {
        $em = $this->getDoctrine()->getManager();
        $c = $this->getDoctrine()
            ->getRepository('App:Inventairecontes')
            ->find($idcontesc);
        $em->remove($c);
        $em->flush();

        $this->addFlash('suppC','Le conte a été supprimé de votre inventaire !');
        return $this->redirectToRoute('inventaire_contes');
    }
    /**
     * @Route ("/invContes/search", name="searchInv")
     * @param Request $request
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws \Symfony\Component\Serializer\Exception\ExceptionInterface
     */
    public function searchConte(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Inventairecontes::class);
        $requestString=$request->get('searchValue');
        $conte=$repository->findContes($requestString);
        $jsonContent=$Normalizer->normalize($conte,'json',['groups'=>'contes']);
        $retour = json_encode($jsonContent);
        return new JsonResponse($jsonContent);
    }

    /**
     * @Route ("/tri", name="tri")
     * @param InventaireContesRepository $repo
     * @return Response
     */
    public function orderByTitreSQl(InventaireContesRepository $repo){
        $invContes=$repo->OrderByTitre();
        return $this->render('inventaire_contes/index.html.twig',
            ['invContes'=>$invContes]);
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
     * @Route("/listeContes", name="redirection_contes")
     */
    public function redirectionContes():Response
    {
        return $this->render('liste_contes/index.html.twig');
    }
}
