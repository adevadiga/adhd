    function Person(first, last) {
        this.first = first;
        this.last = last;
        this.fullName = function() {
            return this.first + ' ' + this.last;
        };
        this.fullNameReversed = function() {
            return this.last + ', ' + this.first;
        };
    }
    var s = new Person('Simon', 'Willison');

We have introduced another keyword: new. new is strongly related to this. It creates a brand new empty object, and then calls the function specified, with this set to that new object. Notice though that the function specified with this does not return a value but merely modifies the this object. It's new that returns the this object to the calling site. Functions that are designed to be called by new are called constructor functions. Common practice is to capitalize these functions as a reminder to call them with new.

Every time we create a person object we are creating two brand new function objects within it — wouldn't it be better if this code was shared?

    function Person(first, last) {
        this.first = first;
        this.last = last;
    }
    Person.prototype.fullName = function() {
        return this.first + ' ' + this.last;
    };
    Person.prototype.fullNameReversed = function() {
        return this.last + ', ' + this.first;
    };

Person.prototype is an object shared by all instances of Person. It forms part of a lookup chain (that has a special name, "prototype chain"): any time you attempt to access a property of Person that isn't set, JavaScript will check Person.prototype to see if that property exists there instead. As a result, anything assigned to Person.prototype becomes available to all instances of that constructor via the this object.

This is an incredibly powerful tool. JavaScript lets you modify something's prototype at any time in your program, which means you can add extra methods to existing objects at runtime:

    var s = new Person('Simon', 'Willison');
    s.firstNameCaps(); // TypeError on line 1: s.firstNameCaps is not a function

    Person.prototype.firstNameCaps = function() {
        return this.first.toUpperCase();
    };
    s.firstNameCaps(); // "SIMON"

Interestingly, you can also add things to the prototype of built-in JavaScript objects. Let's add a method to String that returns that string in reverse:
    var s = 'Simon';
    s.reversed(); // TypeError on line 1: s.reversed is not a function

    String.prototype.reversed = function() {
    var r = '';
    for (var i = this.length - 1; i >= 0; i--) {
        r += this[i];
    }
    return r;
    };

    s.reversed(); // nomiS

Our new method even works on string literals!
    'This can now be reversed'.reversed(); // desrever eb won nac sihT


As mentioned before, the prototype forms part of a chain. The root of that chain is Object.prototype, whose methods include toString() — it is this method that is called when you try to represent an object as a string. This is useful for debugging our Person objects:

