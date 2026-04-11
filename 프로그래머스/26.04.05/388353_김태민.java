/**
조건
 - 행: n, 열: m
 - 지게차는 접근 가능한 컨테이너만 꺼냄: 4면 중 하나라도 외부랑 연결 되어야함
        -> 즉 4방 탐색을 할 때 하나라도 인덱스의 범위를 넘어서야 함
        -> 그래서 외부랑 연결된 모든것을 꺼냄
 - 크레인은 외부랑 연결 안된 것도 꺼낼 수 있음
 - 단일 문자열: 외부에서 접근 가능한 것 만 꺼냄, 지게차를 사용
 - 중복 문자열: 주어진 배열 내 모든 박스를 다 꺼냄, 크레인을 사용
 - 컨테이너 정보 2<=storage == n<= 50

설계
 - 지게차는 처음 전체에서 외부랑 연결된거를 다 꺼내기, 만약 꺼내면서 외부랑 연결된거는 안꺼냄
 - 크레인은 한번에 모든 박스를 다 꺼내기
 - 꺼냈으면 빈 문자열 ""로 만들기
 - 그래서 외부랑 연결된 박스는 사방 탐색이 빈문자열이거나 인덱스의 범위를 넘어 가면 됨.
*/

import java.util.*;
class Solution {
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static int n;
    static int m;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        map = new char[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        for(int i = 0; i < requests.length; i++){
            String c = requests[i];
            
            if(c.length() == 1){ // 지게차 사용
                Deque<Node> deque = new ArrayDeque<>();
                
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < m; k++){
                        if(c.charAt(0) == map[j][k]){
                            boolean is = bfs(j, k);
                            if(is){
                                deque.add(new Node(j, k));
                            }
                        }
                    }
                }
                
                while(!deque.isEmpty()){
                    Node node = deque.poll();
                    map[node.x][node.y] = ' ';
                }
                
            } else { // 크레인 사용
                char a = c.charAt(0);
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < m; k++){
                        if(a == map[j][k]){
                            map[j][k] = ' ';
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != ' ') answer++;
            }
        }
        
        return answer;
    }
    
    static boolean bfs(int x, int y){
        boolean[][] visit = new boolean[n + 2][m + 2];
        char[][] temp = new char[n + 2][m + 2];
        
        for(int i = 0; i < n + 2; i++){
            Arrays.fill(temp[i], ' ');
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                temp[i + 1][j + 1] = map[i][j];
            }
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));
        visit[0][0] = true;
        
        int tx = x + 1;
        int ty = y + 1;
        
        while(!queue.isEmpty()){
            Node now = queue.poll();
            
            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) continue;
                if(visit[nx][ny]) continue;
                
                if(nx == tx && ny == ty) return true;
                
                if(temp[nx][ny] == ' '){
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }
        
        return false;
    }
}