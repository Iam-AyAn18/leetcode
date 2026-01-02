/**
 * JAVA TRICKY INTERVIEW QUESTIONS
 * For Backend Engineers with 5 Years Experience
 *
 * Run each section and predict the output BEFORE running!
 */

public class JavaTrickyQuestions {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("JAVA TRICKY INTERVIEW QUESTIONS");
        System.out.println("=".repeat(60));

        // Uncomment each section to test

        section1_StaticVsInstance();
        Section2.section2_MethodHidingVsOverriding();
        Section3.section3_StringPoolTricks();
        Section4.section4_InheritanceConstructorChain();
        // Section5.section5_ExceptionInFinally(); // Throws exception - uncomment to test
        Section6.section6_AutoboxingTraps();
        Section7.section7_OverloadingConfusion();
        Section8.section8_StaticBlockOrder();
        Section9.section9_CovariantReturnType();
        Section10.section10_FinallyReturnTrap();
        Section11.section11_PassByValueTrap();
        Section12.section12_InterfaceDefaultMethods();
        Section13.section13_InstanceOfNull();
        Section14.section14_HashCodeEquals();
        Section15.section15_ImmutableClassTraps();

        // NEW SECTIONS
        Section16.section16_ExceptionHierarchy();
        Section17.section17_ThreadLocalTraps();
        Section18.section18_ComparableComparator();
        Section19.section19_GenericsWildcards();
        Section20.section20_LambdaClosures();
        Section21.section21_SingletonPatterns();
        Section22.section22_TrickySnippets();
        Section23.section23_MultithreadingTraps();
        Section24.section24_HashMapInternals();
        Section25.section25_ClassLoaderReflection();
        Section26.section26_Java8PlusTraps();
    }

    // ============================================================
    // SECTION 1: Static vs Instance
    // ============================================================
    static void section1_StaticVsInstance() {
        System.out.println("\n--- SECTION 1: Static vs Instance ---");

        // QUESTION: What's the output?
        Parent1 obj = new Child1();
        System.out.println(obj.x);           // ?
        System.out.println(obj.getX());      // ?
        obj.staticMethod();                   // ?
        obj.instanceMethod();                 // ?

        /*
         * ANSWER:
         * obj.x = 10 (field hiding, resolved at compile time by reference type)
         * obj.getX() = 20 (method overriding, resolved at runtime)
         * obj.staticMethod() = "Parent static" (static methods are hidden, not overridden)
         * obj.instanceMethod() = "Child instance" (overriding)
         *
         * KEY CONCEPT:
         * - Fields and static methods: Resolved by REFERENCE TYPE (compile time)
         * - Instance methods: Resolved by ACTUAL OBJECT TYPE (runtime polymorphism)
         */
    }
}

class Parent1 {
    int x = 10;
    static void staticMethod() { System.out.println("Parent static"); }
    void instanceMethod() { System.out.println("Parent instance"); }
    int getX() { return x; }
}

class Child1 extends Parent1 {
    int x = 20;  // Field hiding (not overriding)
    static void staticMethod() { System.out.println("Child static"); }  // Method hiding
    void instanceMethod() { System.out.println("Child instance"); }     // Method overriding
    int getX() { return x; }
}


// ============================================================
// SECTION 2: Method Hiding vs Overriding
// ============================================================
class Section2 {
    static void section2_MethodHidingVsOverriding() {
        System.out.println("\n--- SECTION 2: Method Hiding vs Overriding ---");

        // QUESTION: What's the output?
        Animal2 a = new Dog2();
        a.eat();        // ?
        a.sleep();      // ?

        Dog2 d = new Dog2();
        d.eat();        // ?
        d.sleep();      // ?

        /*
         * ANSWER:
         * a.eat() = "Dog eats" (overriding - runtime polymorphism)
         * a.sleep() = "Animal sleeps" (static hiding - compile time)
         * d.eat() = "Dog eats"
         * d.sleep() = "Dog sleeps"
         *
         * RULE: Static methods are NEVER overridden, they are HIDDEN
         */
    }
}

class Animal2 {
    void eat() { System.out.println("Animal eats"); }
    static void sleep() { System.out.println("Animal sleeps"); }
}

class Dog2 extends Animal2 {
    void eat() { System.out.println("Dog eats"); }
    static void sleep() { System.out.println("Dog sleeps"); }
}


// ============================================================
// SECTION 3: String Pool Tricks
// ============================================================
class Section3 {
    static void section3_StringPoolTricks() {
        System.out.println("\n--- SECTION 3: String Pool Tricks ---");

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        String s4 = new String("Hello").intern();
        String s5 = "Hel" + "lo";           // Compile-time constant
        String s6 = "Hel";
        String s7 = s6 + "lo";              // Runtime concatenation

        System.out.println("s1 == s2: " + (s1 == s2));       // ?
        System.out.println("s1 == s3: " + (s1 == s3));       // ?
        System.out.println("s1 == s4: " + (s1 == s4));       // ?
        System.out.println("s1 == s5: " + (s1 == s5));       // ?
        System.out.println("s1 == s7: " + (s1 == s7));       // ?
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // ?

        /*
         * ANSWER:
         * s1 == s2: true (both from string pool)
         * s1 == s3: false (s3 is new object on heap)
         * s1 == s4: true (intern() returns pool reference)
         * s1 == s5: true (compile-time constant folding)
         * s1 == s7: false (runtime concat creates new object)
         * s1.equals(s3): true (content comparison)
         */
    }
}


