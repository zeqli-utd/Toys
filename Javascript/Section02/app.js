
b();
console.log(a);  // Hoisting

var a = 'Hello World!';

function b() {
  console.log('Called b');
}

/*
Hoisting

Execution context is created (CREATION PHASE)
  - Setup Memory Space for variables and functions 
  - Called "Hoisting"
  - Function is created in the memory. 

Actually order are below:

function b() {
  console.log('Called b');
}
var a;

b();
console.log(a);  // Hoisting

a = 'Hello World!';





*/