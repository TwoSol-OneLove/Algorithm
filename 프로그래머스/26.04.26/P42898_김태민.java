/**
조건
 - 오른쪽과 아래쪽만 움직여서 집에서 학교까지 갈 수 있는 최단 경로의 갯수를
    1,000,000,007로 나눈 나머지를 return하기

설계
 - 일단 오른쪽이랑 아래쪽만 이동 가능하다면 가는 방향과 들어오는 방향은 정해져 있음
 - 그리고 각 칸당 가는 경우의 수는 2가지임
 - 그래서 분기 마다 2개로 쪼개짐
 - 그래서 결국은? 분기의 갯수를 세면 될려나?
 - (i,j)칸일 때의 경우의 수
 - 현재 칸으로 들어오는 경우의 수가 하나다? 그럼 전꺼랑 일치
 - (i,j) = (i-1,j)+(i,j-1) , 값이 그대로 오던가 두 개다 가능하면 +1
*/

import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[n][m];
        int length = puddles.length;
        int arrLen = puddles[0].length;
        int MOD = 1_000_000_007;

        for(int i = 0; i<length; i++){
            int x = puddles[i][0] - 1;
            int y = puddles[i][1] - 1;

            map[y][x] = -1;
        }
        for(int i = 0; i<n; i++){
            if(map[i][0] == -1) break;
            map[i][0] = 1;
        }
        for(int i = 0; i<m; i++){
            if(map[0][i] == -1) break;
            map[0][i] = 1;
        }
        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                if(map[i][j]==-1) continue;
                if(map[i-1][j]>=1 &&map[i][j-1]>=1){
                    map[i][j] = (map[i - 1][j] + map[i][j - 1]) % MOD;
                } else if(map[i-1][j]>=1){
                    map[i][j] = map[i-1][j];
                } else if(map[i][j-1]>=1){
                    map[i][j] = map[i][j-1];
                }
            }
        }
        answer = map[n-1][m-1];
        
        
        return answer;
    }
}

/**
import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        boolean[][] blocked = new boolean[n + 1][m + 1];

        // 물웅덩이 표시
        // puddle[0] = x 좌표, puddle[1] = y 좌표
        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            blocked[y][x] = true;
        }

        // 시작점
        dp[1][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {

                // 시작점은 이미 1로 세팅했으므로 넘어감
                if (y == 1 && x == 1) {
                    continue;
                }

                // 물웅덩이는 갈 수 없는 칸
                if (blocked[y][x]) {
                    dp[y][x] = 0;
                    continue;
                }

                dp[y][x] = (dp[y - 1][x] + dp[y][x - 1]) % MOD;
            }
        }

        return dp[n][m];
    }
}
**/