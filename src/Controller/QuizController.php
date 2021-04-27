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
use Dompdf\Dompdf;
use Dompdf\Options;




class QuizController extends AbstractController
{
    /**
     * @Route("/quiz", name="quiz")
     * @param QuizRep $repo
     * @return Response
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
    public function AddQuiz(Request  $request , \Swift_Mailer $mailer1)
    {
        $Quiz = new Quiz();


        $form = $this->createForm(QuizType::class, $Quiz);
        
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->persist($Quiz);
            $em->flush();
            $message = (new \Swift_Message('Validation'))
            ->setFrom('directeurkidzy@gmail.com','Administration')
            ->setTo('ihebsahli959@gmail.com')
            ->setBody(' Bonjour,Il y a un nouveau Quiz, Vous devrez faire le quiz ,Bonne Chance et Bon Courage ');

        $mailer1->send($message);
        }

        return $this->render('quiz/add.html.twig', array(
            'form' => $form->createView(),
        ));
    }

    /**
     * @Route("/suppirimer/{id}", name="delete")
     */
    function deleteQuiz (QuizRep $repo,$id) {
        $user=$repo->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();
        return $this->redirectToRoute("quiz");
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
    /**
     * @Route("/ReQ", name=" ReQ")
     */
    function QuizRechq (QuizRep $repo , Request $request) {

        $data =$request->get('search');
        $lstQuiz=$repo->findBy(['title'=>$data]);

        return $this->render("quiz/indexEnf.html.twig",
            ['lstQuiz'=>$lstQuiz]);
    }


    
    /**
     * @Route("/searchQuizz ", name="searchQuizz")
     */
    public function searchQuizz(Request $request,NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Quiz::class);
        $requestString=$request->get('searchValue');
        $lstQuiz = $repository->findQuizByTitle($requestString);
        $jsonContent = $Normalizer->normalize($lstQuiz, 'json',['groups'=>'lstQuiz']);
        $retour=json_encode($jsonContent);
        return new Response($retour);
      
    }

    /**
     * @Route("/triQ ", name="triQ")
     */
    public function orderByNomSQL(QuizRep $repository){
        $lstQuiz=$repository->OrderBynom();
        return $this->render('quiz/index.html.twig', ['lstQuiz' => $lstQuiz]);
    }
    /**
     * @Route("/triQE ", name="triQE")
     */
    public function orderByNomESQL(QuizRep $repository){
        $lstQuiz=$repository->OrderBynom();
        return $this->render('quiz/indexEnf.html.twig', ['lstQuiz' => $lstQuiz]);

    }

    /**
     * @Route("/pdf",name="pdf",methods={"GET"})
     */
    public function pdf(QuizRep $repo):Response{
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);
        $lstQuiz=$repo->findAll();

        $html=$this->renderView('quiz/pdf.html.twig',[
            'lstQuiz'=>$lstQuiz
        ]);


        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');


        $dompdf->render();


        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);

    }
}

