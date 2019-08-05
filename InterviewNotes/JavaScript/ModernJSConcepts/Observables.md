# Observables

Observables are similar to arrays, except instead of being stored in memory, items arrive asynchronously over time (also called streams).
We can subscribe to observables and react to events emitted by them. 

JavaScript observables are an implementation of the observer pattern. 
Reactive Extensions (commonly known as Rx*) provides an observables library for JS via RxJS.

To demonstrate the concept of observables, let's consider a simple example: resizing the browser window. 
```
// create window resize stream
// throttle resize events
const resize$ =
  Rx.Observable
    .fromEvent(window, 'resize')
    .throttleTime(350);

// subscribe to the resize$ observable
// log window width x height
const subscription =
  resize$.subscribe((event) => {
    let t = event.target;
    console.log(`${t.innerWidth}px x ${t.innerHeight}px`);
  });

```

The example code above shows that as the window size changes, we can throttle the observable stream and subscribe to the changes to respond to new values in the collection. This is an example of a hot observable.

## Hot Observables

User interface events like button clicks, mouse movement, etc. are hot.
Hot observables will always push even if we're not specifically reacting to them with a subscription.
The window resize example above is a hot observable: the resize$ observable fires whether or not subscription exists.

## Cold Observables

A cold observable begins pushing only when we subscribe to it.
If we subscribe again, it will start over.

```
// create source number stream
const source$ = Rx.Observable.range(1, 5);

// subscribe to source$ observable
const subscription = source$.subscribe(
  (value) => { console.log(`Next: ${value}`); }, // onNext
  (event) => { console.log(`Error: ${event}`); }, // onError
  () => { console.log('Completed!'); }  // onCompleted
);

```

We can subscribe() to the source$ observable we just created. 
Upon subscription, the values are sent in sequence to the observer. 
The onNext callback logs the values: Next: 1, Next: 2, etc. until completion: Completed!.
The cold source$ observable we created doesn't push unless we subscribe to it.

## Observables Takeaways

Observables are streams.
We can observe any stream: from resize events to existing arrays to API responses.
We can create observables from almost anything. A promise is an observable with a single emitted value, but observables can return many values over time.

Observables are often visualized using points on a line, as demonstrated on the RxMarbles site. 
Since the stream consists of asynchronous events over time, it's easy to conceptualize this in a linear fashion and use such visualizations to understand Rx* operators. For example, the following RxMarbles image illustrates the filter operator:

![RX](https://cdn.auth0.com/blog/jsglossary/rxmarbles.png)


# Reactive Programming

Reactive programming is concerned with propagating and responding to incoming events over time, declaratively (describing what to do rather than how).

Reactive programming is often associated with Reactive Extensions, an API for asynchronous programming with observable streams.
Reactive Extensions (abbreviated Rx*) provides libraries for a variety of languages, including JavaScript (RxJS).

## In Practice: Reactive Programming with JavaScript

Here is an example of reactive programming with observables.
Let's say we have an input where the user can enter a six-character confirmation code and we want to print out the latest valid code attempt. Our HTML might look like this:

```
<!-- HTML -->
<input id="confirmation-code" type="text">
<p>
  <strong>Valid code attempt:</strong>
  <code id="attempted-code"></code>
</p>
```

We'll use RxJS and create a stream of input events to implement our functionality, like so:

```
// JS
const confCodeInput = document.getElementById('confirmation-code');
const attemptedCode = document.getElementById('attempted-code');

const confCodes$ =
  Rx.Observable
    .fromEvent(confCodeInput, 'input')
    .map(e => e.target.value)
    .filter(code => code.length === 6);

const subscription = confCodes$.subscribe(
  (value) => attemptedCode.innerText = value,
  (event) => { console.warn(`Error: ${event}`); },
  () => { console.info('Completed!'); }
);

```
We'll observe events from the confCodeInput input element. Then we'll use the map operator to get the value from each input event. Next, we'll filter any results that are not six characters so they won't appear in the returned stream. Finally, we'll subscribe to our confCodes$ observable and print out the latest valid confirmation code attempt.

Note that this was done in response to events over time, declaratively: this is the crux of reactive programming.

## Reactive Programming Takeaways

The reactive programming paradigm involves observing and reacting to events in asynchronous data streams.

