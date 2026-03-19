import java.util.*;

/**
 - 결국 각 숫자를 더하거나 빼거나 둘중하나임
 - 그럼 백트래킹을 해야하나
 - 시간복잡도: 2^n인데 n이 최대 20개 --> 백만정도
*/

class Solution {
    static int[] number;
    static int n;
    static int result;
    static int targetNumber;
    public int solution(int[] numbers, int target) {
        n = numbers.length;
        int answer = 0;
        number = new int[n];
        number = numbers;
        targetNumber = target;
        
        back(0,0);
        answer = result;
        return answer;
    }
    
    static void back(int count, int sum){
        if(count == n){
            if(sum==targetNumber) result++;
            return;
        }
        back(count+1,sum+number[count]);
        back(count+1,sum-number[count]);
    }
}