import java.util.*;

class Solution {
    static int result;
    
    public int solution(int[] numbers, int target) {
        result = 0;
        int[] op = new int[numbers.length];
        
        go(op, 0, numbers, target);
        
        return result;
    }
    
    static public void go(int[] op, int idx, int[] numbers, int target){
        if(idx>=op.length){
            if(calculate(op, numbers)==target){
                result++;
            }
            return;
        }
        // op에 0이 들어있으면 +, 1이 들어있으면 -
        op[idx]=0;
        go(op, idx+1, numbers, target);
        op[idx]=1;
        go(op, idx+1, numbers, target);
    }
    
    static public int calculate(int[] op, int[] numbers){
        int total = numbers[0];
        if(op[0]==1){
            total*=-1;
        }
        for(int i=1;i<op.length;i++){
            if(op[i]==0){
                total+=numbers[i];
            }else if(op[i]==1){
                total-=numbers[i];
            }
        }
        return total;
    }
    
}
