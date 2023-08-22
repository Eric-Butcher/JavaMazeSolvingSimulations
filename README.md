# Java Maze-Solving Simulations

## Introduction
This project is a collection of maze-generating and maze-solving simulations. 
Currently, there are two maze-generating algorithms and two maze-solving algorithms implemented.
The purpose of this project was to create an application for testing and validating maze-solving algorithms for the IEEE Micro-mousse competition team.
This application provides a simple API for creating and validating maze-solving algorithms and visualizing them on the Micro-mouse standard 16x16 maze configuration. 


### Maze-Generating Algorithms:
1. [The Prim Algorithm](https://en.wikipedia.org/wiki/Maze_generation_algorithm#Iterative_randomized_Prim's_algorithm_(without_stack,_without_sets)) 
2. [The Aldous-Broder Algorithm](https://en.wikipedia.org/wiki/Maze_generation_algorithm#Aldous-Broder_algorithm)

### Maze-Solving Algorithms:
1. [The Depth-First Search Algorithm](https://en.wikipedia.org/wiki/Maze_solving_algorithm#Depth-first_search) with a Heuristic
2. [Greedy Best-First Search Algorithm](https://en.wikipedia.org/wiki/Best-first_search#Greedy_BFS) 

## Getting Started

To begin generating a maze, select a maze-generating algorithm from the drop-down menu and click the "Generate Maze" button (the default algorithm for generating will be the Prim algorithm).
Then you can press either the step button, the finish button, or the play/pause button. 
The step button will step through the maze-solving algorithm one step at a time. 
The finish button will run the maze-solving algorithm until it reaches the end. 
The play/pause button will run the maze-solving algorithm until it reaches the end or until the pause button is pressed again to pause.

After a maze has finished generating, use the same approach to solve the maze. 
The default solver will be the Heuristic Depth-First Search. 
The solve button will not do anything unless you have first finished generating a maze to be solved. 
By default, the goals will be the center four cells, which are the destinations to reach for the Micro-mouse competition. 

## How to Use the API

In the model package, there are two abstract classes: Generator and Solver. Both of these abstract classes require their concrete child-classes to implement the iterate() method, the makeViewUpdatePacket() method, and the finish() method. 
The iterate() method is called once per step of the maze-solving algorithm. The finish() method is called once and will call iterate repeatedly until the maze is solved.
The makeViewUpdatePacket() method is called once per step of the maze-solving algorithm and returns a ViewUpdatePacket object. The ViewUpdatePacket object contains the information needed to update the view of the maze. This should be written so that visual updates can be made after each iteration of the maze-solving/generating algorithm.

