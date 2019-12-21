# Closures

Avoid passing new closures to subcomponents, like so:

```
<input
            type="text"
            value={model.name}
            // onChange={(e) => { model.name = e.target.value }}
            // ^ Not this. Use the below:
            onChange={this.handleChange}
            placeholder="Your Name"/>

```

Here’s why: every time the parent component renders, a new function is created and passed to the input.

If the input were a React component, this would automatically trigger it to re-render, regardless of whether its other props have actually changed.


Avoid the following ES6 syntax:

`const ExpandableForm = ({ onExpand, expanded, children }) => {`

Looks very modern, but the function here is actually unnamed.

This lack of name will not be a problem if your Babel is set up correctly — but if it’s not, any errors will show up as occurring in <<anonymous>> which is terrible for debugging.

Unnamed functions can also cause problems with Jest, a React testing library. Due to the potential for difficult-to-understand bugs (and the lack of real benefit) we recommend using function instead of const.
