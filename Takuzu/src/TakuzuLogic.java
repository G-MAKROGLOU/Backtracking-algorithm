import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class TakuzuLogic {
    private final static int[] SPACE = {0, 1};
    static int EMPTY;

    /**
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @return true if the grid is symmetrical otherwise false (only even numbers >= 4 % 2 = 0)
     */
    private static boolean ValidateShape(int[][] TakuzuGrid){
        print("\nChecking length of rows...");
        int rowCount = TakuzuGrid.length;
        int rowLength = rowCount > 0 ? TakuzuGrid[0].length : 0;
        if(rowCount % 2 != 0 ){
            print("Invalid puzzle shape! The puzzle should have an equal number of rows and columns.\nThis number should be greater than 4 and divisible by 2");
            return false;
        }
        print("Rows: OK => Count: " + rowCount);
        print("Starting deep length check for columns...");
        for(int i = 0; i < TakuzuGrid.length; i++) {
            if(TakuzuGrid[i].length == TakuzuGrid.length) print("Column " + i + ": OK => Count: " + TakuzuGrid[i].length);
            else {
                print("Row " + i + " has an invalid number of elements\nCount: " + TakuzuGrid[i].length);
                return false;
            }
        }
        print("The puzzle shape is ok! Dimensions: " + rowCount + "x" + rowLength + "\n");
        return true;
    }//ok


    /**
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @return true if the puzzle is completely filled otherwise false
     */
    private static boolean CheckProgress(int[][] TakuzuGrid){
        int counter = 0;
        for (int[] ints : TakuzuGrid)
            for (int k = 0; k < TakuzuGrid[0].length; k++)
                if (ints[k] == 1 || ints[k] == 0) counter++;

        if(counter == (TakuzuGrid.length * TakuzuGrid[0].length)) {
            print("The puzzle is completely filled! Progress: " +(counter*100)/(TakuzuGrid.length * TakuzuGrid[0].length) + "% Starting validity checks....\n");
            return true;
        }
        print("The grid is incomplete. Number of empty cells: " + ((TakuzuGrid.length * TakuzuGrid[0].length) - counter) + " Progress: " + (counter*100)/(TakuzuGrid.length * TakuzuGrid[0].length) + "%\n");
        return false;
    }//ok


    /**
     * The solver dialog that decides which solver to trigger depending the grid completion status
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     */
    private static void SolverDialog(int[][] TakuzuGrid){
        System.out.print("Do you want me to show you the solution?(Y/N): ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().toLowerCase().matches("y") || scanner.nextLine().toLowerCase().matches("yes")){
            if(IsGridTotallyEmpty(TakuzuGrid)) EmptyGridSolver(TakuzuGrid);
            else {
                if(IncompleteGridSolver(TakuzuGrid)) {
                    print("Solution....");
                    DebugGrid(TakuzuGrid);
                }
                else print("Could not solve the grid...");
            }
        }
    }


    /**
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @return true if the grid is totally empty otherwise false
     */
    private static boolean IsGridTotallyEmpty(int[][] TakuzuGrid){
        int counter = 0;
        for (int[] ints : TakuzuGrid)
            for (int j = 0; j < TakuzuGrid[0].length; j++)
                if (ints[j] != 1 && ints[j] != 0)
                    counter++;
        if(counter == Math.pow(TakuzuGrid.length, 2)){
            print("The grid is totally empty...");
            return true;
        }
        return false;
    }

    /**
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     */
    private static boolean IncompleteGridSolver(int[][] TakuzuGrid){
        for(int i = 0; i < TakuzuGrid.length; i++){
            for(int k = 0; k < TakuzuGrid[0].length; k++) {
                if(TakuzuGrid[i][k] == EMPTY){
                    for(int j = 0; j < SPACE.length; j++){
                        print("\nNow checking value: " + j +" => Row: " + i + " Column: " + k);
                        if(IsNotBreakingRules(i, k, SPACE[j], TakuzuGrid)){
                            TakuzuGrid[i][k] = SPACE[j];
                            if(IncompleteGridSolver(TakuzuGrid)) return true;
                            else TakuzuGrid[i][k] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Recursive backtracking algorithm for solving incomplete grids.
     * IncompleteGridSolver is the trigger of this method form which the
     * parameters are also passed.
     * @param row The grid row
     * @param col The grid column
     * @param value The value we are trying => coming from static Array SPACE
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @return true in order to trigger the next recursive iterations otherwise false and triggers the backtracking
     */
    private static boolean IsNotBreakingRules(int row, int col, int value, int[][] TakuzuGrid){
        TakuzuGrid[row][col] = value;
        int[][] Shifted = ShiftArray(TakuzuGrid);
        int[] currentRow = TakuzuGrid[row];
        int[] currentCol = Shifted[row];
        int rowCounter = 0;
        int colCounter = 0;

        //check triplets in rows
        print("Checking triplets in rows....");
        for(int i = 0; i < currentRow.length; i++){
            if(i < currentRow.length - 2){
                if(currentRow[i] == currentRow[i+1] && currentRow[i] == currentRow[i+2] && currentRow[i] != EMPTY){
                    print("Found triplet in row " + row);
                    TakuzuGrid[row][col] = EMPTY;
                    return false;
                }
            }
        }


        //check triplets in columns
        print("Checking triplets in columns....");
        for (int[] ints : Shifted) {
            for (int k = 0; k < Shifted[0].length; k++) {
                if (k < Shifted[0].length - 2) {
                    if (ints[k] == ints[k + 1] && ints[k] == ints[k + 2] && ints[k] != EMPTY) {
                        TakuzuGrid[row][col] = EMPTY;
                        return false;
                    }
                }
            }
        }

        //check max in rows
        print("Checking max in rows....");
        for(int i = 0; i < TakuzuGrid[row].length; i++){
            if(TakuzuGrid[row][i] == value){
                rowCounter++;
            }
        }
        if(rowCounter > TakuzuGrid.length / 2){
            TakuzuGrid[row][col] = EMPTY;
            return false;
        }

        //check max in columns
        print("Checking max in columns....");
        for(int i = 0; i < Shifted[col].length; i++){
            if(Shifted[col][i] == value){
                colCounter++;
            }
        }
        if(colCounter > TakuzuGrid.length / 2){
            TakuzuGrid[row][col] = EMPTY;
            return false;
        }

        //check same rows
        if(row == 0) return true;
        print("Checking same rows....");
        for(int i = 0; i < TakuzuGrid.length; i++){
            int counter = 0;
            for(int k = 0; k < TakuzuGrid[0].length; k++){
                if(i != row){
                    if(TakuzuGrid[i][k] == currentRow[k]){
                        counter++;
                    }
                }
            }
            if(counter == TakuzuGrid.length){
                TakuzuGrid[row][col] = EMPTY;
                return false;
            }
        }

        //check same columns
        print("Checking same columns...");
        for(int i = 0; i < Shifted.length; i++){
            int counter = 0;
            for(int k = 0; k < Shifted[0].length; k++){
                if(i != row)
                    if(Shifted[i][k] == currentCol[k]) counter++;
            }
            if(counter == TakuzuGrid.length){
                TakuzuGrid[row][col] = EMPTY;
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     */
    private static void EmptyGridSolver(int[][] TakuzuGrid){
        if(TakuzuGrid.length <= 6){
            print("Starting empty grid solver...");
            for(int i = 0; i < TakuzuGrid.length; i++){
                for(int j = 0; j < TakuzuGrid[0].length; j++){
                    if(j % 2 == 0)TakuzuGrid[i][j] = 0;
                    else TakuzuGrid[i][j] = 1;
                }
            }
            ExecutorService threadPool = Executors.newFixedThreadPool(50);
            Runnable task = () -> shuffleRecur(TakuzuGrid);
            threadPool.execute(task);
            threadPool.shutdown();
        }else{
            print("\n\nGrids greater than 6x6 produce an excessive amount of iterations\ncausing the stack to overflow when solving totally empty grids.");
            print("If you want to pass a totally empty grid, try a 4x4 or a 6x6\n\n");
        }
    }

    /**
     * Recursive brute force for empty grid. Up to 6x6.
     * 8x8 loop for a long time even with recursive threads
     * because they produce 8^8 possible results
     * @param TakuzuGrid  A matrix representing the takuzu grid
     */
    private static void shuffleRecur(int[][] TakuzuGrid){
        for (int[] ints : TakuzuGrid) {
            ArrayList<Integer> rowCollection = new ArrayList<>( );
            for (int i : ints) rowCollection.add(i);
            Collections.shuffle(rowCollection);
            for (int m = 0; m < rowCollection.size( ); m++) {
                ints[m] = rowCollection.get(m);
            }
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        Runnable task = () -> shuffleRecur(TakuzuGrid);
        int[][] ShiftedGrid = ShiftArray(TakuzuGrid);
        if(!ValidateMaxInRowOrCol(TakuzuGrid, "row") && !ValidateMaxInRowOrCol(ShiftedGrid, "column"))
            if(!ValidateSameRowOrColumn(TakuzuGrid, "row") && !ValidateSameRowOrColumn(ShiftedGrid, "column"))
                if(!ValidateRowsOrCols(TakuzuGrid, "row") && !ValidateRowsOrCols(ShiftedGrid, "column")) {
                    print("Solution....");
                    DebugGrid(TakuzuGrid);
                    threadPool.shutdown();
                    System.exit(1);
                }
                else threadPool.execute(task);
            else threadPool.execute(task);
        else threadPool.execute(task);
    }

    /**
     *
     * @param TakuzuGrid A matrix representing the takuzu grid
     * @return returns a new Takuzu grid, shifted in order to turn columns into rows
     */
    private static int[][] ShiftArray(int[][] TakuzuGrid){
        int[][] shiftedTakuzu = new int[TakuzuGrid.length][TakuzuGrid[0].length];
        for(int i = 0; i < TakuzuGrid.length; i++)
            for(int j = 0; j < TakuzuGrid[0].length; j++)
                shiftedTakuzu[i][j] = TakuzuGrid[j][i];
        return shiftedTakuzu;
    }


    /**
     * Universal check for rows and columns. For columns the grid has to be shifted first with ShiftArray()
     *
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @param rowOrCol A string for the concatenated output =>  values: 'row' || 'column'
     * @return false if everything is ok otherwise true (inverted)
     */
    private static boolean ValidateMaxInRowOrCol(int[][] TakuzuGrid, String rowOrCol){
        print("Checking for equal number of ones and zeros in each " + rowOrCol + "...");
        for(int i = 0; i < TakuzuGrid.length; i++){
            int zeroSum = 0;
            int oneSum = 0;
            for(int k = 0; k < TakuzuGrid[0].length; k++)
                if(TakuzuGrid[i][k] == 0){
                    zeroSum++;
                }else if(TakuzuGrid[i][k] == 1){
                    oneSum++;
                }
            if(zeroSum == oneSum) print(rowOrCol + ": " + i + " has an equal number of ones and zeros");
            else if(zeroSum > oneSum){
                print("There are more zeros than ones in " + rowOrCol +": " + i);
                return true;
            }else {
                print("There are more ones than zeros in " + rowOrCol +": " + i);
                return true;
            }
        }
        return false;
    }//ok


    /**
     * Universal check for rows and columns. For columns the grid has to be shifted first with ShiftArray()
     *
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @param rowOrCol A string for the concatenated output =>  values: 'row' || 'column'
     * @return false if everything is ok otherwise true (inverted)
     */
    private static boolean ValidateRowsOrCols(int[][] TakuzuGrid, String rowOrCol){
        print("Checking for more than two consecutive ones or zeros in each " + rowOrCol + "...");
        for(int i = 0; i < TakuzuGrid.length; i++){
            for(int k = 0; k < TakuzuGrid[0].length; k++){
                if(k == 0){
                    if(TakuzuGrid[i][k] == TakuzuGrid[i][k + 1] && TakuzuGrid[i][k] == TakuzuGrid[i][k + 2]){
                        print("You can't have more than two consecutive ones in a " + rowOrCol);
                        print("Character: " + TakuzuGrid[i][k] + " Count: 3 " + rowOrCol + ": " + i);
                        return true;
                    }
                }else if(k < TakuzuGrid[0].length - 1){
                    if(TakuzuGrid[i][k] == TakuzuGrid[i][k + 1] && TakuzuGrid[i][k] == TakuzuGrid[i][k - 1]){
                        print("You can't have more than two consecutive ones in a " + rowOrCol);
                        print("Character: " + TakuzuGrid[i][k] + " Count: 3 " + rowOrCol + ": " + i);
                        return true;
                    }
                }else{
                    if(TakuzuGrid[i][k] == TakuzuGrid[i][k - 1] && TakuzuGrid[i][k] == TakuzuGrid[i][k - 2]){
                        print("You can't have more than two consecutive ones in a " + rowOrCol);
                        print("Character: " + TakuzuGrid[i][k-1] + "Count: 3 " + rowOrCol + ": " + i);
                        return true;
                    }
                }
            }
        }
        print("All " + rowOrCol + "s are ok...\n");
        return false;
    }//ok


    /**
     * Universal check for rows and columns. For columns the grid has to be shifted first with ShiftArray()
     *
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @param rowOrCol A string for the concatenated output =>  values: 'row' || 'column'
     * @return true if everything is ok otherwise false
     */
    private static boolean ValidateSameRowOrColumn(int[][] TakuzuGrid, String rowOrCol){
        print("Checking for more than one same " + rowOrCol + " in the grid...");
        for(int i = 0; i < TakuzuGrid.length; i++){
            int[] row = TakuzuGrid[i];
            int counter = 0;
            for(int k = i+1; k < TakuzuGrid.length; k++){
                for(int j = 0; j < row.length; j++){
                    if(row[j] == TakuzuGrid[k][j]){
                        counter++;
                    }
                }
                if(counter == TakuzuGrid.length){
                    print("Found duplicate "+ rowOrCol  + ". All " + rowOrCol +"s in a grid must be unique....");
                    print(rowOrCol + ": " + i + " with " + rowOrCol + ": " + k + " are identical.");
                    return true;
                }
                counter = 0;
            }
        }
        print("All " + rowOrCol + "s are unique...");
        return false;
    }

    /**
     *
     * @param TakuzuGrid A solvable or unsolvable takuzu grid
     * @return true if all checks are passed otherwise false
     */
    static boolean ValidateMatrix(int[][] TakuzuGrid){
        if(!ValidateShape(TakuzuGrid)) return false;
        int[][] shiftedTakuzu = ShiftArray(TakuzuGrid);

        if(!CheckProgress(TakuzuGrid)){
            SolverDialog(TakuzuGrid);
            return false;
        }

        if(ValidateRowsOrCols(TakuzuGrid, "row")) return false;
        if(ValidateRowsOrCols(shiftedTakuzu, "column")) return false;
        if(ValidateMaxInRowOrCol(TakuzuGrid, "row")) return false;
        if(ValidateMaxInRowOrCol(shiftedTakuzu, "column")) return false;
        if(ValidateSameRowOrColumn(TakuzuGrid, "row")) return false;

        return !ValidateSameRowOrColumn(shiftedTakuzu, "column");
    }


    /**
     * Utility method for debugging and printing
     * @param x a string
     */
    static void print(String x){
        System.out.println(x);
    }

    /**
     * For debugging and printing purposes
     * @param TakuzuGrid A matrix representing the takuzu grid
     */
    private static void DebugGrid(int[][] TakuzuGrid){
        System.out.println("{");
        for (int[] ints : TakuzuGrid) {
            System.out.print(" { ");
            for (int j = 0; j < TakuzuGrid[0].length; j++) {
                if (j == TakuzuGrid[0].length - 1) System.out.print(ints[j] + " },\n");
                else System.out.print(ints[j] + ", ");
            }
        }
        System.out.println("}");
    }//ok

}