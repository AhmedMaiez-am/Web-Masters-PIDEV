<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Recompense
 *
 * @ORM\Table(name="recompense")
 * @ORM\Entity
 */
class Recompense
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRec", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idrec;

    /**
     * @var string
     * @Assert\NotBlank(message="nom recompense is required")
     * @Assert\Length(
     * min=2,
     * max = 10,
     * minMessage="le nom de recompense doit contenir au minimum {{ 2 }} caracteres",
     * maxMessage="le nom de recompense doit contenir au plus {{ 10 }} caracteres"
     * )
     * @ORM\Column(name="nomRec", type="string", length=255, nullable=false)
     */
    private $nomrec="";

    /**
     * @var int
     *@Assert\NotBlank(message="nombre de point is required")
     * @Assert\Positive
     *@Assert\NotEqualTo(
     * value=0,
     * message= "le nombre de recompense ne doit pas etre égal à 0"
     * )
     * @ORM\Column(name="nbr_point", type="integer", nullable=false)
     *
     */
    private $nbrPoint=0;

    /**
     * @return int
     */
    public function getIdrec(): int
    {
        return $this->idrec;
    }

    /**
     * @param int $idrec
     */
    public function setIdrec(int $idrec): void
    {
        $this->idrec = $idrec;
    }

    /**
     * @return string
     */
    public function getNomrec(): string
    {
        return $this->nomrec;
    }

    /**
     * @param string $nomrec
     */
    public function setNomrec(string $nomrec): void
    {
        $this->nomrec = $nomrec;
    }

    /**
     * @return int
     */
    public function getNbrPoint(): int
    {
        return $this->nbrPoint;
    }

    /**
     * @param int $nbrPoint
     */
    public function setNbrPoint(int $nbrPoint): void
    {
        $this->nbrPoint = $nbrPoint;
    }


}
