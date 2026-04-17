# Module 9: MVC Architecture & Design Patterns (Simplified)

## 1. What is a Design Pattern?

**Simple Explanation:**
If you want to construct a reliable bridge, you do not invent a brand new engineering design every single time. You cleanly adopt a "Blueprint" that thousands of engineers before you have extensively tested and verified works perfectly safely. 
In programming logic, a **Design Pattern** is that blueprint—a proven, highly reusable solution to a universally common coding problem.

---

## 2. The MVC Design Pattern (12 Mark Guarantee!)

**Simple Explanation (The Traditional Food Stall Analogy):**
Imagine you eat at a simple food stall. 
1.  **View (The Customer's View/Menu):** You look at the menu text. You *never* go into the cooking area.
2.  **Controller (The Waiter/Order Taker):** The waiter listens to you order, writes down your request, and securely passes it to the cook.
3.  **Model (The Kitchen/Cook):** The cook natively takes the request, cooks the food (updates the raw materials), and hands the completed plate to the waiter to serve perfectly back to you.

**Key Definitions for Exam:**
*   **Model:** The Brain. Safely stores the raw data and business logic. It does *not* care what the screen or UI looks like natively.
*   **View:** The Display. Visually presents the data to the user utilizing UI (like HTML pages, Java Swing).
*   **Controller:** The Coordinator. Precisely listens to interactions (clicks, keyboard), securely validates input, and strictly commands the Model on what to structurally do.

**Concept Diagram: MVC Data Flow**
```text
               (User Actions)
                     |
                     V
                [Controller]
                /          \
               /            \ triggers updates
updates data  /              \
             /                V
         [Model] ---------> [View]
            (Business Logic)     (User Interface)
```

**Advantages to strictly memorize:**
1.  **Separation of Concerns:** UI Designers can heavily work on the View interface without risking breaking the core backend Java code inside the Model.
2.  **Multiple Views:** You can efficiently have a Pie Chart (View 1) and a structured Data Table (View 2) both perfectly showing the exact same Data (Model) without writing duplicate logic.

---

## 3. J2EE Multi-Tier Architecture

Sometimes, for massive websites handling incredible traffic, traditional MVC locally simply isn't enough. We split the servers into physical structural "Tiers":

1.  **Client Tier:** What the user natively holds locally (Web Browser, Mobile Phone).
2.  **Web Tier:** The server securely handling the website layout parsing (JSP, Native Servlets).
3.  **Business / EJB Tier:** The core server executing the heavy mathematical or enterprise logic calculations cleanly (EJB).
4.  **EIS / Database Tier:** The hard drive safely storing the persistent data (MySQL, Oracle databases).

### How to Answer Exam Questions on this Topic:
*   **MVC Question (10-12M):** Graphically draw the clean triangle text diagram above. Utilize the Food Stall or Restaurant analogy to physically write 3 paragraphs precisely defining M, V, and C. Distinctly list 2 clear advantages (Separation of Concerns, immense Reusability).
*   **J2EE Tiers (5M):** Sequentially list the 4 tiers vertically in order. Client → Web → EJB → EIS (Structured Database).
