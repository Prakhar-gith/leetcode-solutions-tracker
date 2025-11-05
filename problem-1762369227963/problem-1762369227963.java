// Last updated: 11/6/2025, 12:30:27 AM
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Comparator;

class Solution {

    private class Pair {
        long val;
        int freq;
        
        Pair(long val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long[] ans = new long[n - k + 1];
        
        Comparator<Pair> comp = (a, b) -> {
            if (a.freq != b.freq) {
                return Integer.compare(b.freq, a.freq);
            }
            return Long.compare(b.val, a.val);
        };
        
        Map<Long, Integer> freqMap = new HashMap<>();
        Map<Long, Pair> pairMap = new HashMap<>();
        TreeSet<Pair> topX = new TreeSet<>(comp);
        TreeSet<Pair> others = new TreeSet<>(comp);
        long xSum = 0L;
        long windowSum = 0L;

        for (int i = 0; i < k; i++) {
            long val = (long)nums[i];
            windowSum += val;
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        for (Map.Entry<Long, Integer> entry : freqMap.entrySet()) {
            Pair p = new Pair(entry.getKey(), entry.getValue());
            pairMap.put(entry.getKey(), p);
            others.add(p);
        }

        while (topX.size() < x && !others.isEmpty()) {
            Pair p = others.first();
            others.remove(p);
            topX.add(p);
            xSum += p.val * p.freq;
        }

        ans[0] = (freqMap.size() <= x) ? windowSum : xSum;

        for (int i = k; i < n; i++) {
            long outNum = (long)nums[i - k];
            long inNum = (long)nums[i];
            
            windowSum = windowSum - outNum + inNum;

            int outOldFreq = freqMap.get(outNum);
            Pair outOldPair = pairMap.get(outNum);
            if (topX.remove(outOldPair)) { 
                xSum -= outOldPair.val * outOldPair.freq; 
            } else { 
                others.remove(outOldPair); 
            }
            
            int outNewFreq = outOldFreq - 1;
            if (outNewFreq > 0) {
                Pair outNewPair = new Pair(outNum, outNewFreq);
                pairMap.put(outNum, outNewPair);
                freqMap.put(outNum, outNewFreq);
                others.add(outNewPair);
            } else {
                freqMap.remove(outNum);
                pairMap.remove(outNum);
            }

            int inOldFreq = freqMap.getOrDefault(inNum, 0);
            if (inOldFreq > 0) {
                Pair inOldPair = pairMap.get(inNum);
                if (topX.remove(inOldPair)) { 
                    xSum -= inOldPair.val * inOldPair.freq; 
                } else { 
                    others.remove(inOldPair); 
                }
            }
            
            int inNewFreq = inOldFreq + 1;
            Pair inNewPair = new Pair(inNum, inNewFreq);
            pairMap.put(inNum, inNewPair);
            freqMap.put(inNum, inNewFreq);
            others.add(inNewPair);

            while (topX.size() < x && !others.isEmpty()) {
                Pair p = others.first();
                others.remove(p);
                topX.add(p);
                xSum += p.val * p.freq;
            }
            
            while (!topX.isEmpty() && !others.isEmpty() && comp.compare(others.first(), topX.last()) < 0) {
                Pair bestOfOthers = others.first();
                Pair worstOfTopX = topX.last();
                
                topX.remove(worstOfTopX);
                others.remove(bestOfOthers);
                
                topX.add(bestOfOthers);
                others.add(worstOfTopX);
                
                xSum = xSum - (worstOfTopX.val * worstOfTopX.freq);
                xSum = xSum + (bestOfOthers.val * bestOfOthers.freq);
            }
            
            ans[i - k + 1] = (freqMap.size() <= x) ? windowSum : xSum;
        }
        
        return ans;
    }
}