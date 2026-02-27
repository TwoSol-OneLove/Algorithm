import java.util.*;
import java.io.*;

public class B1107_김아윤 {
	static int N;
	static int M;
	static boolean[] isBroken;
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		if(N==100) {
			System.out.println(0);
			return;
		}
		
		// 1. +, - 버튼으로만 조작한 경우 
		int answer = Math.abs(N - 100);
		
		// 2. 모든 채널 탐색 
		// - 999900까지 도는 이유
		// : 초기 100번에서 M(500,000)까지 +,- 버튼으로만 이동 : 최대 499,900 번
		// : 그렇다면 M(500,000) + 499,900를 초과하는 채널의 경우는 위에서 구한 499,900보다 더 작은 횟수가 나올 수 없다. 
		for(int i=0;i<=999900;i++) {
			if(isValid(i)) {	// 숫자 버튼만으로 만들 수 있는 채널 번호인지 확인하기 
				int length = String.valueOf(i).length();	// 숫자 버튼 누른 횟수 
				int cnt = Math.abs(N-i)+length;	// +, - 까지 합친 총 버튼 누른 횟수
				
				answer = Math.min(answer, cnt);	// 버튼 누른 횟수 최솟값으로 update 
			}
		}
		System.out.println(answer);
	}
	
	// 숫자 버튼만으로 만들 수 있는 채널번호 인지 확인하는 메소드 
	static boolean isValid(int num) {
		// num이 0인경우, while문 돌지 않음 
		if(num==0) {
			return !isBroken[0];
		}
		
		// while문을 돌면서 1의 자리수부터 확인하기 
		while(num!=0) {
			if(isBroken[num%10]) {	
				return false;
			}
			num/=10;
		}
		return true;
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		
		isBroken = new boolean[10];
		
		if(M>0) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0;i<M;i++) {
				int num = Integer.parseInt(st.nextToken());
				isBroken[num]=true;
			}
		}
	}
}
