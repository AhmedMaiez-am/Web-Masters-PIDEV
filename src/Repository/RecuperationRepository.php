<?php

namespace App\Repository;

use App\Entity\Recuperation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Recuperation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Recuperation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Recuperation[]    findAll()
 * @method Recuperation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class RecuperationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Recuperation::class);
    }

    // /**
    //  * @return Recuperation[] Returns an array of Recuperation objects
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
    public function findOneBySomeField($value): ?Recuperation
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */



    public function OrderBynom(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select rec from App\Entity\Recuperation rec order by rec.nomrec ASC');
        return $query->getResult();
    }

    public function OrderByMail(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select r from App\Entity\Recuperation r order by r.emailp ASC');
        return $query->getResult();
    }
    public function findBynomrec($nom)
    {
        return $this->createQueryBuilder('Recuperation')
            ->andWhere('Recuperation.nomrec  LIKE :nomrec')
            ->setParameter('nomrec', '%'.$nom.'%')
            ->getQuery()
            ->getResult()
            ;
    }




}
