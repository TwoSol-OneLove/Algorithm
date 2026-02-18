import java.util.*;
import java.io.*;

public class B3190_김아윤 {
	static int N;
	static int K;
	static int L;
	static int[][] matrix;	// 0 : 빈칸, 1 : 사과 , 2 : 뱀 
	static Queue<ChangeInfo> info;	// 방향 변환 정보를 저장할 Queue 
	static Deque<Node> snake;	// 뱀의 위치 정보들을 저장할 Deque 
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class ChangeInfo{
		int second;
		int dir;
		
		ChangeInfo(int second, int dir){
			this.second = second;
			this.dir = dir;
		}
	}
	
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
		// 걸린 시간을 저장할 변수 
		int time = 0;
		
		// 현재 뱀의 정보 (위치, 방향) 
		int x = 0;
		int y = 0;
		int dir = 1;
		
		snake.add(new Node(x, y));
		matrix[x][y]=2;
		
		while(true) {
			// 방향 전환 
			if(!info.isEmpty() && time == info.peek().second) {
				dir = (dir + info.poll().dir) % 4;
			}
			
			// 다음으로 이동할 칸 찾기 
			int nx = x+dx[dir];
			int ny = y+dy[dir];
			
			// 다음으로 이동할 칸이 벽이나 자기자신의 몸인 경우  
			if(!isValid(nx, ny) || matrix[nx][ny]==2) {
				time++;
				break;
			}
			
			
			if(matrix[nx][ny]==1) {	// 다음으로 이동할 칸이 사과인 경우 
				// 머리 늘리기 
				matrix[nx][ny]=2;
				snake.addLast(new Node(nx, ny));
			} else {				// 다음으로 이동할 칸이 빈칸인 경우 
				// 머리 늘리기 
				matrix[nx][ny]=2;
				snake.addLast(new Node(nx, ny));
				
				// 몸길이 줄여서 꼬리가 위치한 칸 비워주기 
				Node tail = snake.pollFirst();
				matrix[tail.x][tail.y] = 0;
			}
			
			// 위치 이동 
			x=nx;
			y=ny;
			
			// 시간 1 증가 
			time++;
		}
		System.out.println(time);
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
		matrix = new int[N][N];
		
		st = new StringTokenizer(br.readLine(), " ");
		K = Integer.parseInt(st.nextToken());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			matrix[x][y]=1;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		info = new LinkedList<ChangeInfo>();
		for(int i=0;i<L;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int second = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			if(dir=='L') {
				info.add(new ChangeInfo(second, 3));
			} else if(dir=='D') {
				info.add(new ChangeInfo(second, 1));
			}
		}
		snake = new LinkedList<>();
	}
}
