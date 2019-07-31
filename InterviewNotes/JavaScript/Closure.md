    function outer() {
        var a = 10;
        var sum;
        
        // Nested function definition
        function inner() {
            var b = 20;
            
            // Nested function can access outer function's variables and scope
            sum = a + b;
        }
        
        inner();
        console.log(sum);//prints 30
    }

    outer();




    function getNewLife(karmaScore) {
        var message = "My karma score is: " + karmaScore;
        
        // This is function nesting - declaring a function inside a function.
        function nextLife() {
            // Please notice the usage of "message" variable
            alert(message);
        }
        
        // We can return a function from another function
        return nextLife;
    }
    // myNextLife is a function now.
    var myNextLife = getNewLife(90);
    
    // alerts "My karma score is: 90".
    myNextLife();
    
    // myOtherLife is another function now.
    var myOtherLife = getNewLife(95);
    
    // alerts "My karma score is: 95".
    myOtherLife();

Ideally if there were no closures in JavaScript, accessing message in the inner function 
should have thrown a ReferenceError as it doesn’t exist in that function’s scope 
anymore or anywhere else in the global scope.


    <b>A closure is a name given to a feature in the language by which a nested function executed after the execution of the outer function can still access outer function’s scope.</b>

    For a closure to be observed, we somehow need to have a reference to an inner nested function defined in an outer function.
    The inner function can still access the scope of outer function even after the outer function has finished executing, through the closure mechanism.


=================================
What is a “closure” in JavaScript? 
    a closure is function within a parent function that has access to global, parent, and personal variables.

    let globalVar = 1;
    parent = () => {
        inner = 2 * globalVar;
        child = () => {
             innerInner = 3 * inner;
             console.log(innerInner);
        }
    }
     //output 6

     console.log(mul(2)(3)(4)); // output : 24

     function mul (a) {
        return function (b) {
            return function (c) {
                return a * b * c;
            };
        };
    }