<?php

namespace App\Controller;

use App\Entity\Recompense;
use App\Form\RecompenseType;
use App\Repository\RecompenseRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class RecompenseController extends AbstractController
{


    /**
     * @Route("/recompense", name="recompense")
     */
    public function index(): Response
    {
        return $this->render('recompense/index.html.twig', [
            'controller_name' => 'RecompenseController',
        ]);
    }
    /**
     * @Route ("/listrecompense",name="listrecompense")
     */
    public function list(RecompenseRepository $em):Response{
        $Recompenses=$em->findAll();
        return $this->render('recompense/list.html.twig',['Recompenses'=>$Recompenses]);
    }
 /**
  * @Route ("/ajoutrecompense",name="ajoutrecompense")
  */
public function add(Request $req):Response{
    $Recompense=new Recompense();
    $form=$this->createForm(RecompenseType::class,$Recompense);
    $form->handleRequest($req);
    if($form->isSubmitted() && $form->isValid()){
        $em=$this->getDoctrine()->getManager();
        $em->persist($Recompense);
        $em->flush();
        return $this->redirectToRoute('listrecompense'); //route name
    }
    return $this->render('recompense/AddRecompense.html.twig',[
        'form'=>$form->createView()]);
}
/**
 * @Route ("/modifierRecompense/{id}",name="modifierRecompense")
 */
public function update(RecompenseRepository $rp,Request $request,$id):Response{
    $Recompense= $rp->find($id);

    $form=$this->createForm(RecompenseType::class,$Recompense);
    $form->handleRequest($request);
    if($form->isSubmitted() && $form->isValid()){
        $em=$this->getDoctrine()->getManager();
        $em->persist($Recompense);
        $em->flush();
        $this->addFlash('success', "recompense mis à jour");
        return  $this->redirectToRoute('listrecompense');
    }
    return  $this->render('Recompense/update.html.twig',['form'=>$form->createView()]);
}
/**
 * @Route ("/supprimerRecompense/{id}",name="supprimerRecompense")
 */
public function delete(Recompense $r):Response{
    $em=$this->getDoctrine()->getManager();
    $em->remove($r);
    $em->flush();
  return $this->redirectToRoute('listrecompense');
}


    /**
     *
     * @Route("/searchRec",name="searchRec")
     */
public function searchRec(RecompenseRepository $repository,Request $request){
    $data=$request->get('search');
        $Recompenses=$repository->findBynom($data);
    return $this->render('recompense/list.html.twig',['Recompenses'=>$Recompenses]);
}

    /**
     * @param RecompenseRepository $repository
     * @return Response
     * @Route ("/stat",name="stat")
     */
  public function statistique(RecompenseRepository$repository){
    $recompenses=$repository->findAll();
    $recNom=[];
    $recNbr=[];
    foreach ($recompenses as $recompense){
      $recNom[]= $recompense->getNomrec();
      $recNbr[]=$recompense->getNbrPoint();

    }
    return $this->render('recompense/showStatistics.html.twig',
        ['recNom'=> json_encode($recNom),
            'recNbr'=>json_encode($recNbr)]);

}


}
