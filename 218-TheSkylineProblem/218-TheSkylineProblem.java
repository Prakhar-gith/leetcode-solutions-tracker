// Last updated: 12/24/2025, 4:23:58 PM
1class Solution {
2    public List<List<Integer>> getSkyline(int[][] buildings) {
3        List<int[]> h= new ArrayList<>();
4        for(int[] b :buildings){
5            h.add(new int[]{b[0], -b[2]});
6            h.add(new int[]{b[1], b[2]});
7        }
8        Collections.sort(h, (a, b)-> {
9            if(a[0]!= b[0]) return a[0]- b[0];
10            return a[1]- b[1];
11        });
12        TreeMap<Integer, Integer> m= new TreeMap<>();
13        m.put(0, 1);
14        int prev= 0;
15        List<List<Integer>> res= new ArrayList<>();
16        for(int[] x : h){
17            if(x[1]< 0) m.put(-x[1], m.getOrDefault(-x[1], 0)+ 1);
18            else{
19                int c= m.get(x[1]);
20                if(c== 1) m.remove(x[1]);
21                else m.put(x[1], c- 1);
22            }
23            int cur= m.lastKey();
24            if(cur!= prev){
25                res.add(Arrays.asList(x[0], cur));
26                prev= cur;
27            }
28        }
29        return res;
30    }
31}