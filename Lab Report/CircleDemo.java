class Circle { // Declaring a class named Circle
    private double radius; // Declaring a private instance variable for the radius

    public Circle(double radius) { // Constructor to initialize the Circle object
        this.radius = radius; // Assigning the passed parameter to the instance variable
    }

    public double calculateArea() { // Method to calculate the area
        return Math.PI * radius * radius; // Using Math.PI and the formula πr² to return the area
    }

    public double calculatePerimeter() { // Method to calculate the perimeter (circumference)
        return 2 * Math.PI * radius; // Using the formula 2πr to return the perimeter
    }
}

public class CircleDemo { // Main driver class
    public static void main(String[] args) { // Main method
        Circle myCircle = new Circle(5.0); // Creating a new Circle object with radius 5.0

        double area = myCircle.calculateArea(); // Calling the area method and storing the result
        double perimeter = myCircle.calculatePerimeter(); // Calling the perimeter method and storing the result

        System.out.println("Radius: 5.0"); // Printing the input radius
        System.out.println("Area of the circle: " + area); // Printing the calculated area
        System.out.println("Perimeter of the circle: " + perimeter); // Printing the calculated perimeter
    }
}