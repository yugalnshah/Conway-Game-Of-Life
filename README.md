# Conway Game of Life
The purpose of this project is to practice my understanding of 2D arrays and Union-Find.

## Overview
Conway Game of Life is a cellular discrete model of computation devised by John Horton Conway. The game consists of a game board (grid) of n x m cells, each in one of two states, alive or dead.

The game starts with an initial pattern, then it will change what cells are alive or dead from one generation to the next depending on a set of rules. As the Game of Life continues, the game will keep making a new generation (based on the preceding one) until it reaches one of three states.

### Cells
Each cell can be in one of two states, alive or dead, and it has 8 neighboring cells which are the cells that are horizontally, vertically, or diagonally adjacent. The figure below exemplifies neighboring cells on a game board of 4 x 4 cells.

### Rules of the Game
The game starts with a initial set of alive cells (this is the first generation). The next generation evolves from applying the following rules simultaneously to every cell on the game board, i.e. births and deaths happen simultaneously. Afterwards, the rules are iteratively applied to create future generations. Each generation depends exclusively on the preceding one.
