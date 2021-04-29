<?php


namespace App\Repository;


use App\Entity\Quiz;
use App\Entity\Workshop;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;


/**
 * @method Calendar|null find($id, $lockMode = null, $lockVersion = null)
 * @method Calendar|null findOneBy(array $criteria, array $orderBy = null)
 * @method Calendar[]    findAll()
 * @method Calendar[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class QuizRep extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Quiz::class);
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
    public function findQuizByTitle($Title){
        return $this->createQueryBuilder('Quiz')
            ->where('Quiz.Title LIKE :Title')
            ->setParameter('Title', '%'.$Title.'%')
            ->getQuery()
            ->getResult();
    }

    public function OrderBynom(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select q from App\Entity\Quiz q order by q.title ASC');
        return $query->getResult();
    }
    public function OrderBynomE(){
        $em=$this->getEntityManager();
        $query=$em->createQuery('select q from App\Entity\Quiz q order by q.title ASC');
        return $query->getResult();
    }


    public function stat1()
    {
        $query= $this->getEntityManager()
            ->createQuery('select o.quizid,count(o.quizid) as nbdep from App\Entity\Quiz o group by o.quizid');
        return $query->getResult();
    }

}