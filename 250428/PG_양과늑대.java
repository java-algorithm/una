import java.util.*;

class Solution {
    List<List<Integer>> list;
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        list = new ArrayList<>();
        
        for (int i = 0; i < info.length; i++) list.add(new ArrayList<>());
        
        for (int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        
        List<Integer> next = new ArrayList<>();
        next.add(0);
        
        dfs(0, 0, 0, info, next);
        
        return answer;
    }
    
    public void dfs(int current, int sheep, int wolf, int[] info, List<Integer> available) {
        if (info[current] == 0) sheep++;
        else wolf++;
        
        if (sheep <= wolf) return;
        
        answer = Math.max(answer, sheep);
        
        List<Integer> nextNodes = new ArrayList<>(available);
        nextNodes.remove(Integer.valueOf(current));
        nextNodes.addAll(list.get(current));
        
        for (int next : nextNodes) {
            dfs(next, sheep, wolf, info, nextNodes);
        }
    }
}
