<?php

namespace App\Repository;

use App\Entity\Inventairecontes;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Inventairecontes|null find($id, $lockMode = null, $lockVersion = null)
 * @method Inventairecontes|null findOneBy(array $criteria, array $orderBy = null)
 * @method Inventairecontes[]    findAll()
 * @method Inventairecontes[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class InventaireContesRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Inventairecontes::class);
    }

    // /**
    //  * @return Inventairecontes[] Returns an array of Inventairecontes objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('i.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Inventairecontes
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function findContes($cnt){
        return $this->createQueryBuilder('contes')
            ->where('contes.titre LIKE :titre')
            ->setParameter('titre', '%'.$cnt.'%')
            ->getQuery()
            ->getResult();
    }
    public function OrderByTitre(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select r from App\Entity\InventaireContes r order by r.titrec ASC');
        return $query->getResult();
    }
}
