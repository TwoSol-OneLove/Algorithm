import java.util.*;

class Solution {
    
    class Node {
        int x, y, count;
        Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(0,0,1));
        visited[0][0] = true;
        
        while(!deque.isEmpty()){
            Node node = deque.pollFirst();
            if(node.x==n-1&&node.y==m-1){
                answer=node.count;
                break;
            }
            for(int i = 0; i<4; i++){
                int nx = dx[i]+node.x;
                int ny = dy[i]+node.y;
                if(0<=nx&&nx<n&&0<=ny&&ny<m&&maps[nx][ny]==1&&!visited[nx][ny]){
                    visited[nx][ny] = true;
                    deque.add(new Node(nx,ny,node.count+1));
                }
            }
        }
        if(answer==0){
            answer=-1;
        } 
        
        
        return answer;
    }
}