import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 최장 공통 부분 수열 문제
 * 조건
 *  - 제한시간이 0.4초이기 때문에 완탐은 안되고 DP 써야하는 문제인듯*
 *
 * - dp[i][j]: A의 앞에서 i개, B의 앞에서 j개를 봤을 때의 LCS 길이
 */

public class B9251_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int n = a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];

        for(int i =1; i<=n; i++){
            char ca = a.charAt(i-1);

            for(int j = 1; j<=m; j++){
                char cb = b.charAt(j-1);
                if(ca==cb){
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
