Builder:
    In plain words
        Allows you to create different flavors of an object while avoiding constructor pollution. Useful when there could be several flavors of an object. Or when there are a lot of steps involved in creation of an object.

    Real world example
        Imagine you are at Hardee's and you order a specific deal, lets say, "Big Hardee" and they hand it over to you without any questions; this is the example of simple factory. But there are cases when the creation logic might involve more steps. For example you want a customized Subway deal, you have several options in how your burger is made e.g what bread do you want? what types of sauces would you like? What cheese would you want? etc. In such cases builder pattern comes to the rescue.

    telescoping constructor anti-pattern:
        public function Foo($size, $cheese = true, $pepperoni = true, $tomato = false, $lettuce = true)

    Programmatic Example
        The sane alternative is to use the builder pattern. First of all we have our burger that we want to make

            class Burger {
                protected $size;

                protected $cheese = false;
                protected $pepperoni = false;
                protected $lettuce = false;
                protected $tomato = false;

                public function __construct(BurgerBuilder $builder)
                {
                    $this->size = $builder->size;
                    $this->cheese = $builder->cheese;
                    $this->pepperoni = $builder->pepperoni;
                    $this->lettuce = $builder->lettuce;
                    $this->tomato = $builder->tomato;
                }
            }

        And then we have the builder

            class BurgerBuilder {
                public $size;

                public $cheese = false;
                public $pepperoni = false;
                public $lettuce = false;
                public $tomato = false;

                public function __construct(int $size)
                {
                    $this->size = $size;
                }

                public function addPepperoni()
                {
                    $this->pepperoni = true;
                    return $this;
                }

                public function addLettuce()
                {
                    $this->lettuce = true;
                    return $this;
                }

                public function addCheese()
                {
                    $this->cheese = true;
                    return $this;
                }

                public function addTomato()
                {
                    $this->tomato = true;
                    return $this;
                }

                public function build(): Burger
                {
                    return new Burger($this);
                }
            }

        And then it can be used as:
            $burger = (new BurgerBuilder(14))
                    ->addPepperoni()
                    ->addLettuce()
                    ->addTomato()
                    ->build();

    When to use?
        When there could be several flavors of an object and to avoid the constructor telescoping. The key difference from the factory pattern is that; factory pattern is to be used when the creation is a one step process while builder pattern is to be used when the creation is a multi step process.













