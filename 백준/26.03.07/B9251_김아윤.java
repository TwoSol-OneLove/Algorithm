import java.util.*;
import java.io.*;

/*
 * dp[i][j] = com1의 i번째까지 com2의 j번째까지 두 문자열의 LCS 길이
 * dp[0][0] : "" VS ""
 * dp[1][1] : com1[0] VS com2[0]
 * dp[2][1] : com1[1] VS com2[0]
 */
public class B9251_김아윤 {
	static String com1;
	static String com2;
	
	static int[][] dp;
	
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution(){
		for(int i=1;i<=com1.length();i++) {
			for(int j=1;j<=com2.length();j++) {
				if(com1.charAt(i-1)==com2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		System.out.println(dp[com1.length()][com2.length()]);
	}
	
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		com1 = st.nextToken();
		
		st = new StringTokenizer(br.readLine(), " ");
		com2 = st.nextToken();
	
		dp = new int[com1.length()+1][com2.length()+1];
	}
}
