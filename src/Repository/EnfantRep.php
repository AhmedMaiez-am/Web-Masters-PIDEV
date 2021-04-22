<?php


namespace App\Repository;


use App\Entity\Enfant;
use App\Entity\Maitresse;
use App\Entity\Workshop;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Calendar|null find($id, $lockMode = null, $lockVersion = null)
 * @method Calendar|null findOneBy(array $criteria, array $orderBy = null)
 * @method Calendar[]    findAll()
 * @method Calendar[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EnfantRep extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Enfant::class);
    }

    public function findEnfantBynom ($requestString){
        return $this->createQueryBuilder('Enfant')
            ->where('Enfant.nomenfant LIKE :nomenfant')
            ->setParameter('nomenfant', '%'.$requestString.'%')
            ->getQuery()
            ->getResult();
    }
    public function OrderByNomE()
    {
        return $this->createQueryBuilder('m')
            ->orderBy('m.nomenfant','ASC')
            ->getQuery()->getResult()
            ;

    }
    public function OrderByPrenomE()
    {
        return $this->createQueryBuilder('m')
            ->orderBy('m.prenomenfant','DESC')
            ->getQuery()->getResult()
            ;

    }
    public function OrderByAgeE()
    {
        return $this->createQueryBuilder('m')
            ->orderBy('m.age','ASC')
            ->getQuery()->getResult()
            ;

    }
       public function CountEnfant()
    {
        $query =  $this->createQueryBuilder('a')
            ->select('a.age as ageEnfant, COUNT(a) as count')
            ->groupBy('ageEnfant');
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
}