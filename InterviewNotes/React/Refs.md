# Refs and the DOM

Refs provide a way to access DOM nodes or React elements created in the render method.

In the typical React dataflow, props are the only way that parent components interact with their children. To modify a child, you re-render it with new props. 

However, there are a few cases where you need to imperatively modify a child outside of the typical dataflow. The child to be modified could be an instance of a React component, or it could be a DOM element. For both of these cases, React provides an escape hatch.

## When to Use Refs
* Managing focus, text selection, or media playback.
* Triggering imperative animations.
* Integrating with third-party DOM libraries.

## Creating Refs
```
class MyComponent extends React.Component {
  constructor(props) {
    super(props);
    this.myRef = React.createRef();
  }
  render() {
    return <div ref={this.myRef} />;
  }
}

```

## Accessing Refs
When a ref is passed to an element in render, a reference to the node becomes accessible at the current attribute of the ref.
`const node = this.myRef.current;`

## The value of the ref 

* When the ref attribute is used on an HTML element, the ref created in the constructor with React.createRef() receives the underlying DOM element as its current property.

* When the ref attribute is used on a custom class component, the ref object receives the mounted instance of the component as its current.
  
* > You may not use the ref attribute on function components because they don’t have instances.


React will assign the current property with the DOM element when the component mounts, and assign it back to null when it unmounts.
ref updates happen before componentDidMount or componentDidUpdate lifecycle methods.

> You may not use the ref attribute on function components because they don’t have instances:



## Exposing DOM Refs to Parent Components
In rare cases, you might want to have access to a child’s DOM node from a parent component. This is generally not recommended because it breaks component encapsulation, but it can occasionally be useful for triggering focus or measuring the size or position of a child DOM node.

You can use ref forwarding for these cases.

**Ref forwarding lets components opt into exposing any child component’s ref as their own.
**
