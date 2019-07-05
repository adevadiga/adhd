Parse & compile costs are no longer as slow as we once thought.
The three things to focus on for JavaScript bundles are:

# List Types
    1. Improve download time
        1. Keep your JavaScript bundles small, especially for mobile devices. Small bundles improve download speeds, lower memory usage, and reduce CPU costs.
        2. Avoid having just a single large bundle; if a bundle exceeds ~50–100 kB, split it up into separate smaller bundles.(HTTP/2 multiplexing)

    2. Improve execution time
        Avoid Long Tasks that can keep the main thread busy and can push out how soon pages are interactive. Post-download, script execution time is now a dominant cost.
    
    3. Avoid large inline scripts (as they’re still parsed and compiled on the main thread). A good rule of thumb is: if the script is over 1 kB, avoid inlining it 

    By splitting up your code and prioritizing the order in which it is loaded, you can get pages interactive faster and hopefully have lower input latency.


What has V8 done to improve parse/compile?
Raw JavaScript parsing speed in V8 has increased 2×. At the same time, raw parse (and compile) cost has become less visible/important due to other optimization work in Chrome that parallelizes it.

V8 has reduced the amount of parsing and compilation work on the main thread by an average of 40%,by parsing and compiling on a worker thread.


In summary, script resources can be streaming-parsed and-compiled on a worker thread, meaning:
    * V8 can parse+compile JavaScript without blocking the main thread.
    * Streaming starts once the full HTML parser encounters a <script> tag. For parser-blocking scripts, the HTML parser yields, while for async scripts it continues.
    * For most real-world connection speeds, V8 parses faster than download, so V8 is done parsing compiling a few milliseconds after the last script bytes are downloaded.

    The not-so-short explanation is… Much older versions of Chrome would download a script in full before beginning to parse it, which is a straightforward approach but it doesn’t fully utilize the CPU. Between versions 41 and 68, Chrome started parsing async and deferred scripts on a separate thread as soon as the download begins.

    Scripts arrive in multiple chunks. V8 starts streaming once it’s seen at least 30 kB.


