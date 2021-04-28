<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inventairecontes
 *
 * @ORM\Table(name="inventairecontes")
 * @ORM\Entity(repositoryClass="App\Repository\InventaireContesRepository")
 */
class Inventairecontes
{
    /**
     * @var int
     *
     * @ORM\Column(name="idContesC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcontesc;

    /**
     * @var string
     *
     * @ORM\Column(name="titreC", type="string", length=50, nullable=false)
     */
    private $titrec;

    /**
     * @var string
     *
     * @ORM\Column(name="auteurC", type="string", length=50, nullable=false)
     */
    private $auteurc;

    /**
     * @return int
     */
    public function getIdcontesc(): int
    {
        return $this->idcontesc;
    }

    /**
     * @param int $idcontesc
     */
    public function setIdcontesc(int $idcontesc): void
    {
        $this->idcontesc = $idcontesc;
    }

    /**
     * @return string
     */
    public function getTitrec(): string
    {
        return $this->titrec;
    }

    /**
     * @param string $titrec
     */
    public function setTitrec(string $titrec): void
    {
        $this->titrec = $titrec;
    }

    /**
     * @return string
     */
    public function getAuteurc(): string
    {
        return $this->auteurc;
    }

    /**
     * @param string $auteurc
     */
    public function setAuteurc(string $auteurc): void
    {
        $this->auteurc = $auteurc;
    }
    public function show(): string
    {
        return 'Auteur du conte :'. $this->auteurc . ', titre du conte : ' . $this->titrec ;
    }

}
