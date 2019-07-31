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