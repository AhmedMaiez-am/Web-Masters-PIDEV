<?php

namespace App\Form;

use App\Entity\Enfant;
use Gregwar\CaptchaBundle\Type\CaptchaType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;

class EnfantType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomenfant' , TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 4 , 'max' => 100 ])],
            ])
            ->add('prenomenfant' ,  TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 4 , 'max' => 100 ])],
            ])
            ->add('image' ,  FileType::class,array(
                'data_class' => null ))

            ->add('age' )

            ->add('password')
            ->add( 'captcha',CaptchaType::class );



    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Enfant::class,
        ]);
    }
}
