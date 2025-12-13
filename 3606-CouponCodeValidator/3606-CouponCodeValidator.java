// Last updated: 12/13/2025, 7:05:03 PM
1class Solution {
2    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
3        List<Integer> indices = new ArrayList<>();
4        java.util.Map<String, Integer> priority = new java.util.HashMap<>();
5        priority.put("electronics", 0);
6        priority.put("grocery", 1);
7        priority.put("pharmacy", 2);
8        priority.put("restaurant", 3);
9        
10        for(int i= 0; i< code.length; i++) {
11            if(!isActive[i]) continue;
12            if(!priority.containsKey(businessLine[i])) continue;
13            if(code[i].isEmpty()) continue;
14            
15            boolean validCode = true;
16            for(char c : code[i].toCharArray()) {
17                if(!Character.isLetterOrDigit(c) && c != '_') {
18                    validCode = false;
19                    break;
20                }
21            }
22            
23            if(validCode) {
24                indices.add(i);
25            }
26        }
27        
28        Collections.sort(indices, (a, b) -> {
29            int p1 = priority.get(businessLine[a]);
30            int p2 = priority.get(businessLine[b]);
31            if(p1 != p2) return p1 - p2;
32            return code[a].compareTo(code[b]);
33        });
34        
35        List<String> result = new ArrayList<>();
36        for(int i : indices) {
37            result.add(code[i]);
38        }
39        return result;
40    }
41}