# Forwarding Refs

Ref forwarding is a technique for automatically passing a ref through a component to one of its children.

Ref forwarding is an opt-in feature that lets some components take a ref they receive, and pass it further down (in other words, “forward” it) to a child.

```
const FancyButton = React.forwardRef((props, ref) => (
    <button ref={ref} className="fancyButton">
        {props.children}
    </button>
));

// You can now get a ref directly to the DOM button:
const ref = React.createRef();
<FancyButton ref={ref}>Click</FancyButton>
```

> Which are not part of props = key & ref

## Forwarding refs in higher-order components
