Right now we're thinking of creating 3 classes: Cell, Rules, Grid.

* How does a Cell know what rules to apply for its simulation?
The rules class will read a file that specifies the rules and pass it into the cell class.

* How does a Cell know about its neighbors? How can it update itself without affecting its neighbors update?
It can know about its neighbors by location. We can keep a version of the original grid and then update each cell based on that before showing the updated grid frame.

* What behaviors does the Grid itself have? How can it update all of the Cells it contains?
The grid shows an animation of the cells as it changes. For each step it will go through every cell and the next step shows the new update.

* What information about a simulation could be given in a configuration file?
What rules the cells need to follow. Also the grid size, colors, etc.

* How is the GUI updated after all the cells have been updated?
It will be updated with a step function most likely.


