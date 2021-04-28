<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use  Symfony\Component\Validator\Constraints as Assert;
use Vangrg\ProfanityBundle\Validator\Constraints as ProfanityAssert;

/**
 * Enfant
 *
 * @ORM\Table(name="enfant", indexes={@ORM\Index(name="fk_key", columns={"idParent"})})
 * @ORM\Entity
 */
class Enfant
{
    /**
     * @var int
     *
     * @ORM\Column(name="idE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $ide;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEnfant", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @ProfanityAssert\ProfanityCheck(message="You cannot use this type of words")
     */
    private $nomenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomEnfant", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="il faut remplir ce champ")
     * @ProfanityAssert\ProfanityCheck(message="You cannot use this type of words")
     */
    private $prenomenfant;

    /**
     * @var int|null
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $nbrPoint = NULL;

    /**
     * @var int
     *
     * @ORM\Column(name="age", type="integer", nullable=false)
     * @Assert\Length(min="2", max="2",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     */
    private $age;

    /**
     * @var int|null
     *
     * @ORM\Column(name="idParent", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $idparent = NULL;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=50, nullable=false)
     * @Assert\Length(min="6", max="6",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     */
    private $password;

    /**
     *  @Assert\File(mimeTypes={"image/png"})
     *
     * @ORM\Column(name="image", type="string")
     */
    private $image ;

//    /**
//     * @var string|null
//     *
//     * @ORM\Column(name="block", type="string", length=255, nullable=true, options={"default"="NULL"})
//     */
//    private $block = 'NULL';

    /**
     * @return int
     */
    public function getIde(): int
    {
        return $this->ide;
    }

    /**
     * @param int $ide
     */
    public function setIde(int $ide): void
    {
        $this->ide = $ide;
    }

    /**
     * @return string
     */
    public function getNomenfant(): ?string
    {
        return $this->nomenfant;
    }

    /**
     * @param string $nomenfant
     */
    public function setNomenfant(string $nomenfant): void
    {
        $this->nomenfant = $nomenfant;
    }

    /**
     * @return string
     */
    public function getPrenomenfant(): ?string
    {
        return $this->prenomenfant;
    }

    /**
     * @param string $prenomenfant
     */
    public function setPrenomenfant(string $prenomenfant): void
    {
        $this->prenomenfant = $prenomenfant;
    }

    /**
     * @return int|null
     */
    public function getNbrPoint(): ?int
    {
        return $this->nbrPoint;
    }

    /**
     * @param int|null $nbrPoint
     */
    public function setNbrPoint(?int $nbrPoint): void
    {
        $this->nbrPoint = $nbrPoint;
    }

    /**
     * @return int
     */
    public function getAge(): ?int
    {
        return $this->age;
    }

    /**
     * @param int $age
     */
    public function setAge(int $age): void
    {
        $this->age = $age;
    }

    /**
     * @return int|null
     */
    public function getIdparent(): ?int
    {
        return $this->idparent;
    }

    /**
     * @param int|null $idparent
     */
    public function setIdparent(?int $idparent): void
    {
        $this->idparent = $idparent;
    }

    /**
     * @return string
     */
    public function getPassword(): ?string
    {
        return $this->password;
    }

    /**
     * @param string $password
     */
    public function setPassword(string $password): void
    {
        $this->password = $password;
    }


    public function getImage()
    {
        return $this->image;
    }


    public function setImage($image)
    {
        $this->image = $image;
    }

//    /**
//     * @return string|null
//     */
//    public function getBlock(): ?string
//    {
//        return $this->block;
//    }
//
//    /**
//     * @param string|null $block
//     */
//    public function setBlock(?string $block): void
//    {
//        $this->block = $block;
//    }


}
