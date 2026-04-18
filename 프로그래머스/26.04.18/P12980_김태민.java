/**
K칸을 앞으로 점프하거나, (현재까지 온 거리) x 2에 해당하는 위치로 순간이동을 할 수 있음

*/

class Solution {
    public int solution(int n) {
        int ans = 0;
        
        while (n > 0) {
            if (n % 2 == 1) {  // 홀수면 1칸 점프가 필요함
                ans++;
                n--;
            } else {           // 짝수면 순간이동으로 온 것과 같음
                n /= 2;
            }
        }
        
        return ans;
    }
}