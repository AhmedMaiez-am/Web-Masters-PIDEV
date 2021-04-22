<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Contes
 *
 * @ORM\Table(name="contes")
 * @ORM\Entity
 */
class Contes
{
    /**
     * @var int
     *
     * @ORM\Column(name="idConte", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idconte;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=50, nullable=false)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="auteur", type="string", length=50, nullable=false)
     */
    private $auteur;

    /**
     * @var string
     *
     * @ORM\Column(name="contes", type="text", length=65535, nullable=false)
     */
    private $contes;


}
