    var salary = "1000$";

    (function () {
        console.log("Original salary was " + salary);

        // var salary = "5000$";

        console.log("My New Salary " + salary);
    })();

 Original salary was 1000$
 My New Salary 1000$


    var salary = "1000$";

    (function () {
        console.log("Original salary was " + salary);

        var salary = "5000$";

        console.log("My New Salary " + salary);
    })();

Original salary was undefined
My New Salary 5000$