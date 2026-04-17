# Module 11: Java Applets (Simplified)

## 1. What is an Applet?

**Simple Explanation (Universal Analogy):**
Normally, a website is just text and simple pictures. A **Java Applet** is a tiny, incredibly fast automated Java program that downloads directly into your web browser and safely runs inside a small box on the screen. Think of it like bringing a tiny, battery-operated calculator and placing it permanently on your open desk.

**Key Exam Points:**
1.  Requires zero manual installation (runs automatically directly in the browser using the physical Java Plugin).
2.  **Applets don't have a standard `main()` method!** The host browser intrinsically starts them automatically.
3.  **Security Sandbox:** Applets are highly structurally restricted. They *cannot* firmly delete or intrinsically read private files from the user's local hard drive (so they physically cannot be viruses).

---

## 2. Applet Life Cycle (Guaranteed 10 Mark Question)

You must memorize the 5 active stages of an Applet's life. Think of it exactly like securely operating a manual generator machine.

1.  **`init()` (Initializing):** Called exactly *once* when the physical applet downloads. It sets up initial variables, drawing colors, and UI buttons. *(Like securely bringing the machine out of the box).*
2.  **`start()`:** Called exactly when the applet is visually focused. If you minimize the browser entirely and physically maximize it, `start()` securely runs again. *(Like turning the generator ON).*
3.  **`paint(Graphics g)`:** Called whenever the applet fundamentally needs to explicitly draw itself entirely on the screen. Actively used to forcefully draw clean lines or structured text. *(The machine producing electricity).*
4.  **`stop()`:** Called exactly when you securely switch to another browser tab. It heavily pauses the applet to perfectly save computer CPU power. *(Like temporarily turning the machine OFF).*
5.  **`destroy()`:** Called exactly *once* when you completely, permanently close the major browser window. Gets rid of everything fundamentally. *(Packing the machine back into the storage box permanently).*

**Lifecycle Diagram:**
```text
  [HTML Page Loads]
         |
         v
     [ init() ]  ---> (Runs ONCE at download)
         |
         v
     [ start() ] <------+
         |              | (User returns to tab)
         v              |
    [ paint() ]         |
         |              |
         v              | 
    ( running )         |
         |              |
    (User moves away)   |
         |              |
         v              |
     [ stop() ]  -------+
         |
    (User closes browser)
         |
         v
   [ destroy() ] ---> (Runs ONCE, kills memory)
```

---

## 3. Creating & Running an Applet

Since it explicitly doesn't have a `main()` method, how do we run it natively? We embed it in HTML block comments securely, and run it safely using the native `appletviewer` tool strictly in the terminal.

**Exam Code Snippet:**
```java
import java.applet.Applet;
import java.awt.Graphics;

/* Include this exact distinct comment to smoothly run using appletviewer!
<applet code="MyApplet.class" width="300" height="300">
</applet>
*/

// Must legally extend Applet
public class MyApplet extends Applet {
    
    // Step 1: Init Phase
    public void init() {
        // Broad Setup Logic
    }
    
    // Step 3: Explicitly Drawing Graphics
    public void paint(Graphics g) {
        g.drawString("Welcome safely to Applets!", 50, 50);
        
        // Let's draw a perfectly empty geometric circle!
        g.drawOval(100, 100, 50, 50); 
    }
}
```

---

## 4. Parameter Passing

**Simple Explanation:**
Sometimes the basic HTML website natively wants to firmly tell the complex Java Applet something initially (like giving it a "Welcome String Message"). It formally passes this secure data using a strict `<param>` tag inside the HTML block layout.

**HTML Side:**
```html
<applet code="MyApplet.class" width="300" height="300">
    <param name="username" value="StudentName">
</applet>
```
**Java Side (inside init()):**
```java
public void init() {
    String user = getParameter("username"); // Will effectively contain "StudentName"
}
```

### How to Answer Exam Questions on this Topic:
*   **Lifecycle Question (10-12M):** Graphically draw the exact ASCII block diagram. Write specifically 1 line for each deeply of the 5 methods (`init, start, paint, stop, destroy`). Specifically strictly mention that it has literally no `main()` method.
*   **Programming Question / Graphics:** Safely write the Exam Code snippet fundamentally shown above. Always intrinsically import `java.applet.Applet` and heavily `java.awt.Graphics` and put the `<applet>` tag precisely inside a block securely comment.
