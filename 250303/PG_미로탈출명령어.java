import java.util.*;

class Solution {
    public class Node {
        int x, y, level;
        StringBuilder route;
        
        Node (int x, int y, int level, StringBuilder route) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.route = route;
        }
    }
    
    int[][] deltas = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    String[] movement = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (Math.abs(x - r) + Math.abs(y - c) > k) return "impossible";
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y, 0, new StringBuilder()));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int dx = cur.x + deltas[i][0];
                int dy = cur.y + deltas[i][1];
                
                if (dx <= 0 || dy <= 0 || dx > n || dy > m || Math.abs(dx - r) + Math.abs(dy - c) > k - cur.level) continue;
                
                StringBuilder route = new StringBuilder(cur.route.toString());
                route.append(movement[i]);
                
                if (dx == r && dy == c && cur.level + 1 == k) return route.toString();
                
                queue.add(new Node(dx, dy, cur.level + 1, route));
                break;
            }
        }
        
        return "impossible";
    }
}
