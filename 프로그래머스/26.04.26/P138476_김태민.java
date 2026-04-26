import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int t : tangerine) {
            countMap.put(t, countMap.getOrDefault(t, 0) + 1);
        }

        int[] bucket = new int[tangerine.length + 1];
        for (int count : countMap.values()) {
            bucket[count]++;
        }

        int answer = 0;
        int remain = k;

        for (int i = tangerine.length; i >= 1; i--) {
            while (bucket[i] > 0) {
                remain -= i;
                answer++;
                bucket[i]--;

                if (remain <= 0) {
                    return answer;
                }
            }
        }

        return answer;
    }
}