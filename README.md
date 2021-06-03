# CircusServer
Backend logic for a ranked and free Overwatch alternative ladder.

# Contributing
Any code block that has > 1 line inside of it, braces start on a new line. 
```
// This is fine
private void getSomething() {
    return this.something;
}

// This is not fine.
private void doSomething(Object something) {
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
}

// Do this
private void doSomething(Object something)
{
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
    // doing things
}

```
While I respect your opinion to never dedicate entire lines to opening brackets, I don't care and it's wrong. Thank you.

In addition, passing arguemnts should look like this:
```
// Don't do this
call(arg1, arg2, arg3);

// If you do this I'm suing you
call(arg1,arg2,arg3);

// Do this
call( arg1, arg2, arg3 );
```

The formatting in the project is currently inconsistent because I had a change of heart in the middle of development. As I come across things that do not follow my new-found fondness of as readable of code as possible, I will fix them. But I'm not going out of my way to each class to do so as of yet.

## I'll help one person who I know still plays Overwatch set this up.
I won't run this myself. The game sucks.
