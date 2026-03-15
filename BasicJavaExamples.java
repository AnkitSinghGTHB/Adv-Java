/**
 * BasicJavaExamples.java
 *
 * This file contains practical examples for the foundational Java concepts
 * covered in Module 1, designed to be easily reproducible in exams.
 */

// ==========================================
// 1. Classes, Objects, and Access Modifiers
// ==========================================
class Student {
    // Private: Accessible only within this class
    private int id;

    // Public: Accessible from anywhere
    public String name;

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Public getter to access private data
    public int getId() {
        return id;
    }
}

// ==========================================
// 2. Abstract Class vs Interface
// ==========================================

// Abstract Class
abstract class Shape {
    // Abstract method (no body)
    abstract void draw();

    // Concrete method (with body)
    public void description() {
        System.out.println("I am a shape.");
    }
}

// Interface
interface Drawable {
    void setSkinColor(String color); // Implicitly public abstract
}

// Implementing both
class Circle extends Shape implements Drawable {
    @Override
    void draw() {
        System.out.println("Drawing a Circle.");
    }

    @Override
    public void setSkinColor(String color) {
        System.out.println("Skin color set to: " + color);
    }
}

// ==========================================
// 3. Static vs Instance Members
// ==========================================
class Counter {
    int instanceCount = 0; // Unique to each object
    static int staticCount = 0; // Shared across all objects

    public Counter() {
        instanceCount++;
        staticCount++;
    }

    // Static Method can only access static variables
    public static void displayStaticCount() {
        System.out.println("Total Counters created: " + staticCount);
    }
}

// ==========================================
// 4. Main Class Demonstrating Everything
// ==========================================
public class BasicJavaExamples {

    public static void main(String[] args) {
        System.out.println("--- 1. Classes & Objects ---");
        // Creating an object of Student
        Student s1 = new Student(101, "Alice");
        System.out.println("Student Name: " + s1.name);
        System.out.println("Student ID (via getter): " + s1.getId());

        System.out.println("\n--- 2. Abstract Classes & Interfaces ---");
        Circle c = new Circle();
        c.description();
        c.draw();
        c.setSkinColor("Red");

        System.out.println("\n--- 3. Static Keyword ---");
        Counter obj1 = new Counter();
        System.out.println("Obj1 Instance Count: " + obj1.instanceCount); // 1
        Counter obj2 = new Counter();
        System.out.println("Obj2 Instance Count: " + obj2.instanceCount); // 1
        Counter.displayStaticCount(); // Output: 2

        System.out.println("\n--- 4. Arrays ---");
        // Array Declaration and Initialization
        int[] marks = new int[3];
        marks[0] = 85;
        marks[1] = 90;
        marks[2] = 95;

        System.out.println("Marks scored:");
        // Using a control structure (for loop) to iterate over an array
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + marks[i]);
        }
    }
}
