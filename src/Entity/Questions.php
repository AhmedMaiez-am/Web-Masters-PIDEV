<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use  Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\NotBlank;
/**
 * Questions
 *
 * @ORM\Table(name="questions", indexes={@ORM\Index(name="quiz", columns={"quiz"})})
 * @ORM\Entity(repositoryClass="App\Repository\QuestionRep")
 */
class Questions
{
    /**
     * @var int
     *
     * @ORM\Column(name="questionID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $questionid;

    /**
     * @var string|null
     *
     * @ORM\Column(name="question", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     * @Assert\Length(min="3", max="255",minMessage="Doit contenir {{ limit }} caractères",maxMessage="Doit contenir {{ limit }} caractères")
     */
    private $question ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="option1", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $option1 ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="option2", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $option2 ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="option3", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $option3 ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="option4", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $option4 ;

    /**
     * @var string|null
     *
     * @ORM\Column(name="answer", type="string", length=200, nullable=true, options={"default"="NULL"})
     * @Assert\NotBlank(message="Ce champs est obligatoire")
     */
    private $answer ;

    /**
     * @ORM\ManyToOne(targetEntity="Quiz")
     * @ORM\JoinColumn(name="quiz" ,referencedColumnName="quizId")
     */

    private $quiz;

    /**
     * @return int
     */
    public function getQuestionid(): int
    {
        return $this->questionid;
    }

    /**
     * @param int $questionid
     */
    public function setQuestionid(int $questionid): void
    {
        $this->questionid = $questionid;
    }

    /**
     * @return string|null
     */
    public function getQuestion(): ?string
    {
        return $this->question;
    }

    /**
     * @param string|null $question
     */
    public function setQuestion(?string $question): void
    {
        $this->question = $question;
    }

    /**
     * @return string|null
     */
    public function getOption1(): ?string
    {
        return $this->option1;
    }

    /**
     * @param string|null $option1
     */
    public function setOption1(?string $option1): void
    {
        $this->option1 = $option1;
    }

    /**
     * @return string|null
     */
    public function getOption2(): ?string
    {
        return $this->option2;
    }

    /**
     * @param string|null $option2
     */
    public function setOption2(?string $option2): void
    {
        $this->option2 = $option2;
    }

    /**
     * @return string|null
     */
    public function getOption3(): ?string
    {
        return $this->option3;
    }

    /**
     * @param string|null $option3
     */
    public function setOption3(?string $option3): void
    {
        $this->option3 = $option3;
    }

    /**
     * @return string|null
     */
    public function getOption4(): ?string
    {
        return $this->option4;
    }

    /**
     * @param string|null $option4
     */
    public function setOption4(?string $option4): void
    {
        $this->option4 = $option4;
    }

    /**
     * @return string|null
     */
    public function getAnswer(): ?string
    {
        return $this->answer;
    }

    /**
     * @param string|null $answer
     */
    public function setAnswer(?string $answer): void
    {
        $this->answer = $answer;
    }

    /**
     * @return Quiz|null
     */
    public function getQuiz(): ?Quiz
    {
        return $this->quiz;
    }

    /**
     * @param Quiz|null $quiz
     */
    public function setQuiz(?Quiz $quiz): void
    {
        $this->quiz = $quiz;
    }


}
