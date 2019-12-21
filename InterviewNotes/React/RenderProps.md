# Render Props
The term “render prop” refers to a technique for sharing code between React components using a prop whose value is a function.

> A component with a render prop, takes a function,  that returns a React element      and calls it instead of implementing its own render logic.

```
<DataProvider render={data => (
    <h1>Hello {data.name}</h1>
)}>
```

## Use Render Props for Cross-Cutting Concerns
 It’s not always obvious how to share the state or behavior that one component encapsulates to other components that need that same state.

```
 class Mouse extends React.Component {

     render() {
         <div style={{ height: '100%' }} onMouseMove={this.handleMouseMove}>
            {/* Dynamically determine what to render here.*/}
            {this.props.render(this.state)}
         </div>
     }
 }

 class MouseTracker extends React.Component {
  render() {
      return (
      <div>
        <h1>Move the mouse around!</h1>
        <Mouse render={mouse => (
            <Cat mouse={mouse} />
        )}/>
      </div>
  }
 }
```

> A render prop is a function prop that a component uses to know what to render.

## Caveats
With render props no use of making the React.Component. As each time new function is different , props will never match on subsequent renders.

To get around this problem, you can sometimes define the prop as an instance method, like so:

```class MouseTracker extends React.Component {
  // Defined as an instance method, `this.renderTheCat` always
  // refers to *same* function when we use it in render
  renderTheCat(mouse) {
    return <Cat mouse={mouse} />;
  }

  render() {
    return (
      <div>
        <h1>Move the mouse around!</h1>
        <Mouse render={this.renderTheCat} />
      </div>
    );
  }
} 
```