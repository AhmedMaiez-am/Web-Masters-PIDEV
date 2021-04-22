<?php

namespace App\Form;

use App\Entity\Maitresse;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\File ;


class DemandeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('etat')
            ->add('cin' ,  TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 8 , 'max' => 8 ])],
            ])
            ->add('nomm' , TextType::class, [
                'required' => true,
                'constraints' => [new Length(['min' => 2, 'max' => 8 ])],
            ])
            ->add('prenomm')
            ->add('emailmaitresse')

            ->add('path', FileType::class,array(
                'data_class' => null ))
            ->add('cv'  ,FileType::class, array('label' => 'Brochure (PDF file)'))














        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Maitresse::class,
        ]);
    }
}
