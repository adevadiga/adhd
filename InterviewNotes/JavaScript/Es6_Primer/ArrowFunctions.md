## Arrow functions.

1. They are less verbose than traditional function expressions.
2. Their `this` is picked up from surroundings (lexical). Therefore, you don’t need `bind()` or `that = this`, anymore.

The following variables are all lexical inside arrow functions:
   * arguments
   * super
   * this
   * new.target

### Traditional functions are bad non-method functions, due to `this` 
In JavaScript, traditional functions can be used as:

   1. Non-method functions
   2. Methods
   3. Constructors

Due to roles 2 and 3, functions always have their own this. 
But that prevents you from accessing the this of, e.g., a surrounding method from inside a callback (role 1).

You can see that in the following ES5 code:

```
function Prefixer(prefix) {
    this.prefix = prefix;
}
Prefixer.prototype.prefixArray = function (arr) { // (A)
    'use strict';
    return arr.map(function (x) { // (B)
        // Doesn’t work:
        return this.prefix + x; // (C)
    });
};

```

In line C, we’d like to access this.prefix, but can’t,
because the `this` of the function from line B shadows the `this` of the method from line A. 

In strict mode, `this` is undefined in non-method functions, which is why we get an error if we use Prefixer:

```
> var pre = new Prefixer('Hi ');
> pre.prefixArray(['Joe', 'Alex'])
TypeError: Cannot read property 'prefix' of undefined

```

Workaround for this in Es5

#### Solution 1: that = this 
```
Prefixer.prototype.prefixArray = function (arr) {
    var that = this; // (A)
    return arr.map(function (x) {
        return that.prefix + x;
    });
};

```

#### Solution 2: specifying a value for this 
```
Prefixer.prototype.prefixArray = function (arr) {
    return arr.map(function (x) {
        return that.prefix + x;
    }, this);
};

```

#### Solution 3: bind(this) 
You can use the method bind() to convert a function whose this is determined by how it is called (via call(), a function call, a method call, etc.) to a function whose this is always the same fixed value. 

```
Prefixer.prototype.prefixArray = function (arr) {
    return arr.map(function (x) {
        return this.prefix + x;
    }.bind(this)); // (A)
};
```

#### Es6 solution: arrow functions

```
Prefixer.prototype.prefixArray = function (arr) {
    return arr.map((x) => {
        return this.prefix + x;
    });
};

//Fully Es6

class Prefixer {
    constructor(prefix) {
        this.prefix = prefix;
    }
    prefixArray(arr) {
        return arr.map(x => this.prefix + x); // (A)
    }
}

```


### Distinguisher

The source of this is an important distinguishing aspect of arrow functions:

* Traditional functions have a `dynamic this`; its value is determined by how they are called.
* Arrow functions have a `lexical this`; its value is determined by the surrounding scope.

###  Syntax pitfalls

 > Arrow functions bind very loosely - operator precedence is low
 > No line break after arrow function parameters
```
    const func1 = (x, y) // SyntaxError
    => {
        return x + y;
    };

    const func2 = (x, y) => // OK
    {
        return x + y;
    };
    const func3 = (x, y) => { // OK
        return x + y;
    };
    const func4 = (x, y) // SyntaxError
    => x + y;
    const func5 = (x, y) => // OK
    x + y;

    const func6 = ( // OK
    x,
    y
    ) => {
        return x + y;
    };
```

#### Expression vs Statements

Expression -> produce(are evaluated to) values
Statement -> do things

If an expression is the body of an arrow function, you don’t need braces:
*asyncFunc.then(x => console.log(x));*

However, statements have to be put in braces:
*asyncFunc.catch(x => { throw x });*


#### Final comments
An arrow function is different from a normal function in only two ways:

* The following constructs are lexical: arguments, super, this, new.target
* It can’t be used as a constructor: Normal functions support new via the internal method [[Construct]] and the property prototype. Arrow functions have neither, which is why new (() => {}) throws an error.