
# Generala-Demo

Demo Generala/Yahtzee game engine written in simple Java. 

Playable via the console, or library can be imported as engine only for use with other UIs. 

Example of console play...   

```./gradlew -q runConsoleDriver```   

<pre>
***GENERALA: GAME BEGIN***

roll dice (press 1 to roll) 
(rolls remaining this turn:3)
1
current dice:
DIE1 5 (selected:false)
DIE2 2 (selected:false)
DIE3 3 (selected:false)
DIE4 2 (selected:false)
DIE5 4 (selected:false)

select/unselect die to hold (1-5, 0 for done)
4
selected/unselected 4
select/unselect die to hold (1-5, 0 for done)
5
selected/unselected 5
select/unselect die to hold (1-5, 0 for done)
3
selected/unselected 3
select/unselect die to hold (1-5, 0 for done)
2
selected/unselected 2
select/unselect die to hold (1-5, 0 for done)
0

roll dice (press 1 to roll) 
(rolls remaining this turn:2)
1
current dice:
DIE1 2 (selected:false)
DIE2 2 (selected:true)
DIE3 3 (selected:true)
DIE4 2 (selected:true)
DIE5 4 (selected:true)

select/unselect die to hold (1-5, 0 for done)
0

roll dice (press 1 to roll) 
(rolls remaining this turn:1)
1
current dice:
DIE1 1 (selected:false)
DIE2 2 (selected:true)
DIE3 3 (selected:true)
DIE4 2 (selected:true)
DIE5 4 (selected:true)

3 rolls up, turn over
current scorecard:
1)  ONES            selected:false 0         
2)  TWOS            selected:false 0         
3)  THREES          selected:false 0         
4)  FOURS           selected:false 0         
5)  FIVES           selected:false 0         
6)  SIXES           selected:false 0         
7)  THREEKIND       selected:false 0         
8)  FOURKIND        selected:false 0         
9)  FULLHOUSE       selected:false 0         
10) SMALLSTRAIGHT   selected:false 0         
11) LARGESTRAIGHT   selected:false 0         
12) CHANCE          selected:false 0         
13) GENERALA        selected:false 0         
-1) UPPERBONUS      selected:false 0         
TOTAL:0
select a score (using position number)
10
current scorecard:
1)  ONES            selected:false 0         
2)  TWOS            selected:false 0         
3)  THREES          selected:false 0         
4)  FOURS           selected:false 0         
5)  FIVES           selected:false 0         
6)  SIXES           selected:false 0         
7)  THREEKIND       selected:false 0         
8)  FOURKIND        selected:false 0         
9)  FULLHOUSE       selected:false 0         
10) SMALLSTRAIGHT   selected:true 30        
11) LARGESTRAIGHT   selected:false 0         
12) CHANCE          selected:false 0         
13) GENERALA        selected:false 0         
-1) UPPERBONUS      selected:false 0         
TOTAL:30
score chosen, new turn starting

roll dice (press 1 to roll) 
(rolls remaining this turn:3)
</pre>
