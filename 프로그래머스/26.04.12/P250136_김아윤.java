import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[][] land;
    static int[][] island;       // 석유 덩어리 번호 저장할 배열
    static HashMap<Integer, Integer> area;  // (석유 덩어리 번호, 크기) 저장할 HashMap
    
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    
    static class Node{
        int x;
        int y ;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    
    public int solution(int[][] land) {
        
        this.n = land.length;
        this.m = land[0].length;
        this.land = new int[n][m];
        this.land = land;
        
        island = new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(island[i], -1);
        }
        
        area = new HashMap<Integer, Integer>();
        
        // bfs 돌리기
        // 1. 2차원 배열(island)에 석유 덩어리 번호 저장하기
        // 2. HashMap(area)에 (석유 덩어리 번호, 크기) 저장하기
        int cnt = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(land[i][j]==1 && island[i][j]==-1){
                    int totalCnt = bfs(i, j, cnt);
                    area.put(cnt, totalCnt);
                    cnt++;
                }
            }
        }
        
        // 각 열마다 뽑을 수 있는 석유 크기 계산하기
        int maxValue = 0;
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0;i<m;i++){
            // 한 열에서 뽑을 수 있는 석유 덩어리 번호 hs에 넣기
            for(int j=0;j<n;j++){
                if(island[j][i]!=-1){
                    hs.add(island[j][i]);
                }
            }   
            
            // hs에 넣은 석유 크기 계산하기
            int answer = 0;
            for(Integer entity : hs){
                answer+=area.get(entity);
            }
            
            // 최대값으로 갱신하기
            maxValue = Math.max(maxValue, answer);
            hs.clear();
        }
        return maxValue;
    }
    
    static public int bfs(int x, int y, int cnt){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        island[x][y]=cnt;
        int totalCnt = 1;
        
        while(!q.isEmpty()){
            Node node = q.poll();
            x = node.x;
            y = node.y;
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(!isValid(nx, ny)){
                    continue;
                }
                if(land[nx][ny]==1 && island[nx][ny]==-1){
                    q.add(new Node(nx, ny));
                    island[nx][ny] = cnt;
                    totalCnt++;
                }
            }
        }
        
        return totalCnt;
    }
    
    static boolean isValid(int x, int y){
        if(0<=x && x<n && 0<=y && y<m){
            return true;
        }
        return false;
    }
}
