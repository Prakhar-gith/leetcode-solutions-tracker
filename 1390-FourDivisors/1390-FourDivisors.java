// Last updated: 1/4/2026, 6:10:13 PM
1class Solution {
2    public int sumFourDivisors(int[] nums) {
3        int totalSum = 0;
4        for (int num : nums) {
5            int count = 0;
6            int sum = 0;
7            for (int i = 1; i * i <= num; i++) {
8                if (num % i == 0) {
9                    count++;
10                    sum += i;
11                    
12                    if (i * i != num) {
13                        count++;
14                        sum += num / i;
15                    }
16                }
17                if (count > 4) break;
18            }
19            
20            if (count == 4) {
21                totalSum += sum;
22            }
23        }
24        return totalSum;
25    }
26}