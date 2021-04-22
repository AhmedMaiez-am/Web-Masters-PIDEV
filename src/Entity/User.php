<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user")
 * @ORM\Entity
 */
class User
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
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=50, nullable=false)
     */
    private $password;

    /**
     * @var string|null
     *
     * @ORM\Column(name="code", type="string", length=50, nullable=true, options={"default"="NULL"})
     */
    private $code = 'NULL';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateCreation", type="datetime", nullable=true, options={"default"="NULL"})
     */
    private $datecreation = 'NULL';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateExp", type="datetime", nullable=true, options={"default"="NULL"})
     */
    private $dateexp = 'NULL';


}
