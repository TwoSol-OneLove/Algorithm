/**
조건
 - Node 클래스를 하나 만들어서 목적지와 가중치를 저장
 - 리스트의 인덱스가 각 번호이고 그 내부 리스트에 실제 값을 저장
 - k 이하의 시간에 배달할 수 있는 마을의 갯수를 구하기
 - 마을의 갯수 1<=N<=50
 - 도로 정보의 갯수 1<=road<=2000
 - 배달이 가능한 시간 1<=k<=500,000
설계


*/
import java.util.*;

class Solution {
    static class Node implements Comparable<Node> {
        int x, cost;
        Node(int x, int cost){
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static List<Node>[] list;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new ArrayList[N+1];
        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        for(int i = 0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        for(int [] r: road){
            int a = r[0];
            int b = r[1];
            int c = r[2];
            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(node.cost>dist[node.x]) continue;
            
            for(Node next:list[node.x]){
                if(node.cost+next.cost<dist[next.x]){
                    dist[next.x] = node.cost+next.cost;
                    pq.add(new Node(next.x, node.cost+next.cost));
                }
            }
        }

        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }
        
        return answer;    
    }
}