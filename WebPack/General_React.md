# Webpack

Bundling is the process of following imported files and merging them into a single file: a “bundle”.
This bundle can then be included on a webpage to load an entire app at once.

Bundling is great, but as your app grows, your bundle will grow too.
You need to keep an eye on the code you are including in your bundle so that you don’t accidentally make it so large that your app takes a long time to load.

Code-Splitting is a feature which can create multiple bundles that can be dynamically loaded at runtime.

Code-splitting your app can help you “lazy-load” just the things that are currently needed by the user, which can dramatically improve the performance of your app.

## Import()

The best way to introduce code-splitting into your app is through the dynamic import() syntax.

```
import("./math").then(math => {
  console.log(math.add(16, 26));
});
```

## React.lazy

The React.lazy function lets you render a dynamic import as a regular component.

`const OtherComponent = React.lazy(() => import('./OtherComponent'));`

This will automatically load the bundle containing the OtherComponent when this component is first rendered.

**React.lazy** takes a function that must call a dynamic import(). This must return a Promise which resolves to a module with a default export containing a React component.

The lazy component should then be rendered inside a **Suspense component**, which allows us to show some fallback content (such as a loading indicator) while we’re waiting for the lazy component to load.

```
const OtherComponent = React.lazy(() => import('./OtherComponent'));

function MyComponent() {
  return (
    <div>
      <Suspense fallback={<div>Loading...</div>}>
        <OtherComponent />
      </Suspense>
    </div>
  );
}

```


## Route-based code splitting
