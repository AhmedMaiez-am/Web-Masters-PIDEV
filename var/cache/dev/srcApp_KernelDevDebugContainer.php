<?php

// This file has been auto-generated by the Symfony Dependency Injection Component for internal use.

if (\class_exists(\ContainerPZ3fouR\srcApp_KernelDevDebugContainer::class, false)) {
    // no-op
} elseif (!include __DIR__.'/ContainerPZ3fouR/srcApp_KernelDevDebugContainer.php') {
    touch(__DIR__.'/ContainerPZ3fouR.legacy');

    return;
}

if (!\class_exists(srcApp_KernelDevDebugContainer::class, false)) {
    \class_alias(\ContainerPZ3fouR\srcApp_KernelDevDebugContainer::class, srcApp_KernelDevDebugContainer::class, false);
}

return new \ContainerPZ3fouR\srcApp_KernelDevDebugContainer([
    'container.build_hash' => 'PZ3fouR',
    'container.build_id' => '009340dd',
    'container.build_time' => 1619088262,
], __DIR__.\DIRECTORY_SEPARATOR.'ContainerPZ3fouR');
