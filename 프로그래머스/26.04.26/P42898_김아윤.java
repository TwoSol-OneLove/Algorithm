import java.util.*;

class Solution {
    static int[][] matrix;
    static int[][] dp;
    static int n;
    static int m;
    
    static int[] dx = {-1,0};
    static int[] dy = {0,-1};
    
    public int solution(int m, int n, int[][] puddles) {
        matrix = new int[n][m];
        this.n = n;
        this.m = m;
        
        for(int i=0;i<puddles.length;i++){
            int[] water = puddles[i];
            matrix[water[1]-1][water[0]-1] = 1;
        }
        
        dp = new int[n][m];
        dp[0][0] = 1;
        for(int x=0;x<n;x++){
            for(int y=0;y<m;y++){
                if(x==0 && y==0){   // 시작점은 continue 처리 
                    continue;
                }
                if(matrix[x][y]==1){    // 물웅덩이는 continue 처리
                    continue;
                }
                
                int sum = 0;
                for(int i=0;i<2;i++){   // 위, 왼쪽 좌표 확인
                    int nx = x+dx[i];
                    int ny = y+dy[i];
                    if(!isValid(nx, ny)){   // 위, 왼쪽 좌표가 invalid 한 경우 
                        continue;
                    }
                    if(matrix[nx][ny]==1){  // 위, 왼쪽 좌표가 물웅덩이 인 경우 
                        continue;
                    }
                    
                    // 위, 왼쪽 좌표가 정상적인 경우 
                    sum+=dp[nx][ny];    
                    sum%=1000000007;
                }
                dp[x][y] = sum;
            }
        }
        
        return dp[n-1][m-1];
    }
    
    static boolean isValid(int x, int y){
        if(0<=x && x<n && 0<=y && y<m){
            return true;
        }
        return false;
    }
}
