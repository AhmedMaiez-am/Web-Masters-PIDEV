<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Entity\Inventairecours;
use App\Entity\Parents;
use App\Form\FormParents;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\Session ;

class ListeCoursController extends AbstractController
{
    /**
     * @Route("/ajoutCours/{idc}", name="ajouterCours")
     * @param Cours $cours
     * @param FlashyNotifier $flashy
     * @param Request $request1
     * @return RedirectResponse
     */
    public function ajouter(Cours $cours, FlashyNotifier $flashy , Request $request1): RedirectResponse
    {
        if ($cours->getPrix()=="Gratuit") {
            $lstC = new Inventairecours();
            $lstC->setNomc($cours->getNom());
            $lstC->setTypecc($cours->getType());
            $lstC->setDescriptioncc($cours->getDescription());

            $em = $this->getDoctrine()->getManager();
            $em->persist($lstC);
            $em->flush();
            $this->addFlash('success','Le cours a été bien ajouté à votre inventaire !');


            return $this->redirectToRoute('redirection_inv_cours');
        }else {
            $session1 = $request1->getSession();
            $session1->start();
            $var1=$cours->getIdc();
            $session1->set('name1', $var1);
            return $this->redirectToRoute('paiement');
        }
    }

    /**
     * @Route ("/payerCours" , name="payer")
     * @param Request $request2
     * @return RedirectResponse
     */
    public function P (Request $request2): RedirectResponse
    {
        $data=$request2->get('nom');
        $lstP = new Parents();
        if ( $lstP=$this->getDoctrine()->getRepository('App:Parents')->findOneBy(['nomp'=>$data]))
        {
            $session2 = $request2->getSession();
            $session2->start();
            $var=$lstP->getNomp();
            $session2->set('name2', $var);
        }
        return $this->redirectToRoute('getPa');
    }
    /**
     * @Route ("/payerC" , name="paiement")
     * @return Response
     */
    public function N (): Response
    {
        return $this->render("liste_cours/payment.html.twig",
            ['user'=>'paiement']);
    }

    /**
     * @Route("/getParents", name="getParents")
     * @param Request $request
     * @return RedirectResponse
     */
    public function ind(Request $request): RedirectResponse
    {
        $data=$request->get('numcarte');
        $data1 =$request->get('mdpcarte');
        $session = $request->getSession();
        $session->start();
        $var=$session->get('name2');
        $client1 = new Parents();
        if ( $client1=$this->getDoctrine()->getRepository('App:Parents')->findOneBy(['nomp'=>$var])) {

            $carte= $client1->getNumcarte();
            $mdp = $client1->getPasscarte();
            (int)$pt = $client1->getPortefeuille();

            if ($mdp == $data1 && $carte==$data)
            {
                $c=new Cours();
                $inv = new Inventairecours();
                $session3=$request->getSession();
                $session3->start();
                $var3=$session3->get('name1');
                if ($c=$this->getDoctrine()->getRepository('App:Cours')->findOneBy(['idc'=>$var3])){
                    {
                        //Control du solde du parent par rappport au prix
                        ((int)$pr = $c->getPrix());
                        if((int)$pt >= (int)$pr){
                            //nom
                            $nomc=$c->getNom();
                            $inv->setNomc($nomc);
                            //type
                            $typec=$c->getType();
                            $inv->setTypecc($typec);
                            //description
                            $descc=$c->getDescription();
                            $inv->setDescriptioncc($descc);

                            $newPt = (int)$pt-(int)$pr;
                            $client1->setPortefeuille($newPt);

                            //Insertion du cours payant dans l'inventaire
                            $em = $this->getDoctrine()->getManager();
                            $em->persist($inv);
                            $em->flush();

                            //Update du portefeuille du parent
                            $em1 = $this->getDoctrine()->getManager();
                            $em1->persist($c);
                            $em1->flush();


                            $this->addFlash('success','Le cours a été bien ajouté à votre inventaire !');
                        }else{
                            $this->addFlash('payInvalide','Votre crédit du portefeuille est insuffisant !');
                            return $this->redirectToRoute('getPa');
                        }
                    }
                }
                return $this->redirectToRoute('redirection_inv_cours');
            }else{
                return $this->redirectToRoute('getPa');
            }
        }
        else {
            return $this->redirectToRoute('liste_cours');
        }
    }
    /**
     * @Route("/getPare", name="getPa")
     */

    public function in():Response {
        return $this->render("liste_cours/pay.html.twig",
            ['user'=>'getPa']);
    }

    /**
     * @Route("/listeCours", name="liste_cours")
     */
    public function index(): Response
    {
        $lstCours = $this->getDoctrine()
            ->getRepository('App:Cours')
            ->findAll();

        return $this->render('liste_cours/index.html.twig', ['lstCours' => $lstCours]);

    }
    /**
     * @Route("/listeContes", name="redirection_contes")
     */
    public function redirection():Response
    {
        return $this->render('liste_contes/index.html.twig');
    }
    /**
     * @Route("/invCours", name="redirection_inv_cours")
     */
    public function red():Response
    {
        return $this->render('inventaire_cours/index.html.twig');
    }
    /**
     * @Route("/invContes", name="redirection_inv_contes")
     */
    public function r():Response
    {
        return $this->render('inventaire_contes/index.html.twig');
    }

}