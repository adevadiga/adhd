# From CommonJS modules to ES6 modules

Even in ES5, module systems based on either AMD syntax or CommonJS syntax have mostly replaced hand-written solutions such as the revealing module pattern.

> Revealing Module Pattern.

```
var revealingModulePattern = function(){
  var privateVar = 1;
  function privateFunction(){
    alert('private');
  };

  var publicVar = 2;
  function publicFunction(){
    anotherPublicFunction();    
  };

  function anotherPublicFunction(){
    privateFunction();
  };

  // reveal all things private by assigning public pointers
  return {
    publicFunction:publicFunction,
    publicVar:publicVar,
    anotherPublicFunction:anotherPublicFunction
  }
}();

revealingModulePattern.publicFunction();
```

The approaches above all have one thing in common: the use of a single global variable to wrap its code in a function, thereby creating a private namespace for itself using a closure scope.

While each approach is effective in its own way, they have their downsides.

For one, as a developer, you need to know the right dependency order to load your files in. For instance, let’s say you’re using Backbone in your project, so you include the script tag for Backbone’s source code in your file.

However, since Backbone has a hard dependency on Underscore.js, the script tag for the Backbone file can’t be placed before the Underscore.js file.

As a developer, managing dependencies and getting these things right can sometimes be a headache.

Another downside is that they can still lead to namespace collisions. For example, what if two of your modules have the same name? Or what if you have two versions of a module, and you need both?

> CommonJS
CommonJS is a volunteer working group that designs and implements JavaScript APIs for declaring modules.

A CommonJS module is essentially a reusable piece of JavaScript which exports specific objects, making them available for other modules to require in their programs. 

With CommonJS, each JavaScript file stores modules in its own unique module context (just like wrapping it in a closure). In this scope, we use the module.exports object to expose modules, and require to import them.

```
function myModule() {
  this.hello = function() {
    return 'hello!';
  }

  this.goodbye = function() {
    return 'goodbye!';
  }
}

module.exports = myModule;

```

We use the special object module and place a reference of our function into module.exports. This lets the CommonJS module system know what we want to expose so that other files can consume it.

Then when someone wants to use myModule, they can require it in their file, like so:

```
var myModule = require('myModule');

var myModuleInstance = new myModule();
myModuleInstance.hello(); // 'hello!'
myModuleInstance.goodbye(); // 'goodbye!'
```

Advantage:
1. Avoiding global namespace pollution
2. Making our dependencies explicit

Another thing to note is that CommonJS takes a server-first approach and synchronously loads modules. This matters because if we have three other modules we need to require, it’ll load them one by one.

Now, that works great on the server but, unfortunately, makes it harder to use when writing JavaScript for the browser. Suffice it to say that reading a module from the web takes a lot longer than reading from disk. For as long as the script to load a module is running, it blocks the browser from running anything else until it finishes loading. 

> AMD
CommonJS is all well and good, but what if we want to load modules asynchronously? The answer is called Asynchronous Module Definition, or AMD for short.

```
define(['myModule', 'myOtherModule'], function(myModule, myOtherModule) {
  console.log(myModule.hello());
});
```

What’s happening here is that the define function takes as its first argument an array of each of the module’s dependencies. These dependencies are loaded in the background (in a non-blocking manner), and once loaded define calls the callback function it was given.

> Finally, the dependencies themselves must also be defined using the define keyword.

define([], function() {

  return {
    hello: function() {
      console.log('hello');
    },
    goodbye: function() {
      console.log('goodbye');
    }
  };
});

## Multiple exports

```
//lib.js
...
function diag(x, y) {
    return sqrt(square(x) + square(y));
}
module.exports = {
    sqrt: sqrt,
    square: square,
    diag: diag,
};

//------ main1.js ------
var square = require('lib').square;
var diag = require('lib').diag;

//------ main2.js ------
var lib = require('lib');
console.log(lib.square(11)); 

Es6:

import * as lib from 'lib'; // (A)
import { square, diag } from 'lib';
```

## Single exports 

```
//------ myFunc.js ------
module.exports = function () { ··· };

//------ main1.js ------
var myFunc = require('myFunc');
myFunc();

ES6

//------ myFunc.js ------
export default function () { ··· } // no semicolon!

//------ main1.js ------
import myFunc from 'myFunc';
myFunc();

```