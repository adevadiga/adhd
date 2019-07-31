The apply() method calls a function with a given this value, and arguments provided as an array (or an array-like object).

Note: While the syntax of this function is almost identical to that of call(), the fundamental difference is that call() accepts an argument list, while apply() accepts a single array of arguments.

    var max = Math.max.apply(null, [1,2,3,4,5]);
    console.log(max);

    max = Math.max.call(null, 1, 2, 3, 4,5);
    console.log(max);