<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Auth
 *
 * @ORM\Table(name="auth")
 * @ORM\Entity
 */
class Auth
{
    /**
     * @var int
     *
     * @ORM\Column(name="idAuth", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idauth;

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=100, nullable=false)
     */
    private $emailp;

    /**
     * @var string
     *
     * @ORM\Column(name="passP", type="string", length=50, nullable=false)
     */
    private $passp;

    public function getIdauth(): ?int
    {
        return $this->idauth;
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

    public function getPassp(): ?string
    {
        return $this->passp;
    }

    public function setPassp(string $passp): self
    {
        $this->passp = $passp;

        return $this;
    }


}
