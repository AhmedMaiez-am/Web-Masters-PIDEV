<?php

namespace App\Controller;

use App\Entity\Inventairecours;
use App\Repository\InventaireCoursRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class InventaireCoursController extends AbstractController
{
    /**
     * @Route("/invCours", name="inventaire_cours")
     */
    public function index()
    {
        $lstCoursInv = $this->getDoctrine()
            ->getRepository('App:Inventairecours')
            ->findAll();
        return $this->render('inventaire_cours/index.html.twig', ['lstCoursInv' => $lstCoursInv]);
    }
    /**
     * @Route ("/triCours", name="triCours")
     * @param InventaireCoursRepository $repo
     * @return Response
     */
    public function orderByNomSQl(InventaireCoursRepository $repo){
        $lstCoursInv=$repo->OrderByNom();
        return $this->render('inventaire_cours/index.html.twig',
            ['lstCoursInv'=>$lstCoursInv]);
    }

    /**
     * @Route("/invCours/suppCours/{idcc}", name ="suppC")
     * @param $idcc
     * @return RedirectResponse;
     */
    public function SupprimerCours($idcc , FlashyNotifier $flashy)
    {
        $em = $this->getDoctrine()->getManager();
        $c = $this->getDoctrine()
            ->getRepository('App:Inventairecours')
            ->find($idcc);
        $em->remove($c);
        $em->flush();

        $this->addFlash('supp','Le cours a été supprimé de votre inventaire !');
        return $this->redirectToRoute('inventaire_cours');
    }
    /**
     * @Route ("/invCours/search", name="searchInvC")
     * @param Request $request
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function searchConte(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Inventairecours::class);
        $requestString=$request->get('searchValue');
        $conte=$repository->findC($requestString);
        $jsonContent=$Normalizer->normalize($conte,'json',['groups'=>'cours']);
        $retour = json_encode($jsonContent);
        return new JsonResponse($jsonContent);
    }
    /**
     * @Route("/listCours", name="redirection_cours")
     */
    public function redirectionC():Response
    {
        return $this->render('liste_cours/index.html.twig');
    }
    /**
     * @Route("/listeContes", name="redirection_contes")
     */
    public function redirectionContes():Response
    {
        return $this->render('liste_contes/index.html.twig');
    }
    /**
     * @Route("/invContes", name="redirection_inv_contes")
     */
    public function redirectionInvContes():Response
    {
        return $this->render('inventaire_contes/index.html.twig');
    }

}
