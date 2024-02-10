Homework assignment no. 2, Flows
====================================

**Publication date:**  TBD

**Submission deadline:** TBD

## CHANGELOG

General information
-------------------
This section provides general information about the initial structure of the provided codebase.  

### Project Structure
The structure of the project provided as a base for your implementation should meet the following criteria.

1. Package ```cz.muni.fi.pb162.hw02``` contains classes and interfaces provided as a part of the assignment.
- **Do not modify or add any classes or subpackages into this package.**
2. Package  ```cz.muni.fi.pb162.hw02.impl``` should contain your implementation.
- **Anything outside this package will be ignored during evaluation.**


Additionally, the following applies for the initial contents of ``cz.muni.fi.pb162.hw02.impl``

1) The information in **javadoc has precedence over everything**
2) **Interfaces** must be implemented
3) **Interfaces** must keep predefined methods
4) Otherwise, you can modify the code (unless tests are affected) as you see fit
5) When in doubt, **ASK**

**Note:**  
*While a modification of the interface is not strictly prohibited, you don't want to end with [god object](https://en.wikipedia.org/wiki/God_object) implementations.    
On the other hand, you want to adhere to the [single responsibility principle](https://en.wikipedia.org/wiki/Single-responsibility_principle).  
A rule of thumb should be not to modify interfaces unless required by the assignment. Consider extending the interface as an alternative.*

### Names in This Document
Unless fully classified name is provided, all class names are relative to the package ```cz.muni.fi.pb162.hw02``` or ```cz.muni.fi.pb162.hw02.impl``` for classes implemented as a part of your solution.

### Compiling the Project
The project can be compiled and packaged in the same way you already know.

```bash
$ mvn clean install
```

The only difference is that unlike the seminar project, the checks for missing documentation and a style violation will produce an error this time.
You can disable this behavior temporarily when running this command.

```bash
$ mvn clean install -Dcheckstyle.skip
```

You can consult your seminar teacher to help you set the ```checkstyle.skip``` property in your IDE (or just google it).

### Running All Tests
This time not all tests are executed when compiling the project as showed above. The reason is that `InfiniteSupplierFlowMemoryTest` takes a bit longer as it processes several **billions** numbers. 

There are two ways how to run this tests

1. Set the `runMemoryTest` system property to `true`. This can be done either in IDE (ask your seminar tutor or use google), or when running tests via maven as follows

```bash
$ mvn clean install -DrunMemoryTest=true
```
2. The other way of executing this test is by using the `pedantic` profile defined in the `pom.xml`

```bash
$ mvn clean install -Ppedantic
```
The difference is that the `pedantic` profile also restricts the JVM memory to **8 megabytes** -- as stated later in implementation requirements, your solution should be efficient. 

### Submitting the Assignment
Follow instructions of your tutor because the procedure to submit your solution may differ based on your seminar group. However, there are two ways of submission in general:
* Fork the project, develop your code in a development branch, and finally ask for the merge.
* Submit ```target/homework02-2024-1.0-SNAPSHOT-sources.jar``` to the homework vault.

### Minimal Requirements for Acceptance
- Fulfilling all Java course standards (documentation, conventions, etc.)
- Proper code decomposition
  - Split your code into multiple classes
  - Organize your classes in packages
- Single responsibility
  - Class should ideally have a single purpose
- Usage of `java.util.Stream` is not allowed!!!
  - the goal is to understand how they work by implementing them :)
- Flows don't internally hold onto all elements at once (as there might be infinite number of them)
- Each element of the flow is processed only once


Assignment Description
-------------
In this homework you will implement a simplified alternative to `java.util.Stream`. There are several goals to this excercise

- To roughly understand how `java.util.Stream`s work under the hood
- To learn how to use lambda expressions
- To understand what a functional interface is 
- To get acquainted with some of the functional interfaces from `java.util.function.*` (e.g. `Supplier`)
- To obtain elementary understanding of generic types in java 

### Usage example
The implemented API should be roughly usable as demonstrated bellow

```java
// Example of Flow backed by collection
Flows.of(List.of(0, 1, 2, 3, 4, 5))
        .map(x -> x * 2)
        .filter(x -> x > 4)
        .limit(3)
        .map(String::valueOf)
        .map(x -> x + "A")
        .forEach(System.out::println)

// Outputs:
// > 6A
// > 8A
// > 10A
        
        
// Example of Flow backed by a supplier
var counter = new AtomicInteger(0);
var flow = Flows.of(counter::getAndIncrement)
    .skip(5)
    .limit(5) // without this the Flow would print numbers infinitely
    .forEach(System.out::println);

// Outputs:
// > 4
// > 5
// > 6
// > 7
// > 8
```

