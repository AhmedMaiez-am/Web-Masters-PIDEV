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

    public function getIdrecup(): ?int
    {
        return $this->idrecup;
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

    public function getNomenfant(): ?string
    {
        return $this->nomenfant;
    }

    public function setNomenfant(string $nomenfant): self
    {
        $this->nomenfant = $nomenfant;

        return $this;
    }

    public function getEmailp(): ?string
    {
        return $this->emailp;
    }

    public function setEmailp(string $emailp): self
    {
        $this->emailp = $emailp;

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
