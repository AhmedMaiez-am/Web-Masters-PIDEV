<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Enfant
 *
 * @ORM\Table(name="enfant")
 * @ORM\Entity
 */
class Enfant
{
    /**
     * @var int
     *
     * @ORM\Column(name="idEnfant", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEnfant", type="string", length=255, nullable=false)
     */
    private $nomenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomEnf", type="string", length=255, nullable=false)
     */
    private $prenomenf;

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=false)
     */
    private $nbrPoint;

    /**
     * @var int
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var int
     *
     * @ORM\Column(name="idparent", type="integer", nullable=false)
     */
    private $idparent;

    /**
     * @return int
     */
    public function getIdenfant(): int
    {
        return $this->idenfant;
    }

    /**
     * @param int $idenfant
     */
    public function setIdenfant(int $idenfant): void
    {
        $this->idenfant = $idenfant;
    }

    /**
     * @return string
     */
    public function getNomenfant(): string
    {
        return $this->nomenfant;
    }

    /**
     * @param string $nomenfant
     */
    public function setNomenfant(string $nomenfant): void
    {
        $this->nomenfant = $nomenfant;
    }

    /**
     * @return string
     */
    public function getPrenomenf(): string
    {
        return $this->prenomenf;
    }

    /**
     * @param string $prenomenf
     */
    public function setPrenomenf(string $prenomenf): void
    {
        $this->prenomenf = $prenomenf;
    }

    /**
     * @return int
     */
    public function getNbrPoint(): int
    {
        return $this->nbrPoint;
    }

    /**
     * @param int $nbrPoint
     */
    public function setNbrPoint(int $nbrPoint): void
    {
        $this->nbrPoint = $nbrPoint;
    }

    /**
     * @return int
     */
    public function getIdparent(): int
    {
        return $this->idparent;
    }

    /**
     * @param int $idparent
     */
    public function setIdparent(int $idparent): void
    {
        $this->idparent = $idparent;
    }


}
