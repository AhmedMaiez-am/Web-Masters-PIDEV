<?php

namespace App\Controller;


use App\Entity\Questions;
use App\Entity\Quiz;
use App\Entity\Quizresult;
use App\Entity\Student;
use App\Form\QuestionType;
use App\Form\QuizType;
use App\Repository\QuestionRep;
use App\Repository\QuizRep;
use App\Repository\QuizresultsRep;
use Doctrine\ORM\EntityManagerInterface;
use Ob\HighchartsBundle\Highcharts\Highchart;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Console\Question\Question;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
class QuestionController extends Controller
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
    public function index(Request $request)
    {
        $lstQuestion = $this->getDoctrine()->getRepository('App:Questions')->findAll();
        $lstQuestion = $this->get('knp_paginator')->paginate(
        // Doctrine Query, not results
            $lstQuestion,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            6
        );

        return $this->render('question/index.html.twig', ['lstQuestion' => $lstQuestion]);


    }

    /**
     * @Route("/question", name="question")
     */
    public function AddQuestion(Request $request)
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
     * @Route("/suppim/{idq}", name="dd")
     */
    function deleteQuestion(QuestionRep $repo, $idq)
    {
        $user = $repo->find($idq);
        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("questions");
    }

    /**
     * @Route("/updateQuestion/{idq}", name="upquestion")
     */
    public function UpdateQuestion(QuestionRep $rep, Request $request, $idq)
    {
        $Di = $rep->find($idq);
        $Form = $this->createForm(QuestionType:: class, $Di);

        $Form->handleRequest($request);

        if ($Form->isSubmitted() && $Form->isValid()) {

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
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("questions");

        }
        return $this->render('question/updateQuestion.html.twig', [
            'form' => $Form->createView(),
        ]);
    }

    /**
     * @Route (path="/question/{quiz}", name="getQuestion")
     * @param Request $request
     * @param Quiz $quiz
     * @return Response
     */
    public function getQuestion(Request $request, Quiz $quiz): Response
    {
        $questions = $this->em->getRepository(Questions::class)->findBy(['quiz' => $quiz]);

        return $this->render('question/questionsEnf.html.twig', [
            'questions' => $questions,
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
        $student = $this->em->getRepository(Student::class)->find(1);
        /** @var Questions $question */
        $responses = [];
        $score = 0;
        $valid = 0;
        $invalid = 0;
        foreach ($request->request as $questionId => $user_response) {
            $question = $this->em->getRepository(Questions::class)->find((int)$questionId);
            $isTrue = $question->getAnswer() === $user_response;
            $isTrue ? $valid++ : $invalid++;

            if ($isTrue) {
                $score++;
            } elseif ($question->getQuiz()->getIsamericain() == "1") {
                $score--;
            }

            $responses[$questionId] = $isTrue;
        }

        $quizResult = new Quizresult();
        $quizResult->setQuizId($question->getQuiz());
        $quizResult->setScore($score);
        $quizResult->setRightAnswer($valid);
        $quizResult->setStudentId($student);
        $quizResult->setTimestamp(new \DateTime());

        $this->em->persist($quizResult);
        $this->em->flush();

        return $this->json([
            'success' => true,
            'responses' => $responses,
            'valid' => $valid,
            'invalid' => $invalid,
            'score' => $score,

        ]);
    }

    /**
     * @Route("/RechhQ", name="rechQ")
     */
    function QuestionRech(QuestionRep $repo, Request $request)
    {

        $data = $request->get('search');
        $lstQuestion = $repo->findBy(['question' => $data]);

        return $this->render("question/index.html.twig",
            ['lstQuestion' => $lstQuestion]);
    }


    /**
     * @Route("/pdfq",name="pdfq",methods={"GET"})
     */
    public function pdf(QuestionRep $repo): Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);
        $lstQuestion = $repo->findAll();

        $html = $this->renderView('question/pdfq.html.twig', ['lstQuestion' => $lstQuestion]);


        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');


        $dompdf->render();


        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);

    }



}
