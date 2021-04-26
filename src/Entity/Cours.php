<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cours
 *
 * @ORM\Table(name="cours")
 * @ORM\Entity
 */
class Cours
{
    /**
     * @var int
     *
     * @ORM\Column(name="idC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idc;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=200, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=500, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="cours", type="text", length=65535, nullable=false)
     */
    private $cours;

    /**
     * @var string
     *
     * @ORM\Column(name="prix", type="string", length=50, nullable=false)
     */
    private $prix;

    /**
     * @return int
     */
    public function getIdc(): int
    {
        return $this->idc;
    }

    /**
     * @param int $idc
     */
    public function setIdc(int $idc): void
    {
        $this->idc = $idc;
    }

    /**
     * @return string
     */
    public function getNom(): string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getType(): string
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): void
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getDescription(): string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getCours(): string
    {
        return $this->cours;
    }

    /**
     * @param string $cours
     */
    public function setCours(string $cours): void
    {
        $this->cours = $cours;
    }

    /**
     * @return string
     */
    public function getPrix(): string
    {
        return $this->prix;
    }

    /**
     * @param string $prix
     */
    public function setPrix(string $prix): void
    {
        $this->prix = $prix;
    }


}
