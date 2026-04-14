# Module 5: Java Applets

## 1. Introduction to Applets
**Definition:**
An applet is a specialized Java program that is embedded inside an HTML web page to generate dynamic content in a browser. It is downloaded via the internet and primarily executes locally on the client's machine.

**Advantages:**
1.  **Lightning Fast:** It executes entirely on the client side, resulting in almost zero server response lag.
2.  **Platform Independent:** Capable of running smoothly on Windows, Mac, or Linux under standard browsers.
3.  **High Security:** Operates safely inside a strict "sandbox" restriction model (cannot read or delete local client files).

**Disadvantages:**
*   Requires the Java Plugin software to be configured and active on the client's browser, which has largely been deprecated globally in modern browsers.

---

## 2. Java Applet Architecture & Hierarchy
Before writing an applet, one must import the `java.applet.Applet` class.
The hierarchical tree strictly flows as follows:
`Object` $\rightarrow$ `Component` $\rightarrow$ `Container` $\rightarrow$ `Panel` $\rightarrow$ `Applet` $\rightarrow$ `JApplet`

*Notice that Applet extends Panel, marking it as a container that inherently lacks title bars or structural borders.*

---

## 3. Lifecycle of a Java Applet
*(Highly important for theoretical 5-mark and 10-mark questions)*

The lifecycle of an applet is managed entirely by the Java Plug-in, not by a conventional `main()` method. It follows 5 distinct milestones spanning heavily across the `Applet` class and `Component` class.

1.  **`public void init()`**: Called exactly once when the applet is first securely loaded. Used to initialize variables, establish UI components (buttons, labels), and pull parameters.
2.  **`public void start()`**: Invoked right after `init()` completes, and subsequently whenever the browser is natively maximized/restored. Used to launch required threads or begin animations.
3.  **`public void paint(Graphics g)`**: Sourced from the `Component` class. Called whenever the applet's visual layer has explicitly changed and fundamentally requires a redraw (e.g., drawing lines or shapes natively).
4.  **`public void stop()`**: Called when the user leaves the HTML page or explicitly minimizes the tab. Used to pause heavy tasks and CPU threads.
5.  **`public void destroy()`**: Hook called exactly once when the browser physically closes the applet session permanently, destroying resources.

---

## 4. Running Applets
There are two core methods to safely run an applet:
**By HTML File:** Compile the `.java` file, then securely embed it inside an external html file using the `<applet>` tag:
```html
<applet code="First.class" width="300" height="300"></applet>
```

**By AppletViewer Tool:** For developer testing, you write the HTML tag directly inside a Java block comment natively inside the file, then run from terminal:
```bash
javac First.java
appletviewer First.java
```

---

## 5. Graphics and Animations
The `Graphics` class provides foundational tools required to dynamically render primitive visuals inside `paint()`.

*   **Custom Shapes:** 
    *   `drawString("Hello", x, y)`: Draws rendered text logic.
    *   `drawLine(x1, y1, x2, y2)`: Draws strict lines.
    *   `drawRect(x, y, w, h)`, `drawOval(...)`, `drawArc(...)`
*   **Coloring:** You can swap default drawing strokes by applying `g.setColor(Color.red)` natively. Using `fillRect()` or `fillOval()` solidly fills the geometry.
*   **Images:** First strictly pull the image inside `init()` using `getImage(getDocumentBase(), "pic.jpg")`. Then render it directly using `g.drawImage()`.
*   **Animations:** Fundamentally rely on iterating drawing behaviors across a finite loop and forcing the system to securely sleep via `Thread.sleep(100)` to render visually active frames.

---

## 6. Applet Interactive Features

### 1. Parameter Passing
Applets can safely receive customization data straight from the native HTML explicitly through `<param>` tags:
```html
<applet code="Test.class" width="300" height="300">
    <param name="message" value="Welcome to VIT!">
</applet>
```
To dynamically fetch this securely in Java:
`String str = getParameter("message");`

### 2. Applet Context (Communication)
Two separate applet instances currently running on the exact same HTML page can securely talk to each other. By mapping the logical name:
```java
AppletContext ctx = getAppletContext();
Applet a2 = ctx.getApplet("app2_name");
a2.setBackground(Color.yellow);
```

### 3. JApplet
Because traditional AWT is restrictive, developers shifted towards **`JApplet`** (from `javax.swing.*`), offering fully pluggable Swing controls integrated straight into browser architecture safely.

---

## 7. Typical Exam Questions ("Test Me")

### 2/5 Mark Questions:
1. Explain the significance of the `init()` and `destroy()` methods in an Applet lifecycle.
2. How do you pass active HTML parameters seamlessly into a running Java Applet? Provide a short example.
3. List any 4 vital methods found in the Java `Graphics` API.

### 10 Mark Questions:
1. Discuss the complete Lifecycle of a Java Applet using proper method signatures. Who invokes these lifecycle methods naturally, and what replaces the standard `main()` method requirement?
2. Write a complete applet program utilizing the `Graphics` class and threading to draw an image and structurally animate it moving strictly from the left of the screen to the right. Include the necessary HTML comments logic to allow execution via `appletviewer`.
