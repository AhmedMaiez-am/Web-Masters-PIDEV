<?php

// This file has been auto-generated by the Symfony Routing Component.

return [
    '_preview_error' => [['code', '_format'], ['_controller' => 'error_controller::preview', '_format' => 'html'], ['code' => '\\d+'], [['variable', '.', '[^/]++', '_format'], ['variable', '/', '\\d+', 'code'], ['text', '/_error']], [], []],
    '_wdt' => [['token'], ['_controller' => 'web_profiler.controller.profiler::toolbarAction'], [], [['variable', '/', '[^/]++', 'token'], ['text', '/_wdt']], [], []],
    '_profiler_home' => [[], ['_controller' => 'web_profiler.controller.profiler::homeAction'], [], [['text', '/_profiler/']], [], []],
    '_profiler_search' => [[], ['_controller' => 'web_profiler.controller.profiler::searchAction'], [], [['text', '/_profiler/search']], [], []],
    '_profiler_search_bar' => [[], ['_controller' => 'web_profiler.controller.profiler::searchBarAction'], [], [['text', '/_profiler/search_bar']], [], []],
    '_profiler_phpinfo' => [[], ['_controller' => 'web_profiler.controller.profiler::phpinfoAction'], [], [['text', '/_profiler/phpinfo']], [], []],
    '_profiler_search_results' => [['token'], ['_controller' => 'web_profiler.controller.profiler::searchResultsAction'], [], [['text', '/search/results'], ['variable', '/', '[^/]++', 'token'], ['text', '/_profiler']], [], []],
    '_profiler_open_file' => [[], ['_controller' => 'web_profiler.controller.profiler::openAction'], [], [['text', '/_profiler/open']], [], []],
    '_profiler' => [['token'], ['_controller' => 'web_profiler.controller.profiler::panelAction'], [], [['variable', '/', '[^/]++', 'token'], ['text', '/_profiler']], [], []],
    '_profiler_router' => [['token'], ['_controller' => 'web_profiler.controller.router::panelAction'], [], [['text', '/router'], ['variable', '/', '[^/]++', 'token'], ['text', '/_profiler']], [], []],
    '_profiler_exception' => [['token'], ['_controller' => 'web_profiler.controller.exception_panel::body'], [], [['text', '/exception'], ['variable', '/', '[^/]++', 'token'], ['text', '/_profiler']], [], []],
    '_profiler_exception_css' => [['token'], ['_controller' => 'web_profiler.controller.exception_panel::stylesheet'], [], [['text', '/exception.css'], ['variable', '/', '[^/]++', 'token'], ['text', '/_profiler']], [], []],
    'afficheRecup' => [[], ['_controller' => 'App\\Controller\\FrontRecuperationController::listeRecup'], [], [['text', '/afficheRecup']], [], []],
    'listrecompense' => [[], ['_controller' => 'App\\Controller\\RecompenseController::list'], [], [['text', '/listrecompense']], [], []],
    'ajoutrecompense' => [[], ['_controller' => 'App\\Controller\\RecompenseController::add'], [], [['text', '/ajoutrecompense']], [], []],
    'modifierRecompense' => [['id'], ['_controller' => 'App\\Controller\\RecompenseController::update'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/modifierRecompense']], [], []],
    'supprimerRecompense' => [['id'], ['_controller' => 'App\\Controller\\RecompenseController::delete'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/supprimerRecompense']], [], []],
    'searchRec' => [[], ['_controller' => 'App\\Controller\\RecompenseController::searchRec'], [], [['text', '/searchRec']], [], []],
    'stat' => [[], ['_controller' => 'App\\Controller\\RecompenseController::statistique'], [], [['text', '/stat']], [], []],
    'recuperation_recompense' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::index'], [], [['text', '/recuperation']], [], []],
    'listeenfant' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::listEnf'], [], [['text', '/listeenfant']], [], []],
    'listeparent' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::listparent'], [], [['text', '/listeparent']], [], []],
    'recompense' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::list1'], [], [['text', '/listerecompense']], [], []],
    'listerecuperation' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::listRecup'], [], [['text', '/listerecuperation']], [], []],
    'ajoutrecuperation' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::add'], [], [['text', '/ajoutrecuperation']], [], []],
    'supprimerRecuperation' => [['id'], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::delete'], [], [['variable', '/', '[^/]++', 'id'], ['text', '/supprimerRecuperation']], [], []],
    'app_recuperationrecompense_update' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::update'], [], [['text', '/updateRecuperation/{id']], [], []],
    'search' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::search'], [], [['text', '/search']], [], []],
    'app_recuperationrecompense_orderbymailsql' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::orderByMailSQL'], [], [['text', '/Recuperation/tri']], [], []],
    'tri' => [[], ['_controller' => 'App\\Controller\\RecuperationRecompenseController::orederByNom'], [], [['text', '/trinom']], [], []],
];
