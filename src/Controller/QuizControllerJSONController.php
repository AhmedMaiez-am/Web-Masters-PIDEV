<?php

namespace App\Controller;

use App\Entity\Quiz;
use App\Repository\QuizRep;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class QuizControllerJSONController extends AbstractController
{
    /**
     * @Route("/APIquiz",name="AllEvent")
     */
    public function AllEvent(Request $request,NormalizerInterface $normalizer){

        $repo = $this->getDoctrine()->getRepository(Quiz::class);
        $events = $repo->findAll();




        $jsonContent = $normalizer->normalize($events,'json',['groups'=> 'post:read']);

        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/ADDQUIZZ/new" , name ="ADDQUIZZ")
     */
    public function ADDQUIZZ(Request  $request , NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $quiz = new  Quiz();
        $quiz->setTitle($request->get('title'));
        $quiz->setIsamericain($request->get('isamericain'));
        $em->persist($quiz);
        $em->flush();
        $jsonContent= $normalizer->normalize($quiz, 'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));

    }

//    /**
//     * @Route("/UPQUIZ/{id}" , name ="UPQUIZ")
//     */
//    public function UPQUIZ(Request  $request , NormalizerInterface $normalizer,$id)
//    {
//        $em = $this->getDoctrine()->getManager();
//        $quiz = $em->getRepository(Quiz::class)->find($id);
//        $quiz->setTitle($request->get('title'));
//        $quiz->setIsamericain($request->get('isamericain'));
//        $em->flush();
//        $jsonContent= $normalizer->normalize($quiz, 'json',['groups'=>'post:read']);
//        return new Response("Quiz updated successfully".json_encode($jsonContent));
//
//    }

    /**
     * @Route("/SuppQuiz/{id}", name="SuppQuiz")
     */
    function SuppQuiz (QuizRep $repo,NormalizerInterface $normalizer,$id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        $jsonContent= $normalizer->normalize($user, 'json',['groups'=>'post:read']);
        return new Response(("Quiz deleted successfully".json_encode($jsonContent)));
    }


}
