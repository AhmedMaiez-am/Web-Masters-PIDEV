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

    public function getIdrec(): ?int
    {
        return $this->idrec;
    }

    public function getNomrec(): ?string
    {
        return $this->nomrec;
    }

    public function setNomrec(string $nomrec): self
    {
        $this->nomrec = $nomrec;

        return $this;
    }

    public function getNbrPoint(): ?int
    {
        return $this->nbrPoint;
    }

    public function setNbrPoint(int $nbrPoint): self
    {
        $this->nbrPoint = $nbrPoint;

        return $this;
    }


}
