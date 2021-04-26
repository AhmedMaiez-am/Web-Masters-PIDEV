<?php

namespace App\Controller;

use App\Entity\Inventairecours;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class PDFCoursController extends AbstractController
{
    /**
     * @Route("/pdf", name="pdf")
     */
    public function generatePDF()
    {

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);


        $lstCoursInv = $this->getDoctrine()
            ->getRepository('App:Inventairecours')
            ->findAll();
        $html = $this->render('pdf/pdfInvCours.html.twig', ['lstCoursInv' => $lstCoursInv]);


        $dompdf->loadHtml($html);


        $dompdf->setPaper('A4', 'portrait');


        $dompdf->render();


        $dompdf->stream("Inventaire_Cours.pdf", [
            "Attachment" => true
        ]);

    }
}
