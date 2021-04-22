<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Maitresse
 *
 * @ORM\Table(name="maitresse")
 * @ORM\Entity
 */
class Maitresse
{
    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=50, nullable=false, options={"default"="'En Cours'"})
     */
    private $etat = '\'En Cours\'';

    /**
     * @var int
     *
     * @ORM\Column(name="idM", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idm;

    /**
     * @var string
     *
     * @ORM\Column(name="cin", type="string", length=8, nullable=false)
     */
    private $cin;

    /**
     * @var string
     *
     * @ORM\Column(name="nomM", type="string", length=20, nullable=false)
     */
    private $nomm;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomM", type="string", length=20, nullable=false)
     */
    private $prenomm;

    /**
     * @var string
     *
     * @ORM\Column(name="emailMaitresse", type="string", length=250, nullable=false)
     */
    private $emailmaitresse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateDemande", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $datedemande = 'current_timestamp()';

    /**
     * @var string|null
     *
     * @ORM\Column(name="path", type="text", length=65535, nullable=true, options={"default"="NULL"})
     */
    private $path = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="cv", type="text", length=65535, nullable=true, options={"default"="NULL"})
     */
    private $cv = 'NULL';

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getIdm(): ?int
    {
        return $this->idm;
    }

    public function getCin(): ?string
    {
        return $this->cin;
    }

    public function setCin(string $cin): self
    {
        $this->cin = $cin;

        return $this;
    }

    public function getNomm(): ?string
    {
        return $this->nomm;
    }

    public function setNomm(string $nomm): self
    {
        $this->nomm = $nomm;

        return $this;
    }

    public function getPrenomm(): ?string
    {
        return $this->prenomm;
    }

    public function setPrenomm(string $prenomm): self
    {
        $this->prenomm = $prenomm;

        return $this;
    }

    public function getEmailmaitresse(): ?string
    {
        return $this->emailmaitresse;
    }

    public function setEmailmaitresse(string $emailmaitresse): self
    {
        $this->emailmaitresse = $emailmaitresse;

        return $this;
    }

    public function getDatedemande(): ?\DateTimeInterface
    {
        return $this->datedemande;
    }

    public function setDatedemande(\DateTimeInterface $datedemande): self
    {
        $this->datedemande = $datedemande;

        return $this;
    }

    public function getPath(): ?string
    {
        return $this->path;
    }

    public function setPath(?string $path): self
    {
        $this->path = $path;

        return $this;
    }

    public function getCv(): ?string
    {
        return $this->cv;
    }

    public function setCv(?string $cv): self
    {
        $this->cv = $cv;

        return $this;
    }


}
