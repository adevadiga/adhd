# Promise

> The `Promise` object represents the eventual completion (or failure) of an asynchronous operation, and its resulting value.

> Essentially, a promise is a returned object to which you attach callbacks, instead of passing callbacks into a function.

```
//Old way
function successCallback(result) {
  console.log("Audio file ready at URL: " + result);
}

function failureCallback(error) {
  console.log("Error generating audio file: " + error);
}

createAudioFileAsync(audioSettings, successCallback, failureCallback);

//Using Promise
createAudioFileAsync(audioSettings).then(successCallback, failureCallback);

createAudioFileAsync(audioSettings).then(successCallback).catch(failureCallback);

```

## Guarantees

* Callbacks will never be called before the completion of the current run of the JavaScript event loop.
* Callbacks added with then() even after the success or failure of the asynchronous operation, will be called,
* Multiple callbacks may be added by calling then() several times. Each callback is executed one after another, in the order in which they were inserted.

## Chaining

A common need is to execute two or more asynchronous operations back to back, where each subsequent operation starts when the previous operation succeeds, with the result from the previous step. We accomplish this by creating a promise chain.

Here's the magic: the `then()` function returns a new promise, different from the original:

```
const promise = doSomething();
const promise2 = promise.then(successCallback, failureCallback);

```

This second promise (promise2) represents the completion not just of doSomething(), but also of the successCallback or failureCallback you passed in, which can be other asynchronous functions returning a promise.

In the old days, doing several asynchronous operations in a row would lead to the classic callback pyramid of doom:

```
doSomething(function(result) {
  doSomethingElse(result, function(newResult) {
    doThirdThing(newResult, function(finalResult) {
      console.log('Got the final result: ' + finalResult);
    }, failureCallback);
  }, failureCallback);
}, failureCallback);

doSomething()
.then(function(result) {
  return doSomethingElse(result);
})
.then(function(newResult) {
  return doThirdThing(newResult);
})
.then(function(finalResult) {
  console.log('Got the final result: ' + finalResult);
})
.catch(failureCallback);


doSomething()
.then(result => doSomethingElse(result))
.then(newResult => doThirdThing(newResult))
.then(finalResult => {
  console.log(`Got the final result: ${finalResult}`);
})
.catch(failureCallback);
```

> The arguments to then are optional, and catch(failureCallback) is short for then(null, failureCallback)

## Chaining after a catch

```
new Promise((resolve, reject) => {
    console.log('Initial');

    resolve();
})
.then(() => {
    throw new Error('Something failed');
        
    console.log('Do this');
})
.catch(() => {
    console.log('Do that');
})
.then(() => {
    console.log('Do this, no matter what happened before');
});

Initial
Do that
Do this, no matter what happened before

```

## Error propagation
Basically, a promise chain stops if there's an exception, looking down the chain for catch handlers instead.

Promises solve a fundamental flaw with the callback pyramid of doom, by catching all errors, even thrown exceptions and programming errors. This is essential for functional composition of asynchronous operations.


## Promise rejection events

Whenever a promise is rejected, one of two events is sent to the global scope(Window or Worker)
The two events are:

* rejectionhandled: 
  Sent when a promise is rejected, after that rejection has been handled by the executor's reject function.

* unhandledrejection:
  Sent when a promise is rejected but there is no rejection handler available.

  In both cases, the event (of type PromiseRejectionEvent) has as members a promise property indicating the promise that was rejected, and a reason property that provides the reason given for the promise to be rejected.


  These make it possible to offer fallback error handling for promises, as well as to help debug issues with your promise management. These handlers are global per context, so all errors will go to the same event handlers, regardless of source.
```

  window.addEventListener("unhandledrejection", event => {
  /* You might start here by adding code to examine the
     promise specified by event.promise and the reason in
     event.reason */

  event.preventDefault();
}, false);
```

## Creating a Promise around an old callback API
Basically, the promise constructor takes an executor function that lets us resolve or reject a promise manually. 

A Promise can be created from scratch using its constructor. This should be needed only to wrap old APIs.

`setTimeout(() => saySomething("10 seconds passed"), 10000);`
If saySomething() fails or contains a programming error, nothing catches it.

Luckily we can wrap setTimeout in a promise. Best practice is to wrap problematic functions at the lowest possible level, and then never call them directly again:

