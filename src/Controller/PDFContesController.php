<?php

namespace App\Controller;

use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PDFContesController extends AbstractController
{
    /**
     * @Route("/pdfC", name="pdfC")
     */
    public function generatePDF()
    {

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);


        $invContes = $this->getDoctrine()
            ->getRepository('App:Inventairecontes')
            ->findAll();
        $html = $this->render('pdf/pdfInvContes.html.twig', ['invContes' => $invContes]);


        $dompdf->loadHtml($html);


        $dompdf->setPaper('A4', 'portrait');


        $dompdf->render();


        $dompdf->stream("Inventaire_Contes.pdf", [
            "Attachment" => true
        ]);

    }
}
