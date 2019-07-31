Prototypes are the mechanism by which JavaScript objects inherit features from one another.
In this article, we explain how prototype chains work and look at how the prototype property can be used to add methods to existing constructors.

To create object you don't need prototype as we seen earlier.

Prototype-based language
    to provide inheritance, objects can have a prototype object, which acts as a template object that it inherits methods and properties from. An object's prototype object may also have a prototype object, which it inherits methods and properties from, and so on. This is often referred to as a prototype chain, and explains why different objects have properties and methods defined on other objects available to them.

    Well, to be exact, the properties and methods are defined on the prototype property on the Objects' constructor functions, not the object instances themselves.


    In JavaScript, a link is made between the object instance and its prototype (its __proto__ property, which is derived from the prototype property on the constructor), and the properties and methods are found by walking up the chain of prototypes.


    Note: It's important to understand that there is a distinction between an object's prototype (which is available via Object.getPrototypeOf(obj), or via the deprecated __proto__ property) and the prototype property on constructor functions. The former is the property on each instance, and the latter is the property on the constructor. That is, Object.getPrototypeOf(new Foobar()) refers to the same object as Foobar.prototype.



    function Person(name) {
        this.name = name;
        this.greet = function() {
            alert("Hello");   
        }
    }

    var p1 = new Person("anoop");

    p1.constructor -> constructor function

    p1.__proto__ IS the constructor function itself
    p1.__proto__.__proto__ IS the Object

    Object.getPrototypeOf(p1) => IS the constructor function itself


The prototype property: Where inherited members are defined
    Object has more property than whats inherited. 
    the inherited ones are the ones defined on the prototype property, and not the ones that begin with just Object. 
    The prototype property's value is an object, which is basically a bucket for storing properties and methods that we want to be inherited by objects further down the prototype chain.


    So Object.prototype.watch(), Object.prototype.valueOf(), etc., are available to any object types that inherit from Object.prototype, including new object instances created from the Person() constructor.

    Object.is(), Object.keys(), and other members not defined inside the prototype bucket are not inherited by object instances or object types that inherit from Object.prototype. They are methods/properties available just on the Object() constructor itself.


    Important: The prototype property is one of the most confusingly-named parts of JavaScript — you might think that this points to the prototype object of the current object, but it doesn't (that's an internal object that can be accessed by __proto__, remember?). prototype instead is a property containing an object on which you define members that you want to be inherited.

    Every constructor function has a prototype property whose value is an object containing a constructor property.

    Teacher.prototype = Object.create(Person.prototype);


Object member summarySection
    To summarize, you've basically got three types of property/method to worry about:

    1. Those defined inside a constructor function that are given to object instances. 
        These are fairly easy to spot — in your own custom code, they are the members defined inside a constructor using the this.x = x type lines; in built in browser code, they are the members only available to object instances (usually created by calling a constructor using the new keyword, e.g. var myInstance = new myConstructor()).

    2. Those defined directly on the constructor themselves, that are available only on the constructor. 
        These are commonly only available on built-in browser objects, and are recognized by being chained directly onto a constructor, not an instance. For example, Object.keys().

    3. Those defined on a constructor's prototype, which are inherited by all instances and inheriting object classes. 
        These include any member defined on a Constructor's prototype property, e.g. myConstructor.prototype.x()

======================================
PROTOTYPAL CHAIN:

    When it comes to inheritance, JavaScript only has one construct: objects. Each object has a private property which holds a link to another object called its prototype. That prototype object has a prototype of its own, and so on until an object is reached with null as its prototype. By definition, null has no prototype, and acts as the final link in this prototype chain.

    Nearly all objects in JavaScript are instances of Object which sits on the top of a prototype chain.  

    JavaScript objects are dynamic "bags" of properties (referred to as own properties). 
    JavaScript objects have a link to a prototype object. 
    When trying to access a property of an object, the property will not only be sought on the object but on the prototype of the object, the prototype of the prototype, and so on until either a property with a matching name is found or the end of the prototype chain is reached.  


    ```Following the ECMAScript standard, the notation someObject.[[Prototype]] is used to designate the prototype of someObject. Since ECMAScript 2015, the [[Prototype]] is accessed using the accessors Object.getPrototypeOf() and Object.setPrototypeOf(). This is equivalent to the JavaScript property __proto__ which is non-standard but de-facto implemented by many browsers.  It should not be confused with the func.prototype property of functions, which instead specifies the [[Prototype]] to be assigned to all instances of objects created by the given function when used as a constructor. The Object.prototype property represents the Object prototype object.