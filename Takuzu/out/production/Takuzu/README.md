To run the project:
Open it in IntelijIdea or Eclipse and it should be ready for building and running it.
If you it does not detect the class with the main method immediately, you can configure it by doing the following:
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
However, this works with grids up to 6x6 and it takes quite a while (more or less ) in order to create and match all permutations.
 
