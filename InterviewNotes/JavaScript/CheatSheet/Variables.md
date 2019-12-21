# Scope and All fundas

## var hoisting

Because variable declarations (and declarations in general) are processed before any code is executed, declaring a variable anywhere in the code is equivalent to declaring it at the top.
This also means that a variable can appear to be used before it's declared.
This behavior is called "hoisting", as it appears that the variable declaration is moved to the top of the function or global code.

```
bla = 2;
var bla;

// ...is implicitly understood as:

var bla;
bla = 2;
```

For that reason, it is recommended to always declare variables at the top of their scope (the top of global code and the top of function code) so it's clear which variables are function scoped (local) and which are resolved on the scope chain.

It's important to point out that the hoisting will affect the variable declaration, but not its value's initialization. The value will be indeed assigned when the assignment statement is reached:

```
function do_something() {
  console.log(bar); // undefined
  var bar = 111;
  console.log(bar); // 111
}

// ...is implicitly understood as:

function do_something() {
  var bar;
  console.log(bar); // undefined
  bar = 111;
  console.log(bar); // 111
}

```

```
var x = 'outer scope';
(function() {
    console.log(x); //x is undefined due to hoisting of below x declaration.
    var x = 'inner scope';
}());
```

**Enter the Temporal Dead Zone**
console.log(x); // throws a ReferenceError, opposed undefined if using var
let x = 'hey';

As you can see, one of the main differences between the old var and the new let/const declarations (besides their scope) is that the latter are constrained by the Temporal Dead Zone semantics, meaning they will throw a ReferenceError when accessed (read/write) before being initialized, instead of returning undefined as a var-declared variable would. This makes the code more predictable and easier to spot potential bugs.


Taking a second look at the previous example, one could deduce that let/const declarations simply do not hoist, and that would explain the ReferenceError, right? Nope, that's an incorrect over-simplification (and beware of uninformed resources claiming that!).

But wait!
> The variables are created when their containing Lexical Environment is instantiated [...]

This means whenever control flow enters a new scope (e.g. module, function or block scope), all the `let/const` bindings belonging to the given scope are instatiated before any code inside of the given scope is executed -- in other words, `let/const `declarations hoist!

>  but may not be accessed in any way until the variable’s LexicalBinding is evaluated.

This is the TDZ. A given `let/const`-declared binding can't be acessed in any way (read/write) until control flow has evaluated the declaration statement -- that does not refer to the hoisting, but rather to where the declaration actually is in the code.

```
// Accessing `x` here before control flow evaluates the `let x` statement
// would throw a ReferenceError due to TDZ.
// console.log(x);

let x = 42;
// From here on, accessing `x` is perfectly fine!
console.log(x);
```

Tell me this

```
let a = f();
const b = 2;
function f() { return b; }
```

In the first line, the f() call makes control flow jump to and execute the f function, which in turn tries to read the b constant which, at this point in the runtime, is still uninitialized (in TDZ) and thus throws a ReferenceError. As you can see, TDZ semantics apply when trying to access variables from parent scopes as well.


```
// Works fine.
(function(a, b = a) {
    a === 1;
    b === 1;
}(1, undefined));

// Default parameters are evaluated from left to right,
// so `b` is in the TDZ when `a`'s initializer tries to read it.
(function(a = b, b) {}(undefined, 1)); // ReferenceError

// `a` is still in the TDZ when its own initializer tries to read `a`.
// See the "gory details" section above for more details.
(function(a = a) {}()); // ReferenceError
```

_Answer this_

```
let b = 1;
(function(a = b, b) {
    console.log(a, b);
}(undefined, 2));
```
The example above may look a bit confusing, but it is actually a TDZ violation too -- that is because default parameters are evaluated in an intermediate scope which exists between the parent and inner scope of the given function. The a and b parameters are bindings of this (intermediate) scope and are initialized from left to right, hence when a's initializer tries to read b, the b identifier resolves to the b binding in the current scope (the intermediate scope) which is uninitialized at that point and thus throws a ReferenceError due to the TDZ semantics.

As another example, subclasses (created with class x extends y {})'s constructors that try to access this before calling the super constructor will throw a TDZ ReferenceError. That is because as long as a subclass's constructor has not yet called super() its this binding is considered uninitialized. $$ANOOP$$

Likewise, if a subclass constructor execution reaches the end of the constructor code without calling super(), the constructor would (like any other constructor) implicitly try to return this;, which would then throw a TDZ ReferenceError as this is still uninitialized.

https://javascript.info/constructor-new

Reference: ES6 super construct proposal. (note, though, that this proposal is only two weeks old at the time of writing, so it may be changed or discarded altogether from the final ES2015 spec.)
