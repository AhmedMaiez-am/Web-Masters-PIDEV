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
    public function getLoginm(): ?string
    {
        return $this->loginm;
    }

    /**
     * @param string $loginm
     */
    public function setLoginm(string $loginm): void
    {
        $this->loginm = $loginm;
    }

    /**
     * @return string
     */
    public function getPasswordm(): ?string
    {
        return $this->passwordm;
    }

    /**
     * @param string $passwordm
     */
    public function setPasswordm(string $passwordm)
    {
        $this->passwordm = $passwordm;
    }

    /**
     * @return string|null
     */
    public function getCodem(): ?string
    {
        return $this->codem;
    }


    public function setCodem($codem)
    {
        $this->codem = $codem;
    }

    /**
     * @return string|null
     */
    public function getImg(): ?string
    {
        return $this->img;
    }

    /**
     * @param string|null $img
     */
    public function setImg(?string $img): void
    {
        $this->img = $img;
    }


}
