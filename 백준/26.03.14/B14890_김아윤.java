import java.util.*;
import java.io.*;

public class B14890_김아윤 {
	static int N;
	static int L;
	
	static int[][] matrix;
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		int ans = 0;

	    // 가로
	    for (int i = 0; i < N; i++) {
	        if (check(matrix[i])) ans++;
	    }

	    // 세로
	    for (int j = 0; j < N; j++) {
	        int[] col = new int[N];
	        for (int i = 0; i < N; i++) {
	            col[i] = matrix[i][j];
	        }
	        if (check(col)) ans++;
	    }

	    System.out.println(ans);
	}
	
	static boolean check(int[] arr) {
		boolean[] ladder = new boolean[N];	// 사다리 설치 여부
		int count = 1; // 현재 높이의 연속 칸 수

		for(int i=1;i<N;i++) {
			// 동일한 경우 
			if(arr[i-1]==arr[i]) {
				count++;
			}
			
			// 올라가는 경우 
			else if(arr[i-1]==arr[i]-1) {
				if(count>=L) {	// 사다리를 설치할 공간이 있는 경우 
					// 이전 L개의 길에 사다리 설치 여부 확인하기 
					for(int j=i-L;j<i;j++) {
						if(ladder[j]) { 
							return false;
						}else {
							ladder[j]=true; 
						}
					}
				} else {	// 사다리를 설치할 공간이 없는 경우 
					return false;
				}
				count=1;
			}
			
			// 내려가는 경우
			else if(arr[i-1]==arr[i]+1) {
				// 앞으로 L칸이 있는지 확인
				if(i+L>N) {
					return false;
				}
				// 앞으로 L칸의 높이 차이, 사다리 설치 여부 확인 
				for(int j=i;j<i+L;j++) {
					if(arr[j]==arr[i] && !ladder[j]) {
						ladder[j]=true;
					}else {
						return false;
					}
				}
				count=1;
				i += L-1;
			}
			
			// 높이 2이상 차이나는 경우
			else {
				return false;
			}
		}
		return true;
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		matrix = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0;j<N;j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
