<?php


namespace App;
use Symfony\Component\HttpFoundation\Session\SessionInterface;


class SomeService
{
    private $session;

    public function __construct(SessionInterface $session)
    {
        $this->session = $session;
    }

    public function someMethod()
    {
        // stores an attribute in the session for later reuse
        $this->session->set('attribute-name', 'id');

        // gets an attribute by name
        $foo = $this->session->get('foo');

        // the second argument is the value returned when the attribute doesn't exist
        $filters = $this->session->get('filters', []);

        // ...
    }
}