// ============================================================
// SECTION 4: Inheritance Constructor Chain
// ============================================================
class Section4 {
    static void section4_InheritanceConstructorChain() {
        System.out.println("\n--- SECTION 4: Constructor Chain ---");

        // QUESTION: What's the order of output?
        new Child4();

        /*
         * ANSWER (in order):
         * 1. Parent static block
         * 2. Child static block
         * 3. Parent instance block
         * 4. Parent constructor
         * 5. Child instance block
         * 6. Child constructor
         *
         * ORDER: Static blocks (parent→child) → Instance blocks + Constructors (parent→child)
         */
    }
}

class Parent4 {
    static { System.out.println("1. Parent static block"); }
    { System.out.println("3. Parent instance block"); }
    Parent4() { System.out.println("4. Parent constructor"); }
}

class Child4 extends Parent4 {
    static { System.out.println("2. Child static block"); }
    { System.out.println("5. Child instance block"); }
    Child4() { System.out.println("6. Child constructor"); }
}


// ============================================================
// SECTION 5: Exception in Constructor and Catch/Finally
// ============================================================
class Section5 {
    static void section5_ExceptionInFinally() {
        System.out.println("\n--- SECTION 5: Exception Handling ---");

        // QUESTION 1: What happens?
        try {
            System.out.println("Try block");
            throw new RuntimeException("Exception 1");
        } catch (Exception e) {
            System.out.println("Catch block: " + e.getMessage());
            throw new RuntimeException("Exception 2");
        } finally {
            System.out.println("Finally block");
            // If you throw here, it REPLACES the exception from catch!
            // throw new RuntimeException("Exception 3");
        }

        /*
         * Output:
         * Try block
         * Catch block: Exception 1
         * Finally block
         * Then RuntimeException "Exception 2" is thrown
         *
         * KEY: Finally always runs (except System.exit())
         * If finally throws, it REPLACES any pending exception!
         */
    }
}


// ============================================================
// SECTION 6: Autoboxing Traps
// ============================================================
class Section6 {
    static void section6_AutoboxingTraps() {
        System.out.println("\n--- SECTION 6: Autoboxing Traps ---");

        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        Integer e = new Integer(127);

        System.out.println("a == b (127): " + (a == b));   // ?
        System.out.println("c == d (128): " + (c == d));   // ?
        System.out.println("a == e: " + (a == e));         // ?
        System.out.println("a.equals(e): " + a.equals(e)); // ?

        // More traps
        Long x = 100L;
        Integer y = 100;
        System.out.println("x.equals(y): " + x.equals(y)); // ?

        /*
         * ANSWER:
         * a == b: true (Integer cache: -128 to 127)
         * c == d: false (outside cache, different objects)
         * a == e: false (new always creates new object)
         * a.equals(e): true
         * x.equals(y): false (different types!)
         *
         * CACHE RANGES:
         * - Integer: -128 to 127
         * - Byte: -128 to 127 (all values)
         * - Short: -128 to 127
         * - Long: -128 to 127
         * - Character: 0 to 127
         */
    }
}


// ============================================================
// SECTION 7: Method Overloading Confusion
// ============================================================
class Section7 {
    static void section7_OverloadingConfusion() {
        System.out.println("\n--- SECTION 7: Overloading Confusion ---");

        Overloader7 o = new Overloader7();

        o.print(null);              // ?
        o.print((Object) null);     // ?

        o.calculate(5, 10);         // ?
        o.calculate(5.0, 10);       // ?
        o.calculate(5, 10L);        // ?

        /*
         * ANSWER:
         * o.print(null): "String" - Most specific type wins
         * o.print((Object) null): "Object" - Explicit cast
         *
         * o.calculate(5, 10): "int, int"
         * o.calculate(5.0, 10): "double, int"
         * o.calculate(5, 10L): "int, long"
         *
         * RULE: Compiler picks MOST SPECIFIC method at compile time
         * Widening: byte → short → int → long → float → double
         */
    }
}

class Overloader7 {
    void print(Object o) { System.out.println("Object"); }
    void print(String s) { System.out.println("String"); }
    // void print(Integer i) { } // Adding this would cause ambiguity with null!

    void calculate(int a, int b) { System.out.println("int, int"); }
    void calculate(double a, int b) { System.out.println("double, int"); }
    void calculate(int a, long b) { System.out.println("int, long"); }
}


// ============================================================
// SECTION 8: Static Block Execution Order
// ============================================================
class Section8 {
    static void section8_StaticBlockOrder() {
        System.out.println("\n--- SECTION 8: Static Block Order ---");

        System.out.println("Value of x: " + StaticOrder8.x);

        /*
         * Output:
         * Static block 1
         * Static block 2
         * Value of x: 20
         *
         * Static blocks run in order of appearance, only ONCE when class loads
         */
    }
}

class StaticOrder8 {
    static int x = 10;
    static {
        System.out.println("Static block 1");
        x = 15;
    }
    static int y = x + 5;
    static {
        System.out.println("Static block 2");
        x = 20;
    }
}


// ============================================================
// SECTION 9: Covariant Return Type
// ============================================================
class Section9 {
    static void section9_CovariantReturnType() {
        System.out.println("\n--- SECTION 9: Covariant Return Type ---");

        Parent9 p = new Child9();
        System.out.println(p.get().getClass().getSimpleName());  // ?

        Child9 c = new Child9();
        System.out.println(c.get().getClass().getSimpleName());  // ?

        /*
         * ANSWER:
         * Both print "Child9"
         *
         * Covariant return: Child can return a subtype of parent's return type
         * This is valid overriding!
         */
    }
}

class Parent9 {
    Parent9 get() { return new Parent9(); }
}

class Child9 extends Parent9 {
    @Override
    Child9 get() { return new Child9(); }  // Valid! Covariant return
}


