Proxy
    In plain words
        Using the proxy pattern, a class represents the functionality of another class.

    Real world example
        Have you ever used an access card to go through a door? There are multiple options to open that door i.e. it can be opened either using access card or by pressing a button that bypasses the security. The door's main functionality is to open but there is a proxy added on top of it to add some functionality. Let me better explain it using the code example below.

        A proxy, in its most general form, is a class functioning as an interface to something else. A proxy is a wrapper or agent object that is being called by the client to access the real serving object behind the scenes. Use of the proxy can simply be forwarding to the real object, or can provide additional logic. In the proxy extra functionality can be provided, for example caching when operations on the real object are resource intensive, or checking preconditions before operations on the real object are invoked.

    Programmatic Example
        Taking our security door example from above. Firstly we have the door interface and an implementation of door

            interface Door
            {
                public function open();
                public function close();
            }

            class LabDoor implements Door
            {
                public function open()
                {
                    echo "Opening lab door";
                }

                public function close()
                {
                    echo "Closing the lab door";
                }
            }

        Then we have a proxy to secure any doors that we want

            class SecuredDoor
            {
                protected $door;

                public function __construct(Door $door)
                {
                    $this->door = $door;
                }

                public function open($password)
                {
                    if ($this->authenticate($password)) {
                        $this->door->open();
                    } else {
                        echo "Big no! It ain't possible.";
                    }
                }

                public function authenticate($password)
                {
                    return $password === '$ecr@t';
                }

                public function close()
                {
                    $this->door->close();
                }
            }

        And here is how it can be used
            $door = new SecuredDoor(new LabDoor());
            $door->open('invalid'); // Big no! It ain't possible.

            $door->open('$ecr@t'); // Opening lab door
            $door->close(); // Closing lab door


















