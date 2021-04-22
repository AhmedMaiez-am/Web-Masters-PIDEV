<?php

namespace App\Form;

use App\Entity\Questions;
use App\Entity\Quiz;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class QuestionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('quiz',EntityType::class,[
            'required' => true,
            'class' => Quiz::class,
            'choice_label' => 'title',
        ])
        
            ->add('question' , TextType::class, [
                'required' => false,
                'constraints' => [
                    new Length(['min' => 3 , 'max' => 255 ]) ,
                    new NotBlank()] ])

            ->add('option1', TextType::class, [
                'required' => false,
                'constraints' => [
                    new NotBlank()
                ] ] )

            ->add('option2', TextType::class, [
                'required' => false,
                'constraints' => [
                    new NotBlank()
                ] ] )

            ->add('option3', TextType::class, [
                'required' => false,
                'constraints' => [
                    new NotBlank()
                ] ] )

            ->add('option4', TextType::class, [
                'required' => false,
                'constraints' => [

                    new NotBlank()
                ] ] )

            ->add('answer',ChoiceType::class,[
                'required' => true,

                'choices'  => [
                    '1ére choix' => 'answer1',
                    '2éme choix' => 'answer2',
                    '3éme choix' => 'answer3',
                    '4éme choix' => 'answer4'
                ],
                'expanded' => true
            ])
            ->add('quiz',EntityType::class,[
                'required' => true,
                'class' => Quiz::class,
                'choice_label' => 'title',
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Questions::class,
        ]);
    }
}
