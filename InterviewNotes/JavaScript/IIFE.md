IIFEs - Immediately Invoked Function Expressions

    (function () {
        // logic here
    })();

In JavaScript functions can be created either through a function declaration or a function expression. 

A function declaration is the "normal" way of creating a named function.

    // Named function declaration
    function myFunction () { /* logic here */ }

On the other hand, if you are assigning a function to a variable or property, you are dealing with a function expression.

    // Assignment of a function expression to a variable
    var myFunction = function () { /* logic here */ };

    // Assignment of a function expression to a property
    var myObj = {
        myFunction: function () { /* logic here */ }
    };

A function created in the context of an expression is also a function expression. For example:

    // Anything within the parentheses is part of an expression
    (function () { /* logic here */ });

    // Anything after the not operator is part of an expression
    !function () { /* logic here */ };

The key thing about JavaScript expressions is that they return values. In both cases above the return value of the expression is the function.


That means that if we want to invoke the function expression right away we just need to tackle a couple of parentheses on the end. Which brings us back to the first bit of code we looked at.

    (function () {
        // logic here
    })();

The primary reason to use an IIFE is to obtain data privacy. Because JavaScript's var scopes variables to their containing function, any variables declared within the IIFE cannot be accessed by the outside world.

    (function () {
        var foo = "bar";

        // Outputs: "bar"
        console.log(foo);
    })();

    // ReferenceError: foo is not defined
    console.log(foo);

Of course, you could explicitly name and then invoke a function to achieve the same ends.

    function myImmediateFunction () {
        var foo = "bar";

        // Outputs: "bar"
        console.log(foo);
    }

    myImmediateFunction();

    // ReferenceError: foo is not defined
    console.log(foo);

However, this approach has a few downsides. First, it unnecessarily takes up a name in the global namespace, increasing the possibility of name collisions. Second, the intentions of this code aren't as self-documenting as an IIFE. And third, because it is named and isn't self-documenting it might accidentally be invoked more than once.

Passing arguments
    var foo = "foo";
    (function(innerFoo) {
         // Outputs: "foo"
         console.log(innerFoo);
    })(foo);

That’s a function that died immediately after it came to life.

IIFE can also be written using ! instead of (). Since both gives us a expression

    !function(foo) {
         // Outputs: "anoop"
         console.log(foo);
    }("anoop");
The above stylistic variation can be used by replacing “!” with “+”, “-”, or even “~” as well. Basically any unary operator can be used.
All that the first character, “!”, is doing here is to make that function into an expression instead of a function statement/definition. And then we execute that function immediately.

Another quick variation on this is shown below:
    void function() {
        alert("Hello from IIFE!");
    }();





var fibo = function fibonacci() {
    // you can use "fibonacci()" here as this function expression has a name.
};

fibonacci(); //Doesn't work -  fibonacci is not defined. use fibo()



Part II:
    what if you wanted a return value from the IIFE and you want to use that return value elsewhere

    // Variation 1
    (function() {
        alert("I am an IIFE!");
    }()); //Note ())

    // Variation 2
    (function() {
        alert("I am an IIFE, too!");
    })(); ////Note )();


        // Valid IIFE
        (function initGameIIFE() {
            // All your magical code to initalize the game!
        }());

        // Following two are invalid IIFE examples
        function nonWorkingIIFE() {
            // Now you know why you need those parentheses around me!
            // Without those parentheses, I am a function definition, not an expression.
            // You will get a syntax error!
        }();

        function () {
            // You will get a syntax error here as well!
        }();



Return result:
    var result = (function() {
        return "From IIFE";
    }());

    alert(result); // alerts "From IIFE"



=====================================

IIFE to create a private closure scope.

    var Sequence = (function sequenceIIFE() {
    //var Sequence = (function sequenceIIFE() { - both valid
        
        // Private variable to store current counter value.
        var current = 0;
        
        // Object that's returned from the IIFE.
        return {
            getCurrentValue: function() {
                return current;
            },
            
            getNextValue: function() {
                current = current + 1;
                return current;
            }
        };
        
    }());

    console.log(Sequence.getNextValue()); // 1
    console.log(Sequence.getNextValue()); // 2
    console.log(Sequence.getCurrentValue()); // 2


Note paranthesis may not be needed if JS engine can interpret function as expression

    var result = function() {
        return "From IIFE!";
    }();