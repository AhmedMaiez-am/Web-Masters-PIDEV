<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Contes
 *
 * @ORM\Table(name="contes")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ContesRepository")
 */
class Contes
{
    /**
     * @var int
     *
     * @ORM\Column(name="idConte", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("c:read")
     */
    private $idconte;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=50, nullable=false)
     * @Assert\Length(min=3)
     * @Assert\NotBlank
     * @Groups("c:read")
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="auteur", type="string", length=50, nullable=false)
     * @Assert\Length(min=3)
     * @Assert\NotBlank
     * @Groups("c:read")
     */
    private $auteur;

    /**
     * @var string
     *
     * @ORM\Column(name="contes", type="text", length=65535, nullable=false)
     * @Assert\NotBlank
     * @Assert\File(
     *     maxSize = "1024k",
     *     mimeTypes = {"application/pdf", "application/x-pdf"},
     *     mimeTypesMessage = "Please upload a valid PDF"
     * )
     */
    private $contes;

    /**
     * @var int
     * @ORM\Column(name="rate", type="integer", nullable=true)
     */
    private $rate;

    public function getIdconte(): ?int
    {
        return $this->idconte;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getAuteur(): ?string
    {
        return $this->auteur;
    }

    public function setAuteur(string $auteur): self
    {
        $this->auteur = $auteur;

        return $this;
    }

    public function getContes(): ?string
    {
        return $this->contes;
    }

    public function setContes(string $contes): self
    {
        $this->contes = $contes;

        return $this;
    }
    /**
     * @return int
     */
    public function getRate()
    {
        return $this->rate;
    }

    /**
     * @param int $rate
     */
    public function setRate(int $rate): void
    {
        $this->rate = $rate;
    }


}
