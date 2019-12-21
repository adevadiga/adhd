##Event Loop

The JS engine doesn't run in isolation. It runs inside a hosting environment, which is for most developers the typical web browser.

In other words, the JS engine has had no innate sense of time, but has instead been an on-demand execution environment for any arbitrary snippet of JS.
It's the surrounding environment that has always scheduled "events" (JS code executions).


## ES6

```
var funcs = [];

for (let i = 0; i < 5; i++) {
	funcs.push( function(){
		console.log( i );
	} );
}

funcs[3]();		// 3

Same as:
for (var i = 0; i < 5; i++) {
	let j = i;
	funcs.push( function(){
		console.log( j );
	} );
}

funcs[3]();

```
The `let i` in the for header declares an `i `not just for the for loop itself, but it redeclares a new `i `for each iteration of the loop. That means that closures created inside the loop iteration close over those per-iteration variables the way you'd expect.

## const
If you wanted a constant with the undefined value, you'd have to declare const a = undefined to get it.

const a;//throws Missing initializer in const declaration


## Block scoped functions :)

```
if (something) {
	function foo() {
		console.log( "1" );
	}
}
else {
	function foo() {
		console.log( "2" );
	}
}

foo(); // ReferenceError: foo is not defined
```

Question?
5 + null = ?. null coerces to 0