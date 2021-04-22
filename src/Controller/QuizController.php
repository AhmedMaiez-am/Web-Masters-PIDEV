<?php

namespace App\Controller;

use App\Entity\Quiz;
use App\Form\QuestionType;
use App\Form\QuizType;
use App\Repository\QuizRep;
use App\Repository\QuestionRep;
use Doctrine\DBAL\Types\IntegerType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;

class QuizController extends AbstractController
{
    /**
     * @Route("/quiz", name="quiz")
     */
    public function index(QuizRep $repo)
    {
        $lstQuiz=$repo->findAll();
        return $this->render('quiz/index.html.twig', ['lstQuiz' => $lstQuiz]);
    }

    /**
     * @Route("/quizE", name="quizE")
     */
    public function indexQ()
    {
        $lstQuiz=$this->getDoctrine()->getRepository('App:Quiz')->findAll();
        return $this->render('quiz/indexEnf.html.twig', ['lstQuiz' => $lstQuiz]);
    }


    /**
     * @Route("/quest/{quizId}", name="questionsbyquiz")
     */
    public function indexE($quizId,QuestionRep $repo)
    {
        $lstQuestions=$repo->findby(['quiz'=>$quizId]);
       
        return $this->render('question/questionsEnf.html.twig', ['lstQuestion' => $lstQuestions]);
    
    }

    /**
     * @Route("/questT/{quizId}", name="questionsbyquizT")
     */
    public function indexT($quizId,QuestionRep $repo)
    {
        $lstQuestions=$repo->findby(['quiz'=>$quizId]);
       
        return $this->render('question/questionsT.html.twig', ['lstQuestion' => $lstQuestions]);
    
    }

// src/Controller/FormController.php

    /**
     * @Route("/form" , name ="addquiz")
     */
    public function AddQuiz(Request  $request)
    {
        $Quiz = new Quiz();


        $form = $this->createForm(QuizType::class, $Quiz);
        
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->persist($Quiz);
            $em->flush();
        }

        return $this->render('quiz/add.html.twig', array(
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route("/suppirimer/{id}", name="delete")
     */
    function deleteQuiz (QuizRep $repo , $id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("quiz");
    }

    
 /**
     * @Route ("/quizs/search", name="quizsSearch")
     * @param Request $request
     * @param NormalizerInterface $Normalizer
     * @return Response
     * @throws ExceptionInterface
     */
    public function searchQuiz(Request $request, NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Quiz::class);
        $requestString=$request->get('searchValue');
        $conte=$repository->findC($requestString);
        $jsonContent=$Normalizer->normalize($conte,'json',['groups'=>'quiz']);
        $retour = json_encode($jsonContent);

        return new JsonResponse($jsonContent);
    }


    /**
     * @Route("/Rechh", name="rechQuiz")
     */
    function QuizRech (QuizRep $repo , Request $request) {

        $data =$request->get('search');
        $lstQuiz=$repo->findBy(['title'=>$data]);

        return $this->render("quiz/index.html.twig",
            ['lstQuiz'=>$lstQuiz]);
    }
    

}
