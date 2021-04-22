<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Recuperation
 *
 * @ORM\Table(name="recuperation")
 * @ORM\Entity
 */
class Recuperation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRecup", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrecup;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRec", type="string", length=50, nullable=false)
     */
    private $nomrec;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEnfant", type="string", length=50, nullable=false)
     */
    private $nomenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=500, nullable=false)
     */
    private $emailp;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=false)
     */
    private $nbrPoint;


}
