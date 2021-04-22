<?php

namespace App\Repository;

use App\Entity\Contes;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Contes|null find($id, $lockMode = null, $lockVersion = null)
 * @method Contes|null findOneBy(array $criteria, array $orderBy = null)
 * @method Contes[]    findAll()
 * @method Contes[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ContesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Contes::class);
    }

    // /**
    //  * @return Contes[] Returns an array of Contes objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Contes
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function findStudentByT($titre){
        return $this->createQueryBuilder('c')
            ->where('c.titre LIKE :titre')
            ->setParameter('titre', '%'.$titre.'%')
            ->getQuery()
            ->getResult();
    }

}
