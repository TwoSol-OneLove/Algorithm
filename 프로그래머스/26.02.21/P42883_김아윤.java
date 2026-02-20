import java.util.*;
import java.io.*;

class Solution {
    Stack<Integer> st;
    
    public String solution(String number, int k) {
        // StringBuilder를 스택처럼 활용
        StringBuilder sb = new StringBuilder();
        
        // 앞으로 숫자를 제거할 수 있는 횟수 
        int removeCnt= k;
        
        // 숫자를 왼쪽부터 하나씩 탐색 
        for(int i=0;i<number.length();i++){
            
            // 현재 탐색 중인 숫자
            int next = number.charAt(i)-'0';
            
            // 1. 스택의 마지막 숫자(top)가 현재 숫자보다 작다면 -> 작은 숫자 poll & 현재 숫자 push
            // 즉, 앞자리의 작은 수를 제거하여 더 큰 수를 최대한 앞쪽에 배치한다.
            while(sb.length() > 0 && sb.charAt(sb.length()-1)-'0' < next && removeCnt > 0) {
                sb.deleteCharAt(sb.length()-1); // 앞의 작은 숫자 poll
                removeCnt--;    // 제거 가능 횟수 1 차감
            }
            sb.append(next);    // 현재 숫자 push
        }
        
        
        // 2. 제거 횟수가 남아있다면 계속 제거
        while(removeCnt>0) {
            sb.deleteCharAt(sb.length()-1);
            removeCnt--;
        }        
        return sb.toString();
    }
}
