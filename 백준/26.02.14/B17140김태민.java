package daily2602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 조건
 *  - 크기는 3X3인 배열, 인덱스는 1부터 시작, 1초마다 연산이 시작됨
 *  - R연산: 행의 갯수 >= 열의 갯수인 경우만 모든 행에 대해서 정렬을 함
 *  - C연산: 열의 갯수 > 행의 갯수인 경우만 모든 열에 대해서 정렬을 함
 *  - 1. 수의 등장 횟수가 커지는 순으로(오름 차순 정렬)
 *  - 2. 등장 횟수가 같다면 (오름차순 정렬)
 *  - r: 행, c: 열, k: 목표 값
 *  - 연산을 해서 (r,c) 위치 값이 k가 될때까지 걸리는 최소 시간
 *
 * 설계
 *  - 우선선위 큐 사용하면 될듯?
 *  - r,c의 크기가 같은 경우만 분기를 나누어서 처리하면 될듯?
 *  - 어짜피 조건에 따라만 연산 하면 되네
 *
 *  - 크기가 100인 배열을 만들어서 거기서 몇개씩 나오는지 보면 될듯
 *  - r 또는 c 연산 할때 마다 최대 길이를 구해놓고 비교하면서 하면 될듯
 *
 */


public class B17140김태민 {

    static class Node implements Comparable<Node>{

        int num;
        int display;

        public Node(int num, int display){
            this.num = num;
            this.display = display;
        }

        @Override
        public int compareTo(Node o) {
            if(this.display!=o.display){
                return Integer.compare(this.display, o.display);
            }

            return Integer.compare(this.num, o.num);
        }
    }


    static int r, c, k;
    static int[][] map = new int[100][100];
    static int rLength=3;
    static int cLength=3;




    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        int result = 0;

        // 초기 값 채우기
        for(int i = 0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        boolean isTrue = false;

        if(map[r][c] == k){
            System.out.println(0);
            return;
        }
        for(int i = 0; i<100; i++){
            int rSave = rLength;
            int cSave = cLength;

            // R연산
            if(rSave == cSave || rSave>cSave){
                for(int j = 0; j<rSave; j++){
                    isTrue |= RCalcul(cSave, j);
                }
            } else {  // C연산
                for(int j = 0; j<cSave; j++){

                    isTrue |= CCalcul(rSave, j);
                }
            }
            result++;

            if(isTrue){
                break;
            }
            if(i==99){
                result = -1;
            }
        }
        System.out.println(result);

    }


    // R연산 함수
    static boolean RCalcul(int cSave, int rIndex){
        int[] numSave = new int[101];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 각각의 수가 몇번 나왔는지 기록
        for(int i = 0; i<cSave; i++){
            if(map[rIndex][i]==0) continue;
            numSave[map[rIndex][i]]++;
        }

        // 정렬 시키기
        for(int i = 0; i<=100; i++){
            if(numSave[i]!=0){
                pq.add(new Node(i, numSave[i]));
            }
        }

        // 값을 다시 주입시키기
        for(int i = 0; i < 100; i++) map[rIndex][i] = 0;
        int count = 0;
        while(!pq.isEmpty() && count < 100){
            Node node = pq.poll();

            map[rIndex][count++] = node.num;
            if(count >= 100) break;
            map[rIndex][count++] = node.display;
        }
        cLength = Math.min(100, Math.max(cLength, count));
        return map[r][c] == k;


    }

    // L연산 함수
    static boolean CCalcul(int rSave, int cIndex){
        int[] numSave = new int[101];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i<rSave; i++){
            if(map[i][cIndex]==0) continue;
            numSave[map[i][cIndex]]++;
        }

        for(int i = 0; i<=100; i++){
            if(numSave[i]!=0){
                pq.add(new Node(i, numSave[i]));
            }
        }
        for(int i = 0; i < 100; i++) map[i][cIndex] = 0;

        int count = 0;
        while(!pq.isEmpty() && count < 100){
            Node node = pq.poll();

            map[count++][cIndex] = node.num;
            if(count >= 100) break;
            map[count++][cIndex] = node.display;
        }

        rLength = Math.min(100, Math.max(rLength, count));

        return map[r][c] == k;

    }

}

















