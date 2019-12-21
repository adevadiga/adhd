# Testing React components

Jest is a JavaScript test runner that lets you access the DOM via jsdom.
While jsdom is only an approximation of how the browser works, it is often good enough for testing React components.

React Testing Library is a set of helpers that let you test React components without relying on their implementation details. 

## Testing Recipes

### Setup/Teardown

For each test, we usually want to render our React tree to a DOM element that’s attached to document. This is important so that it can receive DOM events. When the test ends, we want to “clean up” and unmount the tree from the document.
`unmountComponentAtNode(container);`

### act()

When writing UI tests, tasks like rendering, user events, or data fetching can be considered as “units”of interaction with a user interface.  
React provides a helper called act() that makes sure all updates related to these “units” have been processed and applied to the DOM before you make any assertions:

### Rendering

```
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

it("renders with or without a name", () => {
  act(() => {
    render(<Hello />, container);
  });
  expect(container.textContent).toBe("Hey, stranger");

  act(() => {
    render(<Hello name="Jenny" />, container);
  });
  expect(container.textContent).toBe("Hello, Jenny!");

});

```

### Data Fetching

```
it("renders user data", async () => {
  const fakeUser = {
    name: "Joni Baez",
    age: "32",
    address: "123, Charming Avenue"
  };

  jest.spyOn(global, "fetch").mockImplementation(() =>
    Promise.resolve({
      json: () => Promise.resolve(fakeUser)
    })
  );

  // Use the asynchronous version of act to apply resolved promises
  await act(async () => {
    render(<User id="123" />, container);
  });

  expect(container.querySelector("summary").textContent).toBe(fakeUser.name);

  // remove the mock to ensure tests are completely isolated
  global.fetch.mockRestore();
});
```

### Mocking Modules

```
jest.mock("./map", () => {
  return function DummyMap(props) {
    return (
      <div data-testid="map">
        {props.center.lat}:{props.center.long}
      </div>
    );
  };
});
```

## Events

## Snapshots

It’s typically better to make more specific assertions than to use snapshots. These kinds of tests include implementation details so they break easily, and teams can get desensitized to snapshot breakages.
Selectively mocking some child components can help reduce the size of snapshots and keep them readable for the code review.

