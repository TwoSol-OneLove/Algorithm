package daily2601;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조건
 *  - 각 도시벌 금액과 늘어나는 고객의 수가 제공됨. 이 수의 정수배 만큼 늘어날 수 있게 할 수 있음
 *  - 호텔의 고객을 적어도 C명 늘이기 위해 투자해야하는 돈의 최솟값 구하기
 *  - C: 적어도 1<=C<=1000명 늘려야함, 도시의 갯수 N: 1<=N<=20
 *  - 둘째 줄부터 N개의 줄에는 각 도시에서 드는 비용과, 고객의 수가 제공됨.
 *
 *
 *
 * 설계
 *  - 누가 봐도 DP
 *  - dp[i]: i명을 홍보 했을 떄 드는 비용
 *  - dp[i] = Math.min(dp[i], dp[i-people]+cost)
 */



public class B1106_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] dp = new int[c+101];
        for(int i = 0; i<c+101; i++){
            dp[i] = 1000000000;
        }
        dp[0] = 0;

        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for(int j = people; j<c+101; j++){
                dp[j] = Math.min(dp[j],dp[j-people]+cost);
            }
        }

        int result = 1000000000;
        for(int i = c; i<c+101; i++){
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);

    }
}















