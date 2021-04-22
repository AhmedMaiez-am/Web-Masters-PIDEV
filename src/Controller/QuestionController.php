<?php

namespace App\Controller;

use App\Entity\Questions;
use App\Entity\Quiz;
use App\Form\QuestionType;
use App\Form\QuizType;
use App\Repository\QuestionRep;
use App\Repository\QuizRep;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Console\Question\Question;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class QuestionController extends AbstractController
{

    /**
     * @var EntityManagerInterface
     */
    private $em;

    public function __construct(EntityManagerInterface $em)
    {
        $this->em = $em;
    }

    /**
     * @Route("/questions", name="questions")
     */
    public function index()
    {
        $lstQuestion=$this->getDoctrine()->getRepository('App:Questions')->findAll();
        return $this->render('question/index.html.twig', ['lstQuestion' => $lstQuestion]);
    }

    /**
     * @Route("/question", name="question")
     */
    public function AddQuestion (Request $request)
    {
       $Questions = new Questions();

        $form = $this->createForm(QuestionType::class, $Questions);
        
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            if ($data->getAnswer() === "answer1") {
                $Questions->setAnswer($Questions->getOption1());
            }
            if ($data->getAnswer() === "answer2") {
                $Questions->setAnswer($Questions->getOption2());
            }
            if ($data->getAnswer() === "answer3") {
                $Questions->setAnswer($Questions->getOption3());
            }
            if ($data->getAnswer() === "answer4") {
                $Questions->setAnswer($Questions->getOption4());
            }

            $em = $this->getDoctrine()->getManager();
            $em->persist($Questions);
            $em->flush();
        }

        return $this->render('question/add.html.twig', array(
            'form' => $form->createView(),
        ));


    }
    /**
     * @Route("/suppi/{idq}", name="dd")
     */
    function deleteQuestion (QuestionRep $repo , $idq) {
        $user=$repo->find($idq);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("questions");
    }

    /**
     * @Route("/updateQuestion/{idq}", name="upquestion")
     */
    public function UpdateQuestion(QuestionRep  $rep , Request $request , $idq)
    {
        $Di = $rep->find($idq);
        $Form =$this->createForm(QuestionType:: class , $Di);
     
        $Form->handleRequest($request);

        if ($Form ->isSubmitted() && $Form->isValid() ) {

            $data = $Form->getData();
            if ($data->getAnswer() === "answer1") {
                $Di->setAnswer($Di->getOption1());
            }
            if ($data->getAnswer() === "answer2") {
                $Di->setAnswer($Di->getOption2());
            }
            if ($data->getAnswer() === "answer3") {
                $Di->setAnswer($Di->getOption3());
            }
            if ($data->getAnswer() === "answer4") {
                $Di->setAnswer($Di->getOption4());
            }
            $em =$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("questions");

        }
        return $this->render('question/updateQuestion.html.twig',[
            'form' =>$Form->createView(),
        ]);
    }

    /**
     * @Route (path="/question/{quiz}", name="getQuestion")
     * @param Request $request
     * @param Quiz $quiz
     * @return Response
     */
    public function getQuestion(Request $request, Quiz $quiz)
    {
        $questions = $this->em->getRepository(Questions::class)->findBy(['quiz' => $quiz]);
        /** get random quiz  */

        $index = rand(0, count($questions) - 1);
        /** @var Questions $question */
        $question = $questions[$index];

        if ($request->isXmlHttpRequest()){
            return $this->json([
                'success' => true,
                'html' => $this->renderView('question/questionsEnf.html.twig', [
                    'question' => $question,
                    'quiz' => $quiz
                ])
            ]);
        }
        return $this->render('question/questionsEnf.html.twig',[
            'question' => $question,
            'quiz' => $quiz
        ]);
    }

    /**
     * @Route (path="/question_validate",name="validateQuestion")
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\JsonResponse
     */
    public function validateQuestion(Request $request)
    {
        if ($request->isXmlHttpRequest()){
            $questionId = $request->request->get('question');
            $user_response = $request->request->get('response');
            /** @var Questions $question */
            $question = $this->em->getRepository(Questions::class)->find((int)$questionId);
            $isTrue = $question->getAnswer() === $user_response;

            return $this->json([
                'success' => true,
                'isTrue' => $isTrue,
            ]);
        }
    }

    /**
     * @Route("/RechhQ", name="rechQ")
     */
    function QuestionRech (QuestionRep $repo , Request $request) {

        $data =$request->get('search');
        $lstQuestion=$repo->findBy(['question'=>$data]);

        return $this->render("question/index.html.twig",
            ['lstQuestion'=>$lstQuestion]);
    }
}
