# Typechecking With PropTypes

As your app grows, you can catch a lot of bugs with typechecking.
For some applications, you can use JavaScript extensions like Flow or TypeScript to typecheck your whole application.
But even if you don’t use those, React has some built-in typechecking abilities.

>For performance reasons, propTypes is only checked in development mode.

```
Greeting.propTypes = {
  name: PropTypes.string
};

// Specifies the default values for props:
Greeting.defaultProps = {
  name: 'Stranger'
};


class Greeting extends React.Component {
  static defaultProps = {
    name: 'stranger'
  }

  render() {
    return (
      <div>Hello, {this.props.name}</div>
    )
  }
}
```

> The propTypes typechecking happens after defaultProps are resolved, so typechecking will also apply to the defaultProps.