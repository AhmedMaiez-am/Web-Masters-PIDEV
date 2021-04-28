<?php


namespace App\Form;
use App\Entity\Cours;
use App\Entity\Typecours;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class FilterType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('type',EntityType::class, [
                    'class'=>Typecours::class,
                    'choice_label'=>'nom',
                    'choice_value' => 'nom'
                ]
            )
            ->add('prix',ChoiceType::class,[
                'choices'=>[
                    'prix ascendant'=>"asc",
                    'prix descendant'=>"dsc",
                ]
            ])
            ->add('rate',ChoiceType::class,[
                'choices'=>[
                    'rate ascendant'=>"asc",
                    'rate descendant'=>"dsc",
                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            //'data_class' => Cours::class,
        ]);
    }
}