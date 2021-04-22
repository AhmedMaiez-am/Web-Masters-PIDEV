<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Enfant
 *
 * @ORM\Table(name="enfant", indexes={@ORM\Index(name="fk_key", columns={"idParent"})})
 * @ORM\Entity
 */
class Enfant
{
    /**
     * @var int
     *
     * @ORM\Column(name="idE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $ide;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEnfant", type="string", length=50, nullable=false)
     */
    private $nomenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomEnfant", type="string", length=50, nullable=false)
     */
    private $prenomenfant;

    /**
     * @var int|null
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $nbrPoint = NULL;

    /**
     * @var int
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=50, nullable=false)
     */
    private $password;

    /**
     * @var string|null
     *
     * @ORM\Column(name="image", type="text", length=65535, nullable=true, options={"default"="NULL"})
     */
    private $image = 'NULL';

    /**
     * @var \Parents
     *
     * @ORM\ManyToOne(targetEntity="Parents")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idParent", referencedColumnName="idP")
     * })
     */
    private $idparent;

    public function getIde(): ?int
    {
        return $this->ide;
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

    public function getPrenomenfant(): ?string
    {
        return $this->prenomenfant;
    }

    public function setPrenomenfant(string $prenomenfant): self
    {
        $this->prenomenfant = $prenomenfant;

        return $this;
    }

    public function getNbrPoint(): ?int
    {
        return $this->nbrPoint;
    }

    public function setNbrPoint(?int $nbrPoint): self
    {
        $this->nbrPoint = $nbrPoint;

        return $this;
    }

    public function getAge(): ?int
    {
        return $this->age;
    }

    public function setAge(int $age): self
    {
        $this->age = $age;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getIdparent(): ?Parents
    {
        return $this->idparent;
    }

    public function setIdparent(?Parents $idparent): self
    {
        $this->idparent = $idparent;

        return $this;
    }


}
