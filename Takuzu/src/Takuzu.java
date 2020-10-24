public class Takuzu {


    public static void main(String[] args) {
        //set the sign for empty through the public static field EMPTY of TakuzuLogic
        TakuzuLogic.EMPTY = 2;
        //here you can instantiate your grids and pass them to the ValidateMatrix method to trigger all checks

        if(TakuzuLogic.ValidateMatrix(Temp.NewSixBySixEmptyGrid())) TakuzuLogic.print("Congratulations!!! You solved the puzzle....");

    }
}
