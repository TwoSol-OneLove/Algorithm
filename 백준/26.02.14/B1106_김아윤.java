import java.util.*;
import java.io.*;

/*
 * 주의점) 정확히 C명일 때의 비용 X, C명 이상일 때의 비용 O
 * dp의 길이를 C가 아니라, (C+1)+100 으로 넉넉하게 생성  
 */

public class B1106_김아윤 {
	static int C;
	static int N;
	static Node[] info;
	
	// i명의 고객을 확보하는데 필요한 최소 비용 
	static int[] dp;
	
	static class Node{
		int cost;
		int cnt;
		
		Node(int cost, int cnt){
			this.cost = cost;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		// 1. 값 초기화 (각 광고를 한 번 사용했을 때의 기본 상태 저장)
		// - 고객 0명을 만드는 비용은 0 
		dp[0] = 0;
		for(int i=0;i<N;i++) {
			Node node = info[i];
			dp[node.cnt]=Math.min(dp[node.cnt], node.cost);
		}
		
		// 2. 이미 만든 고객 수에 다른 광고를 조합, 최소값으로 업데이트해 나감 
		for(int i=1;i<dp.length;i++) {
			for(int j=0;j<info.length;j++) {
				Node next = info[j];
				if(i+next.cnt >= dp.length) {
					continue;
				}
				dp[i+next.cnt]= Math.min(dp[i+next.cnt], dp[i]+next.cost); 
			}
		}
		
		// 3. C명 이상인 경우 중에서 최소 비용 찾기 
		int result = Integer.MAX_VALUE;
		for(int i=C;i<dp.length;i++) {
			result = Math.min(result, dp[i]);
		}
		System.out.println(result);
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		info = new Node[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cost = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			info[i] = new Node(cost, cnt);
		}
		
		dp = new int[C + 101];
		Arrays.fill(dp, 1000000);
	}

}
