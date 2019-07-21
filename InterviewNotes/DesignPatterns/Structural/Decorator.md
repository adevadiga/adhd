Decorator

    In plain words
        Decorator pattern lets you dynamically change the behavior of an object at run time by wrapping them in an object of a decorator class.

    Real world example
        Imagine you run a car service shop offering multiple services. Now how do you calculate the bill to be charged? You pick one service and dynamically keep adding to it the prices for the provided services till you get the final cost. Here each type of service is a decorator.


    Programmatic Example
        Lets take coffee for example. First of all we have a simple coffee implementing the coffee interface

            interface Coffee
            {
                public function getCost();
                public function getDescription();
            }

            class SimpleCoffee implements Coffee
            {
                public function getCost()
                {
                    return 10;
                }

                public function getDescription()
                {
                    return 'Simple coffee';
                }
            }

        We want to make the code extensible to allow options to modify it if required. Lets make some add-ons (decorators)

            class MilkCoffee implements Coffee
            {
                protected $coffee;

                public function __construct(Coffee $coffee)
                {
                    $this->coffee = $coffee;
                }

                public function getCost()
                {
                    return $this->coffee->getCost() + 2;
                }

                public function getDescription()
                {
                    return $this->coffee->getDescription() . ', milk';
                }
            }

            class WhipCoffee implements Coffee
            {
                protected $coffee;

                public function __construct(Coffee $coffee)
                {
                    $this->coffee = $coffee;
                }

                public function getCost()
                {
                    return $this->coffee->getCost() + 5;
                }

                public function getDescription()
                {
                    return $this->coffee->getDescription() . ', whip';
                }
            }

            class VanillaCoffee implements Coffee
            {
                protected $coffee;

                public function __construct(Coffee $coffee)
                {
                    $this->coffee = $coffee;
                }

                public function getCost()
                {
                    return $this->coffee->getCost() + 3;
                }

                public function getDescription()
                {
                    return $this->coffee->getDescription() . ', vanilla';
                }
            }

        Lets make a coffee now
            $someCoffee = new SimpleCoffee();
            echo $someCoffee->getCost(); // 10
            echo $someCoffee->getDescription(); // Simple Coffee

            $someCoffee = new MilkCoffee($someCoffee);
            echo $someCoffee->getCost(); // 12
            echo $someCoffee->getDescription(); // Simple Coffee, milk

            $someCoffee = new WhipCoffee($someCoffee);
            echo $someCoffee->getCost(); // 17
            echo $someCoffee->getDescription(); // Simple Coffee, milk, whip

            $someCoffee = new VanillaCoffee($someCoffee);
            echo $someCoffee->getCost(); // 20
            echo $someCoffee->getDescription(); // Simple Coffee, milk, whip, vanilla





