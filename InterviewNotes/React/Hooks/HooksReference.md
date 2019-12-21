# basic-hooks

## useState

```
  const [state, setState] = useState(initialState);

  setState(newState);

  setState(prevState => prevState - 1);
```
Unlike the setState method found in class components, useState does not automatically merge update objects.
```
setState(prevState => {
  // Object.assign would also work
  return {...prevState, ...updatedValues};
});
```

Lazy initial state via function
```
const [state, setState] = useState(() => {
  const initialState = someExpensiveComputation(props);
  return initialState;
});

```

> If you update a State Hook to the same value as the current state, React will bail out without rendering the children or firing effects.
> Note that React may still need to render that specific component again before bailing out.
> That shouldn’t be a concern because React won’t unnecessarily go “deeper” into the tree. If you’re doing expensive calculations while rendering, you can optimize them with useMemo.


## useEffect

Accepts a function that contains imperative, possibly effectful code.

`useEffect(didUpdate);`

Mutations, subscriptions, timers, logging, and other side effects are not allowed inside the main body of a function component (referred to as React’s render phase). Doing so will lead to confusing bugs and inconsistencies in the UI. 
The function passed to useEffect will run after the render is committed to the screen.  Think of effects as an escape hatch from React’s purely functional world into the imperative world.

By default, effects run after every completed render, but you can choose to fire them only when certain values have changed.

> the previous effect is cleaned up before executing the next effect.
> Unlike componentDidMount and componentDidUpdate, the function passed to useEffect fires after layout and paint, during a deferred event.
> Although useEffect is deferred until after the browser has painted, it’s guaranteed to fire before any new renders. React will always flush a previous render’s effects before starting a new update.

> If you pass an empty array ([]), the props and state inside the effect will always have their initial values.

**** Why u think this happens.

When useEffect method is created, state refers to some object or value. useEffect closure can refer to it and can continue to refer.

However when useState runs it provides u a different object with same value. like for ex: immutability
So effect will still refer to old one.


## useContext

`const value = useContext(MyContext);`

When the nearest <MyContext.Provider> above the component updates, this Hook will trigger a rerender with the latest context value passed to that MyContext provider.

## useReducer

`const [state, dispatch] = useReducer(reducer, initialArg, init);`

 Accepts a reducer of type `(state, action) => newState`, and returns the current state paired with a dispatch method. 

## useCallback

Returns a memoized callback.

```
const memoizedCallback = useCallback(
  () => {
    doSomething(a, b);
  },
  [a, b],
);

```

## useMemo

Returns a memoized value.

`const memoizedValue = useMemo(() => computeExpensiveValue(a, b), [a, b]);`

> You may rely on useMemo as a performance optimization, not as a semantic guarantee.

## useRef

useRef returns a mutable ref object whose `.current` property is initialized to the passed argument (initialValue).
The returned object will persist for the full lifetime of the component.

Essentially, useRef is like a “box” that can hold a mutable value in its `.current` property.

> However, useRef() is useful for more than the ref attribute. It’s handy for keeping any mutable value around similar to how you’d use instance fields in classes.

> Keep in mind that useRef doesn’t notify you when its content changes. Mutating the .current property doesn’t cause a re-render. If you want to run some code when React attaches or detaches a ref to a DOM node, you may want to use a callback ref instead.

## useLayoutEffect

The signature is identical to useEffect, but it fires synchronously after all DOM mutations.
Use this to read layout from the DOM and synchronously re-render. Updates scheduled inside useLayoutEffect will be flushed synchronously, before the browser has a chance to paint.

## useDebugValue

useDebugValue can be used to display a label for custom hooks in React DevTools.
`useDebugValue(value)`


