# Main

statically resolvable patterns.

For example, side effects belong in useEffect, not useMemo.

## How does React associate Hook calls with components?

React keeps track of the currently rendering component. Thanks to the Rules of Hooks, we know that Hooks are only called from React components (or custom Hooks — which are also only called from React components).

There is an internal list of “memory cells” associated with each component. They’re just JavaScript objects where we can put some data. When you call a Hook like useState(), it reads the current cell (or initializes it during the first render), and then moves the pointer to the next one. This is how multiple useState() calls each get independent local state.


## Caveats

useState, It’s similar to this.setState in a class, except it doesn’t merge the old and new state together.

## But what is a Hook?

Hooks are functions that let you “hook into” React state and lifecycle features from function components. 

## Side Effects - 

Data fetching, subscription, event listeners, dom updates from react components.

The Effect Hook, useEffect, adds the ability to perform side effects from a function component.
 It serves the same purpose as componentDidMount, componentDidUpdate, and componentWillUnmount in React classes

 `A Hook is a special function that lets you “hook into” React features. For example, useState is a Hook that lets you add React state to function components.`

`Hooks let you organize side effects in a component by what pieces are related (such as adding and removing a subscription), rather than forcing a split based on lifecycle methods.`

## ✌️ Rules of Hooks

Hooks are JS function but impose two additional rules.

* Only call Hooks at the top level. Don’t call Hooks inside loops, conditions, or nested functions.
* Only call Hooks from React function components or custom hooks.
* In fact, each call to a Hook has a completely isolated state — so you can even use the same custom Hook twice in one component.


## useState

## The Effect Hook lets you perform side effects in function components:

### Effects Without Cleanup: run some additional code after React has updated the DOM.

Network requests, manual DOM mutations, and logging are common examples of effects that don’t require a cleanup. 
> we have to duplicate the code between these two lifecycle methods in class. {componentDidMount, componentDidUpdate}
 in many cases we want to perform the same side effect regardless of whether the component just mounted, or if it has been updated.

> Placing useEffect inside the component let us access props and state without need of special api
> Hooks embrace JavaScript closures and avoid introducing React-specific APIs where JavaScript already provides a solution.

> Does useEffect run after every render? Yes! By default, it runs both after the first render and after every update. React guarantees the DOM has been updated by the time it runs the effects.

* _useEffect is going to be different on every render._  this is what lets us read the count value from inside the effect without worrying about it getting stale. Every time we re-render, we schedule a different effect, replacing the previous one.

> effects happen “after render”. React guarantees the DOM has been updated by the time it runs the effects.

> Unlike componentDidMount or componentDidUpdate, effects scheduled with useEffect don’t block the browser from updating the screen.This makes your app feel more responsive.

> The majority of effects don’t need to happen synchronously

### Effects with Cleanup

> Using multiple useEffects call, lets us separate unrelated logic into different effects:
> Hooks let us split the code based on what it is doing rather than a lifecycle method name. React will apply every effect used by the component, in the order they were specified.

### useEffect tips

  * Use Multiple Effects to Separate Concerns
  * Hooks let us split the code based on what it is doing rather than a lifecycle method name

### Rules of hooks

  * Don’t call Hooks inside loops, conditions, or nested functions.
    Always use Hooks at the top level of your React function. 
    By following this rule, you ensure that Hooks are called in the same order each time a component renders.
    That’s what allows React to correctly preserve the state of Hooks between multiple useState and useEffect calls.

  * Only Call Hooks from React Functions - React function components & Custom hooks


## Building your own hooks

Building your own Hooks lets you extract component logic into reusable functions.
A custom Hook is a JavaScript function whose name starts with ”use” and that may call other Hooks.

### How does a custom Hook get isolated state?

Each call to a Hook gets isolated state. From React’s point of view our component just calls useState and useEffect etc. From React we can call useState and useEffect many times in one component, and they will be completely independent.

For React custom hook is  a function call which if has useState or useEffect, that will have isolated states.

```
  function useReducer(reducer, initialState) {
    const [state, setState] = useState(initialState);

    function dispatch(action) {
      const nextState = reducer(state, action);
      setState(nextState);
    }

    return [state, dispatch];
  }

  const [todos, dispatch] = useReducer(todosReducer, []);

  function handleAddClick(text) {
    dispatch({ type: 'add', text });
  }

```




