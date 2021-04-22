<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Quizresult
 *
 * @ORM\Table(name="quizresult", indexes={@ORM\Index(name="test1", columns={"student_id"}), @ORM\Index(name="test", columns={"quiz_id"})})
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
     * @ORM\Column(name="student_id", type="integer", nullable=false)
     */
    private $studentId;

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


}
