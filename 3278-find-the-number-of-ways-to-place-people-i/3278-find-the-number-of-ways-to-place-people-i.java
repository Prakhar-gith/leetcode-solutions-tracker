class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                int[] A = points[i];
                int[] B = points[j];

                if (A[0] <= B[0] && A[1] >= B[1]) {
                    boolean isRectangleEmpty = true;
                    
                    for (int k = 0; k < n; k++) {
                        if (k == i || k == j) {
                            continue;
                        }

                        int[] C = points[k];
                        
                        if (A[0] <= C[0] && C[0] <= B[0] && B[1] <= C[1] && C[1] <= A[1]) {
                            isRectangleEmpty = false;
                            break;
                        }
                    }

                    if (isRectangleEmpty) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}