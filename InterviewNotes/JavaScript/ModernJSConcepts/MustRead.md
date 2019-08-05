# Scope (Global, Local, Lexical) and Closures

## Scope

Scope is simply the context of our code: where variables and functions are accessible.

```
// Global scope
var globalVar = 'Hello, ';
console.log(localVar); // Uncaught ReferenceError: localVar is not defined

someFunction() {
  // Local scope
  var localVar = 'World!';
  console.log(globalVar + localVar); // 'Hello, World!'
}
```
Everything has access to the global scope. If we open an empty .js file and type var globalVar, this variable is accessible to anything else we'll create. If we executed the file in a browser, globalVar's function scope would be window.

> Note: If we declare a new variable without the var keyword, it will be placed in the global scope no matter where it is in the code. You may have encountered this before (perhaps accidentally).

The `someFunction` function creates its own local scope. It also inherits access to the global scope. We can freely use `globalVar` inside `someFunction`. However, the global scope does not have access to nested contexts, such as someFunction's local scope. If we try to log `localVar` from the global scope, we will receive an error because `localVar` is not defined in the global scope.

In a nutshell, nested functions have their own scope. Functions declared inside another function also have access to their parent functions' scopes. This is called the scope chain.

_Lexical scope (or static scope)_ refers to the fact that every nested function can access the functions that contain it.

```
// Lexical scope and scope chain
var a = 1;

function outerFunc() {
  var b = 2;
  console.log(a + b);

  function middleFunc() {
    var c = 3;
    console.log(a + b + c);

    function innerFunc() {
      var d = 4;
      console.log(a + b + c + d);
    }

    innerFunc(); // logs 10 (1 + 2 + 3 + 4)
  }

  middleFunc(); // logs 6 (1 + 2 + 3)
}

outerFunc(); // logs 3 (1 + 2)
```

# Closures

```
// Closures
function whenMeetingJohn(salutation) {
  var greeting = salutation + ', John!';

  function alertGreeting() {
    alert(greeting);
  }
  return alertGreeting;
}
var atLunchToday = whenMeetingJohn('Hi');

atLunchToday(); // alerts "Hi, John!"
whenMeetingJohn('Whassup')(); // alerts "Whassup, John!"

```

A **closure** is formed when a function (`alertGreeting`) declared inside an outer function (`whenMeetingJohn`) references variables from the outer function's local scope (such as the `greeting` variable).

The term "closure" refers to the function and the lexical environment (any local variables that were in scope when the closure was created) in which that function was declared.



# One-Way Data Flow and Two-Way Data Binding

## One-Way Data Flow

An application or framework with one-way data flow uses the model as the single source of truth.
React is a widely recognized example of one-way data flow (or one-way data binding). 
Messages are sent from the UI in the form of events to signal the model to update.

```
// One-way data flow with React
class OneWay extends React.Component {
  constructor() {
    super();
    this.handleChange = this.handleChange.bind(this);
    // set initial this.state.text to an empty string
    this.state = {
      text: ''
    };
  }
  handleChange(e) {
    // get new input value from the event and update state
    this.setState({
      text: e.target.value
    });
  }
  render() {
    return (
      <div>
        <input type="text" onChange={this.handleChange} />
        <p>Text: {this.state.text}</p>
      </div>
    );
  }
}

```

Data is only flowing in one direction: from the model down. The UI input does not have direct access to the model. If we want to update state in response to changes from the UI, the input must send a message carrying the payload. The only way the UI can influence the model is through this event and the setState() method. The UI will never automagically update the model.

> Note: In order to reflect changes from the model to the UI, React creates a new virtual DOM and diffs the old virtual DOM with the updated virtual DOM. Only the changes are then rendered in the real DOM.

## Two-Way Data Binding

In two-way data binding, the data flows in both directions. 
This means that the JS can update the model and the UI can do so as well. A common example of two-way data binding is with AngularJS.

When we change the input value in the UI, the model will also be updated.
we didn't need set up any events or handlers to explicitly signal the controller that the model was updated in the UI.


## Data Flow and Binding Takeaways

 One-way data flow encourages clean architecture with regard to how data moves through an application. Application state is also easier to manage, updates are more predictable, and performance can be better as well.

## Change Detection in JS Frameworks: Dirty Checking, Accessors, Virtual DOM

Change detection is important for any dynamic JavaScript Single Page Application (SPA). When the user updates something, the app must have a way to detect and react to that change appropriately.

## Dirty Checking

**Dirty checking** refers to a deep comparison that is run on all models in the view to check for a changed value.
 AngularJS's digest cycle adds a watcher for every property we add to the $scope and bind in the UI. Another watcher is added when we want to watch values for changes using $scope.$watch().

 The digest cycle is a loop. AngularJS runs through its list of watchers and checks to see if any of the watched $scope variables have changed (aka, are "dirty"). If a variable has not changed, it moves on to the next watched variable. If it finds one that is dirty, it remembers its new value and re-enters the loop. When no new changes are detected in the entire watch list, the DOM is updated.

 **Accessors** Ember and Backbone use data accessors (getters and setters) for change detection.

**Virtual DOM**

Virtual DOM is used by React to implement change detection.
React doesn't specifically detect each change.
Instead, the virtual DOM is used to diff the previous state of the UI and the new state when a change occurs.
React is notified of such changes by the use of the setState() method, which triggers the render() method to perform a diff.

Virtual DOM is a JavaScript data model that represents the real DOM tree.
When a virtual DOM is generated, nothing is rendered to the browser.
The old model is compared to the new model and once React determines which parts of the virtual DOM have changed, only those parts are patched in the real DOM.

## Smart and Dumb Components

In some cases (such as in React and Angular), component architecture utilizes smart and dumb components. They are also referred to as "container" (smart) and "presentational" (dumb) components.

**Smart Components**

Also known as container components, smart components can manage interactions with the application's state and data. They handle business logic and respond to events emitted from children (which are often dumb components).

**Dumb Components**

Also known as presentational components, dumb components rely on inputs supplied by their parents and are ignorant of application state.
They can be sometimes be considered pure and are modular and reusable. They can communicate to their parents when reacting to an event, but they don't handle the event themselves.

_Presentational (aka Dumb) Components:_
* Focus on "How things look"
* Allow containment with this.props.children
* No dependencies on the rest of the app (ie., no Flux or Redux actions or stores)
* Only receive data; do not load or mutate it
* Are generally functional (with exceptions)

_Container (aka Smart) Components:_
* Focus on "How things work"
* Provide data and behavior to other components
* Usually have no or very little DOM markup, and never have styles
* Are often stateful data sources
* Are generally generated from higher order components


## Tree Shaking Takeaways

Tree shaking is term for JavaScript live code inclusion in module bundlers that use ES2015 static import and export to "shake out" unneeded dependencies on a more granular level.
