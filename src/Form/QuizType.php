<?php

namespace App\Form;

use App\Entity\Quiz;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length ;
use Symfony\Component\Validator\Constraints\NotBlank;

class QuizType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('title' ,TextType::class, [
                'required' => false,
                'constraints' => [
                    new Length(['min' => 3 , 'max' => 255 ]) ,
                    new NotBlank()] ])

            ->add('isamericain', ChoiceType::class ,[
                'choices' => [
                    'Main Statuses' => [
                        'Yes' => 'isAmericain',
                        'No' => 'isNotAmericain',
                    ],

                ],
            ]);

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Quiz::class,
        ]);
    }
}
