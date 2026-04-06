import java.util.*;

class Solution {
    static char[][] charStorage;
    
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    
    public int solution(String[] storage, String[] requests) {
        
        // 1차원 문자열 배열 -> 2차원 char 배열로 변환
        charStorage = new char[storage.length][storage[0].length()];
        for(int i=0;i<storage.length;i++){
            for(int j=0;j<storage[i].length();j++){
                charStorage[i][j] = storage[i].charAt(j);
            }
        }
        
        // 요청 순서대로 처리
        for(int i=0;i<requests.length;i++){
            String com = requests[i];
            if(com.length()==1){    // 길이가 1이면 크레인 제거
                removeCrane(com.charAt(0));    
            } else {    // 길이가 2 이상이면 해당 문자 전체 제거
                removeAll(com.charAt(0));
            }
        }
        
        // 남아있는 컨테이너 개수 계산
        int answer = 0;
        for(int i=0;i<charStorage.length;i++){
            for(int j=0;j<charStorage[i].length;j++){
                if(charStorage[i][j]!='0'){
                    answer++;
                }
            }
        }
        return answer;
    }
    
    // 크레인으로 특정 문자 제거 (외부 공기와 연결된 것만 제거)
    static void removeCrane(char ch){
        // 외부 공기 영역을 BFS로 탐색하여 표시
        boolean[][] outside = findOutside();
        
        // 중간 과정 결과를 저장할 2차원 char 배열 
        char[][] newStorage = new char[charStorage.length][charStorage[0].length];
        
        for(int i=0;i<charStorage.length;i++){
            for(int j=0;j<charStorage[i].length;j++){
                // 제거 대상이 아니면 그대로 유지
                if(charStorage[i][j]!=ch){  
                    newStorage[i][j] = charStorage[i][j];
                    continue;
                }
                
                boolean removable = false;
                for(int k=0;k<4;k++){
                    int nx = i+dx[k];
                    int ny = j+dy[k];
                    if(!isValid(nx, ny) || outside[nx][ny]){    // 범위를 벗어나거나, 외부 공기와 연결된 경우 제거 가능
                        removable = true;
                        break;
                    }
                }
                
                if(removable){
                    newStorage[i][j]='0';
                }else{
                    newStorage[i][j] = ch;
                }
            }
        }
        
        // 결과를 원본 배열에 반영
        for(int i=0;i<charStorage.length;i++){
            for(int j=0;j<charStorage[i].length;j++){
                charStorage[i][j] = newStorage[i][j];
            }
        }
    }
    
    // BFS로 외부 공기 영역을 찾기
    static boolean[][] findOutside(){
        boolean[][] outside = new boolean[charStorage.length][charStorage[0].length];
        Queue<int[]> q = new LinkedList<>();
        
        // 가장자리에서 시작 (외부 공기의 시작점)
        for(int i=0;i<charStorage.length;i++){
            for(int j=0;j<charStorage[0].length;j++){
                if(i==0 || j==0 || i==charStorage.length-1 || j==charStorage[0].length-1){
                    if(charStorage[i][j]=='0' && !outside[i][j]){
                        outside[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }
        
        // BFS 수행 → 외부와 연결된 모든 '0' 탐색
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = cur[0]+dx[i];
                int ny = cur[1]+dy[i];
                if(isValid(nx, ny) && !outside[nx][ny] && charStorage[nx][ny]=='0'){    // 범위 내 + 방문 안함 + 빈 공간이면 확장
                    outside[nx][ny]=true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        
        return outside;
    }
    
    // 특정 문자 전체 제거
    static void removeAll(char ch){
        for(int i=0;i<charStorage.length;i++){
            for(int j=0;j<charStorage[i].length;j++){
                if(charStorage[i][j]==ch){
                    charStorage[i][j]='0';
                }
            }
        }
    }
    
    static boolean isValid(int x, int y){
        if(0<=x && x<charStorage.length && 0<=y && y<charStorage[0].length){
            return true;
        }
        return false;
    }
}
