import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        // n -> 0으로 가는 상황 생각해보자
        while(n>0){
            if(n%2==0){ // 짝수인 경우, 무조건 순간이동해서 온 것이다.
                n/=2;       
            } else {    // 홀수인 경우, 순간이동해서 못 온다. 1 점프해서 온 것이다.
                n-=1;
                ans++;
            }
        }

        return ans;
    }
}
