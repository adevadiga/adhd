# Context

Context provides a way to pass data through the component tree without having to pass props down manually at every level.
Ex: locale preference, UI theme

## When to Use Context
Context is designed to share data that can be considered “global” for a tree of React components,
such as the current authenticated user, theme, or preferred language. 

```
const ThemeContext = React.createContext("light");

const App extends React.Component {
    render() {
        <ThemeContext.Provider value="dark">
            <Toolbar />
        </ThemeContext>
    }
}

function Toolbar(props) {
    return (
        <div>
            <ThemedButton/>
        </div>
    );
}

class ThemedButton extends React.Component {

    static contextType = ThemeContext;

    render() {
        return <Button theme={this.context} />;
    }
}

//Either static property or below class property
MyClass.contextType = MyContext;


```

> Note:
> 
> You can only subscribe to a single context using this API. If you need to read more than one see Consuming Multiple Contexts.
> 
> If you are using the experimental public class fields syntax, you can use a static class field to initialize your contextType.

## Context.Consumer

```
<MyContext.Consumer>
    {value => /* render something based on the context value */} //Function as child - renderProp
</MyContext.Consumer>
```

### Updating Context from a Nested Component

```
// Make sure the shape of the default value passed to
// createContext matches the shape that the consumers expect!
export const ThemeContext = React.createContext({
  theme: themes.dark,
  toggleTheme: () => {},
});


import {ThemeContext} from './theme-context';

function ThemeTogglerButton() {
  // The Theme Toggler Button receives not only the theme
  // but also a toggleTheme function from the context
  return (
    <ThemeContext.Consumer>
      {({theme, toggleTheme}) => (
        <button
          onClick={toggleTheme}
          style={{backgroundColor: theme.background}}>
          Toggle Theme
        </button>
      )}
    </ThemeContext.Consumer>
  );
}

export default ThemeTogglerButton;

```



## Consuming Multiple Contexts

To keep context re-rendering fast, React needs to make each context consumer a separate node in the tree.

```
// Theme context, default to light theme
const ThemeContext = React.createContext('light');

// Signed-in user context
const UserContext = React.createContext({
  name: 'Guest',
});

class App extends React.Component {
  render() {
    const {signedInUser, theme} = this.props;

    // App component that provides initial context values
    return (
      <ThemeContext.Provider value={theme}>
        <UserContext.Provider value={signedInUser}>
          <Layout />
        </UserContext.Provider>
      </ThemeContext.Provider>
    );
  }
}

function Layout() {
  return (
    <div>
      <Sidebar />
      <Content />
    </div>
  );
}

// A component may consume multiple contexts
function Content() {
  return (
    <ThemeContext.Consumer>
      {theme => (
        <UserContext.Consumer>
          {user => (
            <ProfilePage user={user} theme={theme} />
          )}
        </UserContext.Consumer>
      )}
    </ThemeContext.Consumer>
  );
}


```

If two or more context values are often used together, you might want to consider creating your own render prop component that provides both.


