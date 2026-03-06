import java.util.*;
import java.io.*;

/*
 * 높이가 내려가는 순간, 그보다 높은 건물은 이어질 수 없다. 
 */
public class B1863_김아윤 {
	static int N;
	static Stack<Integer> heights;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		inputAndSolution();
	}
	
	static void inputAndSolution() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		cnt = 0;
		heights = new Stack<Integer>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 1. 높이가 작아지는 경우 -> 높은 건물들 종료 (pop, cnt++)
			// : 높이가 내려가는 순간, 현재 건물 높이보다 높은 건물들은 현재 건물과 합칠 수 없다. -> pop으로 꺼내면서 cnt++ 해주기 
			while(!heights.isEmpty() && heights.peek()>y) {
				heights.pop();
				cnt++;
			}
			
			if(y!=0) {	// 높이가 0이면 -> 건물 존재하지 않는 상태
				// 2. 높이가 커지는 경우 -> 새 건물 시작 (push) 
				// +) 높이가 같은 경우 -> 이어지는 중이니 아무것도 안 함
				if(heights.isEmpty() || heights.peek()!=y) {
					heights.add(y);
				}
			}
		}
		
		// 반복문 끝난 이후, 아직 스택에 남아있는 건물들을 꺼내면서 cnt++ 해주기  
		while(!heights.isEmpty()) {
			heights.pop();
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
