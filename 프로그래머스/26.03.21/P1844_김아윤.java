import java.util.*;

class Solution {
    
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    
    static int n;
    static int m;
    
    static int[][] distance;

    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        distance = new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(distance[i], -1);
        }
        
        bfs(maps);
        
        return distance[n-1][m-1];
    }
    
    public void bfs(int[][] maps){
        Queue<Node> q =  new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new Node(0,0));
        distance[0][0]=1;
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(!isValid(nx, ny)){
                    continue;
                }
                if(visited[nx][ny]==false && maps[nx][ny]==1){
                    q.add(new Node(nx, ny));
                    visited[nx][ny]=true;
                    distance[nx][ny]=distance[x][y]+1;
                }
            }
        }
    }
    
    public boolean isValid(int x, int y){
        if(0<=x && x<n && 0<=y && y<m){
            return true;
        }
        return false;
    }
}
