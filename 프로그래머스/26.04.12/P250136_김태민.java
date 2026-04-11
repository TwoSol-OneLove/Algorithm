import java.util.*;

class Solution {
    
    static class Node {
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[] result;
    static int index = 2;
    static int length;
    static int answer;
    static int[][] land;
    
    public int solution(int[][] lands) {
        length = lands.length;
        result = new int[length * lands[0].length + 2];
        land = lands;
        answer = 0;
        
        for(int i = 0; i < lands[0].length; i++){
            sichu(i);
        }
        
        return answer;
    }
    
    static void sichu(int x){
    Set<Integer> visited = new HashSet<>();
    int count = 0;
    
    for(int i = 0; i < length; i++){
        if(land[i][x] == 0){
            continue;
        } else if(land[i][x] == 1){
            count += search(i, x);
            visited.add(land[i][x]);
        } else {
            if(visited.contains(land[i][x])){
                continue;
            } else {
                count += result[land[i][x]];
                visited.add(land[i][x]);
            }
        }
    }
    
    answer = Math.max(count, answer);
}
    
    static int search(int x, int y){
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(x, y));
        land[x][y] = index;
        int count = 1;
        
        while(!deque.isEmpty()){
            Node node = deque.poll();
            
            for(int i = 0; i < 4; i++){
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;
                
                if(nx >= 0 && nx < land.length && ny >= 0 && ny < land[0].length && land[nx][ny] == 1){
                    land[nx][ny] = index;
                    count++;
                    deque.add(new Node(nx, ny));
                }
            }
        }
        
        result[index++] = count;
        return count;
    }
}