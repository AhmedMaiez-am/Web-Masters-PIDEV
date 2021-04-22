<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inventairecours
 *
 * @ORM\Table(name="inventairecours")
 * @ORM\Entity
 */
class Inventairecours
{
    /**
     * @var int
     *
     * @ORM\Column(name="idCc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcc;

    /**
     * @var string
     *
     * @ORM\Column(name="nomC", type="string", length=50, nullable=false)
     */
    private $nomc;

    /**
     * @var string
     *
     * @ORM\Column(name="typeCc", type="string", length=50, nullable=false)
     */
    private $typecc;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionCc", type="string", length=500, nullable=false)
     */
    private $descriptioncc;

    public function getIdcc(): ?int
    {
        return $this->idcc;
    }

    public function getNomc(): ?string
    {
        return $this->nomc;
    }

    public function setNomc(string $nomc): self
    {
        $this->nomc = $nomc;

        return $this;
    }

    public function getTypecc(): ?string
    {
        return $this->typecc;
    }

    public function setTypecc(string $typecc): self
    {
        $this->typecc = $typecc;

        return $this;
    }

    public function getDescriptioncc(): ?string
    {
        return $this->descriptioncc;
    }

    public function setDescriptioncc(string $descriptioncc): self
    {
        $this->descriptioncc = $descriptioncc;

        return $this;
    }


}
