<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Directeur
 *
 * @ORM\Table(name="directeur")
 * @ORM\Entity
 */
class Directeur
{
    /**
     * @var int
     *
     * @ORM\Column(name="idD", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idd;

    /**
     * @var string
     *
     * @ORM\Column(name="login", type="string", length=30, nullable=false)
     */
    private $login;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=30, nullable=false)
     */
    private $password;


}