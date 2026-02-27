import java.util.*;
import java.io.*;

public class B10986_김아윤 {
	static int N;
	static int M;
	static long[] nums;
	static long[] remainderCnt;	// % M 연산 결과의 개수를 저장할 배열 
	
	// - 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수 구하기 
	// (i ~ j 구간 합) % M == 0
	// (sums[j] - sums[i-1]) % M == 0
	// sums[j] % M - sums[i-1] % M == 0
	// sums[j] % M == sums[i-1] % M
	// -> 즉, "누접합의 나머지가 같은 두 쌍(i,j)"의 개수 구하면 된다. 
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
	
	static void solution() {
		long count = 0;
		
		// 1. (누적합 % M) 연산 결과 구하기
		long sum = 0;	// 누적합 
		for(int i=0;i<N;i++) {
			sum += nums[i];
			int remainder = (int)(sum%M);
			
			// 쌍을 구하지 않아도(혼자로도) % M 연산 결과가 0이다 -> 바로 count++
			if(remainder == 0) {
				count++;
			}
			remainderCnt[remainder]++;
		}
		
		// 2. 같은 나머지 중에서 2개씩 짝 만들기 (nC2)
		for(int i=0;i<remainderCnt.length;i++) {
			long result = remainderCnt[i] * (remainderCnt[i]-1) / 2;
			count += result;
		}
		
		System.out.println(count);
	}
	
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		remainderCnt = new long[M];
	}
}
