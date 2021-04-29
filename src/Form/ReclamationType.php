<?php

namespace App\Form;

use App\Entity\Reclamation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class ReclamationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('nom')
            ->add('prenom')
            ->add('email')
            ->add('reclamation')
            ->add('type', ChoiceType::class, [
                'choices'  => [
                    'Cours' => "Cours",
                    'Enseignant' => "Enseignant",
                    'Enfant' => "Enfant",
                    'Contes' => "Contes",
                    'Autres' => "Reclamation spécifique lire la description",
                ],
            ]);
        ;


    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
