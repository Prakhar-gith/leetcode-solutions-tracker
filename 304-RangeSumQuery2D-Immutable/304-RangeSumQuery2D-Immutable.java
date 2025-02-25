// Algorithm:
// the idea is simple: to get the range sum of any given querry
// calc the prefix sum for each elem in the matrix
// to do this: since it is 2 d arr we do psum 2 times
// we first calc the psum row wise
// then we do the psum col wise
// then for any given query TL (x1,y1) and BR (x2,y2)
// we'll first take the psum from (0,0) to (x2,y2)
// then subtract the left behind portionin between, ie before TL or basically out of our query range => (0,0) to ( x1 - 1 , y2 ) and (0,0) to (x2, y1 - 1)
// but here we have an overlap of a specific region (x1 - 1, y1 - 1)
// which means it'll be subtracted 2 times, so simply we add it one more time to the equation
// the eqn will be => 
// *Psum([x2][y2]) - Psum([x1 - 1][y2]) - Psum([x2][y1 - 1]) + Psum[x1 - 1][y1 - 1]*

class NumMatrix { // so leetcode has give a predefined structuere for this problem in a class, its constructor to automatically generate the prefix sum array
    // sabse pehle to bas create kar lo prefix sum array, which will be basically a global array to be used in the upcoming functions
    int[][] psum; // created the psum array
    // now the cinstrctor of the class given:
    public NumMatrix(int[][] matrix) { // takes matrix as the parameter
        // first we'll get the matrix length n x m in rows (n) and columns (m) 
        int n = matrix.length; // rows length
        int m = matrix[0].length; // col length
        // now we will initialise the previously created global psum array here
        psum = new int[n][m]; 

        // then we iterate over no of rows
        // to take the psum ROW WISE:
        for(int i = 0; i < n; i++){
            //  now for each row we just need to add the element to its previous element 
            // in the same row, jo ki idhar aaega uske column se , 
            // so basically for each row we iterate its elements (ie respective columns):
            for(int j = 0; j < m; j++){
                // now first we check if the col no. is 0
                // If yes - we've nothin to add, the psum is the elem itself 
                if(j == 0){
                    psum[i][j] = matrix[i][j];
                }
                // otherwise for all the cols we add the psum(mat[i][j-1]) -> the psum of   prev elem 
                // to the curr elem ie mat[i][j]
                else{
                    psum[i][j] = psum[i][j-1] + matrix[i][j]; // psum(prev elem) + curr elem
                    // notice here we are adding  
                }
            }
        }
        // now we'll calc the PSUM COL WISE  
        // col wise:
        // so we'll first iterate over each col
        for(int j = 0; j < m; j++){
            //  now we'll itterate over the rows of each col
            for(int i = 1; i < n; i++){ // we skipped the 0th row as it is going to be the same only
                //  now, in the curr col elem add the element just above it 
                psum[i][j] = psum[i-1][j] + psum[i][j];
            }
        }
    }
    
    // now here is the query function - where we calc the sum of the given region/query
    // TL (x1,y1) and BR (x2,y2)
    public int sumRegion(int x1, int y1, int x2, int y2) {
        int sum = 0; 
        sum = sum + psum[x2][y2];
        if(x1-1 >=0){
            sum = sum - psum[x1-1][y2];
        }
        if(y1-1 >=0){
            sum = sum - psum[x2][y1-1];
        }
        if(x1-1 >=0 && y1-1 >= 0){
            sum = sum + psum[x1-1][y1-1];
        }
        return sum;

    }
}
// here is the visualisation
/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */


 /**
 * Matrix Visualization (6x6 Grid) for Range Sum Query:
 * 
 *  0 1 2 3 4 5
 * 0 . . . . . .
 * 1 . . . . . .
 * 2 . . * . . .  <- TL (2,2)
 * 3 . . . * . .  
 * 4 . . . . * .  <- BR (4,4)
 * 5 . . . . . .
 * 
 * Green Region (Query Area): From (2,2) to (4,4)
 * Yellow Regions (To Subtract): 
 *   - Left of TL: (0,0) to (1,4)
 *   - Above TL: (0,0) to (4,1)
 * 
 * Formula: Psum[4][4] - Psum[1][4] - Psum[4][1] + Psum[1][1]
 */
 /**
 * General Matrix Visualization (n x m Grid) for Range Sum Query:
 * 
 *   0 1 ... y1 ... y2
 * 0  .  .  ...  .  .
 * :  .  .  ...  .  .
 * x1 .  .  *   .  .  <- TL (x1,y1)
 * :  .  .  ...  .  .
 * x2 .  .  ...  *  .  <- BR (x2,y2)
 * 
 * Green Region (Query Area): From (x1,y1) to (x2,y2)
 * Yellow Regions (To Subtract): 
 *   - Left of TL: (0,0) to (x1-1,y2)
 *   - Above TL: (0,0) to (x2,y1-1)
 * 
 * Overlap Correction: Add back Psum[x1-1][y1-1] to fix double subtraction
 * 
 * Formula: Psum[x2][y2] - Psum[x1-1][y2] - Psum[x2][y1-1] + Psum[x1-1][y1-1]
 */
 /**
 * Prefix Sum Calculation Process:
 * 
 * Row-wise Psum (for each row i):
 *   - Psum[i][0] = matrix[i][0]
 *   - Psum[i][j] = Psum[i][j-1] + matrix[i][j] (for j > 0)
 * 
 * Column-wise Psum (for each column j):
 *   - Psum[i][j] = Psum[i-1][j] + Psum[i][j] (for i > 0)
 * 
 * ASCII Representation:
 *   Row 0: [a b c] -> [a a+b a+b+c]
 *   Row 1: [d e f] -> [d d+e d+e+f] -> After Col Psum: [d d+e d+e+f; d+a d+e+a+b d+e+f+a+b+c]
 */
 /**
 * Example Query Visualization (5x5 Grid):
 * 
 *  0 1 2 3 4
 * 0 . . . . .
 * 1 . . * . .  <- TL (2,1)
 * 2 . . . . .
 * 3 . . . . .
 * 4 . . . . *  <- BR (5,5)
 * 
 * Green Region (Query Area): From (2,1) to (5,5)
 * Yellow Regions (To Subtract): 
 *   - Left of TL: (0,0) to (1,5)
 *   - Above TL: (0,0) to (5,0)
 * 
 * Formula: Psum[5][5] - Psum[1][5] - Psum[5][0] + Psum[1][0]
 */
 /**
 * Original Matrix (6x4):
 * 
 *  0  1  2  3
 *  1  5  6  9
 *  6  4  8 11
 *  9  2  9  8
 *  4  2  7  6
 *  3  7  2  3
 */
 /**
 * Row-wise Prefix Sum (3x3 Example):
 * 
 *  a      a+b    a+b+c
 *  d      d+e    d+e+f
 *  g      g+h    g+h+i
 */
 /**
 * Final Prefix Sum Array (3x3 Example):
 * 
 *  a         a+b        a+b+c
 *  a+d       a+b+d+e    a+b+c+d+e+f
 *  a+d+g     a+b+d+e+g+h  a+b+c+d+e+f+g+h+i
 */
 /**
 * Query Region Example: Sum from TL (1,1) to BR (3,3)
 * 
 *  TL(1,1)--------
 *  |              |
 *  |              |
 *  --------BR(3,3)
 *  
 *  Formula: Psum[x2][y2] - Psum[x1-1][y2] - Psum[x2][y1-1] + Psum[x1-1][y1-1]
 */
 