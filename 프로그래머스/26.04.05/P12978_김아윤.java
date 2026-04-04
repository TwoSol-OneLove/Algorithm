import java.util.*;

class Solution {
    static int N;
    static int [][] matrix;
    static int K;
    
    static int[] distance;
    
    static class Node implements Comparable<Node>{
        int num;
        int dist;
        
        Node(int num, int dist){
            this.num = num;
            this.dist = dist;
        }
        
        public int compareTo(Node next){
            return Integer.compare(this.dist, next.dist);
        }
    }
    
    public int solution(int N, int[][] road, int K) {
    
        this.N = N;
        this.K = K;
        matrix = new int[N+1][N+1];
        for(int i=0;i<N+1;i++){
            Arrays.fill(matrix[i], Integer.MAX_VALUE);
        }
        
        for(int i=0;i<road.length;i++){
            int node1 = road[i][0];
            int node2 = road[i][1];
            int dist = road[i][2];
            matrix[node1][node2] = Math.min(matrix[node1][node2], dist);
            matrix[node2][node1] = Math.min(matrix[node2][node1], dist);
        }
        
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        dijkstra();
        
        int result = 0;
        for(int i=1;i<=N;i++){
            if(distance[i]<=K){
                result++;
            }
        }
        return result;
    }
    
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        distance[1] = 0;
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int num = node.num;
            int dist = node.dist;
            
            for(int i=1;i<=N;i++){
                if(matrix[num][i]!=Integer.MAX_VALUE && distance[i]>dist+matrix[num][i]){
                    distance[i] = dist+matrix[num][i];
                    pq.add(new Node(i, distance[i]));
                }
            }
        }
        
    }
}
