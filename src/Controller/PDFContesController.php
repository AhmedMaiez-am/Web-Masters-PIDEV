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
// Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $invContes = $this->getDoctrine()
            ->getRepository('App:Inventairecontes')
            ->findAll();
        $html = $this->render('pdf/pdfInvContes.html.twig', ['invContes' => $invContes]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Inventaire_Contes.pdf", [
            "Attachment" => true
        ]);

    }
}
