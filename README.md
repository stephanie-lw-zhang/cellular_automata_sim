simulation
====

This project implements a cellular automata simulator.

Names: Stephanie Zhang, Connie Wu, Edward Hsieh

### Timeline
Start Date:

Finish Date: March 9

Hours Spent: ~40 hours

### Primary Roles
Edward focused on visualization. Connie and Stephanie split up rule implementation.

### Resources Used
- StackOverFlow
- OracleDocs


### Running the Program
Main class: SimulationMain

Data files needed: All included within program

Interesting data files:

Features implemented:

- Triangle, Square, Hexagonal Grids
- Toroidal, Finite, and Spherical Edge Policy
- Complete, Cardinal, and Diagonal Neighbor Arrangement

Exceptions:

- SimulationException -> general exception from simulation
- SimulationUIException -> exception from interactions with the GUI
- MissingResourceException -> Configuration file insufficient
- FileNotFoundException -> Configuration file not found

Edge of Choice:
Sphere

Neighbor of Choice:
Diaganals, which is found using the difference between complete and cardinal neighborhoods. However, for hexagons complete and cardinal are the same, so we implemented a special version where the neighborhood are along the slanted edges of the hexagon (4 total).

Assumptions or Simplifications:

- Dynamic parameters are spelled the same within a configuration file and are of the same case at each character

Known Bugs:

- Changing animation speed does not work very well sometimes
