# Symbols
Symbols are a new primitive type in ECMAScript 6. 
They are tokens that serve as unique IDs.

`const mySymbol = Symbol('mySymbol');`

```
const COLOR_RED    = Symbol('Red');
const COLOR_ORANGE = Symbol('Orange');

function getComplement(color) {
    switch (color) {
        case COLOR_RED:
            return COLOR_GREEN;
        case COLOR_ORANGE:
            return COLOR_BLUE;
        default:
            throw new Exception('Unknown color: '+color);
    }
}


Symbol() === Symbol()
false

//Can give description via param
```
Every time you call Symbol('Red'), a new symbol is created. Therefore, COLOR_RED can never be mistaken for another value. That would be different if it were the string 'Red'.