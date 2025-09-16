class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Long> res = new LinkedList<>();

        for (int num : nums) {
            long currentNum = num;

            while (!res.isEmpty()) {
                long prevNum = res.getLast();
                long commonDivisor = gcd(prevNum, currentNum);

                if (commonDivisor == 1) {
                    break;
                }

                res.removeLast();
                currentNum = (prevNum / commonDivisor) * currentNum;
            }
            res.add(currentNum);
        }

        List<Integer> finalResult = new ArrayList<>(res.size());
        for (long val : res) {
            finalResult.add((int) val);
        }

        return finalResult;
    }

    private long gcd(long a, long b) {
        while (b > 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}