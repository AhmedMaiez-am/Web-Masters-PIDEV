<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Recuperation
 *@ORM\Entity(repositoryClass="App\Repository\RecuperationRepository")
 * @ORM\Table(name="recuperation")
 * @ORM\Entity
 */
class Recuperation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRecup", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("Recuperation:read")
     */
    private $idrecup;

    /**
     * @var string|null
     * @Assert\NotBlank(message="nom recompense is required")
     * @Assert\Length(
     * min=2,
     * max = 10,
     * minMessage="le nom de recompense doit contenir au minimum {{ 2 }} caracteres",
     * maxMessage="le nom de recompense doit contenir au plus {{ 10 }} caracteres"
     * )
     * @ORM\Column(name="nomRec", type="string", length=255, nullable=false)
     * @Groups("Recuperation:read")
     */
    private $nomrec="";

    /**
     * @var string
     *@Assert\NotBlank(message="nom enfant is required")
     *@Assert\Length(
     * min=2,
     * max = 10,
     * minMessage="le nom de l'enfant doit contenir au minimum {{ 2 }} caracteres",
     * maxMessage="le nom de l'enfant doit contenir au plus {{ 10 }} caracteres"
     * )
     * @ORM\Column(name="nomEnf", type="string", length=255, nullable=false)
     * @Groups("Recuperation:read")
     */
    private $nomenf="";

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=255, nullable=false)
     *@Assert\Email(
     *message = "The email '{{ value }}' is not a valid email."
     *)
     * @Groups("Recuperation:read")
     */
    private $emailp="";

    /**
     * @var int
     *
     * @ORM\Column(name="nbr_point", type="integer", nullable=false)
     *@Assert\NotBlank(message="nombre de point is required")
     * @Assert\Positive
     *@Assert\NotEqualTo(
     * value=0,
     * message= "le nombre de recompense ne doit pas etre égal à 0"
     * )
     * @Groups("Recuperation:read")
     */
    private $nbrPoint=0;

    /**
     * @return int
     */
    public function getIdrecup(): int
    {
        return $this->idrecup;
    }

    /**
     * @param int $idrecup
     */
    public function setIdrecup(int $idrecup): void
    {
        $this->idrecup = $idrecup;
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
     * @return string
     */
    public function getNomenf(): string
    {
        return $this->nomenf;
    }

    /**
     * @param string $nomenf
     */
    public function setNomenf(string $nomenf): void
    {
        $this->nomenf = $nomenf;
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
