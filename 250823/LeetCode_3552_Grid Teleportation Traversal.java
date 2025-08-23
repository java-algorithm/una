/**
 * 리트코드 3552번 Grid Teleportation Traversal
 * - BFS
 * A . .
 * A . .
 * . A .
 * 일 경우 한번 A 포탈을 사용했으므로 이후에는 사용 불가, 답은 1
 */

import java.util.*;

class Solution {
    public int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int minMoves(String[] matrix) {
        int n = matrix.length;
        int m = matrix[0].length();

        String[][] map = new String[n][m];
        Map<String, List<int[]>> ports = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = matrix[i].charAt(j);

                map[i][j] = String.valueOf(c);

                if (c >= 'A' && c <= 'Z') {
                    String alphabet = String.valueOf(c);
                    ports.putIfAbsent(alphabet, new ArrayList<>());
                    ports.get(alphabet).add(new int[]{i, j});
                }
            }
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        boolean[][] visited = new boolean[n][m];
        queue.add(new int[] {0, 0, 0});
        visited[0][0] = true;

        if (!map[0][0].equals(".")) {
            List<int []> alphabets = ports.get(map[0][0]);

            for (int[] alphabet : alphabets) {
                queue.add(new int[] {alphabet[0], alphabet[1], 0});
                visited[alphabet[0]][alphabet[1]] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int X = cur[0];
            int Y = cur[1];
            int D = cur[2];

            if (X == n - 1 && Y == m - 1) return D;

            for (int[] delta : deltas) {
                int dx = X + delta[0];
                int dy = Y + delta[1];

                if (dx >= n || dy >= m || dx < 0 || dy < 0 || visited[dx][dy] || map[dx][dy].equals("#")) continue;

                if (map[dx][dy].equals(".")) {
                    queue.add(new int[] {dx, dy, D + 1});
                    visited[dx][dy] = true;
                } else {
                    List<int[]> alphabets = ports.get(map[dx][dy]);

                    for (int[] alphabet : alphabets) {
                        queue.add(new int[] {alphabet[0], alphabet[1], D + 1});
                        visited[alphabet[0]][alphabet[1]] = true;
                    }
                }
            }
        }

        return -1;
    }
}
