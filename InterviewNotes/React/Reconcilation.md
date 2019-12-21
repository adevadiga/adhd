# Reconciliation

There are some generic solutions to this algorithmic problem of generating the minimum number of operations to transform one tree into another. However, the state of the art algorithms have a complexity in the order of O(n3) where n is the number of elements in the tree.

React implements a heuristic O(n) algorithm based on two assumptions:

1. Two elements of different types will produce different trees.
2. The developer can hint at which child elements may be stable across different renders with a key prop.

## The Diffing Algorithm

When diffing two trees, React first compares the two root elements. The behavior is different depending on the types of the root elements.

### Elements Of Different Types

Whenever the root elements have different types, React will tear down the old tree and build the new tree from scratch. Going from <a> to <img>, or from <Article> to <Comment>, or from <Button> to <div> - any of those will lead to a full rebuild.

When tearing down a tree, old DOM nodes are destroyed. Component instances receive componentWillUnmount(). When building up a new tree, new DOM nodes are inserted into the DOM. Component instances receive componentWillMount() and then componentDidMount(). Any state associated with the old tree is lost.

Any components below the root will also get unmounted and have their state destroyed.

```
<div>
  <Counter />
</div>

<span>
  <Counter />
</span>

```
> This will destroy the old Counter and remount a new one.


### DOM Elements Of The Same Type

When comparing two `React DOM elements `of the same type,
React looks at the attributes of both, keeps the same underlying DOM node,
and only updates the changed attributes. 

```
<div className="before" title="stuff" />

<div className="after" title="stuff" />
```
By comparing these two elements, React knows to only modify the className on the underlying DOM node.


When updating style, React also knows to update only the properties that changed. For example:
```
<div style={{color: 'red', fontWeight: 'bold'}} />

<div style={{color: 'green', fontWeight: 'bold'}} />

```
When converting between these two elements, React knows to only modify the color style, not the fontWeight.

> After handling the DOM node, React then recurses on the children.


### Component Elements Of The Same Type

When a component updates, the instance stays the same, so that state is maintained across renders.
React updates the props of the underlying component instance to match the new element, and calls componentWillReceiveProps() and componentWillUpdate() on the underlying instance.

Next, the render() method is called and the diff algorithm recurses on the previous result and the new result.

## Recursing On Children
By default, when recursing on the children of a DOM node, React just iterates over both lists of children at the same time and generates a mutation whenever there’s a difference.

For example, when adding an element at the end of the children, converting between these two trees works well:

```
<ul>
  <li>first</li>
  <li>second</li>
</ul>

<ul>
  <li>first</li>
  <li>second</li>
  <li>third</li>
</ul>

```
React will match the two <li>first</li> trees, match the two <li>second</li> trees, and then insert the <li>third</li> tree.


If you implement it naively, inserting an element at the beginning has worse performance. For example, converting between these two trees works poorly:

```
<ul>
  <li>Duke</li>
  <li>Villanova</li>
</ul>

<ul>
  <li>Connecticut</li>
  <li>Duke</li>
  <li>Villanova</li>
</ul>

```
React will mutate every child instead of realizing it can keep the <li>Duke</li> and <li>Villanova</li> subtrees intact. This inefficiency can be a problem.

### Keys

In order to solve this issue, React supports a key attribute. When children have keys, React uses the key to match children in the original tree with children in the subsequent tree.
For example, adding a key to our inefficient example above can make the tree conversion efficient:

```
<ul>
  <li key="2015">Duke</li>
  <li key="2016">Villanova</li>
</ul>

<ul>
  <li key="2014">Connecticut</li>
  <li key="2015">Duke</li>
  <li key="2016">Villanova</li>
</ul>

```
As a last resort, you can pass an item’s index in the array as a key. This can work well if the items are never reordered, but reorders will be slow.
Reorders can also cause issues with component state when indexes are used as keys. Component instances are updated and reused based on their key. If the key is an index, moving an item changes it. As a result, component state for things like uncontrolled inputs can get mixed up and updated in unexpected ways.