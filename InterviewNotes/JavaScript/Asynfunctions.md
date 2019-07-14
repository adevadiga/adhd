Async functions - making promises friendly

They allow you to write promise-based code as if it were synchronous, but without blocking the main thread. They make your asynchronous code less "clever" and more readable.

    `async function myFirstAsyncFunction() {
        try {
            const fulfilledValue = await promise;
        }
        catch (rejectedValue) {
            // …
        }
    }`

If you use the async keyword before a function definition, you can then use await within the function. When you await a promise, the function is paused in a non-blocking way until the promise settles. If the promise fulfills, you get the value back. If the promise rejects, the rejected value is thrown.

Example: Logging a fetch

Say we wanted to fetch a URL and log the response as text. Here's how it looks using promises:


    function logFetch(url) {
        return fetch(url)
            .then(response => response.text())
            .then(text => {
                console.log(text);
            }).catch(err => {
                console.error('fetch failed', err);
            });
    }

    async function logFetch(url) {
        try {
            const response = await fetch(url);
            console.log(await response.text());
        } catch (Exception e) {
            console.log('fetch failed', err);
        }
    }


Async return values:

Async functions always return a promise, whether you use await or not.
That promise resolves with whatever the async function returns, or rejects with whatever the async function throws.


