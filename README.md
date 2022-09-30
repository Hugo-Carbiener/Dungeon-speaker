<h1 style="text-align:center"> Dungeon Speaker </h1>

![start](https://user-images.githubusercontent.com/41008899/193330976-6a4008de-39e4-4b4e-b760-c111f5825708.png)

**Dungeon Speaker** is a student project for the sake of the first year of engineering cursus at [Telecom Sudparis](https://www.telecom-sudparis.eu/).  
It is inspired from the amazing game [AI dungeon](https://play.aidungeon.io/main/landing). As such, in Dungeon Speaker, the player can *wander around the dungeon*, *fight off foes* and *loot some items* by typing instructions in the text field. 


 ## Our objectives :

* :european_castle: Generate a dungeon map as randomly as possible to offer a lot of replayability as well as many various events, objects and caracters to interact with.
* :pencil2: Create an environement in which players can interact with the game simply by typing a description of their actions.
* :speech_balloon: Develop a way to generate a narration of the events unfolding during the game.


## An overview of the UI :

![forward](https://user-images.githubusercontent.com/41008899/193331606-fc3f21a0-2da3-47c2-a2d9-316026ef0d66.png)

## Update - Dungeon Layout & Map display :

We managed to implement a **procedurally generated** dungeon layout in the form of a *non-binary tree*. This way the player can move from room to rooms using the cardinal points.  

I also worked on a practical way to display the map in **Ascii** whenever the player types something like *"I look at the map"*.
Here is how it looks like ! 

<img width="611" alt="map" src="https://user-images.githubusercontent.com/41008899/193332786-590ce4ee-435a-4173-8b21-8cc8ef75651c.png">

Note that you can see the player's position where the square is filled ! 
