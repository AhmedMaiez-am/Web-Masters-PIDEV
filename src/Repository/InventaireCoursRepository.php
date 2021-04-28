<?php

namespace App\Repository;

use App\Entity\Inventairecours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Inventairecours|null find($id, $lockMode = null, $lockVersion = null)
 * @method Inventairecours|null findOneBy(array $criteria, array $orderBy = null)
 * @method Inventairecours[]    findAll()
 * @method Inventairecours[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class InventaireCoursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Inventairecours::class);
    }

    // /**
    //  * @return Inventairecours[] Returns an array of Inventairecours objects
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
    public function findOneBySomeField($value): ?Inventairecours
    {
        return $this->createQueryBuilder('i')
            ->andWhere('i.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function findC($cr){
        return $this->createQueryBuilder('cours')
            ->where('cours.nom LIKE :nom')
            ->setParameter('nom', '%'.$cr.'%')
            ->getQuery()
            ->getResult();
    }
    public function OrderByNom(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select r from App\Entity\InventaireCours r order by r.nomc ASC');
        return $query->getResult();
    }
}
