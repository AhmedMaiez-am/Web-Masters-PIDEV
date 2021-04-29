<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Student
 *
 * @ORM\Table(name="student")
 * @ORM\Entity
 */
class Student
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
     * @var string
     *
     * @ORM\Column(name="firstName", type="string", length=255, nullable=false)
     */
    private $firstname;

    /**
     * @var string
     *
     * @ORM\Column(name="lastName", type="string", length=255, nullable=false)
     */
    private $lastname;

    /**
     * @var string
     *
     * @ORM\Column(name="mobile", type="string", length=255, nullable=false)
     */
    private $mobile;

    /**
     * @var string
     *
     * @ORM\Column(name="gender", type="string", length=255, nullable=false, options={"fixed"=true})
     */
    private $gender;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=255, nullable=false)
     */
    private $password;

    /**
     * @ORM\OneToMany(targetEntity=Quizresult::class, mappedBy="student_id")
     */
    private $quizId;

    public function __construct()
    {
        $this->quizId = new ArrayCollection();
    }

    /**
     * @return Collection|Quizresult[]
     */
    public function getQuizResults(): Collection
    {
        return $this->quizId;
    }

    public function addQuizResult(Quizresult $QuizResult): self
    {
        if (!$this->QuizResult->contains($QuizResult)) {
            $this->QuizResult[] = $QuizResult;
            $QuizResult->setStudentId($this);
        }

        return $this;
    }

    public function removeQuizResult(Quizresult $QuizResult): self
    {
        if ($this->QuizResult->removeElement($QuizResult)) {
            // set the owning side to null (unless already changed)
            if ($QuizResult->getStudentId() === $this) {
                $QuizResult->setStudentId(null);
            }
        }

        return $this;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getFirstname(): ?string
    {
        return $this->firstname;
    }

    public function setFirstname(string $firstname): self
    {
        $this->firstname = $firstname;

        return $this;
    }

    public function getLastname(): ?string
    {
        return $this->lastname;
    }

    public function setLastname(string $lastname): self
    {
        $this->lastname = $lastname;

        return $this;
    }

    public function getMobile(): ?string
    {
        return $this->mobile;
    }

    public function setMobile(string $mobile): self
    {
        $this->mobile = $mobile;

        return $this;
    }

    public function getGender(): ?string
    {
        return $this->gender;
    }

    public function setGender(string $gender): self
    {
        $this->gender = $gender;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * @return Collection|Quizresult[]
     */
    public function getQuizId(): Collection
    {
        return $this->quizId;
    }

    public function addQuizId(Quizresult $quizId): self
    {
        if (!$this->quizId->contains($quizId)) {
            $this->quizId[] = $quizId;
            $quizId->setStudentId($this);
        }

        return $this;
    }

    public function removeQuizId(Quizresult $quizId): self
    {
        if ($this->quizId->removeElement($quizId)) {
            // set the owning side to null (unless already changed)
            if ($quizId->getStudentId() === $this) {
                $quizId->setStudentId(null);
            }
        }

        return $this;
    }


}