// ============================================================
// SECTION 10: Finally Return Trap (VERY TRICKY!)
// ============================================================
class Section10 {
    static void section10_FinallyReturnTrap() {
        System.out.println("\n--- SECTION 10: Finally Return Trap ---");

        System.out.println("Result: " + getValue());  // ?

        /*
         * ANSWER: 30
         *
         * WHY? The return in finally OVERRIDES the return in try!
         * This is a BAD practice but common interview question.
         *
         * The try block's return value (10) is computed and stored,
         * but finally's return replaces it.
         */
    }

    static int getValue() {
        try {
            return 10;
        } finally {
            return 30;  // This overrides try's return!
        }
    }
}


// ============================================================
// SECTION 11: Pass by Value Trap
// ============================================================
class Section11 {
    static void section11_PassByValueTrap() {
        System.out.println("\n--- SECTION 11: Pass by Value Trap ---");

        // Primitives
        int num = 10;
        changeValue(num);
        System.out.println("Primitive after change: " + num);  // ?

        // Objects - reference copy
        StringBuilder sb = new StringBuilder("Hello");
        changeContent(sb);
        System.out.println("StringBuilder after changeContent: " + sb);  // ?

        StringBuilder sb2 = new StringBuilder("Hello");
        reassignReference(sb2);
        System.out.println("StringBuilder after reassign: " + sb2);  // ?

        // Array trap
        int[] arr = {1, 2, 3};
        modifyArray(arr);
        System.out.println("Array after modify: " + arr[0]);  // ?

        /*
         * ANSWER:
         * Primitive: 10 (unchanged - pass by value)
         * StringBuilder after changeContent: "HelloWorld" (content modified via reference copy)
         * StringBuilder after reassign: "Hello" (reassigning reference doesn't affect original)
         * Array after modify: 100 (arrays are objects, content can be modified)
         *
         * JAVA IS ALWAYS PASS BY VALUE!
         * For objects, the VALUE of the reference is passed (not the object itself)
         */
    }

    static void changeValue(int x) { x = 20; }
    static void changeContent(StringBuilder s) { s.append("World"); }
    static void reassignReference(StringBuilder s) { s = new StringBuilder("New"); }
    static void modifyArray(int[] a) { a[0] = 100; }
}


// ============================================================
// SECTION 12: Interface Default Methods & Diamond Problem
// ============================================================
interface Interface12A {
    default void show() { System.out.println("Interface A"); }
}

interface Interface12B {
    default void show() { System.out.println("Interface B"); }
}

class Section12 implements Interface12A, Interface12B {
    // MUST override when two interfaces have same default method!
    @Override
    public void show() {
        Interface12A.super.show();  // Can call specific interface
        System.out.println("Class implementation");
    }

    static void section12_InterfaceDefaultMethods() {
        System.out.println("\n--- SECTION 12: Interface Default Methods ---");

        new Section12().show();

        /*
         * Output:
         * Interface A
         * Class implementation
         *
         * RULES:
         * 1. Class methods win over interface default methods
         * 2. More specific interface wins (sub-interface over super-interface)
         * 3. If conflict, class MUST override and resolve
         */
    }
}


// ============================================================
// SECTION 13: instanceof with null
// ============================================================
class Section13 {
    static void section13_InstanceOfNull() {
        System.out.println("\n--- SECTION 13: instanceof with null ---");

        String s = null;
        System.out.println("null instanceof String: " + (s instanceof String));  // ?
        System.out.println("null instanceof Object: " + (null instanceof Object));  // ?

        Object obj = "Hello";
        System.out.println("String obj instanceof String: " + (obj instanceof String));  // ?

        /*
         * ANSWER:
         * null instanceof String: false
         * null instanceof Object: false
         * String obj instanceof String: true
         *
         * null instanceof ANYTHING is always FALSE!
         */
    }
}


// ============================================================
// SECTION 14: hashCode and equals Contract
// ============================================================
class Section14 {
    static void section14_HashCodeEquals() {
        System.out.println("\n--- SECTION 14: hashCode & equals ---");

        Person14 p1 = new Person14("John", 25);
        Person14 p2 = new Person14("John", 25);

        System.out.println("p1.equals(p2): " + p1.equals(p2));  // ?
        System.out.println("p1 == p2: " + (p1 == p2));          // ?

        java.util.HashSet<Person14> set = new java.util.HashSet<>();
        set.add(p1);
        set.add(p2);
        System.out.println("Set size: " + set.size());  // ?

        /*
         * With ONLY equals overridden (no hashCode):
         * - equals: true
         * - ==: false
         * - Set size: 2 (WRONG! Should be 1)
         *
         * With BOTH equals AND hashCode overridden:
         * - Set size: 1 (CORRECT!)
         *
         * CONTRACT:
         * 1. If equals() returns true, hashCode() MUST return same value
         * 2. If hashCode() same, equals() may or may not be true
         * 3. equals() must be: reflexive, symmetric, transitive, consistent
         */
    }
}

class Person14 {
    String name;
    int age;

    Person14(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person14 person = (Person14) o;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, age);
    }
}


// ============================================================
// SECTION 15: Immutable Class Traps
// ============================================================
class Section15 {
    static void section15_ImmutableClassTraps() {
        System.out.println("\n--- SECTION 15: Immutable Class Traps ---");

        // This class looks immutable but ISN'T!
        int[] scores = {90, 85, 88};
        BrokenImmutable15 broken = new BrokenImmutable15("John", scores);
        System.out.println("Before: " + broken.getScores()[0]);

        // External modification
        scores[0] = 100;
        System.out.println("After external change: " + broken.getScores()[0]);  // ?

        // Internal modification
        broken.getScores()[1] = 200;
        System.out.println("After getter change: " + broken.getScores()[1]);  // ?

        /*
         * ANSWER:
         * After external change: 100 (BROKEN! Should still be 90)
         * After getter change: 200 (BROKEN! Should still be 85)
         *
         * TO MAKE TRULY IMMUTABLE:
         * 1. Make class final
         * 2. Make all fields private and final
         * 3. Don't provide setters
         * 4. Deep copy mutable objects in constructor
         * 5. Return copies of mutable objects in getters
         */
    }
}

