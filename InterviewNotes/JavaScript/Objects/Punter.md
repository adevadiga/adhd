    function Animal (name, energy) {
        let animal = {}
        animal.name = name
        animal.energy = energy

        animal.eat = function (amount) {
            console.log(`${this.name} is eating.`)
            this.energy += amount
        }

        animal.sleep = function (length) {
            console.log(`${this.name} is sleeping.`)
            this.energy += length
        }
        return animal;
    }

    const leo = Animal('Leo', 7)
    const snoop = Animal('Snoop', 10)

We end up re-creating the method greet every time we instantiate Animal.
What if instead of re-creating those methods every time we create a new Animal, we move them to their own object then we can have each Animal reference that object?

    Every function in JavaScript has a prototype property that references an object. 
    Again, prototype is just a property that every function in JavaScript has and, it allows us to share methods across all instances of a function.

With these things we can do following to share methods across instances.

    function Animal (name, energy) {
        _let animal = Object.create(Animal.prototype)_
        animal.name = name
        animal.energy = energy

        _return animal_
    }

    Animal.prototype.eat = function (amount) {
        console.log(`${this.name} is eating.`)
        this.energy += amount
    }

    Animal.prototype.sleep = function (length) {
        console.log(`${this.name} is sleeping.`)
        this.energy += length
    }

    Animal.prototype.play = function (length) {
        console.log(`${this.name} is playing.`)
        this.energy -= length
    }

Instead of doing the above by-hand, you can use **new** keyword.

Here’s the cool thing about **new** - when you invoke a function using the new keyword, those two lines are done for you implicitly (“under the hood”) and the object that is created is called **this**.

Using comments to show what happens under the hood and assuming the Animal constructor is called with the new keyword, it can be re-written as this.

    ```
    function Animal (name, energy) {
        // const this = Object.create(Animal.prototype)

        this.name = name
        this.energy = energy

        // return this
    }

    ```

Without new:
```
function Animal (name, energy) {
    this.name = name
    this.energy = energy
}

const leo = Animal('Leo', 7)
console.log(leo) // undefined
```

Note that global object will have two property now - `name, animal`


Adding static method: Instead of adding to prototype just add to type
```
Animal.nextToEat = function (nextToEat) {
  const sortedByLeastEnergy = animals.sort((a,b) => {
    return a.energy - b.energy
  })

  return sortedByLeastEnergy[0].name
}

console.log(Animal.nextToEat([leo, snoop])) // Leo
```

By default, the prototype object will have a constructor property which points to the original function or the class that the instance was created from. 
any instances will be able to access their constructor via instance.constructor.

> You may have seen __proto__ used before to get an instances’ prototype. That’s a relic of the past. Instead, use Object.getPrototypeOf(instance) as we saw above.

Well a for in loop is going to loop over all of the enumerable properties on both the object itself as well as the prototype it delegates to.
This is where hasOwnProperty can help us out.

Note that for..in doesn't go up the inheritance chain.

```
const leo = new Animal('Leo', 7)

for(let key in leo) {
  if (leo.hasOwnProperty(key)) {
    console.log(`Key: ${key}. Value: ${leo[key]}`)
  }
}

```

**instanceof**:
The way that instanceof works is it checks for the presence of constructor.prototype in the object’s prototype chain.


Force our constructor to call using new and not just as function
```
function Animal (name, energy) {
  if (this instanceof Animal === false) {
    console.warn('Forgot to call Animal with the new keyword')
  }

  this.name = name
  this.energy = energy
}
```


**Re-creating Object.create**
1. It takes in an argument that is an object.
2. It creates an object that delegates to the argument object on failed lookups.
3. It returns the new created object.

Object.create = function (objToDelegateTo) {
    function Fn(){}
    Fn.prototype = objToDelegateTo;
    return new Fn();
};

**Arrow Functions**
Arrow functions don’t have their own _this_ keyword. As a result, arrow functions can’t be constructor functions and if you try to invoke an arrow function with the new keyword, it’ll throw an error.

```
const Animal = () => {}
const leo = new Animal() // Error: Animal is not a constructor
```

arrow functions also don’t have a prototype property.


**Classical Inheritance tree**
```
function Animal (name, energy) {
  this.name = name
  this.energy = energy
}

Animal.prototype.eat = function (amount) {
  console.log(`${this.name} is eating.`)
  this.energy += amount
}

Animal.prototype.sleep = function (length) {
  console.log(`${this.name} is sleeping.`)
  this.energy += length
}

Animal.prototype.play = function (length) {
  console.log(`${this.name} is playing.`)
  this.energy -= length
}
```

function Dog(name, energy, breed) {
    Animal.call(this, name, energy);
    this.breed = breed;
}

Now what happens is Dog will have all properties of Animal, due to `Animal.call(this, name, energy);` but not methods of Animal.

To solve this, we can use Object.create

```
function Dog (name, energy, breed) {
  Animal.call(this, name, energy)

  this.breed = breed
}

Dog.prototype = Object.create(Animal.prototype)
```
> Note:
> Without the above line, `Dog.prototype = Object.create(Animal.prototype)`, if we print 
> Dog.prototype, its
>     {
>         constructor: ƒ Dog(name, energy, breed),
>         __proto__: Object
>     }
> But if we do this, `Dog.prototype = Object.create(Animal.prototype)`, then Dog.prototype will be
> Animal {
>   __proto__:
        eat: ƒ (amount)
        play: ƒ (length)
        sleep: ƒ (length)
        constructor: ƒ Animal(name, energy)
    __proto__: Object
> }


Now, whenever there’s a failed lookup on an instance of Dog, JavaScript will delegate that lookup to Animal.prototype

To summarize, we’ve created our base class (Animal) as well as our subclass (Dog), let’s see what it looks like under the hood when we create an instance of Dog.

```
const charlie = new Dog('Charlie', 10, 'Goldendoodle')

charlie.name // Charlie
charlie.energy // 10
charlie.breed // Goldendoodle
```
Nothing fancy so far, but let’s look at what happens when we invoke a method located on Animal.

```
charlie.eat(10)

/*
1) JavaScript checks if charlie has an eat property - it doesn't.
2) JavaScript then checks if Dog.prototype has an eat property
    - it doesn't.
3) JavaScript then checks if Animal.prototype has an eat property
    - it does so it calls it.
*/

```

To fix we overrding Dog's prototyp - no constructor avaliable since prototype is been oveeridden

Dog.prototype = Object.create(Animal.prototype)

Dog.prototype.bark = function () {
  console.log('Woof Woof!')
  this.energy -= .1
}

Dog.prototype.constructor = Dog