<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inventairecontes
 *
 * @ORM\Table(name="inventairecontes")
 * @ORM\Entity
 */
class Inventairecontes
{
    /**
     * @var int
     *
     * @ORM\Column(name="idContesC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcontesc;

    /**
     * @var string
     *
     * @ORM\Column(name="titreC", type="string", length=50, nullable=false)
     */
    private $titrec;

    /**
     * @var string
     *
     * @ORM\Column(name="auteurC", type="string", length=50, nullable=false)
     */
    private $auteurc;


}
