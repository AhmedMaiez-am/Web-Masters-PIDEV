<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Donation
 *
 * @ORM\Table(name="donation")
 * @ORM\Entity
 */
class Donation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="NumC", type="string", length=10, nullable=false)
     */
    private $numc;

    /**
     * @var string
     *
     * @ORM\Column(name="Montant", type="string", length=20, nullable=false)
     */
    private $montant;


}
