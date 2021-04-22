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


}
