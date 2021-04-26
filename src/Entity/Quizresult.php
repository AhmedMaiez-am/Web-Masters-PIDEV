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
     * @ORM\ManyToOne(targetEntity="Quiz")
     * @ORM\JoinColumn(name="$quizId" ,referencedColumnName="$quizId")
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
     * @ORM\ManyToOne(targetEntity="Student")
     * @ORM\JoinColumn(name="$student_id" ,referencedColumnName="id")
     */
    private $student_id;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getQuizId()
    {
        return $this->quizId;
    }

    /**
     * @param mixed $quizId
     */
    public function setQuizId($quizId): void
    {
        $this->quizId = $quizId;
    }

    /**
     * @return int
     */
    public function getRightAnswer(): int
    {
        return $this->rightAnswer;
    }

    /**
     * @param int $rightAnswer
     */
    public function setRightAnswer(int $rightAnswer): void
    {
        $this->rightAnswer = $rightAnswer;
    }

    /**
     * @return \DateTime
     */
    public function getTimestamp()
    {
        return $this->timestamp;
    }

    /**
     * @param \DateTime $timestamp
     */
    public function setTimestamp($timestamp): void
    {
        $this->timestamp = $timestamp;
    }

    /**
     * @return int
     */
    public function getScore(): int
    {
        return $this->score;
    }

    /**
     * @param int $score
     */
    public function setScore(int $score): void
    {
        $this->score = $score;
    }

    /**
     * @return mixed
     */
    public function getStudentId()
    {
        return $this->student_id;
    }

    /**
     * @param mixed $student_id
     */
    public function setStudentId($student_id): void
    {
        $this->student_id = $student_id;
    }


}
