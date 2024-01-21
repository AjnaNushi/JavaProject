# JavaProject

In this project, several object-oriented programming (OOP) concepts are used:

Abstraction:
The use of classes, methods, and objects allows for abstraction. For example, the TicTacToe and GamePanel classes encapsulate the game logic for Tic Tac Toe and Snake, respectively, abstracting away the implementation details.

Encapsulation:
The classes encapsulate the properties and behaviors of the game components. For instance, the GamePanel class encapsulates the logic for the Snake game, including the snake's position, movement, and drawing.

Classes and Objects:
The entire project is organized using classes. Examples include TicTacToe, GamePanel, GameMenu, etc. Objects of these classes are created to represent instances of the game and its components.

Inheritance:
Inheritance is used in the GameFrame class, which extends the JFrame class to create a specialized frame for the game. Similarly, the TicTacToe and GamePanel classes extend basic classes (JFrame and JPanel, respectively) to build on their functionalities.

Polymorphism:
Polymorphism is demonstrated in the overridden paintComponent and actionPerformed methods in the GamePanel class. These methods provide different implementations for drawing the game components and responding to timer events.

GUI and Event-Driven Programming:
The entire project is based on GUI and event-driven programming using Swing. Components like buttons, panels, frames, and labels are used to create the graphical user interface. Event listeners, such as ActionListener and KeyListener, handle user inputs and events.
