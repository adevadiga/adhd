The delete operator removes a property from an object. It cannot remove a variable. So the answer to the question depends on how the global variable or property is defined.

(1) If it is created with var, it cannot be deleted.

For example:

var g_a = 1; //create with var, g_a is a variable 
delete g_a; //return false
console.log(g_a); //g_a is still 1
(2) If it is created without var, it can be deleted.

g_b = 1; //create without var, g_b is a property 
delete g_b; //return true
console.log(g_b); //error, g_b is not defined
Technical Explanation
1. Using var
In this case the reference g_a is created in what the ECMAScript spec calls "VariableEnvironment" that is attached to the current scope - this may be the a function execution context in the case of using var inside a function (though it may be get a little more complicated when you consider let) or in the case of "global" code the VariableEnvironment is attached to the global object (often window).

References in the VariableEnvironment are not normally deletable - the process detailed in ECMAScript 10.5 explains this in detail, but suffice it to say that unless your code is executed in an eval context (which most browser-based development consoles use), then variables declared with var cannot be deleted.

2. Without Using var
When trying to assign a value to a name without using the var keyword, Javascript tries to locate the named reference in what the ECMAScript spec calls "LexicalEnvironment", and the main difference is that LexicalEvironments are nested - that is a LexicalEnvironment has a parent (what the ECMAScript spec calls "outer environment reference") and when Javscript fails to locate the reference in a LexicalEenvironment, it looks in the parent LexicalEnvironment (as detailed in 10.3.1 and 10.2.2.1). The top level LexicalEnvironment is the "global environment", and that is bound to the global object in that its references are the global object's properties. So if you try to access a name that was not declared using a var keyword in the current scope or any outer scopes, Javascript will eventually fetch a property of the window object to serve as that reference. As we've learned before, properties on objects can be deleted.

Notes
It is important to remember that var declarations are "hoisted" - i.e. they are always considered to have happened in the beginning of the scope that they are in - though not the value initialization that may be done in a var statement - that is left where it is. So in the following code, a is a reference from the VariableEnvironment and not the window property and its value will be 10 at the end of the code:

function test() { a = 5; var a = 10; }

The above discussion is when "strict mode" is not enabled. Lookup rules are a bit different when using "strict mode" and lexical references that would have resolved to window properties without "strict mode" will raise "undeclared variable" errors under "strict mode". I didn't really understand where this is specified, but its how browsers behave.