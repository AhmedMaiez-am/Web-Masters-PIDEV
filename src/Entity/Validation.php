<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Validation
 *
 * @ORM\Table(name="validation")
 * @ORM\Entity
 */
class Validation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idm", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idm;

    /**
     * @var string
     *
     * @ORM\Column(name="loginM", type="string", length=50, nullable=false)
     */
    private $loginm;

    /**
     * @var string
     *
     * @ORM\Column(name="passwordM", type="string", length=50, nullable=false)
     */
    private $passwordm;

    /**
     * @var string|null
     *
     * @ORM\Column(name="codem", type="string", length=50, nullable=true, options={"default"="NULL"})
     */
    private $codem = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="img", type="text", length=65535, nullable=true, options={"default"="NULL"})
     */
    private $img = 'NULL';


}
