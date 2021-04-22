<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Directeur
 *
 * @ORM\Table(name="directeur")
 * @ORM\Entity
 */
class Directeur
{
    /**
     * @var string
     *
     * @ORM\Column(name="login", type="string", length=30, nullable=false)
     */
    private $login;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=30, nullable=false)
     */
    private $password;

    /**
     * @var int
     *
     * @ORM\Column(name="IdD", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idd;

    public function getLogin(): ?string
    {
        return $this->login;
    }

    public function setLogin(string $login): self
    {
        $this->login = $login;

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

    public function getIdd(): ?int
    {
        return $this->idd;
    }


}
