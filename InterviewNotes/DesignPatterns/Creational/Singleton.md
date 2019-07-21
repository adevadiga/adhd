Singleton

    In plain words
        Ensures that only one object of a particular class is ever created.

    Real world example
        There can only be one president of a country at a time. The same president has to be brought to action, whenever duty calls. President here is singleton.

    Singleton pattern is actually considered an anti-pattern and overuse of it should be avoided. It is not necessarily bad and could have some valid use-cases but should be used with caution because it introduces a <b>global state in your application</b> and change to it in one place could affect in the other areas and it could become pretty difficult to debug. The other bad thing about them is it makes your code tightly coupled plus mocking the singleton could be difficult.

    Programmatic Example
        To create a singleton, make the constructor private, disable cloning, disable extension and create a static variable to house the instance and double locking.




