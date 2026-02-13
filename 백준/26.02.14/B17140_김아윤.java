import java.util.*;
import java.io.*;

public class B17140_김아윤 {
	static int R;
	static int C;
	static int K;
	
	static int n;
	static int m;
	static int[][] matrix;

	static int times;
	
	static class Node implements Comparable<Node>{
		int num;
		int cnt;
		
		Node(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node next) {
			// 정렬 조건 
			// 1. 숫자 개수 - 오름차순
			// 2. 숫자 수 - 오름차순
			if(this.cnt == next.cnt) {
				return Integer.compare(this.num, next.num);
			}
			return Integer.compare(this.cnt, next.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		while(matrix[R][C]!=K) {
			
			// 100초가 지난 경우 
			if(times>100) {
				times = -1;
				break;
			}
			
			// 새로운 배열을 저장할 newArr
			int[][] newArr = new int[101][101];
			
			// 한 번 실행하고 나서의 행/열의 길이를 저장할 변수 
			int maxLength = 0;
			
			if(n>=m) {
				for(int i=0;i<n;i++) {
					// 1. 한 행에 존재하는 수 카운트하기 
					HashMap<Integer, Node> cnt = new HashMap<>();
					for(int j=0;j<m;j++) {
						// 0은 무시 
						if(matrix[i][j]==0) {
							continue;
						}
						if(cnt.containsKey(matrix[i][j])) {
							Node node = cnt.get(matrix[i][j]);
							node.cnt = node.cnt+1;
							cnt.put(matrix[i][j], node);
						} else {
							cnt.put(matrix[i][j], new Node(matrix[i][j], 1));
						}
					}
					
					// 2. 정렬하기
					ArrayList<Node> nodes = new ArrayList<>();
					for(Integer key : cnt.keySet()) {
						Node node = cnt.get(key);
						nodes.add(node);
					}
					Collections.sort(nodes);

					// 3. 새로운 배열에 값 넣기
					maxLength = Math.max(maxLength, Math.min(100, nodes.size()*2));
					for(int k=0;k<nodes.size();k++) {
						if(2*k>=100) {
							break;
						}
						Node node = nodes.get(k);
						newArr[i][2*k] = node.num;
						newArr[i][2*k+1] = node.cnt;
					}
				}
				// 바뀐 배열의 행의 길이 update
				m = maxLength;
			}else if(n<m) {
				for(int i=0;i<m;i++) {
					// 1. 한 열에 존재하는 수 카운트하기 
					HashMap<Integer, Node> cnt = new HashMap<>();
					for(int j=0;j<n;j++) {
						// 0은 무시 
						if(matrix[j][i]==0) {
							continue;
						}
						if(cnt.containsKey(matrix[j][i])) {
							Node node = cnt.get(matrix[j][i]);
							node.cnt = node.cnt+1;
							cnt.put(matrix[j][i], node);
						} else {
							cnt.put(matrix[j][i], new Node(matrix[j][i], 1));
						}
					}
					
					// 2. 정렬하기
					ArrayList<Node> nodes = new ArrayList<>();
					for(Integer key : cnt.keySet()) {
						Node node = cnt.get(key);
						nodes.add(node);
					}
					Collections.sort(nodes);

					// 3. 새로운 배열에 값 넣기
					maxLength = Math.max(maxLength, Math.min(100, nodes.size()*2));
					for(int k=0;k<nodes.size();k++) {
						if(2*k>=100) {
							break;
						}
						Node node = nodes.get(k);
						newArr[2*k][i] = node.num;
						newArr[2*k+1][i] = node.cnt;
					}
				}
				// 바뀐 배열의 열의 길이 update
				n = maxLength;
			}
			
			matrix = newArr;
			
			// 시간 1 증가 
			times++;
		}
		System.out.println(times);
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken())-1;
		C = Integer.parseInt(st.nextToken())-1;
		K = Integer.parseInt(st.nextToken());
		
		n = 3;
		m = 3;
		matrix = new int[101][101];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<m;j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		times = 0;
	}
}
