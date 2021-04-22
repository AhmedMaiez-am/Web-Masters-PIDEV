<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Parents
 *
 * @ORM\Table(name="parents")
 * @ORM\Entity
 */
class Parents
{
    /**
     * @var int
     *
     * @ORM\Column(name="idP", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idp;

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=100, nullable=false)
     */
    private $emailp;

    /**
     * @var string
     *
     * @ORM\Column(name="nomP", type="string", length=100, nullable=false)
     */
    private $nomp;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomP", type="string", length=100, nullable=false)
     */
    private $prenomp;

    /**
     * @var string
     *
     * @ORM\Column(name="telP", type="string", length=20, nullable=false)
     */
    private $telp;

    /**
     * @var string
     *
     * @ORM\Column(name="passwordP", type="string", length=50, nullable=false)
     */
    private $passwordp;

    /**
     * @var string
     *
     * @ORM\Column(name="NumCarte", type="string", length=20, nullable=false)
     */
    private $numcarte;

    /**
     * @var string|null
     *
     * @ORM\Column(name="passCarte", type="string", length=4, nullable=true, options={"default"="NULL"})
     */
    private $passcarte = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="portefeuille", type="string", length=8, nullable=true, options={"default"="NULL"})
     */
    private $portefeuille = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="codep", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $codep = 'NULL';

    public function getIdp(): ?int
    {
        return $this->idp;
    }

    public function getEmailp(): ?string
    {
        return $this->emailp;
    }

    public function setEmailp(string $emailp): self
    {
        $this->emailp = $emailp;

        return $this;
    }

    public function getNomp(): ?string
    {
        return $this->nomp;
    }

    public function setNomp(string $nomp): self
    {
        $this->nomp = $nomp;

        return $this;
    }

    public function getPrenomp(): ?string
    {
        return $this->prenomp;
    }

    public function setPrenomp(string $prenomp): self
    {
        $this->prenomp = $prenomp;

        return $this;
    }

    public function getTelp(): ?string
    {
        return $this->telp;
    }

    public function setTelp(string $telp): self
    {
        $this->telp = $telp;

        return $this;
    }

    public function getPasswordp(): ?string
    {
        return $this->passwordp;
    }

    public function setPasswordp(string $passwordp): self
    {
        $this->passwordp = $passwordp;

        return $this;
    }

    public function getNumcarte(): ?string
    {
        return $this->numcarte;
    }

    public function setNumcarte(string $numcarte): self
    {
        $this->numcarte = $numcarte;

        return $this;
    }

    public function getPasscarte(): ?string
    {
        return $this->passcarte;
    }

    public function setPasscarte(?string $passcarte): self
    {
        $this->passcarte = $passcarte;

        return $this;
    }

    public function getPortefeuille(): ?string
    {
        return $this->portefeuille;
    }

    public function setPortefeuille(?string $portefeuille): self
    {
        $this->portefeuille = $portefeuille;

        return $this;
    }

    public function getCodep(): ?string
    {
        return $this->codep;
    }

    public function setCodep(?string $codep): self
    {
        $this->codep = $codep;

        return $this;
    }


}
