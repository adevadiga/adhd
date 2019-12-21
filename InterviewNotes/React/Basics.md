# Main Concepts

## Why JSX?
React embraces the fact that rendering logic is inherently coupled with other UI logic: how events are handled, how the state changes over time, and how the data is prepared for display.

Instead of artificially separating technologies by putting markup and logic in separate files, React separates concerns with loosely coupled units called “components” that contain both

Thinking about how the UI should look at any given moment rather than how to change it over time eliminates a whole class of bugs.

## Components

Components let you split the UI into independent, reusable pieces, and think about each piece in isolation. 


## Using State Correctly

1. Do Not Modify State Directly
2. State Updates May Be Asynchronous
    this.setState((state, props) => ({
    counter: state.counter + props.increment
    }));
3. State Updates are Merged

## The data flows down
If you imagine a component tree as a waterfall of props, each component’s state is like an additional water source that joins it at an arbitrary point but also flows down.

## Keys

Keys help React identify which items have changed, are added, or are removed. Keys should be given to the elements inside the array to give the elements a stable identity:

> Keys serve as a hint to React but they don’t get passed to your components.