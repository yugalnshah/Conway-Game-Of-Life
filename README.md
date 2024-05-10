# Conway Game of Life
The purpose of this project is to practice my understanding of 2D arrays and Union-Find.

## Overview
Conway Game of Life is a cellular discrete model of computation devised by John Horton Conway. The game consists of a game board (grid) of **n x m** cells, each in one of two states, alive or dead.

The game starts with an initial pattern, then it will change what cells are alive or dead from one generation to the next depending on a set of rules. As the Game of Life continues, the game will keep making a new generation (based on the preceding one) until it reaches one of three states.

### Cells
Each cell can be in one of two states, alive or dead, and it has 8 neighboring cells which are the cells that are horizontally, vertically, or diagonally adjacent. The figure below exemplifies neighboring cells on a game board of 4 x 4 cells.

### Rules of the Game
The game starts with a initial set of alive cells (this is the first generation). The next generation evolves from applying the following rules simultaneously to every cell on the game board, i.e. births and deaths happen simultaneously. Afterwards, the rules are iteratively applied to create future generations. Each generation depends exclusively on the preceding one.

<img width="1224" alt="Rules" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/af1d3a4e-72ca-4bc7-8f1e-9bdbebc5f589">

### States for the Game
There are three states that the game can reach in regards to the rules provided.

There are no more living cells for the next generation.
There are living cells, but the next generation is the same as the last (stable game).
The next generation infinitely cycles throughout the game.
Note that there are more scenarios for states 2 and 3, these are just one example for each.

<img width="1097" alt="Screenshot 2024-05-10 at 7 07 40 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/d615bd29-d3cc-4840-bfaf-f0e9f4e6d5d3">

### Methods

#### 1. GameOfLife – One argument constructor 

* This method builds your game board (grid) from the input file (initial game pattern).
* You have been provided some input files to test this method (input1.txt, input2.txt, input3.txt). The format is as follows:
    * One line containing the number of rows in the board game
    * One line containing the number of columns in the board game
    * Several lines (one for each row) containing the cell state (false for dead, true for alive) for each column in the board, space separated

Use the **StdIn** library to read from a file:

* StdIn.setFile(filename) opens a file to be read
* StdIn.readInt() reads the next integer value from the opened file (weather the value is in the current line or in the next line)
* StdIn.readBoolean() read the next boolean value from the opened file (weather the value is in the current line or in the next line)


#### 2. getCellState

Given two integers representing the cell row and column this method returns true if the cell is alive and false if the cell is dead.

Test this method using the driver:

* upon clicking the button **Cell State** you will be prompted to select a cell
* select a cell by clicking in one square                                                                   
* the selected cell will light up red and the driver will state whether the cell is living or dead.
<img width="308" alt="Screenshot 2024-05-10 at 7 13 24 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/cb2c5383-f736-4860-9a39-0889194e856a">

#### 3. isAlive

Returns true or false based on whether or not there are living cells within the board (grid)

Test this method using the driver. Click on the **Is Alive** button and the driver will display whether or not there are living cells on the board game.
<img width="308" alt="Screenshot 2024-05-10 at 7 16 42 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/c8a186f8-745b-47ad-a83a-3bf0af9ec4fd">

#### 4. numOfAliveNeighbors

Given two integers representing the cell row and column this method returns the cell’s number of alive neighbors of a maximum of 8 neighbors.

Test this method using the driver:

* upon clicking the button **Alive Neighbors** you will be prompted to select a cell
* select a cell by clicking in one square
* the selected cell will light up red and the driver will state the total number of alive cells around the selected cell
<img width="300" alt="Screenshot 2024-05-10 at 7 17 51 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/c6e2bc6c-5262-4d3a-ab6b-f2552d48dd1e">

#### 5. computeNewGrid

This is where you will be using the rules of the game (stated above) to compute the next generation of cells.

* create a new board (grid) to be returned, representing the new generation
* for each cell, use the numOfAliveNeighbors method to determine how many cells are alive around a cell
* then using the number of alive neighbors with the rules of the game determine if the cell will be set to be alive or dead in the new grid

#### 6. nextGeneration

Update the board (grid) with the board returned by the computeNewGrid method.

Test this and the computeNewGrid methods using the driver by clicking the **Next Generation** button.

The driver will state **Next generation calculated.**
<img width="302" alt="Screenshot 2024-05-10 at 7 19 27 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/320c15d0-6ef3-4fe8-bea6-932de0f6b865">

#### 7. nextGeneration - one argument

The input integer parameter represents the number of generations to compute (fast forward).

Test this methods using the driver: 

* upon clicking the **Next N Generations** button you will be prompted to enter a number
* enter a number and click the **Submit** button
* the driver will state that the board has evolved the number of generations requested
<img width="602" alt="Screenshot 2024-05-10 at 7 20 33 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/d855440a-3535-4fda-b240-65afda0f48c2">

#### 8. numOfCommunites

This method computes and returns the number of separate communities of cells in the board. A community is made of connected cells.

Recall that Union-Find keeps track of connected components. In this assignment each connected component is a community of cells (all the cells in the community are connected).
* Cells from separate communities are not connected.
In algorithm Weighted Quick Union UF each community (connected component) is a tree. The community’s representative is the root of the tree.

* To connect two cells use the union method
* To find the root of the tree a cell belongs to use the find method
* To find the number of communities count the number of unique roots of trees
  
Test this methods using the driver by clicking the **Communities** button.

The driver will state the number of communities currently on the board.
<img width="277" alt="Screenshot 2024-05-10 at 7 21 52 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/c205c3a8-25b9-456e-9d3e-79a4104153c5">

### Find below the expected outputs after going through one generation of grids 1-6

<img width="625" alt="Screenshot 2024-05-10 at 7 22 24 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/652a1580-587c-4459-9697-dae6c9e9a5b0">
<img width="633" alt="Screenshot 2024-05-10 at 7 22 31 PM" src="https://github.com/yugalnshah/Conway-Game-of-Life/assets/162384655/708cd5b9-4787-4aa3-8dae-dacf66447a71">