// BROKEN Immutable - common mistake!
class BrokenImmutable15 {
    private final String name;
    private final int[] scores;  // Mutable object!

    BrokenImmutable15(String name, int[] scores) {
        this.name = name;
        this.scores = scores;  // WRONG! Should deep copy
    }

    String getName() { return name; }
    int[] getScores() { return scores; }  // WRONG! Should return copy
}

// CORRECT Immutable
final class CorrectImmutable15 {
    private final String name;
    private final int[] scores;

    CorrectImmutable15(String name, int[] scores) {
        this.name = name;
        this.scores = scores.clone();  // Deep copy!
    }

    String getName() { return name; }
    int[] getScores() { return scores.clone(); }  // Return copy!
}


// ============================================================
// BONUS: More Tricky Scenarios to Remember
// ============================================================

/*
 * ADDITIONAL TRICKY CONCEPTS:
 *
 * 1. PRIVATE METHODS:
 *    - Cannot be overridden (not visible to child)
 *    - Child can have same signature (not overriding, just new method)
 *
 * 2. FINAL METHODS:
 *    - Cannot be overridden
 *    - Can be inherited and used
 *
 * 3. STATIC BINDING vs DYNAMIC BINDING:
 *    - Static: private, final, static methods (compile time)
 *    - Dynamic: instance methods (runtime)
 *
 * 4. ABSTRACT CLASS vs INTERFACE:
 *    - Abstract: can have constructors, instance variables, any access modifiers
 *    - Interface: variables are public static final, methods are public
 *    - Java 8+: interfaces can have default and static methods
 *    - Java 9+: interfaces can have private methods
 *
 * 5. TRY-WITH-RESOURCES:
 *    - Resources closed in REVERSE order of creation
 *    - close() called BEFORE catch and finally
 *
 * 6. VARARGS:
 *    - Only one vararg per method, must be last parameter
 *    - void method(int... a, String s) // INVALID
 *    - void method(String s, int... a) // VALID
 *
 * 7. ENUM:
 *    - Implicitly final and extends java.lang.Enum
 *    - Cannot extend other classes
 *    - Can implement interfaces
 *    - Constructors are implicitly private
 *
 * 8. INNER CLASSES:
 *    - Non-static inner class: has reference to outer class
 *    - Static nested class: no reference to outer class
 *    - Local class: defined in method
 *    - Anonymous class: no name, defined inline
 *
 * 9. GENERICS ERASURE:
 *    - Type info erased at runtime
 *    - List<String> and List<Integer> same at runtime
 *    - Cannot do: new T(), new T[], instanceof List<String>
 *
 * 10. CLONING:
 *     - clone() is protected in Object
 *     - Must implement Cloneable (marker interface)
 *     - Default clone() is SHALLOW copy
 */


// ============================================================
// SECTION 16: Exception Hierarchy & Multi-Catch
// ============================================================
class Section16 {
    static void section16_ExceptionHierarchy() {
        System.out.println("\n--- SECTION 16: Exception Hierarchy ---");

        // QUESTION 1: Will this compile?
        // try {
        //     throw new FileNotFoundException();
        // } catch (IOException e) {
        //     System.out.println("IOException");
        // } catch (FileNotFoundException e) {  // COMPILE ERROR! Already caught above
        //     System.out.println("FileNotFoundException");
        // }

        // QUESTION 2: Multi-catch - what's the type of e?
        try {
            int x = 1/0;
        } catch (ArithmeticException | NullPointerException e) {
            // e is effectively final!
            // e = new ArithmeticException();  // COMPILE ERROR!
            System.out.println("Exception type: " + e.getClass().getSimpleName());
        }

        // QUESTION 3: Checked vs Unchecked
        // testCheckedException();  // Forces you to handle or declare
        testUncheckedException();   // No need to handle

        /*
         * KEY CONCEPTS:
         * 1. Catch blocks must be ordered from SPECIFIC to GENERAL
         * 2. In multi-catch, variable is EFFECTIVELY FINAL
         * 3. Checked Exceptions: Must be declared or caught
         *    - Exception, IOException, SQLException, etc.
         * 4. Unchecked Exceptions: RuntimeException and its subclasses
         *    - NullPointerException, ArrayIndexOutOfBoundsException, etc.
         * 5. Error: JVM issues (OutOfMemoryError, StackOverflowError) - don't catch!
         */
    }

    static void testCheckedException() throws java.io.IOException {
        throw new java.io.IOException("Checked!");
    }

    static void testUncheckedException() {
        // No throws declaration needed
        if (false) throw new RuntimeException("Unchecked!");
    }
}


// ============================================================
// SECTION 17: ThreadLocal Traps
// ============================================================
class Section17 {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    static void section17_ThreadLocalTraps() {
        System.out.println("\n--- SECTION 17: ThreadLocal Traps ---");

        // Main thread
        threadLocal.set(100);
        System.out.println("Main thread: " + threadLocal.get());  // ?

        // New thread
        Thread t = new Thread(() -> {
            System.out.println("New thread initial: " + threadLocal.get());  // ?
            threadLocal.set(200);
            System.out.println("New thread after set: " + threadLocal.get());  // ?
        });
        t.start();
        try { t.join(); } catch (InterruptedException e) {}

