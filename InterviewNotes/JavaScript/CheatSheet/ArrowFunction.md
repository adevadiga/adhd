# Arrow Functions

* More concise
* `this` is picked up from surroundings
* implicit return

In an arrow function, `this` is equal to the `this` value of the enclosing execution context. What it means is that an arrow function doesn't create a new this, it grabs it from its surrounding instead.

Before arrow functions, every new function defined its own this value based on how the function was called:

* A new object in the case of a constructor.
* undefined in strict mode function calls.
* The base object if the function was called as an "object method".
  etc.


An arrow function does not have its own this. The this value of the enclosing lexical scope is used; arrow functions follow the normal variable lookup rules. So while searching for this which is not present in current scope, an arrow function ends up finding the this from its enclosing scope.

Thus, in the following code, the this within the function that is passed to setInterval has the same value as the this in the lexically enclosing function:
```
function Person(){
  this.age = 0;

  setInterval(() => {
    this.age++; // |this| properly refers to the Person object
  }, 1000);
}

var p = new Person();
```

_Relation with strict mode_
Given that this comes from the surrounding lexical context, strict mode rules with regard to this are ignored.

_Invoked through call or apply_
Since arrow functions do not have their own this, the methods call() and apply() can only pass in parameters. Any this argument is ignored.

```
var adder = {
  base: 1,

  add: function(a) {
    var f = v => v + this.base;
    return f(a);
  },

  addThruCall: function(a) {
    var f = v => v + this.base;
    var b = {
      base: 2
    };

    return f.call(b, a);
  }
};

console.log(adder.add(1));         // This would log 2
console.log(adder.addThruCall(1)); // This would log 2 still
```

_No binding of arguments_

Arrow functions do not have their own arguments object. Thus, in this example, arguments is simply a reference to the arguments of the enclosing scope:
```
var arguments = [1, 2, 3];
var arr = () => arguments[0];

arr(); // 1

function foo(n) {
  var f = () => arguments[0] + n; // foo's implicit arguments binding. arguments[0] is n
  return f();
}

foo(3); // 6

```

_Arrow functions used as methods_

As stated previously, arrow function expressions are best suited for non-method functions. Let's see what happens when we try to use them as methods:
```
'use strict';

var obj = {
  i: 10,
  b: () => console.log(this.i, this),
  c: function() {
    console.log(this.i, this);
  }
}

obj.b(); // prints undefined, Window {...} (or the global object)
obj.c(); // prints 10, Object {...}
```

_Use of the new operator_

Arrow functions cannot be used as constructors and will throw an error when used with new.
```

var Foo = () => {};
var foo = new Foo(); // TypeError: Foo is not a constructor
```

_Use of prototype property_

Arrow functions do not have a prototype property.

```
var Foo = () => {};
console.log(Foo.prototype); // undefined

```

_Use of the yield keyword_
The yield keyword may not be used in an arrow function's body (except when permitted within functions further nested within it). As a consequence, arrow functions cannot be used as generators.


