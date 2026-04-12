import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int[2];
        
        // 최대 카페트 크기 : 2000 * 2000
        for(int i=3;i<2000;i++){
            for(int j=3;j<2000;j++){
                int brownCnt = j*2+(i-2)*2; // (맨 위 한줄 + 맨 아래 한줄) + (왼쪽 + 오른쪽)
                int yellowCnt = i*j-brownCnt;
                if(brownCnt==brown && yellowCnt==yellow){
                    answer[0] = j;
                    answer[1] = i;
                    return answer;
                }
            }
        }
        return answer;
    }
}
