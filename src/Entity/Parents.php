<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Parent
 *
 * @ORM\Table(name="parent")
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
     * @ORM\Column(name="nomP", type="string", length=255, nullable=false)
     */
    private $nomp;

    /**
     * @var string
     *
     * @ORM\Column(name="telP", type="string", length=255, nullable=false)
     */
    private $telp;

    /**
     * @var string
     *
     * @ORM\Column(name="loginP", type="string", length=255, nullable=false)
     */
    private $loginp;

    /**
     * @var string
     *
     * @ORM\Column(name="passwordP", type="string", length=255, nullable=false)
     */
    private $passwordp;

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=255, nullable=false)
     */
    private $emailp;

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


}
