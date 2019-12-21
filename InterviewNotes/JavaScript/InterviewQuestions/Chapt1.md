## Functions
```
console.log(square(5));
/* ... */
function square(n) { return n * n; }
```

prints 25

```
console.log(square(5));
 
var square = function(n) { 
  return n * n; 
}
```
TypeError: square is not a function
In JavaScript, if you define a function as a variable, the variable name will be hoisted but you cannot access until JS execution encounters its definition.


##Understand bind, apply and call

Basically, these are the prototype methods of functions to alter behavior to achieve something.

Use .bind() when you want that function to later be called with a certain context, useful in events. 

Use .call() or .apply() when you want to invoke the function immediately, with modification of the context.


var cylinder = {
    pi: 3.14,
    volume: function(r, h) {
        return this.pi * r * r * h;
    }
};

cylinder.volume.call({pi: 3.14159}, 2, 6);

Did you see those function arguments are passed as subsequent arguments after context object?
Apply is exactly same except Function arguments are passed as a list for god’s sake.

cylinder.volume.apply({pi: 3.14159}, [2, 6]);


Bind attaches a brand new this to a given function. In bind’s case, the function is not executed instantly like Call or Apply.


var newVolume = cylinder.volume.bind({pi: 3.14159}); // This is not instant call
// After some long time, somewhere in the wild 
newVolume(2,6); // Now pi is 3.14159

What is the use of Bind? It allows us to inject a context into a function which returns a new function with updated context.
It means this variable will be user supplied variable. This is very useful while working with JavaScript events.


## Understand JavaScript scope well

1. Global scope
2. Local Scope/Function scope
3. Block scope(Introduced in ES6)

## Understand this keyword well

## Understand objects well 

Object.seal is slightly different from the freeze. It allows configurable properties but won’t allow new property addition or deletion or properties.


## Understand Prototypical Inheritance well

> In JS, call function and prototype object provides inheritance

These four things you should remember about prototypical inheritance.

* Class properties are bound using this
* Class methods are bound using prototype object
* To inherit properties, use call function passing this object
* To inherit methods, use Object.create to link prototypes of parent and child
* Always set child class constructor to itself for getting the right identity of its objects

```
function Animal(name, type) {
  this.name = name;
  this.type = type;
}

Animal.prototype.shout = function() {
    console.log(this.name + 'is ' + this.sound + 'ing...');
}

function Dog(name, type) {
   Animal.call(this, name, type);
   this.sound = "bow";
}

Dog.prototype = Object.create(Animal.prototype);

Dog.prototype.constructor = Dog;

```

Constructor fix needs to be done due to.
pet.constructor; // returns Animal
Dog.prototype.constructor; // returns Animal


## Understand the callbacks and promises well

## Understand Error handling patterns

## Other things to know (Hoisting, Event Bubbling)
a JavaScript VM does two things while running a program:
1. First scan the program, collect all the variable and function declarations and assign memory spaces for it.
2. Run the program now by filling variable values assigned any, if not, fill undefined

## Event Bubbling
“Event bubbling and capturing are two ways of event propagation in the HTML DOM API when an event occurs in an element inside another element, and both elements have registered a handler for that event.
The event propagation mode determines in which order the elements receive the event.”

With bubbling, the event is first captured and handled by the innermost element and then propagated to outer elements. With capturing, the process is in reverse.

`addEventListener("click", handler, useCapture=false)`

The third argument useCapture is the key. The default value is false. So, it will be a bubbling model where the event is handled by the innermost element first and it propagates outwards till it reaches the parent element. If that argument is true, it is capturing model.
