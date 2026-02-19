import java.util.*;
import java.io.*;

/*
 * PQ를 활용하여 현재 진행 중인 강의 중 가장 빨리 끝나는 것 찾기 
 * - 빨리 끝났으면 → 그 강의실 재사용 가능
 * - 아직 안 끝났으면 → 새 강의실 필요
 * 시간 복잡도 : O(n*logn)
 */
public class B11000_김아윤 {
	static int N;
	static ArrayList<Node> classes;
	
	static class Node implements Comparable<Node>{
		int start;
		int end;
		
		Node(int start, int end){
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Node next) {
			if(this.start==next.start) {
				return Integer.compare(this.end, next.end);
			}
			return Integer.compare(this.start, next.start);
		}
		
		@Override
		public String toString() {
			return "Node[start : "+start+", end : "+end+"]";
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		// 사용될 강의실 목록
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// 강의 정렬 (끝나는 시간 기준 오름차순) 
		Collections.sort(classes);
		
		// 첫 강의 종료 시간 추가 
		pq.add(classes.get(0).end);
		
		// 강의 순회 
		for(int i=1;i<classes.size();i++) {
			Node nextClass = classes.get(i);
			if(pq.peek()<=nextClass.start) {		// 가장 빨리 끝나는 강의 끝나는 시간 <= 현재 강의 시작 시간 (기존 강의실 사용 가능)  
				pq.poll();							// 해당 강의실 끝나는 시간 update를 위해 poll();
			}
			pq.add(nextClass.end);					// 해당 강의실 끝나는 시간 update or 새로운 강의실 추가
		}
		
		// 사용하고 있는 강의실 개수 출력
		System.out.println(pq.size());
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		classes = new ArrayList<>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			classes.add(new Node(start, end));
		}
	}
}
