
    function foo ()  {
    console.log(a);//undefined - hoisting
    //console.log(b);//ReferenceError: b is not defined
    var a = 10;
    console.log(a);
    }
    
    foo();

    function foo3 ()  {
    console.log(a);//ReferenceError: a is not defined
    let a = 10;
    console.log(a);
    }
    foo3();