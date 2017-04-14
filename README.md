# ForestGrowth
Authors:
Dominic Del Vecchio
Sai Gowthami Bojja

Program is run form main. After the program is run press start on the board. program will stop after 20 cycles of 
1000 timesteps

Line 35 in Main calls the forest constructor and can be used to change the growth rates for both species
it is currentley set to 2% as the probability function calls numbers in the range of 1-1000, so 20 == 2%

In order to run two species growtree1() on line 204 of Forrest must be commented out and growtree2() on line 
205 must be uncommented. The two different functions were used to help with perfomrance on species1. Species 1
will still work but not the same if species 2 is set to 0 and growTree2() is active as it first checks
to see which species will have a chance to grow.

To add firefighters line 255 of Forrest  must be uncomented. It will start with 0 fire fighters and add 50 per cycle.
Each cylce is 1000 timesteps. If you want to check firefighters more directly the number can be changed from
zero to the desired amount by changin the number in fireDep on line 33 of Forrest.

Also uncommenting line 253 in Forrest will cause the growth rate to increment by 5% every cycle of 1000 timesteps.
Line 254 will do the same for species 2 but as mention above growTree2 must be uncommented and growTree1() must be commented out


