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
// Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $lstCoursInv = $this->getDoctrine()
            ->getRepository('App:Inventairecours')
            ->findAll();
        $html = $this->render('pdf/pdfInvCours.html.twig', ['lstCoursInv' => $lstCoursInv]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Inventaire_Cours.pdf", [
            "Attachment" => true
        ]);

    }
}
