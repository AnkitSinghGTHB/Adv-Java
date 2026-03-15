import os
import re

solutions_dir = r"c:\Users\ankit\Downloads\Adv Java\Question_Bank_Solutions"

outputs = {
    'Q1.md': 'Enter current account balance: 1000\nEnter withdrawal amount: 1500\nError: Insufficient balance for this withdrawal.\nThank you for using our banking services.',
    'Q2.md': 'Enter the student\'s marks (0-100): 105\nError: Invalid marks entered! Marks must be between 0 and 100.\nExecution continues naturally beyond the try-catch block.',
    'Q3.md': 'Create a new password: pass\nSecurity Alert: Password is too weak. It must be at least 8 characters long.\nMoving to the next step of the registration process...',
    'Q4.md': 'Enter the current temperature in Celsius: 100\nInput Error: Warning: Unrealistic temperature reading detected (100.0°C).\nResuming normal monitoring operations...',
    'Q5.md': 'Enter the product price: $-5\nSystem Error: A product\'s price cannot be zero or negative.\nProceeding to inventory management module...',
    'Q6.md': 'Employee object serialized successfully.\nEmployee object deserialized successfully.\n\n--- Displaying Deserialized Data ---\nEmployee ID: 101, Name: Alice Smith, Salary: $85000.0',
    'Q7.md': 'Book object serialized to book.ser\nBook object deserialized from book.ser\n\n--- Deserialized Book Details ---\nBook ID: 5001, Title: \'Advanced Java Programming\', Author: John Doe',
    'Q8.md': 'BankAccount object serialized successfully.\nBankAccount object deserialized successfully.\n\n--- Deserialized Account Status ---\nA/C Number: 99887766\nHolder: Jane Doe\nBalance: $15250.75',
    'Q9.md': 'Product object serialized into product.dat\nProduct object deserialized from product.dat\n\n--- Extracted Product Details ---\nProduct ID: 101 | Name: Wireless Mouse | Price: $25.99',
    'Q10.md': 'Teacher object serialized and saved.\nTeacher object deserialized completely.\n\n--- Deserialized Teacher Profile ---\nTeacher ID: 401\nName: Dr. Alan Turing\nSubject: Computer Science',
    'Q11.md': '[A GUI Window titled "Addition Calculator" appears with input fields and an Add button.]',
    'Q12.md': '[A GUI Window titled "Color Changer" appears. Clicking the button changes background color randomly.]',
    'Q13.md': '[A GUI Window titled "Course Selection App" appears. Selecting a course updates the label.]',
    'Q14.md': '[A GUI Window titled "Login Portal" appears. Entering "admin" and "1234" prints "Login Successful" in green.]',
    'Q15.md': '--- Employee Monthly Salaries ---\nJan Salary: $5000.0\nFeb Salary: $5200.0\nMar Salary: $5200.0\nDec Salary: $6000.0',
    'Q16.md': '--- Student Subject Grades ---\nSubject 1 Grade: A+\nSubject 2 Grade: A\nSubject 3 Grade: B+\nSubject 4 Grade: A-\nSubject 5 Grade: A',
    'Q17.md': '--- Weekly Temperature Report ---\nMonday: 22.5°C\nTuesday: 24.0°C\nWednesday: 21.8°C\nThursday: 23.1°C\nFriday: 25.4°C\nSaturday: 27.2°C\nSunday: 26.5°C',
    'Q18.md': '--- Annual Quarterly Sales Report ---\nQuarter 1 Sales: $150000.0\nQuarter 2 Sales: $175500.5\nQuarter 3 Sales: $160000.0\nQuarter 4 Sales: $210000.75\nTotal Annual Sales: $695501.25',
    'Q19.md': '--- 10-Day Student Attendance Record ---\nDay 1: Present\nDay 2: Present\nDay 3: Absent\nDay 4: Present\nDay 5: Present\nDay 6: Present\nDay 7: Absent\nDay 8: Absent\nDay 9: Present\nDay 10: Present',
    'Q20.md': 'Enter Student Roll No: 101\nEnter Student Name: Alice\nEnter Student Marks: 95\nRecord inserted successfully!\nResources closed naturally via try-with-resources.',
    'Q21.md': 'Enter Employee ID to update: 1001\nEnter New Salary: $85000.0\nSuccess: Employee salary updated to $85000.0\nCleanup completed.',
    'Q22.md': 'Enter the Product ID to delete: 505\nProduct ID 505 was successfully deleted.\nConnection and Statement automatically closed.',
    'Q23.md': 'Enter Customer ID to retrieve details: 707\n\n--- Customer Record Found ---\nCustomer ID: 707\nName: Acme Corp\nCity: New York\nEnsured closing of Connection, Statement, and ResultSet.',
    'Q26.md': '--- Student Database ---\nRoll Number: 101\nName: Alice Smith\nGrade GPA: 3.8\n-------------------------\nRoll Number: 102\nName: Bob Johnson\nGrade GPA: 3.5\n-------------------------',
    'Q27.md': 'Total Salary for John is: $52500.0',
    'Q29.md': '[A GUI Window titled "Event Delegation Demo" appears.]\nButton was clicked! Action Command: Click Me\nTimestamp: 1678886300000',
    'Q31.md': '1. Hello Java\n2. Hello Java\n3. Hello Java\n...\n10. Hello Java',
    'Q32.md': '1. Multithreading in Action\n2. Multithreading in Action\n...\n7. Multithreading in Action',
    'Q33.md': '[A GUI Window titled "BorderLayout vs FlowLayout" appears displaying side-by-side layout comparisons.]',
    'Q34.md': '[A GUI Window titled "Nested Layouts Example" appears showing a login control center interface.]'
}

for filename, output_text in outputs.items():
    filepath = os.path.join(solutions_dir, filename)
    if not os.path.exists(filepath):
        continue
        
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        
    # Check if Sample Output is already there to avoid duplicates
    if "**Sample Output:**" in content:
        print(f"Skipping {filename}, sample output already exists.")
        continue
        
    # We want to insert the sample output right after the java block.
    # The java block ends with ```
    # The next line is usually \n---\n[< Previous
    
    inject_str = f"```\n\n**Sample Output:**\n```text\n{output_text}\n```\n"
    new_content = re.sub(r'```\s*\n\s*---', f'{inject_str}\n---', content, count=1)
    
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(new_content)
        
    print(f"Updated {filename}")
        
print("Done!")
