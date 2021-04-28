<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Questions
 *
 * @ORM\Table(name="questions", indexes={@ORM\Index(name="quiz", columns={"quiz"})})
 * @ORM\Entity
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
     */
    private $question = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="option1", type="string", length=200, nullable=true, options={"default"="NULL"})
     */
    private $option1 = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="option2", type="string", length=200, nullable=true, options={"default"="NULL"})
     */
    private $option2 = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="option3", type="string", length=200, nullable=true, options={"default"="NULL"})
     */
    private $option3 = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="option4", type="string", length=200, nullable=true, options={"default"="NULL"})
     */
    private $option4 = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="answer", type="string", length=200, nullable=true, options={"default"="NULL"})
     */
    private $answer = 'NULL';

    /**
     * @var int|null
     *
     * @ORM\Column(name="quiz", type="integer", nullable=true, options={"default"="NULL"})
     */
    private $quiz = NULL;

    public function getQuestionid(): ?int
    {
        return $this->questionid;
    }

    public function getQuestion(): ?string
    {
        return $this->question;
    }

    public function setQuestion(?string $question): self
    {
        $this->question = $question;

        return $this;
    }

    public function getOption1(): ?string
    {
        return $this->option1;
    }

    public function setOption1(?string $option1): self
    {
        $this->option1 = $option1;

        return $this;
    }

    public function getOption2(): ?string
    {
        return $this->option2;
    }

    public function setOption2(?string $option2): self
    {
        $this->option2 = $option2;

        return $this;
    }

    public function getOption3(): ?string
    {
        return $this->option3;
    }

    public function setOption3(?string $option3): self
    {
        $this->option3 = $option3;

        return $this;
    }

    public function getOption4(): ?string
    {
        return $this->option4;
    }

    public function setOption4(?string $option4): self
    {
        $this->option4 = $option4;

        return $this;
    }

    public function getAnswer(): ?string
    {
        return $this->answer;
    }

    public function setAnswer(?string $answer): self
    {
        $this->answer = $answer;

        return $this;
    }

    public function getQuiz(): ?int
    {
        return $this->quiz;
    }

    public function setQuiz(?int $quiz): self
    {
        $this->quiz = $quiz;

        return $this;
    }


}
