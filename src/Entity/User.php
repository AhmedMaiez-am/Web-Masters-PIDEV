<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user")
 * @ORM\Entity
 */
class User
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=50, nullable=false)
     */
    private $password;

    /**
     * @var string|null
     *
     * @ORM\Column(name="code", type="string", length=50, nullable=true, options={"default"="NULL"})
     */
    private $code = 'NULL';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateCreation", type="datetime", nullable=true, options={"default"="NULL"})
     */
    private $datecreation = 'NULL';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateExp", type="datetime", nullable=true, options={"default"="NULL"})
     */
    private $dateexp = 'NULL';

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

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

    public function getCode(): ?string
    {
        return $this->code;
    }

    public function setCode(?string $code): self
    {
        $this->code = $code;

        return $this;
    }

    public function getDatecreation(): ?\DateTimeInterface
    {
        return $this->datecreation;
    }

    public function setDatecreation(?\DateTimeInterface $datecreation): self
    {
        $this->datecreation = $datecreation;

        return $this;
    }

    public function getDateexp(): ?\DateTimeInterface
    {
        return $this->dateexp;
    }

    public function setDateexp(?\DateTimeInterface $dateexp): self
    {
        $this->dateexp = $dateexp;

        return $this;
    }


}
