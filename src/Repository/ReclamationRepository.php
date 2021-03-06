<?php

namespace App\Repository;

use App\Entity\Reclamation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Reclamation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reclamation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reclamation[]    findAll()
 * @method Reclamation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReclamationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Reclamation::class);
    }

    public function findReclamationByNom($nom){
        return $this->createQueryBuilder('reclamation')
            ->where('reclamation.nom LIKE :nom')
            ->setParameter('nom', '%'.$nom.'%')
            ->getQuery()
            ->getResult();
    }

//    public function listOrderByName()
//    {
//        return $this->createQueryBuilder('r')
//            ->orderBy('r.nom', 'ASC')
//            ->getQuery()->getResult();
//    }
//    public function listOrderByPren()
//    {
//        return $this->createQueryBuilder('r')
//            ->orderBy('r.prenom', 'ASC')
//            ->getQuery()->getResult();
//    }
    /**
     * @return void
     */
    public function search($mots){
        $query =$this->createQueryBuilder('a');
        if ($mots!= null){
            $query->where('MATCH_AGAINST(a.nom,a.prenom) AGAINST 
            (:mots boolean)>0')
                ->setParameter('mots',$mots);
        }
        return $query->getQuery()->getResult();
    }


    // /**
    //  * @return Reclamation[] Returns an array of Reclamation objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Reclamation
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
