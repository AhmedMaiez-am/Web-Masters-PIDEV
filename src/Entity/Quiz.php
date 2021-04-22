<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Respository\QuizRep;
use  Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\NotBlank;
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
     */
    private $quizid;

    /**
     * @var string
     *
     * @ORM\Column(name="Title", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Assert\Length(min="3", max="40",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     */
    private $title;

    /**
     * @var string
     *
     * @ORM\Column(name="isamericain", type="string", length=255, nullable=false)
     */
    private $isamericain;

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
     * @return string
     */
    public function getIsamericain(): ?string
    {
        return $this->isamericain;
    }

    /**
     * @param string $isamericain
     */
    public function setIsamericain(string $isamericain): void
    {
        $this->isamericain = $isamericain;
    }


}
