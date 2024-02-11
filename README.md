<div style="text-align:center;"><img src="src/main/resources/pemacs-logo.png"></div>

# Skill Builder 2 - Defining Classes and Methods

## Learning Outcomes

By the end of this activity, a student should be able to:

1. Implement a simple Java class.
2. Include and use static fields.
3. Implement accessor and mutator methods
4. Design objects that interact with each other.

## The Grok Coalition

Imagine you are writing a video game that has a character called a Grok. The Grok is a simple creature that can eat power pills and increase its power level by the amount of power available in the power pill. Let's begin by implementing a PowerPill class that represents all PowerPill objects.  You will implemnet the Grok class in a future Skill Builder.

## The PowerPill Class

A power pill is an object that is available for the Grok to ingest.  Once taken, it transfers its power to the Grok.  A client can create different power pills with varying levels of power and names.  Provided below are template classes.

```java
public class PowerPill
{
    
}

```

## Required Activities

You are to implement the following in the `PowerPill` class.

1. Add a ***static*** integer field called `DEFAULT_POWER` and set it to 10. (**NOTE: static variables can be initialized outside of constructors, but NOT INSTANCE variables!**)
2. Add an integer field called `power`.
3. Add a String field called `name`.
4. Add a value constructor with a string parameter called `name`.  Add the javadoc comment below before the constructor name.
<pre>
	/**
     \* Initializes this power pill to a default power value
     \* and sets the name of the pill to name.
     \* @param name the name of this power pill.
     */
</pre>
5. Add a value constructor where the first parameter is a string called `name` and the second parameter is an integer called `power`.

	<pre>
		/**
	     * Initializes this power pill to the value of power
	     * and sets the name of the pill to name.
	     * @param name the name of this power pill
	     * @param power the power level of this power pill.
	     */
	</pre>

6. Add getter methods with an appropriate javadoc comment for each method.
7. Add setter methods with an appropriate javadoc comment for each method.
8. Add a `toString` method that returns a string formatted as,
   
   <pre>
   PowerPill &lt;name&gt; = &lt;power&gt;
   </pre>
   
   where `<name>` is replaced by the PowerPill object's name and <br>
         `<power>` is replaced with the PowerPill object's power

For example,

```
PowerPill p = new PowerPill("Pink", 20);
System.out.printn(p);
```

results in,

```
PowerPill Pink = 20
```

## How To Use the Class

An example of how the class may be used is,

```java
PowerPill bluePill = new PowerPill("Blue");
PowerPill redPill = new PowerPill("Red",40);

int bluePower = bluePill.getPower();
int redpower = redPill.getPower();

```

## Implementing and Testing the Classes

In this Skill Builder, you are being asked to implement the class using the steps outlined in the **Required Activity** section.  The class PowerPill contains TODO comments that match the steps provided in the **Required Activities** section.

You will notice that there are errors in the `PowerPillTest` class.  This is because the methods required in the `PowerPill` class are not implemented.  You MUST complete each of the requirements outlined in the **Required Activity** section until all syntax errors in `PowerPillTest` are resolved, then begin your testing.

**Note:** *If your constructors are incorrectly implemented, then the getter methods will fail the test.  So, make sure the constructors pass their tests!*

In general, getting used to the idea of using the symbolic debugger to locate the source of a bug is an excellent overall strategy.  So, use it on every method that fails the test! Place a breakpoint at the beginning of the method or in the test method of the test class and use the symbolic debugger to find the problem.

<span style="font-size:2em;color:green;">Happy Coding!</span>