        System.out.println("Main thread after child: " + threadLocal.get());  // ?

        /*
         * ANSWER:
         * Main thread: 100
         * New thread initial: 0 (each thread has its own copy!)
         * New thread after set: 200
         * Main thread after child: 100 (unchanged!)
         *
         * TRAPS:
         * 1. Each thread has ISOLATED copy of ThreadLocal variable
         * 2. MEMORY LEAK: In thread pools, ThreadLocal not removed
         *    - Always call threadLocal.remove() when done!
         * 3. InheritableThreadLocal: Child threads can inherit values
         */

        // ALWAYS clean up!
        threadLocal.remove();
    }
}


// ============================================================
// SECTION 18: Comparable vs Comparator
// ============================================================
class Section18 {
    static void section18_ComparableComparator() {
        System.out.println("\n--- SECTION 18: Comparable vs Comparator ---");

        java.util.List<Employee18> employees = new java.util.ArrayList<>();
        employees.add(new Employee18("Alice", 50000, 30));
        employees.add(new Employee18("Bob", 60000, 25));
        employees.add(new Employee18("Charlie", 55000, 35));

        // Natural ordering (Comparable - by name)
        java.util.Collections.sort(employees);
        System.out.println("By name (Comparable): " + employees);

        // Custom ordering (Comparator - by salary)
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));
        System.out.println("By salary (Comparator): " + employees);

        // Comparator chaining
        employees.sort(
            java.util.Comparator.comparing((Employee18 e) -> e.age)
                .thenComparing(e -> e.name)
                .reversed()
        );
        System.out.println("By age desc, then name: " + employees);

        /*
         * KEY DIFFERENCES:
         *
         * Comparable<T>:
         * - Interface in java.lang
         * - Defines NATURAL ordering
         * - Implement compareTo(T o)
         * - Single way to compare
         *
         * Comparator<T>:
         * - Interface in java.util
         * - Defines CUSTOM ordering
         * - Implement compare(T o1, T o2)
         * - Multiple ways to compare
         *
         * RETURN VALUES:
         * - Negative: this < other (or o1 < o2)
         * - Zero: this == other
         * - Positive: this > other
         *
         * TRICKY: What if compareTo inconsistent with equals?
         * - TreeSet/TreeMap use compareTo for uniqueness!
         * - Can lead to "duplicates" being lost
         */
    }
}

class Employee18 implements Comparable<Employee18> {
    String name;
    double salary;
    int age;

    Employee18(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public int compareTo(Employee18 other) {
        return this.name.compareTo(other.name);  // Natural order by name
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}


// ============================================================
// SECTION 19: Generics Wildcards & Type Erasure
// ============================================================
class Section19 {
    static void section19_GenericsWildcards() {
        System.out.println("\n--- SECTION 19: Generics Wildcards ---");

        java.util.List<Integer> intList = java.util.Arrays.asList(1, 2, 3);
        java.util.List<Double> doubleList = java.util.Arrays.asList(1.1, 2.2, 3.3);
        java.util.List<Number> numList = new java.util.ArrayList<>();

        // QUESTION: Which calls compile?
        // printList(intList);        // Compile error if List<Object>
        printWildcard(intList);       // Works with List<?>
        printUpperBound(intList);     // Works with List<? extends Number>
        // addToList(intList);        // Compile error! Can't add to ? extends
        addToLowerBound(numList, 42); // Works with List<? super Integer>

        /*
         * WILDCARDS EXPLAINED:
         *
         * List<?>           - Unbounded: read-only, can only add null
         * List<? extends T> - Upper bound: can READ as T, CANNOT add (except null)
         *                     "Producer Extends" - PE
         * List<? super T>   - Lower bound: can ADD T or subtypes, read as Object
         *                     "Consumer Super" - CS
         *
         * PECS: Producer Extends, Consumer Super
         *
         * TYPE ERASURE TRAPS:
         */

        // These are the SAME at runtime!
        java.util.List<String> strings = new java.util.ArrayList<>();
        java.util.List<Integer> integers = new java.util.ArrayList<>();
        System.out.println("Same class? " +
            (strings.getClass() == integers.getClass()));  // true!

        // Cannot do:
        // if (list instanceof List<String>) {}  // Compile error
        // T obj = new T();                       // Compile error
        // T[] arr = new T[10];                   // Compile error

        // Heap pollution example
        heapPollutionDemo();
    }

    static void printWildcard(java.util.List<?> list) {
        for (Object o : list) System.out.print(o + " ");
        System.out.println();
    }

    static void printUpperBound(java.util.List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) sum += n.doubleValue();
        System.out.println("Sum: " + sum);
    }

    static void addToLowerBound(java.util.List<? super Integer> list, Integer val) {
        list.add(val);  // OK! Can add Integer or subtype
    }

    @SafeVarargs  // Suppresses heap pollution warning
    static <T> void heapPollutionDemo(T... args) {
        Object[] array = args;  // Valid but dangerous!
        // array[0] = "String";  // Would cause ClassCastException later
        System.out.println("Varargs with generics can cause heap pollution!");
    }
}


// ============================================================
// SECTION 20: Lambda & Effectively Final
// ============================================================
class Section20 {
    static void section20_LambdaClosures() {
        System.out.println("\n--- SECTION 20: Lambda Closures ---");

        // QUESTION: Will this compile?
        int counter = 0;
        // Runnable r = () -> counter++;  // COMPILE ERROR! counter not effectively final

        final int finalCounter = 10;
        Runnable r1 = () -> System.out.println("Final: " + finalCounter);  // OK

        // Workaround with array or AtomicInteger
        int[] counterArr = {0};
        java.util.concurrent.atomic.AtomicInteger atomicCounter =
            new java.util.concurrent.atomic.AtomicInteger(0);

        Runnable r2 = () -> {
            counterArr[0]++;  // OK! Array reference is effectively final
            atomicCounter.incrementAndGet();  // OK! Object reference is final
        };
        r2.run();
        r2.run();
        System.out.println("Array counter: " + counterArr[0]);  // 2
        System.out.println("Atomic counter: " + atomicCounter.get());  // 2

        // QUESTION: What's the output?
        java.util.List<Runnable> runnables = new java.util.ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;  // Must capture in effectively final variable
            runnables.add(() -> System.out.println("Value: " + finalI));
        }
        runnables.forEach(Runnable::run);  // 0, 1, 2 (NOT 3, 3, 3!)

