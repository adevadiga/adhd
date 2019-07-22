💡 Strategy

    In plain words
        Strategy pattern allows you to switch the algorithm or strategy based upon the situation.

    Wikipedia says
        In computer programming, the strategy pattern (also known as the policy pattern) is a behavioural software design pattern that enables an <b>algorithm's behavior to be selected at runtime</b>.

    Real world example
        Consider the example of sorting, we implemented bubble sort but the data started to grow and bubble sort started getting very slow. In order to tackle this we implemented Quick sort. But now although the quick sort algorithm was doing better for large datasets, it was very slow for smaller datasets. In order to handle this we implemented a strategy where for small datasets, bubble sort will be used and for larger, quick sort.

    Programmatic example

        Translating our example from above. First of all we have our strategy interface and different strategy implementations

            interface SortStrategy
            {
                public function sort(array $dataset): array;
            }

            class BubbleSortStrategy implements SortStrategy
            {
                public function sort(array $dataset): array
                {
                    echo "Sorting using bubble sort";

                    // Do sorting
                    return $dataset;
                }
            }

            class QuickSortStrategy implements SortStrategy
            {
                public function sort(array $dataset): array
                {
                    echo "Sorting using quick sort";

                    // Do sorting
                    return $dataset;
                }
            }

        And then we have our client that is going to use any strategy

            class Sorter
            {
                protected $sorter;

                public function __construct(SortStrategy $sorter)
                {
                    $this->sorter = $sorter;
                }

                public function sort(array $dataset): array
                {
                    return $this->sorter->sort($dataset);
                }
            }

        And it can be used as

            $dataset = [1, 5, 4, 3, 2, 8];

            $sorter = new Sorter(new BubbleSortStrategy());
            $sorter->sort($dataset); // Output : Sorting using bubble sort

            $sorter = new Sorter(new QuickSortStrategy());
            $sorter->sort($dataset); // Output : Sorting using quick sort


    Strategy Pattern: Problems It Solves
        Strategy Pattern prevents hard-wiring of all the algorithms into the program. This makes our program complex and much more bogus and hard to refactor/maintain and understand.

                class BasicAuth {}
                class DigestAuth {}
                class OpenIDAuth {}
                class OAuth {}

                class AuthProgram {
                    runProgram(authStrategy:any, ...) {
                        this.authenticate(authStrategy)
                        // ...
                    }
                    authenticate(authStrategy:any) {
                        switch(authStrategy) {
                            if(authStrategy == "basic")
                                useBasic()
                            if(authStrategy == "digest")
                                useDigest()
                            if(authStrategy == "openid")
                                useOpenID()
                            if(authStrategy == "oauth")
                                useOAuth()
                        }
                    }
                }

                Instead of switch, let AuthProgram contains instance of AuthStrategy -  which is externally injected.
                
                class AuthProgram {
                    private _strategy: AuthStrategy
                    
                    authenticate() {
                        if(this._strategy == null) {
                            log("No Authentication Strategy set.")
                        }
                        this._strategy.auth()
                    }
                }


Refactor below classes:

        class Document {...}
        class Printer {
            print(doc: Document, printStyle: Number) {
                if(printStyle == 0 /* color printing*/) {
                    // ...
                }
                if(printStyle == 1 /* black and white printing*/) {
                    // ...            
                }
                if(printStyle == 2 /* sepia color printing*/) {
                    // ...
                }
                if(printStyle == 3 /* hue color printing*/) {
                    // ...            
                }
                if(printStyle == 4 /* oil printing*/) {
                    // ...
                }
                // ...
            }
        }

    Refactor using Strategy:

        class Document {...}

        interface PrintingStrategy {
            void print();
        }

        class ColorPrintingStrategy implements PrintingStrategy {
            void print() {
                ...
            }
        }

        class InvertedColorPrintingStrategy implements PrintingStrategy {
            void print() {
                ...
            }
        }

        class Printer {
            PrintingStrategy printingStrategy;

            void print() {
                printingStrategy.print();
            }
        }

    Use:

        Printer printer = new Printer(new ColorPrintingStrategy());
        printer.print();

    In Strategy Pattern, composition is used over inheritance.


Strategy Pattern: When To Use
    Strategy Pattern should be used when you begin to notice recurring algorithms but in different variations.
    This way, you need to separate the algorithms into classes and feed them based on want in your program.

    Next, if you notice recurring conditional statements around a related algorithm.

    When most of your classes have related behaviors. It will be time to move them into classes.


https://blog.bitsrc.io/keep-it-simple-with-the-strategy-design-pattern-c36a14c985e9























