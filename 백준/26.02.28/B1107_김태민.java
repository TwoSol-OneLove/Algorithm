package daily2602;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조건
 *  - 이동하려는 채널: 0<=N<=500,000
 *  - 고장난 버튼의 개수: 0<=M<=10
 *  - 처음 시작은 100에서 시작
 *
 *
 * 설계
 *  - 고장 안난 버튼 중에서 최대한 N과 가까운 숫자를 처음에 만들어야 함
 *  - dp 문제인가
 *  - +,-로 접근할 때 최소일 수도 있고 직접 밥로 갔을 때 최소일 수도 있음
 *  - ex) 99 같은 경우는 99를 누르면 2번이지만 -를 누르면 1번임
 *
 *  - 백트래킹 같기도
 *  - 남은 숫자 조합에서 주어진 N에 가장 가까운수를 찾기
 *
 *  - +로 접근하다가 그자리 숫자를 한방에 누를수 있으면 누르고 그 횟수를 비교하기
 *  -
 *
 *
 */

public class B1107_김태민 {

    static String n;
    static int m;
    static int[] nums = new int[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = br.readLine();

        m = Integer.parseInt(br.readLine());

        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                nums[Integer.parseInt(st.nextToken())] = -1;
            }
        }



        System.out.println(solution());
    }

    static int solution() {
        int target = Integer.parseInt(n);

        // 1) +,-만으로 가는 경우
        int result = Math.abs(target - 100);

        // 2) 숫자버튼으로 i 만든 뒤 +,-로 target 맞추기
        for (int i = 0; i <= 1_000_000; i++) {
            String s = String.valueOf(i);

            boolean ok = true;
            for (int j = 0; j < s.length(); j++) {
                int d = s.charAt(j) - '0';
                if (nums[d] == -1) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                int press = s.length() + Math.abs(target - i);
                result = Math.min(result, press);
            }
        }

        return result;
    }
}







