<?php


namespace App\Repository;


use App\Entity\Parents;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Calendar|null find($id, $lockMode = null, $lockVersion = null)
 * @method Calendar|null findOneBy(array $criteria, array $orderBy = null)
 * @method Calendar[]    findAll()
 * @method Calendar[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ParentRep extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Parents::class);
    }
    public function CountParent()
    {
        $oui = "oui";
        $non = "non" ;
        $query =  $this->createQueryBuilder('c')
            ->select('COUNT(c) as countParent , c.block as identifiant  ')
            ->andWhere('c.block = :oui or c.block = :non')
            ->setParameter(':oui' , $oui )
            ->setParameter(':non' , $non )
            ->groupBy('identifiant');
        return $query->getQuery()->getResult();
    }

    // /**
    //  * @return Calendar[] Returns an array of Calendar objects
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
    public function findOneBySomeField($value): ?Calendar
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    public function nom($n){
        return $this->createQueryBuilder('parents')
            -> where('parents.nomp LIKE :nomp')
            ->setParameter('nomp','%'.$n.'%')
            ->getQuery()
            ->getResult();
    }


}