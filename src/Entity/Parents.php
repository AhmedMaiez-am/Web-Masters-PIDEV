<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Parents
 *
 * @ORM\Table(name="parents")
 * @ORM\Entity
 */
class Parents
{
    /**
     * @var int
     *
     * @ORM\Column(name="idP", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idp;

    /**
     * @var string
     *
     * @ORM\Column(name="emailP", type="string", length=100, nullable=false)
     */
    private $emailp;

    /**
     * @var string
     *
     * @ORM\Column(name="nomP", type="string", length=100, nullable=false)
     */
    private $nomp;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomP", type="string", length=100, nullable=false)
     */
    private $prenomp;

    /**
     * @var string
     *
     * @ORM\Column(name="telP", type="string", length=20, nullable=false)
     */
    private $telp;

    /**
     * @var string
     *
     * @ORM\Column(name="passwordP", type="string", length=50, nullable=false)
     */
    private $passwordp;

    /**
     * @var string
     *
     * @ORM\Column(name="NumCarte", type="string", length=20, nullable=false)
     */
    private $numcarte;

    /**
     * @var string|null
     *
     * @ORM\Column(name="passCarte", type="string", length=4, nullable=true, options={"default"="NULL"})
     */
    private $passcarte = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="portefeuille", type="string", length=8, nullable=true, options={"default"="NULL"})
     */
    private $portefeuille = 'NULL';

    /**
     * @var string|null
     *
     * @ORM\Column(name="codep", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $codep = 'NULL';


}
