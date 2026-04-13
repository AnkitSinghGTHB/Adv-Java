// 1. Creating the Java Bean
class Employee implements java.io.Serializable { // A JavaBean should ideally implement Serializable

    private int id; // Private property: Employee ID
    private String name; // Private property: Employee Name

    public Employee() { // Public no-argument constructor (Required for Beans)
    }

    public int getId() { // Getter method for ID
        return id; // Returns the current ID
    }

    public void setId(int id) { // Setter method for ID
        this.id = id; // Sets the ID property
    }

    public String getName() { // Getter method for Name
        return name; // Returns the current Name
    }

    public void setName(String name) { // Setter method for Name
        this.name = name; // Sets the Name property
    }
}

// 2. Executing the Java Bean
public class BeanExecutor { // Driver class to test the JavaBean
    public static void main(String[] args) { // Main method

        Employee emp = new Employee(); // Instantiating the JavaBean using the no-arg constructor

        emp.setId(101); // Populating data using setter method
        emp.setName("Alice Smith"); // Populating data using setter method

        System.out.println("Employee ID: " + emp.getId()); // Accessing data using getter method and printing
        System.out.println("Employee Name: " + emp.getName()); // Accessing data using getter method and printing
    }
}