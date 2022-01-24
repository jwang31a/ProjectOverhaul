# ProjectOverhaul
Jomin Zhang \
Kevin Xiao \
Jun Hong Wang

# Random Forest

Generates a maze, with the player labelled as !, spawning in the middle of the maze.
X stands for a wall that the player cannot walk into, and 0 represents an open path that the player can walk on.
The player has a limited vision of their surroundings, as well as vision of places they have been to.
All tiles that the player has not been to will be labelled as ?.
The monster will be chasing the player, and will be labelled as a *.
The goal is to escape the maze by reaching one of the sides.

# How-To-Play

In terminal, type javac Woo.java (in v4) and then java Woo.
Enter w, a, s, or d to go in the direction corresponding to the key.
The goal is to make it to the end, while avoiding the monster.
