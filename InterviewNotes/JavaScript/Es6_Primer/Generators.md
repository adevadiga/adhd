# Generators

Generators are functions which can be exited and later re-entered. Their context (variable bindings) will be saved across re-entrances.

Generators in JavaScript -- especially when combined with Promises -- are a very powerful tool for asynchronous programming as they mitigate -- if not entirely eliminate -- the problems with callbacks.

Calling generator function doesn't execute its body immediately, an `iterator object` is returned instead.

When the iterator's next() method is called, the generator function's body is executed until the first yield expression, which specifies the value to be returned from the iterator or, with yield*, delegates to another generator function.

The next() method returns an object with a value property containing the yielded value and a done property which indicates whether the generator has yielded its last value, as a boolean. 

Calling the next() method with an argument will resume the generator function execution, replacing the yield expression where execution was paused with the argument from next().

A return statement in a generator, when executed, will make the generator finish.
If a value is returned, it will be set as the value property of the object returned by the generator.

Much like a return statement, an error thrown inside the generator will make the generator finished -- unless caught within the generator's body.

When a generator is finished, subsequent next calls will not execute any of that generator's code, they will just return an object of this form: `{value: undefined, done: true}`.

```
function* idMaker() {
    var index = 0;
    while (index < index+1)
        yield index++;
}

var gen = idMaker();

console.log(gen.next().value); // 0
console.log(gen.next().value); // 1 


function* anotherGenerator(i) {
  yield i + 1;
  yield i + 2;
  yield i + 3;
}

function* generator(i) {
  yield i;
  yield* anotherGenerator(i); //Invoke another generator
  yield i + 10;
}

var gen = generator(10);

console.log(gen.next().value); // 10
console.log(gen.next().value); // 1
```

## Return statement in a generator

```
function* yieldAndReturn() {
  yield "Y";
  return "R";
  yield "unreachable";
}

var gen = yieldAndReturn()
console.log(gen.next()); // { value: "Y", done: false }
console.log(gen.next()); // { value: "R", done: true }
console.log(gen.next()); // { value: undefined, done: true }
```

## Generator as an object property

```
const someObj = {
  *generator () {
    yield 'a';
    yield 'b';
  }
}

const gen = someObj.generator()

console.log(gen.next()); // { value: 'a', done: false }
console.log(gen.next()); // { value: 'b', done: false }
console.log(gen.next()); // { value: undefined, done: true }
```

## Generator as an object method

```
class Foo {
    *generator() {
        yield 1;
        yield 2;
    }
}

const f = new Foo ();
const gen = f.generator();
console.log(gen.next()); // { value: 1, done: false }
```

## Generator as a computed property

class Foo {
    *[Symbol.iterator]() {
        yield 1;
        yield 2;
    }
}

const someObj = {
    *[Symbol.iterator]() {
        yield 1;
        yield 2;
    }
}

console.log(Array.from(new Foo)); // [ 1, 2 ]
console.log(Array.from(SomeObj)); // [ 'a', 'b' ]

## Generators are not constructable
```
function* f() {}
var obj = new f; // throws "TypeError: f is not a constructor
Generator defined in an expression
```

## Generator defined in an expression

```
const foo = function* () {
  yield 10;
  yield 20;
};

const bar = foo();
console.log(bar.next()); // {value: 10, done: false}

```

##  Roles played by generators 

1. Iterators (data producers): Each yield can return a value via next(), which means that generators can produce sequences of values via loops and recursion. Due to generator objects implementing the interface Iterable, these sequences can be processed by any ECMAScript 6 construct that supports iterables. Two examples are: for-of loops and the spread operator (...).
   
2. Observers (data consumers): yield can also receive a value from next() (via a parameter). That means that generators become data consumers that pause until a new value is pushed into them via next().
   
3. Coroutines (data producers and consumers): Given that generators are pausable and can be both data producers and data consumers, not much work is needed to turn them into coroutines (cooperatively multitasked tasks).