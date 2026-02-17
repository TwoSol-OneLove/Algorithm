package daily2602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 조건
 *  - NxN 행렬, (0,0)부터 시작, 처음에는 오른 쪽을 향해 있음
 *  - 벽 or 자기 몸에 부딪히면 게임은 끝남
 *  - 먼저 꼬리는 두고 몸 길이를 늘려 다음칸에 머리를 둠
 *  - 이동한 칸에 사과가 있으면 그 칸의 사과가 사라지고 꼬리는 그대로
 *  - 이동한 칸에 사과가 없으면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워줌, 즉 몸길이는 변하지 않음
 *  - 사과의 위치와 뱀의 이동 경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산
 *
 *  - N: 보드의 크기 2<=N<=100, K: 사과의 갯수(0<=K<=100) -> k가 0이면 바로 끝남
 *  - k줄에 걸쳐서 사과의 위치가 주어짐
 *  - x: x초가 끝난 뒤 왼쪾(C가 L) 또는 오른쪽(C가 D)로 90도 방향을 회전 시칸다는 뜻임
 *  - 즉, x초 만큼은 바라 보는 방향으로 x칸 만큼 이동하는 거임
 *
 * 설계
 *  - dx,dy를 미리 만들어 놓고 오른쪽이면 +1, 왼쪽이면 -1 이런식으로 방향 설계 하면 될듯
 *  -
 *
 *
 */

public class B3190뱀 {

    static int n;
    static int[][] map;
    static int[] dx = {0,1,0,-1}; // 오, 하, 좌 ,상
    static int[] dy = {1,0,-1,0};
    static int k; // 사과 위치의 갯수
    static int l; // 방향 변화 횟수
    static Deque<Direction> deque;

    static class Snake{
        int x, y;
        public Snake(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    static class Direction{
        int length;
        String v;
        public Direction(int length, String v){
            this.length = length;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];


        // 값을 입력 받으면서 사과의 위치도 넣기
        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            map[x][y] = 1;

            // 진행하면서 사과를 먹었다면 먹을 때 마다 전체 사과의 갯수를 확인 하면 될듯
        }
        l = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();

        for(int i = 0; i<l; i++){
            st = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(st.nextToken());
            String v = st.nextToken();
            deque.add(new Direction(direction,v));
        }
        int result = solution();
        System.out.println(result);

    }

    // 사과를 먹을 떄 마다 카운트,
    static int solution(){
        int count = 0; // 사과 먹은 갯수를 카운트
        int times = 0; // 게임을 진행한 시간
        int nowV = 0; // 초기 방향은 오른쪽

        Deque<Snake> snake = new ArrayDeque<>();
        snake.addFirst(new Snake(0,0));
        int befortimes = 0;


        int x = 0;
        int y = 0;
        map[x][y] = -1;
        while (!deque.isEmpty()){
            Direction direction = deque.pollFirst();
            int length = direction.length;
            String v = direction.v;
            for(int i = 0; i<length-befortimes; i++){
                x = x+dx[nowV];
                y = y+dy[nowV];
                if(isRange(x,y)){
                    if(map[x][y]==1){
                        count++;
                        map[x][y] = -1;
                    } else {
//                        map[x][y] = -1;
                        Snake tail = snake.pollLast();
                        map[tail.x][tail.y] = 0;
                    }
                    map[x][y] = -1;
                    snake.addFirst(new Snake(x,y));


                }else {// 위 범위를 벗어나면 게임 오버
                    return times+1;
                }
                times++;
            }
            // 방향 전환해야함
            nowV = changeVector(v, nowV);
            befortimes = length;

        }

        // deque를 뺴고도 일단 계속 직진 해야함
        while (true){
            x = x + dx[nowV];
            y = y + dy[nowV];
            times++;

            // 벽 또는 몸 충돌이면 종료
            if(!(x>=0 && x<n && y>=0 && y<n) || map[x][y] == -1){
                return times;
            }

            // 머리 추가
            snake.addFirst(new Snake(x,y));

            if(map[x][y] == 1){
                // 사과: 꼬리 유지
                map[x][y] = -1;
            } else {
                // 빈칸: 꼬리 제거
                map[x][y] = -1;
                Snake tail = snake.pollLast();
                map[tail.x][tail.y] = 0;
            }
        }
    }


    // 방향 전환용 메서드
    static int changeVector(String v, int changedV){
        if(v.equals("D")){ // 오른 쪽으로 90도 이동 -> 인덱스 +1
            changedV +=1;
            if(changedV>3){
                changedV = 0;
            }
        } else {
            changedV -= 1;
            if(changedV<0){
                changedV = 3;
            }
        }
        return changedV;
    }

    static boolean isRange(int x, int y){
        // 몸통 값은 -1로 설정
        if(x>=0 && x<n&&y>=0&&y<n && map[x][y]!=-1){
            // x,y가 범위 안에 있고 -1이 아니면 가능
            return true;
        }
        return false;
    }
}






