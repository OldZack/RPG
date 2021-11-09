Wanzhi Wang, z1wang@bu.edu, U72807790

This program implements a RPG game: Legends: Monsters and Heroes.

Execution:
>javac *.java
>java Main.java

Class description:
Inventory: represents a group of items carried by a unit (hero or market), classified by item type.
Armory: subclass of gears, represents an armor item in the game.
Weaponry: subclass of gears, represents a weapon item in the game.
Potion: subclass of gears, represents a potion item in the game.
Spell: subclass of gears, represents a spell item in the game.
Gear: represent an item in the game.

Player: represents a group of heroes controlled by a single player.
Enemy: represents a group of monsters as the player's opponent.
Hero: subclass of character, represents a single hero character in the game.
Monster: subclass of character, represents a single monster character in the game.
Character: represents a character in the game.

Map: represents the map of the game, also keep track of player's position.
Tile: represents a single unit that forms a map, has different contents.
Market: represents the market, capable of selling and purchasing items.

Parser: used to read game files and parse data into arrays.
levelRequirements: an interface used to check whether the hero's level is high enough to equip an item.
manaRequirements: an interface used to check whether the hero's mana is enough to cast a spell.

LMH: subclass of RPG, file that contains the main logic and element interaction of game Legends: Monsters and Heroes.
GameManager: used for user to select different games.
RPG: file that contains the major elements of a RPG.
Main: the main file of the program.

Other info:
The program ensures the scalability of items and characters.
The game handles every possible invalid input.
The game has colored output.
The game has a music. (Music by Jason Shaw on Audionautix.com)