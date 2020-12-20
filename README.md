# Catch Them All 

## Decription
### Authors : Liel Vaknin & Renana Levy

This project consists of two parts.
The first one implements algorithms for developing a data structure type of a directed weighted graph.
The second one uses this graph we created in order to develop pokemons game.

---
## api

 In this package there are 5 classes which each class implements a given interface.

* NodeData class implements the node_data interface.
It represents the set of operations applicable on a node in a directed weighted graph.
This class implements the methods:
set & get methods, toString method and equals method.

* GeoLocation class implements the geo_location interface.
It represents a geo location <x,y,z>, aka Point3D.
This class implements the methods: 
getters for x, y and z and a method which calculates the distance between 2 geo locations. 

* EdgeData class implements the edge_data interface.
It represents the set of operations applicable on a directional edge in a directed weighted graph.
This class implements the methods:
set & get methods and equals method.

* DWGraph_DS class implements the directed_weighted_graph interface.
It represents a directed weighted graph.
This class implements the methods:
set & get methods, methods for adding / removing nodes and edges to / from the graph,
a method for connecting an edge with weight w between a given src node to a given dest node, toString method and equals method.

* DWGraph_Algo class implements the dw_graph_algorithms interface.
It represents a Directed Weighted Graph Theory algorithms.
The class includes a set of operations applicable on the graph g - 
initialization of graph g with a given graph, a getGraph method, a deep copy method, 
a method which uses the [DFS algorithm](https://en.wikipedia.org/wiki/Depth-first_search) for the implementation of method which checks
if the graph is strongly connected or not, finding the shortest path in the graph between a given source and destination and a method for finding its length - using  [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm),
a method which saves this graph to a given file name and a method which loads a graph to this graph algorithm (using JSON format and gson library).


## Tests
This ptoject includes 2 JUNIT tests :
 -  DWGraph_DSTest - for testing class DWGraph_DS algorithms.
 -  DWGraph_AlgoTest - for testing class DWGraph_Algo algorithms. 
 
### An example of a directed weighted graph: 

![Graph example](https://github.com/LielVaknin/ex2/blob/master/resources/Graph%20example.png)

## Pokemons game 

 There are 2 packages and 9 classes in this part.
 
 ### GameClient package
 
 This package contains all classes which implements the logic of the game:
* Arena class represents the arena of the game and manages the logic of the game in it.
* CL_Agent class represents an agent in the game.
* CL_Pokemon class represents a pokemon in the game.
* jsonToObject class receives data from the server (jar file) on which the game is performed and loads the information from it which based on strings represented as JSON.
* GamePlay class manages the processes of the game.
* Ex2 contains the "main" method which runs the whole project.

 ### Gui package
 
 This package contains all classes which implements the Graphical User Interface (Gui) of the game.
 * Frame class extends JFrame which displays the gamePanel.
 * gamePanel class extends JPanel and displays the game arena window.
 * loginPanel class extends JPanel implements ActionListener. Displays the login window of the game.
 
### How the game works?

We implemented a simple logic game in which there are agents and pokemons.
Each pokemon has different value and the role of the agents is to catch as many pokemons as 
possible in order to increase their own value.
Our algorithm determines the start position of each agent before starting the game and also directs each one along the graph during the game.
The algorithm finds the nearest pokemon for each agent and moves the agent to there - according to [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).
The more Pokemons are caught, the higher score in the game!<br />
There are 0-23 levels and each one has a different amount of agents, pokemons and limited time.
When Pokemon caught, a new one appears in the game arena.

## Installation
For installing the game please follow the instructions below:
* Clone this repository.
* To start playing the game run Ex2.
* Insert your ID & Chooce level.
* Press Login and let's "catch them all!"

### An example of running level 11 of the game :

*Inserting ID and level here :*

![Login example](https://github.com/LielVaknin/ex2/blob/master/resources/Login%20example.PNG)

*After pressing Login the game starts :*

![gamePanel](https://github.com/LielVaknin/ex2/blob/master/resources/gamePanel.PNG)

### *Have fun!*

*For more information go to the project's [wiki pages](https://github.com/LielVaknin/ex2/wiki)*


 


