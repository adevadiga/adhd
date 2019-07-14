Command:
    Encapsulate a request as an object, thereby letting users parameterize clients with different requests, queue or log requests, and support undoable operations.

    Commands are an object-oriented replacement for callbacks.

    In a nutshell, it just an indirect method call stored in some data structure, object or variable and invoked later. The command can be replaced by just a lambda function.

    A generic example would be you ordering food at a restaurant. 
    You (i.e. Client) - ask the waiter (i.e. Invoker) - to bring some food (i.e. Command) -
    and waiter simply forwards the request to, Chef (i.e. Receiver) who has the knowledge of what and how to cook.

    Another example would be you (i.e. Client) switching on (i.e. Command) the television (i.e. Receiver) using a remote control (Invoker).

    In plain words, Allows you to encapsulate actions in objects. The key idea behind this pattern is to provide the means to decouple client from receiver.

    Ex:

    `// Receiver
    class Bulb
    {
        public function turnOn()
        {
            echo "Bulb has been lit";
        }

        public function turnOff()
        {
            echo "Darkness!";
        }
    }`

    then we have an interface that each of the commands are going to implement and then we have a set of commands

    `interface Command
    {
        public function execute();
        public function undo();
        public function redo();
    }

    // Command
    class TurnOn implements Command
    {
        protected $bulb;

        public function __construct(Bulb $bulb)
        {
            $this->bulb = $bulb;
        }

        public function execute()
        {
            $this->bulb->turnOn();
        }

        public function undo()
        {
            $this->bulb->turnOff();
        }

        public function redo()
        {
            $this->execute();
        }
    }

    class TurnOff implements Command
    {
        protected $bulb;

        public function __construct(Bulb $bulb)
        {
            $this->bulb = $bulb;
        }

        public function execute()
        {
            $this->bulb->turnOff();
        }

        public function undo()
        {
            $this->bulb->turnOn();
        }

        public function redo()
        {
            $this->execute();
        }
    }`

    Then we have an Invoker with whom the client will interact to process any commands
    `// Invoker
    class RemoteControl
    {
        public function submit(Command $command)
        {
            $command->execute();
        }
    }`

    Finally let's see how we can use it in our client

    `$bulb = new Bulb();

    $turnOn = new TurnOn($bulb);
    $turnOff = new TurnOff($bulb);

    $remote = new RemoteControl();
    $remote->submit($turnOn); // Bulb has been lit!
    $remote->submit($turnOff); // Darkness!`

    Command pattern can also be used to implement a transaction based system. Where you keep maintaining the history of commands as soon as you execute them. If the final command is successfully executed, all good otherwise just iterate through the history and keep executing the undo on all the executed commands.

=========================
Command Pattern in depth:

Packaging commands as objects and sending them to a receiver enables a clean, loosely coupled design that is easy to maintain.

One component's request is transmitted to another in the Command pattern.

The Command pattern is not simply a method call. The request is packaged in some way.

One use of Command pattern is to package Java code to tell remote process what to do.
A different use would be the undo stack in an editor or word processor.

Conditional Dispatcher: - code using lot of conditional statements.
Some Conditional Dispatcher will become enormous and un-weidly as they evolve to handle new requests or as their handling logic grows.

================


Use the Command pattern when you want to
        1. parameterize objects by an action to perform. 
           You can express such parameterization in a procedural language with a callback function, that is, a function that's registered somewhere to be called at a later point. Commands are an object-oriented replacement for callbacks.

        2. specify, queue, and execute requests at different times. 
            A Command object can have a lifetime independent of the original request. If the receiver of a request can be represented in an address space-independent way, then you can transfer a command  object for the request to a different process and fulfill the request there.

        3. support undo.
            The Command's execute operation can store state for reversing its effects in the command itself. The Command interface must have an added Unexecute operation that reverses the effects of a previous call to execute. Executed commands are stored in a history list. Unlimited-level undo and redo is achieved by traversing this list backwards and forwards calling unexecute and execute, respectively

        4. support logging changes so that they can be reapplied in case of a system crash. 


Typical Use Case
    to keep a history of requests
    implement callback functionality
    implement the undo functionality

Real world examples
    java.lang.Runnable
    org.junit.runners.model.Statement
    Netflix Hystrix
    javax.swing.Action


==========
Summary:
The Gang of Four's Design Patterns states that the command pattern is applicable to five situations:

Parameterize objects by action to perform. Ex.: menu items. In that sense commands are an OO replacement to callbacks and unneeded in Javascript. /u/notunlikethewaves mentions that.
Specify, queue and execute requests at different times.
Support undo by storing necessary data in the Command object for reversing.
Support logging changes so they can be applied after a crash.
Represent transactions (high-level operations built on primitive operations).
