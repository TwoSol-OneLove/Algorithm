import java.util.*;
import java.io.*;

public class B23349_김아윤 {
	static int N;
	static HashSet<String> students;	// 투표한 학생의 이름을 저장하는 HashSet 
	static HashMap<String, int[]> info;	// 장소(String)에서 촬영하기를 원하는 시간대(int[])를 저장하는 HashMap 
	static ArrayList<Node> result;		// 각 장소별 인기 있는 시간 대를 저장할 ArrayList 
	
	// 각 장소별 인기 있는 시간 대를 저장하기 위한 클래스
	static class Node implements Comparable<Node>{
		String place;	// 장소 이름 
		int start;		// 인기있는 시간대의 시작 시간 
		int end;		// 인기 있는 시간대의 끝 시간 
		int cnt;		// 원하는 학생 수 
		
		Node(String place, int start, int end, int cnt){
			this.place = place;
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node next) {
			// 정렬 기준
			// 1. 원하는 학생 수를 기준으로 내림차순 정렬 
			// 2. 장소 이름 사전 순을 기준으로 오름차순 정렬
			// 3. 시작 시간을 기준으로 오름차순 정렬
			// 4. 끝 시간을 기준으로 오름차순 정렬
			if(this.cnt == next.cnt) {
				if(this.place.compareTo(next.place) == 0) {
					if(this.start == next.start) {
						return Integer.compare(this.end, next.end);
					}
					return Integer.compare(this.start, next.start);
				}
				return this.place.compareTo(next.place);
			}
			return Integer.compare(this.cnt, next.cnt) * (-1);
		}

		@Override
		public String toString() {
			return place + " " + start + " " + end;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		
		for(String place : info.keySet()) {		// 장소 별로 for문을 돌면서 
			int maxCount = Integer.MIN_VALUE;
			Node resultNode = new Node(null, -1, -1, -1);
			
			// 인기 있는 시간대 찾기 
			int[] popular = info.get(place);
			boolean isFinish = true;
			for(int i=0;i<50001;i++) {
				// 동일한 장소일 때, 가장 빠른 시간대로 자동 걸러짐 
				if(maxCount<popular[i]) {
					resultNode = new Node(place, i, i+1, popular[i]);
					maxCount = popular[i];
					isFinish = false;
				} else if(maxCount==popular[i] && !isFinish) {
					resultNode.end = i+1;
				} else if(maxCount>popular[i]) {
					isFinish = true;
				}
			}
			
			// 장소 별로 가장 인기있는 시간대 Node를 result에 추가하기 
			result.add(resultNode);
		}
		
		// result에는 장소 별로 가장 인기 있는 시간대 하나 씩 들어가 있음 -> 정렬하기 
		Collections.sort(result);		
		System.out.println(result.get(0).toString());
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		students = new HashSet<String>();
		info = new HashMap<String, int[]>();
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			String place = st.nextToken();
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(students.contains(name)) {	// 이미 투표를 한 학생이라면 패스하기 
				continue;
			}else {
				// 투표한 학생 목록에 넣기
				students.add(name);	
				
				// 투표 적용하기
				int[] popular;
				if(info.containsKey(place)) {
					popular = info.get(place);
				} else {
					popular = new int[50001];
				}
				for(int j=start;j<end;j++) {
					popular[j]++;
				}
				info.put(place, popular);
			}
		}
		result = new ArrayList<>();
	}
}
