<?php

namespace App\Form;

use App\Entity\Cours;
use App\Entity\Typecours;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class Cours1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('type',EntityType::class, [
                    'class'=>Typecours::class,
                    'choice_label'=>'nom',
                ]
            //  'choices'=>array(
            ///  'math'=>"math",
            //'farncai'=>'francai',
            //)
            //             )
            )

            ->add('description')
            ->add('cours',FileType::class,['data_class' => null]
            )

            ->add('prix')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Cours::class,
        ]);
    }
}
