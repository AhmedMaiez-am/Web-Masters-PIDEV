<?php

namespace App\Controller;

use App\Entity\Inventairecours;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Color\Color;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelLow;
use Endroid\QrCode\QrCode;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCode\Builder\BuilderInterface;
use Endroid\QrCodeBundle\Response\QrCodeResponse;

class QRcodeCoursController extends AbstractController
{
    /**
     * @Route("/QRCours/{idcc}", name="QRCours")
     * @param Inventairecours $c
     */
    public function index(Inventairecours $c)
    {

        $b = new Inventairecours();
        $b->setNomc($c->getNomc());
        $b->setTypecc($c->getTypecc());
        $b->setDescriptioncc($c->getDescriptioncc());
        $result = Builder::create()
            ->writer(new PngWriter())
            ->writerOptions([])
            ->data($b->show())
            ->encoding(new Encoding('UTF-8'))
            ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
            ->size(300)
            ->margin(10)
            ->roundBlockSizeMode(new RoundBlockSizeModeMargin())
            ->build();

        // Directly output the QR code
        header('Content-Type: '.$result->getMimeType());
        echo $result->getString();

// Generate a data URI to include image data inline (i.e. inside an <img> tag)
        $dataUri = $result->getDataUri();

        return $this->render("QRCode/QRCours.html.twig", ['data'=>$dataUri]);

    }
}
