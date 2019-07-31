An object is a collection of related data and/or functionality (which usually consists of several variables and functions — which are called properties and methods when they are inside objects.)

ways to create object instances
    1. Declaring object literal
    2. Using constructor function
    3. The Object constructor
    4. Using the create() method
    

The Object constructor:
    var person1 = new Object();
    person1.name = 'Chris';
    person1['age'] = 38;
    person1.greeting = function() {
        alert('Hi! I\'m ' + this.name + '.');
    };

    You can also pass an object literal to the Object() constructor as a parameter, to prefill it with properties/methods. 

    var person1 = new Object({
        name: 'Chris',
        age: 38,
        greeting: function() {
            alert('Hi! I\'m ' + this.name + '.');
        }
    });

    var person2 = Object.create(person1);

