<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Float_;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * Cours
 *
 * @ORM\Table(name="cours")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\CoursRepository")
 */
class Cours
{
    /**
     * @var int
     *
     * @ORM\Column(name="idC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("c:read")
     */
    private $idc;

    /**
     * @var string
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     * @Assert\Length(min=3)
     * @Assert\NotBlank
     * @Groups("c:read")
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=200, nullable=false)
     * @Groups("c:read")
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=500, nullable=false)
     * @Assert\NotBlank
     * @Groups("c:read")
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(type="string")
    @Assert\File(
     *     maxSize = "1024k",
     *     mimeTypes = {"application/pdf", "application/x-pdf"},
     *     mimeTypesMessage = "Please upload a valid PDF"
     * )
     */
    private $cours;

    /**
     * @var string
     * @ORM\Column(name="prix", type="string", length=50, nullable=false)
     * @Groups("c:read")
     */
    private $prix;

    /**
     * @var int
     * @ORM\Column(name="rate", type="integer", nullable=true)
     */
    private $rate;


    public function getIdc(): ?int
    {
        return $this->idc;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getCours()
    {
        return $this->cours;
    }

    public function setCours(string $cours)
    {
        $this->cours = $cours;

        return $this;
    }

    public function getPrix(): ?string
    {
        return $this->prix;
    }

    public function setPrix(string $prix): self
    {
        $this->prix = $prix;

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
