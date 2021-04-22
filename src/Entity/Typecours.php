<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Typecours
 *
 * @ORM\Table(name="typecours")
 * @ORM\Entity
 */
class Typecours
{
    /**
     * @var int
     *
     * @ORM\Column(name="idT", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idt;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     */
    private $nom;

    public function getIdt(): ?int
    {
        return $this->idt;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }


}
