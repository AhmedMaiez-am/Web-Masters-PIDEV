<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Quizresult
 *
 * @ORM\Table(name="quizresult", indexes={@ORM\Index(name="test", columns={"quiz_id"}), @ORM\Index(name="test1", columns={"student_id"})})
 * @ORM\Entity
 */
class Quizresult
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var int
     *
     * @ORM\Column(name="quiz_id", type="integer", nullable=false)
     */
    private $quizId;

    /**
     * @var int
     *
     * @ORM\Column(name="right_answer", type="integer", nullable=false)
     */
    private $rightAnswer;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="timestamp", type="datetime", nullable=false, options={"default"="current_timestamp()"})
     */
    private $timestamp = 'current_timestamp()';

    /**
     * @var int
     *
     * @ORM\Column(name="score", type="integer", nullable=false)
     */
    private $score;

    /**
     * @var \Quizresult
     *
     * @ORM\ManyToOne(targetEntity="Quizresult")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="student_id", referencedColumnName="id")
     * })
     */
    private $student;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getQuizId(): ?int
    {
        return $this->quizId;
    }

    public function setQuizId(int $quizId): self
    {
        $this->quizId = $quizId;

        return $this;
    }

    public function getRightAnswer(): ?int
    {
        return $this->rightAnswer;
    }

    public function setRightAnswer(int $rightAnswer): self
    {
        $this->rightAnswer = $rightAnswer;

        return $this;
    }

    public function getTimestamp(): ?\DateTimeInterface
    {
        return $this->timestamp;
    }

    public function setTimestamp(\DateTimeInterface $timestamp): self
    {
        $this->timestamp = $timestamp;

        return $this;
    }

    public function getScore(): ?int
    {
        return $this->score;
    }

    public function setScore(int $score): self
    {
        $this->score = $score;

        return $this;
    }

    public function getStudent(): ?self
    {
        return $this->student;
    }

    public function setStudent(?self $student): self
    {
        $this->student = $student;

        return $this;
    }


}
