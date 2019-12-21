# ES6(ECMAScript 2015)

There are three major categories of features:
    * Classes
    * Modules

    * New methods for strings and Arrays
    * Promises
    * Maps, Sets

    * Generators
    * Proxies
    * WeakMaps

Additional: lexical block scoping, iterators and generators, destructuring patterns


## Small Features:

### Handling multiple return values 

```
    const [, year, month, day] =
        /^(\d\d\d\d)-(\d\d)-(\d\d)$/
        .exec('2999-12-31');


    {writable, configurable};

```

### From for to forEach() to for-of 

```
arr.forEach(function (elem) {
    console.log(elem);
});

for (const elem of arr) {
    console.log(elem);
}

//If you want both index and value of each array element, for-of has got you 
//covered, too, via the new Array method entries() and destructuring:

for (const [index, elem] of arr.entries()) {
    console.log(index+'. '+elem);
}
```

### Handling named parameters

```
function selectEntries({ start=0, end=-1, step=1 }) {
    ···
}
```

### Making the parameter optional 

```
function selectEntries({ start=0, end=-1, step=1 } = {}) {
    ···
}
```

### From arguments to rest parameters 
```
function logAllArguments() {
    for (var i=0; i < arguments.length; i++) {
        console.log(arguments[i]);
    }
}

function logAllArguments(...args) {
    for (const arg of args) {
        console.log(arg);
    }
}

Rest parameters are even nicer if you are only interested in trailing parameters:

function format(pattern, ...args) {
    ···
}
```


### From function expressions in object literals to method definitions

In JavaScript, methods are properties whose values are functions.

In ES5 object literals, methods are created like other properties. The property values are provided via function expressions.

```
var obj = {
    foo: function () {
        ···
    },
    bar: function () {
        this.foo();
    }, // trailing comma is legal in ES5
}

```
ES6 has method definitions, special syntax for creating methods:

```
const obj = {
    foo() {
        ···
    },
    bar() {
        this.foo();
    },
}
```


### Derived classes

```
function Person(name) {
    this.name = name;
}
Person.prototype.describe = function () {
    return 'Person called '+this.name;
};

function Employee(name, title) {
    Person.call(this, name);
    this.title = title;
}

Employee.prototype = Object.create(Person.prototype);
Employee.prototype.constructor = Employee;

Employee.prototype.describe = function () {
    return Person.prototype.describe.call(this) // super.describe()
           + ' (' + this.title + ')';
};

```

in Es6

```
class Person {
    constructor(name) {
        this.name = name;
    }

    describe() {
        return 'Person called '+this.name;
    }
}

class Employee extends Person {
    constructor(name, title) {
        super(name);
        this.title = title;
    }

    describe() {
        return super.describe() + ' (' + this.title + ')';
    }
}
```


### From objects to Maps
Using the language construct object as a map from strings to arbitrary values (a data structure) has always been a makeshift solution in JavaScript. 

The safest way to do so is by creating an object whose prototype is null. Then you still have to ensure that no key is ever the string '__proto__', because that property key triggers special functionality in many JavaScript engines.

```
var dict = Object.create(null);
function countWords(word) {
    var escapedWord = escapeKey(word);
    if (escapedWord in dict) {
        dict[escapedWord]++;
    } else {
        dict[escapedWord] = 1;
    }
}
function escapeKey(key) {
    if (key.indexOf('__proto__') === 0) {
        return key+'%';
    } else {
        return key;
    }
}

//ES6

const map = new Map();
function countWords(word) {
    const count = map.get(word) || 0;
    map.set(word, count + 1);
}

```

### From Array.prototype.slice() to Array.from() or the spread operator 

var arr1 = Array.prototype.slice.call(arguments); // ES5
const arr2 = Array.from(arguments); // ES6

const arr1 = [...'abc'];

###  Parameter handling

In ECMAScript 6, you can (ab)use default parameter values to achieve more concise code

/**
 * Called if a parameter is missing and
 * the default value is evaluated.
 */
function mandatory() {
    throw new Error('Missing parameter');
}
function foo(mustBeProvided = mandatory()) {
    return mustBeProvided;
}