        /*
         * KEY CONCEPTS:
         * 1. Lambdas can only access effectively final local variables
         * 2. "Effectively final" = assigned once and never modified
         * 3. Instance/static fields CAN be modified (no restriction)
         * 4. Each loop iteration captures ITS OWN copy of the variable
         *
         * COMMON MISTAKE (in old for-loop style):
         * for (int i = 0; i < 3; i++) {
         *     // i is NOT effectively final - reassigned each iteration
         *     runnables.add(() -> System.out.println(i));  // ERROR!
         * }
         */

        // Method reference equivalents
        java.util.List<String> names = java.util.Arrays.asList("Alice", "Bob");

        // These are equivalent:
        names.forEach(s -> System.out.println(s));
        names.forEach(System.out::println);

        // Different types of method references:
        // 1. Static method:      ClassName::staticMethod
        // 2. Instance method:    instance::method
        // 3. Instance method:    ClassName::instanceMethod (first param becomes receiver)
        // 4. Constructor:        ClassName::new
    }
}


// ============================================================
// SECTION 21: Singleton Patterns & Breaking Them
// ============================================================
class Section21 {
    static void section21_SingletonPatterns() {
        System.out.println("\n--- SECTION 21: Singleton Patterns ---");

        // 1. Eager Initialization (Thread-safe but loads even if not used)
        // 2. Lazy Initialization (Not thread-safe)
        // 3. Synchronized Method (Thread-safe but slow)
        // 4. Double-Checked Locking (Fast and thread-safe)
        // 5. Bill Pugh / Holder Pattern (Lazy + Thread-safe)
        // 6. Enum Singleton (Best - handles serialization & reflection)

        System.out.println("Double-checked: " + DoubleCheckedSingleton.getInstance());
        System.out.println("Bill Pugh: " + BillPughSingleton.getInstance());
        System.out.println("Enum: " + EnumSingleton.INSTANCE);

        /*
         * WAYS TO BREAK SINGLETON:
         * 1. Reflection - can access private constructor
         * 2. Serialization - deserialize creates new object
         * 3. Cloning - clone() creates new object
         *
         * ENUM SINGLETON handles all these!
         */
    }
}

// Double-Checked Locking
class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;  // volatile is CRITICAL!

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {  // Second check (with lock)
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() { return "DoubleCheckedSingleton"; }
}

// Bill Pugh Singleton (Recommended)
class BillPughSingleton {
    private BillPughSingleton() {}

    // Inner class not loaded until getInstance() called
    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String toString() { return "BillPughSingleton"; }
}

// Enum Singleton (BEST)
enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Enum singleton method");
    }
}


// ============================================================
// SECTION 22: Tricky Interview Code Snippets
// ============================================================
class Section22 {
    static void section22_TrickySnippets() {
        System.out.println("\n--- SECTION 22: Tricky Code Snippets ---");

        // TRICK 1: Post-increment in return
        System.out.println("Post-increment trick: " + getAndIncrement());  // ?

        // TRICK 2: String switch null
        // String s = null;
        // switch(s) { case "a": break; }  // NullPointerException!

        // TRICK 3: Ternary operator type
        Object obj = true ? new Integer(1) : new Double(2.0);
        System.out.println("Ternary type: " + obj + " class: " + obj.getClass().getSimpleName());  // ?

        // TRICK 4: Infinite loop with short
        // for (short s = 0; s < Short.MAX_VALUE + 1; s++) {}  // Infinite! s wraps around

        // TRICK 5: Character arithmetic
        char c1 = 'a';
        char c2 = 'b';
        // char c3 = c1 + c2;  // COMPILE ERROR! Result is int
        char c3 = (char)(c1 + c2);  // OK
        System.out.println("char + char = " + (c1 + c2) + " (int)");

        // TRICK 6: Array covariance
        Object[] objects = new String[3];
        objects[0] = "Hello";
        // objects[1] = 123;  // Compiles but throws ArrayStoreException!

        // TRICK 7: Labeled break/continue
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) break outer;
                System.out.print(i + "" + j + " ");
            }
        }
        System.out.println("<- labeled break");

        /*
         * ANSWERS:
         * 1. Post-increment: 0 (returns value THEN increments)
         * 3. Ternary: 1.0, Double (numeric promotion to common type!)
         */
    }

    static int value = 0;
    static int getAndIncrement() {
        return value++;  // Returns 0, then value becomes 1
    }
}


