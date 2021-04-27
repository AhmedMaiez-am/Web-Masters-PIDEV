<?php

namespace App\Repository;

use App\Entity\Cours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Cours|null find($id, $lockMode = null, $lockVersion = null)
 * @method Cours|null findOneBy(array $criteria, array $orderBy = null)
 * @method Cours[]    findAll()
 * @method Cours[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CoursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Cours::class);
    }

    // /**
    //  * @return Cours[] Returns an array of Cours objects
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
    public function findOneBySomeField($value): ?Cours
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function findStudentByNsc($nom){
        return $this->createQueryBuilder('c')
            ->where('c.nom LIKE :nom')
            ->setParameter('nom', '%'.$nom.'%')
            ->getQuery()
            ->getResult();
    }
    public function filterByPriceType($prix,$type,$rate){
        $query= $this->createQueryBuilder('c')
           ->where('c.type = :type')
            ->setParameter('type', $type)
            ->orderBy('c.rate',$rate);

        if($prix=='asc'){
            $query
                ->addSelect('ABS(c.prix) AS HIDDEN prix')
                ->orderBy('prix');

        }

        if($prix=='dsc'){
            $query
                ->addSelect('-ABS(c.prix) AS HIDDEN prix')
                ->orderBy('prix');

        }
        return $query
            ->getQuery()
            ->getResult();
    }
}
