/**
 * 프로그래머스 합승 택시 요금
 * - 다익스트라!!!!
 */

import java.util.*;

class Solution {
    class Node implements Comparable<Node> {
        int node, value;
        
        Node (int node, int value) {
            this.node = node;
            this.value = value;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }
    
    List<Node> list[];
    int[] dist;
    int len;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        len = n;
        
        list = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < fares.length; i++) {
            list[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
            list[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
        
        int[] together = dijkstra(s);
        int[] aloneA = dijkstra(a);
        int[] aloneB= dijkstra(b);
        int cost = Integer.MAX_VALUE;
        
        for (int i = 1; i <= n; i++) {
            if (together[i] == Integer.MAX_VALUE || aloneA[i] == Integer.MAX_VALUE || aloneB[i] == Integer.MAX_VALUE) continue;
            
            cost = Math.min(cost, together[i] + aloneA[i] + aloneB[i]);
        }
        
        return cost;
    }
    
    public int[] dijkstra(int s) {
        dist = new int[len + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(s, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int node = cur.node;
            int value = cur.value;
            
            if (dist[node] < value) continue;
            
            for (int i = 0; i < list[node].size(); i++) {
                int nextN = list[node].get(i).node;
                int nextV = list[node].get(i).value;
                
                if (dist[nextN] > value + nextV) {
                    queue.add(new Node(nextN, value + nextV));
                    dist[nextN] = value + nextV;
                }
            }
        }
        
        return dist;
    }
}
