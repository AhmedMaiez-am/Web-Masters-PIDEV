<?php

namespace App\Controller;

use App\Entity\Inventairecontes;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class QRcodeContesController extends AbstractController
{
    /**
     * @Route("/QRContes/{idcontesc}", name="QRContes")
     * @param Inventairecontes $c
     */
    public function index(Inventairecontes $c)
    {
        $b = new Inventairecontes();
        $b->setAuteurc($c->getAuteurc());
        $b->setTitrec($c->getTitrec());
        $result = Builder::create()
            ->writer(new PngWriter())
            ->writerOptions([])
            ->data($b->show())
            ->encoding(new Encoding('UTF-8'))
            ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
            ->size(300)
            ->margin(10)
            ->roundBlockSizeMode(new RoundBlockSizeModeMargin())
            ->labelText($b->getTitrec())
            ->build();

        // Directly output the QR code
        header('Content-Type: '.$result->getMimeType());
        echo $result->getString();

// Generate a data URI to include image data inline (i.e. inside an <img> tag)
        $dataUri = $result->getDataUri();

        return $this->render("QRCode/QRContes.html.twig", ['data'=>$dataUri]);
    }
}
