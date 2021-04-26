<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inventairecours
 *
 * @ORM\Table(name="inventairecours")
 * @ORM\Entity(repositoryClass="App\Repository\InventaireCoursRepository")
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

    /**
     * @return int
     */
    public function getIdcc(): int
    {
        return $this->idcc;
    }

    /**
     * @param int $idcc
     */
    public function setIdcc(int $idcc): void
    {
        $this->idcc = $idcc;
    }

    /**
     * @return string
     */
    public function getNomc(): string
    {
        return $this->nomc;
    }

    /**
     * @param string $nomc
     */
    public function setNomc(string $nomc): void
    {
        $this->nomc = $nomc;
    }

    /**
     * @return string
     */
    public function getTypecc(): string
    {
        return $this->typecc;
    }

    /**
     * @param string $typecc
     */
    public function setTypecc(string $typecc): void
    {
        $this->typecc = $typecc;
    }

    /**
     * @return string
     */
    public function getDescriptioncc(): string
    {
        return $this->descriptioncc;
    }

    /**
     * @param string $descriptioncc
     */
    public function setDescriptioncc(string $descriptioncc): void
    {
        $this->descriptioncc = $descriptioncc;
    }

    public function show(): string
    {
        return 'Nom du cours : '. $this->nomc . ', type du cours : ' . $this->typecc . ', description du cours : ' . $this->descriptioncc;
    }


}
