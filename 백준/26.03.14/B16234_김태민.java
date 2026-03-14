package daily2603;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class B16234_김태민 {
    static int n, l, r;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;

        while (true) {
            visited = new boolean[n][n];
            boolean moved = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            moved = true;
                        }
                    }
                }
            }

            if (!moved) break;
            result++;
        }

        System.out.println(result);
    }

    static boolean bfs(int x, int y) {
        Deque<Node> queue = new ArrayDeque<>();
        ArrayList<Node> list = new ArrayList<>();

        queue.offer(new Node(x, y));
        list.add(new Node(x, y));
        visited[x][y] = true;

        int sum = map[x][y];

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny]) continue;

                int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);

                if (diff >= l && diff <= r) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny));
                    list.add(new Node(nx, ny));
                    sum += map[nx][ny];
                }
            }
        }

        if (list.size() == 1) return false;

        int avg = sum / list.size();
        for (Node node : list) {
            map[node.x][node.y] = avg;
        }

        return true;
    }
}