import java.util.*;
import java.io.*;

/*
 * 1. 모든 칸 탐색하면서
 * 	a. 인접한 칸의 인구 차이가 L이상 R이하인(이동가능한) 좌표들 찾기 (연합)
 *  b. 인구 이동 진행 
 * 2. 만약 연합이 없었다면 종료, 있었다면 days++하고 다시 1번부터 진행 
 */
public class B16234_김아윤 {
	static int N;
	static int L;
	static int R;
	
	static int[][] matrix;	// 각 칸의 인구수 
	static boolean[][] visited;	// 현재 라운드에서 방문 여부 
	
	static int days;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		
		while(true) {
			visited = new boolean[N][N];
			boolean goWhile = false;	// 이번 라운드에 인구 이동 발생 여부 (while문 종료 조건) 
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					
					// 아직 어떤 연합에도 속하지 않은 칸만 BFS 시작점으로 사용 
					if(!visited[i][j]) {
						if(bfs(i, j)) {
							goWhile=true;	// 연합 발생 
						}
					}
				}
			}
			
			if(!goWhile) {	// 연합 없는 경우 
				break;
			}
			else {	// 연합 있는 경우 
				days++;
			}
		}
		System.out.println(days);
	}
	
	// bfs 탐지 조건 : 인구 차이가 L이상 R이하 
	static boolean bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		List<Node> union = new ArrayList<>();	// 연합인 노드를 저장할 리스트 
		
		q.add(new Node(x, y));
		visited[x][y]=true;
		
		union.add(new Node(x, y));
		int total = matrix[x][y];	// 연합 총 인구수 
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			x = node.x;
			y = node.y;
			for(int i=0;i<4;i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(!isValid(nx, ny)) {
					continue;
				}
				if(visited[nx][ny]) {
					continue;
				}
				
				int diff = Math.abs(matrix[x][y]-matrix[nx][ny]);
				if(L<= diff && diff<=R) {
					visited[nx][ny]=true;
					q.add(new Node(nx, ny));
					union.add(new Node(nx, ny));
					total+=matrix[nx][ny];
				}
			}
		}
		
		if(union.size()==1) {	// 연합이 자기자신뿐인 경우 
			return false;
		}
		
		// 연합 내 인구수 계산하여 수정 
		int population = total/union.size();
		
		for(int i=0;i<union.size();i++) {
			Node node = union.get(i);
			matrix[node.x][node.y] = population;
		}
		return true;
	}
	
	static boolean isValid(int x, int y) {
		if(0<=x && x<N && 0<=y && y<N) {
			return true;
		}
		return false;
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][N];
		days = 0;
	}
}
