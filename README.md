# ProjectOverhaul
Jomin Zhang + Chompsky \
Kevin Xiao Mr. Swag \
Jun Hong Wang + Bob

# Random Forest Description

Our project is a maze, with a monster in the middle.
The goal is to make it out of the maze without being caught by the monster.

The program will generate a maze with a certain set of conditions to make sure that the player can escape the maze.
The player will spawn in the middle, as well as the monster.
Every time the player moves, the monster will.

# Files

Woo.java is the driver file for it, and doesn't contain anything other than a main method.
Game.java contains the maze generation, the display, and the game itself.
Player.java contains the instance variables associated with the player, like position, as well as accessor and set methods.
Monster.java contains the instance variables associated with the monster, like position, as well as the chase method, which will be invoked in the game every time the player moves.

# How-To-Play

To start the program, go into v4 of the project, and type in the terminal javac Woo.java, then java Woo.
At the beginning of the maze, the maze displayed will only be square of ?, representing tiles where the player has not gone to, and the player with a !.
The player will input w, a, s, or d to go in each of the four directions.
Every time the player moves (onto an open spot), the monster will move and chase the player.
If the monster gets to the player successfully, the player will be caught, and the game will be over.
If the player makes it out of the maze successfully, then the player wins. 
