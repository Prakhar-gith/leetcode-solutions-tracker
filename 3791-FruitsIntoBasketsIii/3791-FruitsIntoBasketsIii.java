// Last updated: 8/30/2025, 6:56:00 PM
class Solution {
    private int[] tree;
    private int n;
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        this.n = baskets.length;
        this.tree = new int[4 * n];
        
        build(1, 0, n - 1, baskets);
        
        int unplacedCount = 0;
        for (int fruit : fruits) {
            int basketIndex = findLeftmostFit(1, 0, n - 1, fruit);
            
            if (basketIndex == -1) {
                unplacedCount++;
            } else {
                update(1, 0, n - 1, basketIndex, -1);
            }
        }
        
        return unplacedCount;
    }

    private void build(int node, int start, int end, int[] baskets) {
        if (start == end) {
            tree[node] = baskets[start];
        } else {
            int mid = start + (end - start) / 2;
            build(2 * node, start, mid, baskets);
            build(2 * node + 1, mid + 1, end, baskets);
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
        } else {
            int mid = start + (end - start) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }
    }

    private int findLeftmostFit(int node, int start, int end, int requiredCapacity) {
        if (tree[node] < requiredCapacity) {
            return -1;
        }
        
        if (start == end) {
            return start;
        }
        
        int mid = start + (end - start) / 2;
        
        if (tree[2 * node] >= requiredCapacity) {
            return findLeftmostFit(2 * node, start, mid, requiredCapacity);
        } else {
            return findLeftmostFit(2 * node + 1, mid + 1, end, requiredCapacity);
        }
    }
}