# Stateless Programming Advantage

    There are lots of advantages to stateless programming, not least of which is dramatically multithreaded and concurrent code.
    To put it bluntly, mutable state is enemy of multithreaded code.

    Since there are no race conditions, there's no reason to use locks either, so immutability eliminates another whole class of bugs related to deadlocks as well.

    There are also lots of other benefits, including simplified debugging (i.e. functions are pure and do not mutate state in other parts of an application), more terse and expressive code, less boilerplate code compared to languages which are heavily dependent on design patterns, and the compiler can more aggressively optimize your code.


## Stateful

    When something is “stateful”, it is a central point that stores information in memory about the app/component’s state. It also has the ability to change it. It is essentially a “living” thing that has knowledge of past, current and potential future state changes.

## Stateless

    When something is “stateless”, it calculates its internal state but it never directly mutates it. This allows for complete referential transparency meaning that given the same inputs, it will always produce the same output. These are not essentially “living” as they are merely passed information. This means it has no knowledge of the past, current or future state changes.

## Components

    A component is an isolated piece of behaviour or functionality that allows us to divide behaviour into roles, much like we would with JavaScript functions.



# Imperative and Declarative Programming

## Imperative Programming
Imperative programming describes how a program's logic works in explicit commands with statements that modify the program state.

Consider a function that increments every number in an array of integers. 
```
function incrementArray(arr) {
    let resultArr = [];
    for (let i = 0; i < arr.length; i++) {
        resultArr.push(arr[i] + 1);
    }
    return resultArr;
}
```

This function shows exactly how the function's logic works: we iterate over the array and explicitly increase each number, pushing it to a new array. We then return the resulting array. This is a step-by-step description of the function's logic.

## Declarative Programming

Declarative programming describes what a program's logic accomplishes without describing how.

A very straightforward example of declarative programming can be demonstrated with SQL. We can query a database table (People) for people with the last name Smith like so:

`SELECT * FROM People WHERE LastName = 'Smith'`

This code is easy to read and describes what we want to accomplish. There is no description of how the result should be achieved. The computer takes care of that.

Now consider the incrementArray() function we implemented imperatively above. Let's implement this declaratively now:

```
function incrementArray(arr) {
  return arr.map(item => item + 1);
}

```
