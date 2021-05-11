<?php

namespace App\Controller;

use App\Entity\Questions;
use App\Repository\QuestionRep;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class QuestionControllerJSONController extends AbstractController
{
    /**
     * @Route("/AllQuestions", name="AllQuestions")
     */
    public function AllQuestions(QuestionRep $repo , NormalizerInterface $normalizer)
    {
        $lstQuestion=$repo->findAll();

        $jsonContent = $normalizer ->normalize($lstQuestion,'json',['groups'=>'post:read']);

        return  new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/ADDQUESTIONN/new" , name ="ADDQUESTIONN")
     */
    public function ADDQUESTIONN(Request  $request , NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $question = new  Questions();
        $question->setQuestion($request->get('question'));
        $question->setOption1($request->get('option1'));
        $question->setOption2($request->get('option2'));
        $question->setOption3($request->get('option3'));
        $question->setOption4($request->get('option4'));
        $question->setAnswer($request->get('answer'));
        $question->setQuiz($request->get('quiz'));
        $em->persist($question);
        $em->flush();
        $jsonContent= $normalizer->normalize($question, 'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/SuppQuestion/{id}", name="SuppQuestion")
     */
    function SuppQuestion (QuestionRep $repo,NormalizerInterface $normalizer,$id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        $jsonContent= $normalizer->normalize($user, 'json',['groups'=>'post:read']);
        return new Response(("Question deleted successfully".json_encode($jsonContent)));
    }

    /**
     * @Route("/UPQUESTION/{id}" , name ="UPQUESTION")
     */
    public function UPQUESTION(Request  $request , NormalizerInterface $normalizer,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $question = $em->getRepository(Questions::class)->find($id);
        $question->setQuestion($request->get('question'));
        $question->setOption1($request->get('option1'));
        $question->setOption2($request->get('option2'));
        $question->setOption3($request->get('option3'));
        $question->setOption4($request->get('option4'));
        $question->setAnswer($request->get('answer'));
        $question->setQuiz($request->get('quiz'));
        $em->flush();
        $jsonContent= $normalizer->normalize($question, 'json',['groups'=>'post:read']);
        return new Response("Question updated successfully".json_encode($jsonContent));

    }
}
