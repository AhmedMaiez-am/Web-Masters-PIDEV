<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use App\Respository\QuizRep;
use  Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Quiz
 *
 * @ORM\Table(name="quiz")
 * @ORM\Entity(repositoryClass="App\Repository\QuizRep")
 */
class Quiz
{
    /**
     * @var int
     *
     * @ORM\Column(name="quizId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups ("post:read")
     */
    private $quizid;

    /**
     * @var string
     *
     * @ORM\Column(name="Title", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Assert\Length(min="3", max="40",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     *  @Groups ("post:read")
     */
    private $title;

    /**
     * @var int
     *
     * @ORM\Column(name="isamericain", type="integer", nullable=true)
     *  @Groups ("post:read")
     */
    private $isamericain;

//    /**
//     * @ORM\OneToMany(targetEntity=Quizresult::class, mappedBy="quizId", orphanRemoval=true)
//     */
//    private $quizresults;
//
//    public function __construct()
//    {
//        $this->quizresults = new ArrayCollection();
//    }

    /**
     * @return int
     */
    public function getQuizid(): ?int
    {
        return $this->quizid;
    }

    /**
     * @param int $quizid
     */
    public function setQuizid(int $quizid): void
    {
        $this->quizid = $quizid;
    }

    /**
     * @return string
     */
    public function getTitle(): ?string
    {
        return $this->title;
    }

    /**
     * @param string $title
     */
    public function setTitle(string $title): void
    {
        $this->title = $title;
    }

    /**
     * @return int
     */
    public function getIsamericain(): ?int
    {
        return $this->isamericain;
    }

    /**
     * @param int $isamericain
     */
    public function setIsamericain(int $isamericain): void
    {
        $this->isamericain = $isamericain;
    }



//    /**
//     * @return Collection|Quizresult[]
//     */
//    public function getQuizresults(): Collection
//    {
//        return $this->quizresults;
//    }
//
//    public function addQuizresult(Quizresult $quizresult): self
//    {
//        if (!$this->quizresults->contains($quizresult)) {
//            $this->quizresults[] = $quizresult;
//            $quizresult->setQuizId($this);
//        }
//
//        return $this;
//    }
//
//    public function removeQuizresult(Quizresult $quizresult): self
//    {
//        if ($this->quizresults->removeElement($quizresult)) {
//            // set the owning side to null (unless already changed)
//            if ($quizresult->getQuizId() === $this) {
//                $quizresult->setQuizId(null);
//            }
//        }
//
//        return $this;
//    }


}
