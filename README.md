# Catch Them All 

## Decription
### Authors : Liel Vaknin & Renana Levy

This project consists of two parts.
The first one implements algorithms for developing a data structure type of a directed weighted graph.
The second one actually uses this graph we created in order to develop pokemons game.

---
## api

 In this package there are 5 classes which each class implements a given interface.

* NodeData class implements the node_data interface.
It represents the set of operations applicable on a node in a directed weighted graph.
This class implements the methods:
Default constructor, copy constructor, set & get methods, toString method,
equals and hashCode methods and compareTo method (this class also implements the Comparable interface).

* GeoLocation class implements the geo_location interface.
It represents a geo location <x,y,z>, aka Point3D.
This class implements the methods: 
Constructor which gets x, y and z, another one which gets only x and y and a constructor which gets a String pos and initializes x, y and z, 
get methods and a method which calculates the distance between 2 geo locations. 

* EdgeData class implements the edge_data interface.
It represents the set of operations applicable on a directional edge in a directed weighted graph.
This class implements the methods:
Constructor which gets src, dest and edgeWeight, copy constructor, set & get methods, equals and hashCode methods.

* DWGraph_DS class implements the directed_weighted_graph interface.
It represents a directed weighted graph.
This class implements the methods:
Default constructor, copy constructor, set & get methods, methods for adding / removing nodes and edges to / from the graph,
a method for connecting an edge with weight w between a given src node to a given dest node, toString method and equals and hashCode methods

* DWGraph_Algo class implements the dw_graph_algorithms interface.
It represents a Directed Weighted Graph Theory algorithms.
The class includes a set of operations applicable on the graph g - 
initialization of graph g with a given graph, a getGraph method, a deep copy method, 
a method which uses the [DFS algorithm](https://en.wikipedia.org/wiki/Depth-first_search) for the implementation of method which checks
if the graph is connected or not, finding the shortest path in the graph between a given source and destination and a method for finding its length - using  [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm),
a method which saves this graph to a given file name and a method which loads a graph to this graph algorithm (using JSON format and gson library).


## Tests package
This package includes 2 JUNIT tests :
 -  DWGraph_DSTest - for testing class DWGraph_DS algorithms.
 -  DWGraph_AlgoTest - for testing class DWGraph_Algo algorithms. 
 
### An example of a directed weighted graph:

## Game implementation 

 There are 2 packages and 10 classes in this part.
 
 ### GameClient package
 
 This package contains all classes which implements the logic of the game:
* Arena class represents the arena of the game and manages the logic of the game in it.
* CL_Agent class represents an agent in the game.
* CL_Pokemon class represents a pokemon in the game.
* jsonToObject class receives data from the server (jar file) on which the game is performed and loads the information from it which based on strings represented as JSON.
* GamePlay class 
* Ex2 contains the "main" method which runs the whole project.

 ### Gui package
 
 This package contains all classes which implements the Graphical User Interface (Gui) of the game.
 * Frame class extends JFrame and represents the frame on which the loginPanel and the gamePanel drawing on.
 * gamePanel class extends JPanel uses for drawing the window of the game on the frame.
 * loginPanel class extends JPanel uses for drawing the login window on the frame.
 
### Our game goal

We implemented a simple logic game in which there are agents and pokemons.
Each pokemon has different value and the role of the agents is to catch as many pokemons as 
possible in order to increase their own value.
Our algorithm determines the start position of each agent before starting the game and also directs each one along the graph during the game.
The algorithm finds the nearest pokemon for each agent and moves the agent to there - according to [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm).
The more Pokemons are caught, the higher score in the game!

## Installation
For installing the game please follow the instructions below:
* Clone this repository.
* To start playing the game run Ex2.
* Insert your ID & Chooce level.
* Press Login and let's "catch them all!"

### An example of running level 11 of the game :



### *Have fun!*

*For more information go to the project's [wiki pages](https://github.com/LielVaknin/ex2/wiki)*


 


