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

    /**
     * @return int
     */
    public function getIdconte(): int
    {
        return $this->idconte;
    }

    /**
     * @param int $idconte
     */
    public function setIdconte(int $idconte): void
    {
        $this->idconte = $idconte;
    }

    /**
     * @return string
     */
    public function getTitre(): string
    {
        return $this->titre;
    }

    /**
     * @param string $titre
     */
    public function setTitre(string $titre): void
    {
        $this->titre = $titre;
    }

    /**
     * @return string
     */
    public function getAuteur(): string
    {
        return $this->auteur;
    }

    /**
     * @param string $auteur
     */
    public function setAuteur(string $auteur): void
    {
        $this->auteur = $auteur;
    }

    /**
     * @return string
     */
    public function getContes(): string
    {
        return $this->contes;
    }

    /**
     * @param string $contes
     */
    public function setContes(string $contes): void
    {
        $this->contes = $contes;
    }
    public function __toString()
    {
        return $this->titre . '(' . $this->auteur . ')';
    }

}
