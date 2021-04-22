<?php

namespace App\Form;

use App\Entity\Parents;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints\Length;


class FormParents extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $option)
    {
        $builder
            ->add('numcarte' ,TextType::class, [
            'required' => true,
            'constraints' => [new Length([ 'max' => 8 ])],
        ])
            ->add('portefeuille' ,TextType::class, [
                'required' => true,
                'constraints' => [new Length([ 'max' => 5 ])],
            ])
            ->add('passcarte' ,TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 4 , 'max' => 4 ])],
            ] );
    }
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class'=>Parents::class,
        ]);
    }
}