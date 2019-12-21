# Hooks

New feature introduced in React 16.8
They let you use state and other React features without writing a class.

Hooks offer a powerful and expressive new way to reuse functionality between components.

Hook equivalents to the uncommon getSnapshotBeforeUpdate and componentDidCatch lifecycles yet, but we plan to add them soon.

## Why?

### Its hard to re-use stateful logic between components.

React doesn’t offer a way to “attach” reusable behavior to a component (for example, connecting it to a store). 
You may be familiar with patterns like render props and higher-order components that try to solve this.

But these patterns require you to restructure your components when you use them, which can be cumbersome and make code harder to follow. 
 If you look at a typical React application in React DevTools, you will likely find a “wrapper hell” of components surrounded by layers of providers, consumers, higher-order components, render props, and other abstractions.

 With Hooks, you can extract stateful logic from a component so it can be tested independently and reused. Hooks allow you to reuse stateful logic without changing your component hierarchy. This makes it easy to share Hooks among many components or with the community.


 ### Complex components become hard to understand

 Mutually related code that changes together gets split apart, but completely unrelated code ends up combined in a single method. This makes it too easy to introduce bugs and inconsistencies.

 In many cases it’s not possible to break these components into smaller ones because the stateful logic is all over the place. It’s also difficult to test them.
 This is one of the reasons many people prefer to combine React with a separate state management library. However, that often introduces too much abstraction, requires you to jump between different files, and makes reusing components more difficult.

 To solve this, Hooks let you split one component into smaller functions based on what pieces are related (such as setting up a subscription or fetching data), rather than forcing a split based on lifecycle methods. You may also opt into managing the component’s local state with a reducer to make it more predictable.

 ### Classes confuse both people and machines

we’ve found that classes can be a large barrier to learning React. 

Additionally, React has been out for about five years, and we want to make sure it stays relevant in the next five years. As Svelte, Angular, Glimmer, and others show, ahead-of-time compilation of components has a lot of future potential. Especially if it’s not limited to templates. Recently, we’ve been experimenting with component folding using Prepack, and we’ve seen promising early results. However, we found that class components can encourage unintentional patterns that make these optimizations fall back to a slower path. Classes present issues for today’s tools, too. For example, classes don’t minify very well, and they make hot reloading flaky and unreliable. We want to present an API that makes it more likely for code to stay on the optimizable path.