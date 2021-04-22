<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Validation
 *
 * @ORM\Table(name="validation")
 * @ORM\Entity
 */
class Validation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idm", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idm;

    /**
     * @var string
     *
     * @ORM\Column(name="loginM", type="string", length=50, nullable=false)
     */
    private $loginm;

    /**
     * @var string
     *
     * @ORM\Column(name="passwordM", type="string", length=50, nullable=false)
     */
    private $passwordm;

    /**
     * @var string|null
     *
     * @ORM\Column(name="codem", type="string", length=50, nullable=true, options={"default"="NULL"})
     */
    private $codem = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="img", type="text", length=65535, nullable=true, options={"default"="NULL"})
     */
    private $img = 'NULL';

    public function getIdm(): ?int
    {
        return $this->idm;
    }

    public function getLoginm(): ?string
    {
        return $this->loginm;
    }

    public function setLoginm(string $loginm): self
    {
        $this->loginm = $loginm;

        return $this;
    }

    public function getPasswordm(): ?string
    {
        return $this->passwordm;
    }

    public function setPasswordm(string $passwordm): self
    {
        $this->passwordm = $passwordm;

        return $this;
    }

    public function getCodem(): ?string
    {
        return $this->codem;
    }

    public function setCodem(?string $codem): self
    {
        $this->codem = $codem;

        return $this;
    }

    public function getImg(): ?string
    {
        return $this->img;
    }

    public function setImg(?string $img): self
    {
        $this->img = $img;

        return $this;
    }


}
