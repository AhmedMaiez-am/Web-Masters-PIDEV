<?php

namespace App\Form;

use App\Entity\Parents;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class ParentsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('emailp' , EmailType::class )
            ->add('nomp')
            ->add('prenomp')
            ->add('telp' ,TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 8 , 'max' => 8 ])],
            ])
            ->add('passwordp' ,PasswordType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 6 , 'max' => 8 ])],
            ])
            ->add('numcarte' ,TextType::class, [
                'required' => true,
                'constraints' => [new Length([ 'max' => 8 ])],
            ])
            ->add('passcarte' ,PasswordType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 4 , 'max' => 4 ])],
            ] )
            ->add('portefeuille')

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Parents::class,
        ]);
    }
}
