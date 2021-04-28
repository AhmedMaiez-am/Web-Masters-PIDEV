<?php

namespace App\Entity;

use App\Repository\ConseilRepository;
use  Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ConseilRepository::class)
 */
class Conseil
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $idc;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $nomc;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $prenomc;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $conseil;





    public function getIdc(): ?int
    {
        return $this->idc;
    }

    public function getNom(): ?string
    {
        return $this->nomc;
    }

    public function setNom(string $nom): self
    {
        $this->nomc = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenomc;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenomc = $prenom;

        return $this;
    }

    public function getConseil(): ?string
    {
        return $this->conseil;
    }

    public function setConseil(string $conseil): self
    {
        $this->conseil = $conseil;

        return $this;
    }



}
