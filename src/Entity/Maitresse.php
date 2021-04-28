<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use  Symfony\Component\Validator\Constraints as Assert;

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
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @Assert\Length(min="8", max="8",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     * @ORM\Column(name="cin", type="string", length=8, nullable=false)
     */
    private $cin;

    /**
     * @var string
     * @ORM\Column(name="nomM", type="string", length=20, nullable=false)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     */
    private $nomm;

    /**
     * @var string
     * @ORM\Column(name="prenomM", type="string", length=20, nullable=false)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     */
    private $prenomm;

    /**
     * @var string
     *
     * @ORM\Column(name="emailMaitresse", type="string", length=250, nullable=false)
     * @Assert\Email(message="Email {{ value }} non valide")
     */
    private $emailmaitresse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateDemande", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $datedemande ;

    /**
     *
     * @Assert\File(mimeTypes={"image/png"})
     * @ORM\Column(name="path", type="string")
     */
    private $path ;

    /**
     *
     *
     * @ORM\Column(name="cv", type="string")
     */
    private $cv ;

    /**
     * @return string
     */
    public function getEtat(): ?string
    {
        return $this->etat;
    }

    /**
     * @param string $etat
     */
    public function setEtat(string $etat): void
    {
        $this->etat = $etat;
    }

    /**
     * @return int
     */
    public function getIdm(): ?int
    {
        return $this->idm;
    }

    /**
     * @param int $idm
     */
    public function setIdm(int $idm): void
    {
        $this->idm = $idm;
    }

    /**
     * @return string
     */
    public function getCin(): ?string
    {
        return $this->cin;
    }

    /**
     * @param string $cin
     */
    public function setCin(string $cin): void
    {
        $this->cin = $cin;
    }

    /**
     * @return string
     */
    public function getNomm(): ?string
    {
        return $this->nomm;
    }

    /**
     * @param string $nomm
     */
    public function setNomm(string $nomm): void
    {
        $this->nomm = $nomm;
    }

    /**
     * @return string
     */
    public function getPrenomm(): ?string
    {
        return $this->prenomm;
    }

    /**
     * @param string $prenomm
     */
    public function setPrenomm(string $prenomm): void
    {
        $this->prenomm = $prenomm;
    }

    /**
     * @return string
     */
    public function getEmailmaitresse(): ?string
    {
        return $this->emailmaitresse;
    }

    /**
     * @param string $emailmaitresse
     */
    public function setEmailmaitresse(string $emailmaitresse): void
    {
        $this->emailmaitresse = $emailmaitresse;
    }

    /**
     * @return \DateTime
     */
    public function getDatedemande()
    {
        return $this->datedemande;
    }

    /**
     * @param \DateTime $datedemande
     */
    public function setDatedemande($datedemande): void
    {
        $this->datedemande = $datedemande;
    }


    public function getPath()
    {
        return $this->path;
    }


    public function setPath( $path)
    {
        $this->path = $path;
    }


    public function getCv()
    {
        return $this->cv;
    }


    public function setCv($cv)
    {
        $this->cv = $cv;
    }


}
