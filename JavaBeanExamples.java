import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaBeanExamples.java
 * 
 * This file contains typical 10-mark exam programming answers for:
 * 1. A perfectly compliant JavaBean class.
 * 2. A simulated Event Delegation Model.
 */

// ==========================================
// 1. A Compliant JavaBean Class
// ==========================================
/**
 * To be a JavaBean, a class must:
 * 1. Be public.
 * 2. Have a public default (no-argument) constructor.
 * 3. Have private properties.
 * 4. Have public getter and setter methods.
 * 5. Implement java.io.Serializable.
 */
class EmployeeBean implements Serializable {

    // 3. Private properties
    private int empId;
    private String name;
    private double salary;
    private boolean isActive;

    // 2. Public no-argument constructor
    public EmployeeBean() {
    }

    // 4. Public Getters and Setters

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    // Setter demonstrating simple validation
    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        }
    }

    // Convention for boolean getters is usually is<Property>()
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}

// ==========================================
// 2. The Event Delegation Model (Simulated)
// ==========================================

// A. The "Event Object" encapsulating the change
class SalaryChangeEvent {
    private double oldSalary;
    private double newSalary;

    public SalaryChangeEvent(double oldSalary, double newSalary) {
        this.oldSalary = oldSalary;
        this.newSalary = newSalary;
    }

    public double getNewSalary() {
        return newSalary;
    }
}

// B. The "Event Listener" interface
interface SalaryChangeListener {
    void onSalaryChanged(SalaryChangeEvent event); // Callback method
}

// C. The "Event Source" (A modified Employee class that fires events)
class ObservableEmployee {
    private double salary;

    // List to hold globally registered listeners
    private List<SalaryChangeListener> listeners = new ArrayList<>();

    // Register a listener (e.g., button.addActionListener(this))
    public void addSalaryChangeListener(SalaryChangeListener listener) {
        listeners.add(listener);
    }

    public void setSalary(double newSalary) {
        double oldSalary = this.salary;
        this.salary = newSalary;

        // 1. Source (this) generates the Event Object
        SalaryChangeEvent event = new SalaryChangeEvent(oldSalary, newSalary);

        // 2. Source delegates responsibility by notifying all registered listeners
        for (SalaryChangeListener listener : listeners) {
            // 3. Callback method triggered
            listener.onSalaryChanged(event);
        }
    }
}

// ==========================================
// Main Class to demonstrate
// ==========================================
public class JavaBeanExamples {
    public static void main(String[] args) {
        System.out.println("=== 1. Testing the JavaBean ===");
        EmployeeBean emp = new EmployeeBean(); // Uses no-arg constructor
        emp.setEmpId(101);
        emp.setName("John Doe");
        emp.setSalary(75000.00);
        emp.setActive(true);

        System.out.println("Employee ID: " + emp.getEmpId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Salary: $" + emp.getSalary());

        System.out.println("\n=== 2. Testing the Event Delegation Model ===");
        ObservableEmployee boss = new ObservableEmployee();

        // Implement the "Listener" (Reacts to the event)
        SalaryChangeListener auditLogger = new SalaryChangeListener() {
            @Override
            public void onSalaryChanged(SalaryChangeEvent event) {
                System.out.println("AUDIT LOG: Salary was updated to $" + event.getNewSalary());
            }
        };

        // Register the listener with the source
        boss.addSalaryChangeListener(auditLogger);

        System.out.println("Changing salary...");
        // When the setter is called, it triggers the event, which notifies the listener
        boss.setSalary(100000.00);
    }
}