// ============================================================
// SUMMARY CHEAT SHEET
// ============================================================
/*
 * ================================================================
 * JAVA INTERVIEW CHEAT SHEET
 * ================================================================
 *
 * POLYMORPHISM:
 * - Fields: Compile-time (reference type)
 * - Static methods: Compile-time (hiding, not overriding)
 * - Instance methods: Runtime (true polymorphism)
 *
 * OVERLOADING vs OVERRIDING:
 * - Overloading: Same name, different params, compile-time
 * - Overriding: Same signature, runtime polymorphism
 * - Covariant return type allowed (return subtype)
 *
 * INITIALIZATION ORDER:
 * 1. Static blocks (parent → child), once per class load
 * 2. Instance blocks (parent → child)
 * 3. Constructors (parent → child)
 *
 * STRINGS:
 * - Literals → String pool
 * - new String() → Heap
 * - intern() → Pool reference
 * - Compile-time constants folded
 *
 * AUTOBOXING:
 * - Integer cache: -128 to 127
 * - == on cached values: true
 * - == outside cache: false (use .equals())
 *
 * PASS BY VALUE:
 * - Java is ALWAYS pass by value
 * - For objects, the reference value is copied
 * - Can modify object contents, can't change reference
 *
 * EXCEPTIONS:
 * - Catch order: specific → general
 * - finally always runs (except System.exit())
 * - finally return overrides try/catch return
 *
 * GENERICS:
 * - Type erasure at runtime
 * - PECS: Producer Extends, Consumer Super
 * - Can't instantiate type parameter
 *
 * EQUALS/HASHCODE:
 * - If equals() true, hashCode() must be same
 * - Override both or neither
 *
 * IMMUTABILITY:
 * - final class, private final fields
 * - Deep copy mutable objects in/out
 *
 * SINGLETON:
 * - Enum is best (handles serialization, reflection)
 * - Double-checked locking needs volatile
 *
 * LAMBDA:
 * - Access only effectively final local variables
 * - Instance/static fields can be modified
 *
 * ================================================================
 */


// ============================================================
// SECTION 23: Multithreading Traps
// ============================================================
class Section23 {
    private static int sharedCounter = 0;
    private static volatile int volatileCounter = 0;

    static void section23_MultithreadingTraps() {
        System.out.println("\n--- SECTION 23: Multithreading Traps ---");

        // TRAP 1: volatile doesn't mean atomic!
        volatileCounter = 0;
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) volatileCounter++;  // NOT ATOMIC!
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) volatileCounter++;
        });
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (InterruptedException e) {}
        System.out.println("Volatile counter (expected 2000): " + volatileCounter);  // Often < 2000!

        /*
         * VOLATILE MISCONCEPTIONS:
         * - volatile guarantees VISIBILITY, not ATOMICITY
         * - x++ is NOT atomic: read-modify-write
         * - Use AtomicInteger for atomic operations
         *
         * VOLATILE GUARANTEES:
         * 1. Writes are immediately visible to other threads
         * 2. Prevents instruction reordering around volatile
         * 3. 64-bit reads/writes are atomic (long, double)
         */

        // TRAP 2: Thread.sleep() doesn't release locks!
        Object lock = new Object();
        System.out.println("Thread.sleep() DOES NOT release lock");
        System.out.println("Object.wait() DOES release lock");

        // TRAP 3: join() and its traps
        /*
         * KEY THREAD METHODS:
         *
         * wait()   - releases lock, waits for notify, must be in synchronized
         * notify() - wakes ONE waiting thread, doesn't release lock immediately
         * notifyAll() - wakes ALL waiting threads
         * sleep()  - pauses thread, DOESN'T release lock, static method
         * yield()  - hints scheduler to give other threads a chance
         * join()   - waits for thread to die
         *
         * COMMON MISTAKES:
         * 1. Calling wait/notify without synchronized = IllegalMonitorStateException
         * 2. Using sleep() instead of wait() for condition waiting
         * 3. Not using while loop for wait() (spurious wakeups!)
         */

        // Correct wait pattern
        // synchronized(lock) {
        //     while (!condition) {  // WHILE, not IF!
        //         lock.wait();
        //     }
        // }

        System.out.println("Always use while() with wait(), not if()");
    }
}


// ============================================================
// SECTION 24: HashMap Internals
// ============================================================
class Section24 {
    static void section24_HashMapInternals() {
        System.out.println("\n--- SECTION 24: HashMap Internals ---");

        // QUESTION: What happens here?
        java.util.Map<BadKey24, String> map = new java.util.HashMap<>();
        BadKey24 key1 = new BadKey24(1);
        map.put(key1, "value1");

        key1.id = 2;  // Modifying key after insertion!
        System.out.println("Get with modified key: " + map.get(key1));  // ?
        System.out.println("Get with new key(2): " + map.get(new BadKey24(2)));  // ?
        System.out.println("Get with new key(1): " + map.get(new BadKey24(1)));  // ?

        /*
         * ANSWER:
         * All return null! Why?
         * - key1 now has hashCode for id=2
         * - It's in bucket for id=1
         * - HashMap can't find it!
         *
         * RULE: NEVER modify a key after putting in HashMap!
         * Use immutable keys (String, Integer, etc.)
         */

        /*
         * HASHMAP INTERNALS (Java 8+):
         *
         * 1. STRUCTURE:
         *    - Array of Node<K,V> (buckets)
         *    - Default capacity: 16
         *    - Load factor: 0.75
         *    - Threshold = capacity * load factor
         *
         * 2. PUT OPERATION:
         *    - hash = key.hashCode() ^ (hashCode >>> 16)  // Spread high bits
         *    - index = hash & (n-1)  // Same as hash % n for power of 2
         *    - If collision: linked list → tree (if > 8 nodes)
         *
         * 3. TREE CONVERSION:
         *    - Linked list → Red-Black Tree when bucket has > 8 nodes
         *    - Tree → Linked list when bucket has < 6 nodes
         *    - O(n) → O(log n) for collision handling
         *
         * 4. RESIZE:
         *    - When size > threshold, double the capacity
         *    - All entries rehashed (expensive!)
         *
         * 5. NULL HANDLING:
         *    - HashMap: allows 1 null key, multiple null values
         *    - Hashtable: NO nulls allowed (throws NPE)
         *    - ConcurrentHashMap: NO nulls allowed
         */

        // ConcurrentHashMap vs Collections.synchronizedMap
        java.util.Map<String, String> syncMap =
            java.util.Collections.synchronizedMap(new java.util.HashMap<>());
        java.util.Map<String, String> concurrentMap =
            new java.util.concurrent.ConcurrentHashMap<>();

        System.out.println("\nConcurrentHashMap vs SynchronizedMap:");
        System.out.println("- SynchronizedMap: locks entire map");
        System.out.println("- ConcurrentHashMap: segment-level/bucket-level locking");
        System.out.println("- ConcurrentHashMap: no nulls, better for concurrent reads");
    }
}

