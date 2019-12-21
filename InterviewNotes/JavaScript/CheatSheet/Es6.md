# ES6 features

## Destructuring objects and arrays

Destructuring is a convenient way of creating new variables by extracting some values from data stored in objects or arrays.

```
const person = {
  firstName: "Nick",
  lastName: "Anderson",
  age: 35,
  sex: "M"
};

const { firstName: first, age, city = "Paris" } = person; // That's it !
```

## Spread operator "..."

The spread operator `...` has been introduced with ES2015 and is used to expand elements of an iterable (like an array) into places where multiple elements can fit.

```
const arr1 = ["a", "b", "c"];
const arr2 = [...arr1, "d", "e", "f"]; // ["a", "b", "c", "d", "e", "f"]

function myFunc(x, y, ...params) {
  console.log(x);
  console.log(y);
  console.log(params); //array
}

myFunc("a", "b", "c", "d", "e", "f")


const { x, y, ...z } = { x: 1, y: 2, a: 3, b: 4 };
console.log(x); // 1
console.log(y); // 2
console.log(z); // { a: 3, b: 4 }

const n = { x, y, ...z };
console.log(n); // { x: 1, y: 2, a: 3, b: 4 }



function createStudent(firstName, lastName, ...grades) {
  // firstName = "Nick"
  // lastName = "Anderson"
  // [10, 12, 6] -- "..." takes all other parameters passed and creates a "grades" array variable that contains them

  const avgGrade = grades.reduce((acc, curr) => acc + curr, 0) / grades.length; // computes average grade from grades

  return {
    firstName: firstName,
    lastName: lastName,
    grades: grades,
    avgGrade: avgGrade
  }
}

const student = createStudent("Nick", "Anderson", 10, 12, 6);
console.log(student);
```


## Object property shorthand

When assigning a variable to an object property, if the variable name is equal to the property name, you can do the following:

```
const x = 10;
const myObj = { x };
console.log(myObj.x) // 10

```


## Promises

A promise is an object which can be returned synchronously from an asynchronous function (ref). Promises can be used to avoid callback hell.

Promises can have three different states:
    * Pending
    * Fulfilled
    * Rejected
  
```
const xFetcherPromise = new Promise(//Create promise using "new" keyword and store in a variable
  function(resolve, reject) { //Promise constructor takes a fn param which has resolve and reject parameters itself
    $.get("X") // Launch the Ajax request
      .done(function(X) { // Once the request is done...
        resolve(X); // ... resolve the promise with the X value as parameter
      })
      .fail(function(error) { // If the request has failed...
        reject(error); // ... reject the promise with the error as parameter
      });
  }
)

xFetcherPromise
  .then(function(X) {
    console.log(X);
  })
  .catch(function(err) {
    console.log(err)
  })


```

> If the promise has already been fulfilled or rejected when a corresponding handler is attached, the handler will be called, so there is no race condition between an asynchronous operation completing and its handlers being attached.


## Imports / Exports

ES6 modules are used to access variables or functions in a module explicitly exported by the modules it imports.

# Named exports

```
// mathConstants.js
export const pi = 3.14;
export const exp = 2.7;
export const alpha = 0.35;

// -------------

// myFile.js
import { pi, exp } from './mathConstants.js'; // Named import -- destructuring-like syntax
console.log(pi) // 3.14
console.log(exp) // 2.7

// -------------

// mySecondFile.js
import * as constants from './mathConstants.js'; // Inject all exported values into constants variable
console.log(constants.pi) // 3.14
console.log(constants.exp) // 2.7
```

While named imports looks like destructuring, they have a different syntax and are not the same. They don't support default values nor deep destructuring.

Besides, you can do aliases but the syntax is different from the one used in destructuring:

# Default import / export

Concerning the default export, there is only a single default export per module. A default export can be a function, a class, an object or anything else. This value is considered the "main" exported value since it will be the simplest to import. 

# Binding

```
var person = {
  myFunc: function() { ... }
}

person.myFunc.call(person, "test") // person Object -- first call parameter is injected into *this*
person.myFunc("test") // person Object -- person.myFunc() is syntax sugar for person.myFunc.call(person, "test")

var myBoundFunc = person.myFunc.bind("hello") // Creates a new function in which we inject "hello" in *this* value
person.myFunc("test") // person Object -- The bind method has no effect on the original method
myBoundFunc("test") // "hello" -- myBoundFunc is person.myFunc with "hello" bound to *this*
```