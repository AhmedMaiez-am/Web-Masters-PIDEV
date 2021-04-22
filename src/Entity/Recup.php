<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Recup
 *
 * @ORM\Table(name="recup")
 * @ORM\Entity
 */
class Recup
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     */
    private $id;

    /**
     * @var int
     *
     * @ORM\Column(name="idCv", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcv;

    /**
     * @var string
     *
     * @ORM\Column(name="code", type="string", length=100, nullable=false)
     */
    private $code;


}
