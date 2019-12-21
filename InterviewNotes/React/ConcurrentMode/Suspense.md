# Concurrent Mode

React 16.6 added a `<Suspense>` component that lets you “wait” for some code to load and declaratively specify a loading state (like a spinner) while we’re waiting:

```
const ProfilePage = React.lazy(() => import('./ProfilePage')); // Lazy-loaded

// Show a spinner while the profile is loading
<Suspense fallback={<Spinner />}>
  <ProfilePage />
</Suspense>
```

Suspense for Data Fetching is a new feature that lets you also use <Suspense> to declaratively “wait” for anything else, including data.

> Suspense is not a data fetching library. It’s a mechanism for data fetching libraries to communicate to React that the data a component is reading is not ready yet. React can then wait for it to be ready and update the UI. 

## What Suspense Lets You Do

* It lets data fetching libraries deeply integrate with React. 
* It lets you orchestrate intentionally designed loading states.
* It helps you avoid race conditions. Even with await, asynchronous code is often error-prone. * * Suspense feels more like reading data synchronously — as if it was already loaded.

Although it’s technically doable, Suspense is not currently intended as a way to start fetching data when a component renders. Rather, it lets components express that they’re “waiting” for data that is already being fetched

## Traditional Approaches vs Suspense

### Fetch-on-render (for example, fetch in useEffect):

Start rendering components. Each of these components may trigger data fetching in their effects and lifecycle methods. This approach often leads to “waterfalls”.

### Fetch-then-render (for example, Relay without Suspense):

Start fetching all the data for the next screen as early as possible. When the data is ready, render the new screen. We can’t do anything until the data arrives.

### Render-as-you-fetch (for example, Relay with Suspense):

Start fetching all the required data for the next screen as early as possible, and start rendering the new screen immediately — before we get a network response. As data streams in, React retries rendering components that still need data until they’re all ready.


Waterfalls are common in code that fetches data on render. They’re possible to solve, but as the product grows, many people prefer to use a solution that guards against this problem.


## Approach 2: Fetch-Then-Render (not using Suspense)

Libraries can prevent waterfalls by offering a more centralized way to do data fetching. For example, Relay solves this problem by moving the information about the data a component needs to statically analyzable fragments, which later get composed into a single query.


> So fetching all data for the new screen and then rendering is often a more practical option.