```
const wait = ms => new Promise(resolve => setTimeout(resolve, ms));
wait.then(() => saySomething("10 seconds passed")).catch(failureCallback);

```

## Composition

Promise.resolve() and Promise.reject() are shortcuts to manually create an already resolved or rejected promise respectively. 

Promise.all() and Promise.race() are two composition tools for running asynchronous operations in parallel.

We can start operations in parallel and wait for them all to finish like this:
```
Promise.all([func1(), func2(), func3()])
.then(([result1, result2, result3]) => { /* use result1, result2 and result3 */ });

```

Sequential composition is possible using some clever JavaScript:

```
[f1(), f2(), f3()].reduce((acc, f) => acc.then(f),  Promise.resolve())
    .then(result3 => { /* use result3 */ });

const applySync = (acc, val) => acc.then(val);
const composeAsync = (...funcs) => x => funcs.reduce(applyAsync, Promise.resolve(x));

const transformData = composeAsync(func1, func2, func3);
const result3 = transformData(data);
```

## Timing

To avoid surprises, functions passed to then() will never be called synchronously, even with an already-resolved promise:

```
Promise.resolve().then(() => console.log(2));
console.log(1); // 1, 2
```



> Promises are all about making asynchronous code more readable and behave like synchronous code without hiding that fact.

## The Deferred anti-pattern

In Deferred anti-pattern, "deferred" objects are created for no reason, complicating code.

First example is creating deferred object when you already have a promise or thenable:

```
myApp.factory('Configurations', function (Restangular, MotorRestangular, $q) {
    var getConfigurations = function () {
        var deferred = $q.defer();

        MotorRestangular.all('Motors').getList().then(function (Motors) {
            //Group by Config
            var g = _.groupBy(Motors, 'configuration');
            //Map values
            var mapped = _.map(g, function (m) {
                return {
                    id: m[0].configuration,
                    configuration: m[0].configuration,
                    sizes: _.map(m, function (a) {
                        return a.sizeMm
                    })
                }
            });
            deferred.resolve(mapped);
        });
        return deferred.promise;
    };

    return {
        config: getConfigurations()
    }

});


```

This superfluous wrapping is also dangerous, any kind of errors and rejections are swallowed and not propagated to the caller of this function.

Instead of using the Deferred anti-pattern, the code should simply return the promise it already has and propagate values using return:

```
myApp.factory('Configurations', function (Restangular, MotorRestangular, $q) {
    var getConfigurations = function () {
        //Just return the promise we already have!
        return MotorRestangular.all('Motors').getList().then(function (Motors) {
            //Group by Cofig
            var g = _.groupBy(Motors, 'configuration');
            //Return the mapped array as the value of this promise
            return _.map(g, function (m) {
                return {
                    id: m[0].configuration,
                    configuration: m[0].configuration,
                    sizes: _.map(m, function (a) {
                        return a.sizeMm
                    })
                }
            });
        });
    };

    return {
        config: getConfigurations()
    }

});
```

Not only is the code shorter but more importantly, if there is any error it will propagate properly to the final consumer.

=============================

A Promise is in one of these states:
    * pending: initial state, neither fulfilled nor rejected.
    * fulfilled: meaning that the operation completed successfully.
    * rejected: meaning that the operation failed.
  * 
The constructor is primarily used to wrap functions that do not already support promises.


Predit output:

```
function asyncFunc1() {
   console.log("asyncFunc1 called...")
  return new Promise(function(resolve, reject) {
    setTimeout(() => {
      console.log("asyncFunc1 ended...")
      resolve("hello");
    }, 1000)
  });
}

function asyncFunction2() {
  console.log("asyncFunction2 called...")
  return new Promise(function(resolve, reject) {
    setTimeout(() => {
      console.log("asyncFunction2 ended...")
      resolve("World");
    }, 1000)
  });
}


asyncFunc1()
.then(result1 => {
    // Use result1
    console.log("First then");
    console.log(result1);
    return asyncFunction2();// If u dont return here, this function doesn't part of chain at ////all.
})
.then(result2 => { // (B)
    // Use result2
     console.log("second then");
     console.log(result2);
})
.catch(error => {
  console.log(error);
    // Handle errors of asyncFunc1() and asyncFunc2()
});
```