Look at the provided tests for more examples. 

### Generic Data Types in Java
[Generics](https://www.baeldung.com/java-generics) are Java's implementation of programming language concept called [Parametric polymorphism](https://en.wikipedia.org/wiki/Parametric_polymorphism) -- type variables for your types. Although this may sound complicated, on the most basic level, generics provide the answer to the question "**...of what?**". 

_Note: The linked article on Baeldung site goes into a bit more detail. You don't need to understand bounded and wildcards generics for this assignment.__  


Looking at variable `List myList`, it is obvious that `myList` is a list of elements. Elements **of what?**. Without generics we wouldn't know (_this is a lie, which will be corrected in a moment_). What about `List<String> myList`? It is still obviously a list. **List of what?** Quite obviously it is a list **of Strings** as stated by The `<String>` part of the type declaration. Similarly `List<Integer> myList` would be a list of Integers. 

_Note: We lied to you about not knowing the type of elements for `List myList`. The type is known as `List myList` in Java is the same as `List<Object> myList`. However, using `List` without the type parameter will often result in compiler warnings and is not considered type safe._ 

### Implementation Notes
There are generally two types of operations/methods you can perform on the `Flow`.

- **Intermediate operations** are registered on method call, however the actual work is only performed once a **terminal operation** is called. 
- **Terminal operations** on method call trigger the actual execution of all registered **intermediate operations** in order, followed by the execution of the single called  **terminal operation**. Once a terminal operation is called the flow is terminated and no further operations may be called on it (you don't need to ensure this, we guarantee that this won't be attempted in tests).


Consult javadocs for more details. Creating a main method and experimenting with your implementation on top of provided tests is highly recommended.

### Implementation Hints and Ideas
The goal of this section is to provide you with a rough outline of possible implementation. By no means is this supposed to be a detail description nor is it the only possible approach towards a successful solution. 


#### Generic idea 

The `Flow` interface represents an API of a potentially infinite flow of Items. It is supposed to provide an interface of "what can be done" with these items. It doesn't necessarily mean that the item manipulation logic needs to be fully located in the implementing classes. 


The `FlowSource` interface is the source of items. It should internally track what the "current" item is, it should be able to provide this item, and it should also be able to advance to the next item (if available). At the very minimum you will have to crete two implementations of `FlowSource`. One that wraps an `Iterator<T>` and provides access to its items, and one that wraps a `Supplier<T>` and server items from it. The first one should be pretty straightforward, the later will have to consider the potential terminal value (which signals that there are no more valid elements). The `FlowSource#advance()` method is the right place where to determine whether next item is available.


#### Idea No. 1, Decorated Sources 
This approach takes advantage of the [Decorator Pattern](https://www.digitalocean.com/community/tutorials/decorator-design-pattern-in-java-example) which provides the means of modifying object behaviour at runtime. The idea is to enrich or restrict object's functionality by wrapping the modified object by another object of the same type, that internally uses the original. Later in the course you will learn that the IO APIs in java are based on this concept.

 `Flow` and `FlowSource` implementations can take advantage of this pattern in a sense that a "starting" `Flow` is always created with one of the two basic `FlowSource` implementations described above, and when you call an intermediate operation (say `Flow#filter()`) the method just returns a new `Flow`, that will use a "decorated" `FlowSource` (say a `FilteringFlowSource`). This "decorated" source will enrich the functionality of the original source (in our example it would add the filtering logic to the `FlowSource#advance()` method). Other operations could be implemented in similar fashion.  
 
#### Idea No 2, Function Composition
Once agan a `Flow` is always created with one of the two basic `FlowSource`s, however this time there would be no "decorators". Instead, the `Flow` implementation will internally hold a reference to `Predicate<T> currentFilter` and `Function<U, T> currentTransformation`. When `Flow#filter(filter)` is called, `currentFilter` predicated will be composed with the one provided to the `Flow#filter(filter)`.

```java
currentFilter = currentFilter.and(filter);
```

Instance of `Flow` will then have to be responsible for applying this filter when interacting with its `FlowSource`.


Similarly, a composition can be used for transformations. However, this time, the `Flow#map(transformation)`will need to return a new instance of `Flow` since the type of the elements in the transformed `Flow` is going to be different from the original. So you would do something like

```java
return new MyFlow<>(source, currentTransformation.andThen(transformation));
```

_Note: This approach comes as less natural to the author of this assignment, as it somewhat complicates the implementation of other methods (such as `Flow#skip()`. However, you might feel differently about it!_