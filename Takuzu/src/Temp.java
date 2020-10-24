class Temp {

    /**
     *
     * @return returns a new solvable takuzu grid
     */
    static int[][] NewSolvableGrid(){
        return new int[][]{
                {0, 2, 1, 2, 2, 0, 2, 1},
                {2, 2, 0, 2, 1, 1, 2, 2},
                {1, 2, 2, 0, 2, 2, 1, 2},
                {2, 1, 2, 2, 0, 2, 2, 2},
                {2, 0, 2, 2, 2, 2, 0, 2},
                {1, 2, 0, 2, 2, 2, 1, 2},
                {2, 2, 2, 1, 2, 2, 2, 1},
                {2, 1, 2, 2, 1, 2, 2, 0}
        };
    }


    static int[][] NewSixBySixEmptyGrid(){
        return new int[][]{
                {2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2},
        };
    }

    static int[][] NewFourByFourGrid(){
        return new int[][]{
                {1, 0, 2, 2},
                {2, 2, 1, 2},
                {0, 2, 2, 2},
                {2, 2, 2, 2}
        };
    }
    static int[][] NewFourByFourEmptyGrid(){
        return new int[][]{
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2}
        };
    }



    /**
     *
     * @return returns a new solvable takuzu grid
     */
    static int[][] NewSolvedGrid(){
        return new int[][]{
                {0, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1, 1, 0, 0}
        };
    }

    /**
     *
     * @return returns a new takuzu grid with an error in the columns
     */
    static int[][] NewFalseColGrid(){
        return new int[][]{
                {0, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1, 1, 0, 0}
        };
    }

    /**
     *
     * @return returns a new takuzu grid with an error in the rows
     */
    static int[][] NewFalseRowGrid(){
        return new int[][]{
                {0, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1, 1, 0, 0}
        };
    }

    /**
     *
     * @return returns a new grid with two same rows
     */
    static int[][] NewSameRowGrid(){
        return new int[][]{
                {0, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    /**
     *
     * @return a new takuzu grid with two same columns
     */
    static int[][] NewSameColumnGrid(){
        return new int[][] {
                {0, 0, 1,  0, 0,  0, 1, 1},
                {1, 1, 0,  0, 0,  1, 0, 0},
                {1, 0, 1,  1, 1,  0, 1, 0},
                {0, 1, 0,  1, 1,  0, 0, 1},
                {0, 1, 1,  0, 0,  1, 0, 0},
                {0, 0, 1,  1, 1,  1, 1, 1},
                {0, 0, 1,  1, 1,  0, 1, 1},
                {0, 0, 1,  0, 0,  0, 1, 1},
        };
    }

    /**
     *
     * @return a new takuzu grid with an error in row length
     */
    static int[][] NewFalseRowLenGrid(){
        return new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
    }


    /**
     *
     * @return a new takuzu grid with an error in column length
     */
    static int[][] NewFalseColLenGrid(){
        return new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };
    }


    /**
     *
     * @return a new takuzu grid with an error in the columns
     */
    static int[][] NewGridNoQuads(){
        return new int[][]{
                {0, 0, 1, 1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0, 1, 1},
                {0, 1, 1, 0, 1, 1, 0, 0}
        };
    }

    static int[][] GridWithManySolutions(){
       return new int[][] {
            {2, 1, 0, 2, 2, 2},
            {1, 2, 2, 2, 0, 2},
            {2, 2, 0, 2, 2, 2},
            {1, 1, 2, 2, 1, 0},
            {2, 2, 2, 2, 0, 2},
            {2, 2, 1, 2, 2, 2}
        };
    }

}
