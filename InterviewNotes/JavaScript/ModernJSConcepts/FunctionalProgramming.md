# Functional Programming

Now we've learned about purity, statelessness, immutability, declarative programming, and higher-order functions. These are all concepts that are important in understanding the functional programming paradigm.

* Core functionality is implemented using pure functions without side effects.
* Data is immutable.
* Functional programs are stateless.
* Imperative container code manages side effects and executes declarative, pure core code.

> *If we tried to write a JavaScript web application composed of nothing but pure functions with no side effects, it couldn't interact with its environment and therefore wouldn't be particularly useful.

```
const fpCopy = `Functional programming is powerful and enjoyable to write. It's very cool!`;

// remove punctuation from string
const stripPunctuation = (str) =>
  str.replace(/[.,\/#!$%\^&\*;:{}=\-_`~()]/g, '');

// split passed string on spaces to create an array
const getArr = (str) =>
  str.split(' ');

// count items in the passed array
const getWordCount = (arr) =>
  arr.length;

// find items in the passed array longer than 5 characters
// make items lower case
const getKeywords = (arr) =>
  arr
    .filter(item => item.length > 5)
    .map(item => item.toLowerCase());

// process copy to prep the string, create an array, count words, and get keywords
function processCopy(str, prepFn, arrFn, countFn, kwFn) {
  const copyArray = arrFn(prepFn(str));

  console.log(`Word count: ${countFn(copyArray)}`);
  console.log(`Keywords: ${kwFn(copyArray)}`);
}

processCopy(fpCopy, stripPunctuation, getArr, getWordCount, getKeywords);
// result: Word count: 11
// result: Keywords: functional,programming,powerful,enjoyable

```