class BadKey24 {
    int id;

    BadKey24(int id) { this.id = id; }

    @Override
    public int hashCode() { return id; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BadKey24) return this.id == ((BadKey24) o).id;
        return false;
    }
}


// ============================================================
// SECTION 25: ClassLoader & Reflection
// ============================================================
class Section25 {
    static void section25_ClassLoaderReflection() {
        System.out.println("\n--- SECTION 25: ClassLoader & Reflection ---");

        // ClassLoader hierarchy
        System.out.println("String's ClassLoader: " + String.class.getClassLoader());  // null (Bootstrap)
        System.out.println("This class's ClassLoader: " + Section25.class.getClassLoader());

        /*
         * CLASSLOADER HIERARCHY:
         *
         * 1. Bootstrap ClassLoader (null in Java)
         *    - Loads JDK core classes (rt.jar, java.base)
         *    - Written in native code
         *
         * 2. Platform/Extension ClassLoader
         *    - Loads extension classes (ext folder)
         *
         * 3. Application/System ClassLoader
         *    - Loads classes from classpath
         *
         * DELEGATION MODEL:
         * - Child asks parent first (parent delegation)
         * - If parent can't load, child tries
         * - Prevents class shadowing of core classes
         *
         * CLASS LOADING STEPS:
         * 1. Loading: Read .class file
         * 2. Linking: Verify, Prepare, Resolve
         * 3. Initialization: Run static blocks
         */

        // Reflection traps
        try {
            Person14 person = Person14.class.getDeclaredConstructor(String.class, int.class)
                .newInstance("John", 25);
            System.out.println("Created via reflection: " + person.name);

            // Access private field
            java.lang.reflect.Field nameField = Person14.class.getDeclaredField("name");
            nameField.setAccessible(true);  // Bypass access check
            nameField.set(person, "Modified");
            System.out.println("After reflection modification: " + person.name);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * REFLECTION INTERVIEW QUESTIONS:
         *
         * 1. How to break Singleton?
         *    Constructor.setAccessible(true) then newInstance()
         *
         * 2. How to modify final field?
         *    Field.setAccessible(true), then for static final:
         *    Remove final modifier, then set value
         *
         * 3. Performance?
         *    Reflection is 10-100x slower than direct access
         *    JVM can't optimize reflective calls
         *
         * 4. Security?
         *    SecurityManager can restrict reflection
         *    Java 9+ modules can prevent deep reflection
         */

        System.out.println("Reflection can break encapsulation!");
    }
}


// ============================================================
// SECTION 26: Java 8+ Features Traps
// ============================================================
class Section26 {
    static void section26_Java8PlusTraps() {
        System.out.println("\n--- SECTION 26: Java 8+ Features Traps ---");

        // Optional traps
        java.util.Optional<String> opt = java.util.Optional.of("Hello");
        // java.util.Optional<String> nullOpt = java.util.Optional.of(null);  // NPE!
        java.util.Optional<String> nullableOpt = java.util.Optional.ofNullable(null);  // OK

        // WRONG usage
        // if (opt.isPresent()) { String s = opt.get(); }  // Anti-pattern!

        // CORRECT usage
        String result = opt.orElse("default");
        String result2 = opt.orElseGet(() -> "expensive default");  // Lazy
        opt.ifPresent(System.out::println);

        /*
         * OPTIONAL ANTI-PATTERNS:
         * 1. Optional.get() without isPresent check
         * 2. Using Optional as method parameter
         * 3. Using Optional for class fields
         * 4. Optional.of() with possibly null value
         * 5. Returning null instead of Optional.empty()
         */

        // Stream traps
        java.util.List<String> list = java.util.Arrays.asList("a", "b", "c");
        java.util.stream.Stream<String> stream = list.stream();
        stream.forEach(System.out::print);
        // stream.forEach(System.out::print);  // IllegalStateException! Stream already used

        System.out.println("\nStreams are single-use!");

        // Parallel stream trap
        java.util.List<Integer> numbers = java.util.Arrays.asList(1, 2, 3, 4, 5);
        java.util.List<Integer> results = new java.util.ArrayList<>();

        // WRONG - ArrayList is not thread-safe!
        // numbers.parallelStream().forEach(results::add);  // Race condition!

        // CORRECT - use collect
        java.util.List<Integer> safeResults = numbers.parallelStream()
            .collect(java.util.stream.Collectors.toList());

        System.out.println("Don't mutate shared state in parallel streams!");

        /*
         * STREAM BEST PRACTICES:
         * 1. Streams are single-use
         * 2. Intermediate operations are lazy
         * 3. Don't mutate source during stream operations
         * 4. Use collect() instead of forEach() to collect results
         * 5. Parallel streams aren't always faster (overhead!)
         */
    }
}

