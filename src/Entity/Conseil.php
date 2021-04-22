<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Conseil
 *
 * @ORM\Table(name="conseil")
 * @ORM\Entity
 */
class Conseil
{
    /**
     * @var int
     *
     * @ORM\Column(name="idc", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idc;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=10, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="nomc", type="string", length=20, nullable=false)
     */
    private $nomc;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomc", type="string", length=20, nullable=false)
     */
    private $prenomc;

    /**
     * @var string
     *
     * @ORM\Column(name="conseil", type="string", length=255, nullable=false)
     */
    private $conseil;


}
