package daily2603;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14890_김태민 {
    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    static int solution() {
        int result = 0;

        for (int i = 0; i < n; i++) {
            int[] line = new int[n];
            for (int j = 0; j < n; j++) {
                line[j] = map[i][j];
            }
            if (check(line)) result++;
        }

        for (int j = 0; j < n; j++) {
            int[] line = new int[n];
            for (int i = 0; i < n; i++) {
                line[i] = map[i][j];
            }
            if (check(line)) result++;
        }

        return result;
    }

    static boolean check(int[] line) {
        boolean[] used = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            if (line[i] == line[i + 1]) continue;

            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            // 오르막일 때
            if (line[i] + 1 == line[i + 1]) {
                for (int j = i; j > i - l; j--) {
                    if (j < 0 || line[j] != line[i] || used[j]) return false;
                    used[j] = true;
                }
            }
            // 내리막일 떄
            else if (line[i] - 1 == line[i + 1]) {
                for (int j = i + 1; j <= i + l; j++) {
                    if (j >= n || line[j] != line[i + 1] || used[j]) return false;
                    used[j] = true;
                }
            }
        }

        return true;
    }
}