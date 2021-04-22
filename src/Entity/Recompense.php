<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Recompense
 *
 * @ORM\Table(name="recompense")
 * @ORM\Entity
 */
class Recompense
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRec", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrec;

    /**
     * @var string
     *
     * @ORM\Column(name="nomRec", type="string", length=30, nullable=false)
     */
    private $nomrec;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=false)
     */
    private $nbrPoint;


}
