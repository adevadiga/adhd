The value null represents the intentional absence of any object value. It is one of JavaScript's primitive values.

 undefined, which is a value of type undefined that indicates an uninitialized variable — that is, a value hasn't even been assigned yet. 
 but in JavaScript it is possible to declare a variable without assigning a value to it. If you do this, the variable's type is undefined. undefined is actually a constant.

let allows you to declare block-level variables. The declared variable is available from the block it is enclosed in.

===>
    In JavaScript, blocks do not have scope; only functions have a scope.
    So if a variable is defined using var in a compound statement (for example inside an if control structure), it will be visible to the entire function.
    let and const declarations allow you to create block-scoped variables.
    
==>
    Loops:
        1. Traditional
        2. for...of  = for (let value of array) 
        3. for...in     = for (let property in object) {

===>
    Note that array.length isn't necessarily the number of items in the array. Consider the following:

        var a = ['dog', 'cat', 'hen'];
        a[100] = 'fox';
        a.length; // 101
    Remember — the length of the array is one more than the highest index.

    You could also iterate over an array using a for...in loop, however this does not iterate over the array elements, but the array indices. Furthermore, if someone added new properties to Array.prototype, they would also be iterated over by such a loop. Therefore this loop type is not recommended for arrays.


    var a = ['dog', 'cat', 'hen'];
    a["peacock"] = 'fox';
    a[10] = 'fox2';

    for (let elem in a) {
        console.log(elem);
    }
    //0 1 2 10 peacock

    for (let elem of a) {
        console.log(elem);
    }
    //dog, cat, hen, undefined, undefined, undefined ,undefined,undefined, undefined,undefined, fox2



==>
    This highlights a potential problem with anonymous functions: how do you call them recursively if they don't have a name? JavaScript lets you name function expressions for this. You can use named IIFEs (Immediately Invoked Function Expressions) as shown below:

        var charsInBody = (function counter(elm) {  //Notice name = counter
            if (elm.nodeType == 3) { // TEXT_NODE
                return elm.nodeValue.length;
            }
            var count = 0;
            for (var i = 0, child; child = elm.childNodes[i]; i++) {
                count += counter(child);
            }
            return count;
        })(document.body);


===>

NEW:

    We have introduced another keyword: new. new is strongly related to this. It creates a brand new empty object, and then calls the function specified, with this set to that new object. 



