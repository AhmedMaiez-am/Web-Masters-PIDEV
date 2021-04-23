<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/_profiler' => [[['_route' => '_profiler_home', '_controller' => 'web_profiler.controller.profiler::homeAction'], null, null, null, true, false, null]],
        '/_profiler/search' => [[['_route' => '_profiler_search', '_controller' => 'web_profiler.controller.profiler::searchAction'], null, null, null, false, false, null]],
        '/_profiler/search_bar' => [[['_route' => '_profiler_search_bar', '_controller' => 'web_profiler.controller.profiler::searchBarAction'], null, null, null, false, false, null]],
        '/_profiler/phpinfo' => [[['_route' => '_profiler_phpinfo', '_controller' => 'web_profiler.controller.profiler::phpinfoAction'], null, null, null, false, false, null]],
        '/_profiler/open' => [[['_route' => '_profiler_open_file', '_controller' => 'web_profiler.controller.profiler::openAction'], null, null, null, false, false, null]],
        '/afficheRecup' => [[['_route' => 'afficheRecup', '_controller' => 'App\\Controller\\FrontRecuperationController::listeRecup'], null, null, null, false, false, null]],
        '/listrecompense' => [[['_route' => 'listrecompense', '_controller' => 'App\\Controller\\RecompenseController::list'], null, null, null, false, false, null]],
        '/ajoutrecompense' => [[['_route' => 'ajoutrecompense', '_controller' => 'App\\Controller\\RecompenseController::add'], null, null, null, false, false, null]],
        '/searchRec' => [[['_route' => 'searchRec', '_controller' => 'App\\Controller\\RecompenseController::searchRec'], null, null, null, false, false, null]],
        '/stat' => [[['_route' => 'stat', '_controller' => 'App\\Controller\\RecompenseController::statistique'], null, null, null, false, false, null]],
        '/recuperation' => [[['_route' => 'recuperation_recompense', '_controller' => 'App\\Controller\\RecuperationRecompenseController::index'], null, null, null, false, false, null]],
        '/listeenfant' => [[['_route' => 'listeenfant', '_controller' => 'App\\Controller\\RecuperationRecompenseController::listEnf'], null, null, null, false, false, null]],
        '/listeparent' => [[['_route' => 'listeparent', '_controller' => 'App\\Controller\\RecuperationRecompenseController::listparent'], null, null, null, false, false, null]],
        '/listerecompense' => [[['_route' => 'recompense', '_controller' => 'App\\Controller\\RecuperationRecompenseController::list1'], null, null, null, false, false, null]],
        '/listerecuperation' => [[['_route' => 'listerecuperation', '_controller' => 'App\\Controller\\RecuperationRecompenseController::listRecup'], null, null, null, false, false, null]],
        '/ajoutrecuperation' => [[['_route' => 'ajoutrecuperation', '_controller' => 'App\\Controller\\RecuperationRecompenseController::add'], null, null, null, false, false, null]],
        '/updateRecuperation/{id' => [[['_route' => 'app_recuperationrecompense_update', '_controller' => 'App\\Controller\\RecuperationRecompenseController::update'], null, null, null, false, false, null]],
        '/search' => [[['_route' => 'search', '_controller' => 'App\\Controller\\RecuperationRecompenseController::search'], null, null, null, false, false, null]],
        '/Recuperation/tri' => [[['_route' => 'app_recuperationrecompense_orderbymailsql', '_controller' => 'App\\Controller\\RecuperationRecompenseController::orderByMailSQL'], null, null, null, false, false, null]],
        '/trinom' => [[['_route' => 'tri', '_controller' => 'App\\Controller\\RecuperationRecompenseController::orederByNom'], null, null, null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/_(?'
                    .'|error/(\\d+)(?:\\.([^/]++))?(*:38)'
                    .'|wdt/([^/]++)(*:57)'
                    .'|profiler/([^/]++)(?'
                        .'|/(?'
                            .'|search/results(*:102)'
                            .'|router(*:116)'
                            .'|exception(?'
                                .'|(*:136)'
                                .'|\\.css(*:149)'
                            .')'
                        .')'
                        .'|(*:159)'
                    .')'
                .')'
                .'|/modifierRecompense/([^/]++)(*:197)'
                .'|/supprimerRec(?'
                    .'|ompense/([^/]++)(*:237)'
                    .'|uperation/([^/]++)(*:263)'
                .')'
            .')/?$}sD',
    ],
    [ // $dynamicRoutes
        38 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        57 => [[['_route' => '_wdt', '_controller' => 'web_profiler.controller.profiler::toolbarAction'], ['token'], null, null, false, true, null]],
        102 => [[['_route' => '_profiler_search_results', '_controller' => 'web_profiler.controller.profiler::searchResultsAction'], ['token'], null, null, false, false, null]],
        116 => [[['_route' => '_profiler_router', '_controller' => 'web_profiler.controller.router::panelAction'], ['token'], null, null, false, false, null]],
        136 => [[['_route' => '_profiler_exception', '_controller' => 'web_profiler.controller.exception_panel::body'], ['token'], null, null, false, false, null]],
        149 => [[['_route' => '_profiler_exception_css', '_controller' => 'web_profiler.controller.exception_panel::stylesheet'], ['token'], null, null, false, false, null]],
        159 => [[['_route' => '_profiler', '_controller' => 'web_profiler.controller.profiler::panelAction'], ['token'], null, null, false, true, null]],
        197 => [[['_route' => 'modifierRecompense', '_controller' => 'App\\Controller\\RecompenseController::update'], ['id'], null, null, false, true, null]],
        237 => [[['_route' => 'supprimerRecompense', '_controller' => 'App\\Controller\\RecompenseController::delete'], ['id'], null, null, false, true, null]],
        263 => [
            [['_route' => 'supprimerRecuperation', '_controller' => 'App\\Controller\\RecuperationRecompenseController::delete'], ['id'], null, null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
