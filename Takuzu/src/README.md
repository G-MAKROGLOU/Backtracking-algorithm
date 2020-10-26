# Backtracking-algorithm
Backtracking algorithm

````
 A Backtracking algorithm in order to solve a Takuzu or else Binary puzzle. 
 It also contains a very primitive implementation of a brute force kind of recursive 
 algorithm for the solution of totally empty grids with a limitation of up to 6x6 by grids.
 The constraints and the validations are implemented differently for empty grids, incomplete grids,
 and complete grids and some of the methods that involve row and column filtering are universal by using
 a function to shift rows to columns. 
 
 ````

To run the project:
Open it in IntelijIdea or Eclipse and it should be ready for building and running it.
If it does not detect the class with the main method immediately, you can configure it by doing the following:
1) Click the dropdown next to the hammer icon on top right
2) Select Edit configurations
3) In the new window click the plus icon on the top left
4) Select Application from the list of of options on the left pane
5) Add a name for your configuration
6) On the 'Main class' field click the three dots at the end of the input. IntelijIdea will detect the main class automatically
7) Click Apply and Ok

Minimum requirements: Java 1.8.0 (lambda expressions)

I left also an extra class in the project called temp where you can find different grids in order to test
against different possibilities. 

This is a more compact attempt, with the backtracking algorithm working as it should.
For totally empty grids, I implemented a simple brute force logic with threads to avoid Stack Overflow errors
However, this works with grids up to 6x6 and it takes quite a while (more or less 18 seconds) in order to create and match all permutations.

You can instantiate any grid from the Temp class inside Takuzu class right inside the ValidateMatrix method:

Example:
````
public static void main(String[] args) {
        //set the sign for empty through the public static field EMPTY of TakuzuLogic
        TakuzuLogic.EMPTY = 2;
        
        if(TakuzuLogic.ValidateMatrix(Temp.GridWithManySolutions())) 
            TakuzuLogic.print("Congratulations!!! You solved the puzzle....");
    }

````
 
 For defining the empty symbol that fits your grids you can use the static field EMPTY as in the example(as long as it's an integer).
 You can find also a couple of utility methods for printing and debugging inside TakuzuLogic at the end of the class.
 All of the checks are verbose so you can get an idea of what is going on through each iteration. 
 The list of the available grids:
 1) NewSolvableGrid()
 2) NewSixBySixEmptyGrid()
 3) NewFourByFourGrid()
 4) NewFourByFourEmptyGrid()
 5) NewSolvedGrid()
 6) NewFalseColGrid()
 7) NewFalseRowGrid()
 8) NewSameRowGrid()
 9) NewSameColumnGrid()
 10) NewFalseRowLenGrid()
 11) NewFalseColLenGrid()
 12) NewGridNoQuads()
 13) GridWithManySolutions()
 
