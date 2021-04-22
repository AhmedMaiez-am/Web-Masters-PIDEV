<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert ;

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
     * @Assert\Length(min=8,max = 8,minMessage="le numéro de la carte doit contenir au minimum {{ 8 }} caracteres",maxMessage="le numéro de la carte doit contenir au plus {{ 8 }} caracteres")
     */
    private $numcarte;

    /**
     * @var string
     *
     * @ORM\Column(name="passCarte", type="string", length=4, nullable=false)
     * @Assert\Length(min=4,max = 4,minMessage="le mot de passe de la carte doit contenir au minimum {{ 4}} caracteres",maxMessage="le mot de passe de la carte doit contenir au plus {{ 4 }} caracteres")
     */
    private $passcarte;

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

    /**
     * @return int
     */
    public function getIdp(): int
    {
        return $this->idp;
    }

    /**
     * @param int $idp
     */
    public function setIdp(int $idp): void
    {
        $this->idp = $idp;
    }

    /**
     * @return string
     */
    public function getEmailp(): string
    {
        return $this->emailp;
    }

    /**
     * @param string $emailp
     */
    public function setEmailp(string $emailp): void
    {
        $this->emailp = $emailp;
    }

    /**
     * @return string
     */
    public function getNomp(): string
    {
        return $this->nomp;
    }

    /**
     * @param string $nomp
     */
    public function setNomp(string $nomp): void
    {
        $this->nomp = $nomp;
    }

    /**
     * @return string
     */
    public function getPrenomp(): string
    {
        return $this->prenomp;
    }

    /**
     * @param string $prenomp
     */
    public function setPrenomp(string $prenomp): void
    {
        $this->prenomp = $prenomp;
    }

    /**
     * @return string
     */
    public function getTelp(): string
    {
        return $this->telp;
    }

    /**
     * @param string $telp
     */
    public function setTelp(string $telp): void
    {
        $this->telp = $telp;
    }

    /**
     * @return string
     */
    public function getPasswordp(): string
    {
        return $this->passwordp;
    }

    /**
     * @param string $passwordp
     */
    public function setPasswordp(string $passwordp): void
    {
        $this->passwordp = $passwordp;
    }

    /**
     * @return string
     */
    public function getNumcarte(): string
    {
        return $this->numcarte;
    }

    /**
     * @param string $numcarte
     * @Assert\Length(min=8,max = 8,minMessage="le nom de recompense doit contenir au minimum {{ 8 }} caracteres",maxMessage="le numèro de la carte doit contenir au plus {{ 8 }} caracteres")
     */
    public function setNumcarte(string $numcarte): void
    {
        $this->numcarte = $numcarte;
    }

    /**
     * @return string|null
     * @Assert\Length(min=4,max = 4,minMessage="le nom de recompense doit contenir au minimum {{ 4}} caracteres",maxMessage="le mot de passe de la carte doit contenir au plus {{ 4 }} caracteres")
     */
    public function getPasscarte(): ?string
    {
        return $this->passcarte;
    }

    /**
     * @param string|null $passcarte
     */
    public function setPasscarte(?string $passcarte): void
    {
        $this->passcarte = $passcarte;
    }

    /**
     * @return string|null
     */
    public function getPortefeuille(): ?string
    {
        return $this->portefeuille;
    }

    /**
     * @param string|null $portefeuille
     */
    public function setPortefeuille(?string $portefeuille): void
    {
        $this->portefeuille = $portefeuille;
    }

    /**
     * @return string|null
     */
    public function getCodep(): ?string
    {
        return $this->codep;
    }

    /**
     * @param string|null $codep
     */
    public function setCodep(?string $codep): void
    {
        $this->codep = $codep;
    }


}
