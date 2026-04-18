/**
조건
 - 빈곳에 사람들을 넣어서 심사 받게 하는데 모든 사람들이 심사를 받는데 걸리는 시간을 최소화
 - n은 사람의 수고, times는 각 입국 심사대에서 걸리는 시간임
 
설계
 - n이 최대 10억
 - 걸리는 시간도 최대 10억

*/




class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = 0;
        
        for(int time: times){
            right = Math.max(right, time);
        }
        right *=n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            
            long count = 0;
            
            for (int time : times) {
                count += mid / time;
                
                if (count >= n) {
                    break;
                }
            }
            
            if (count >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}