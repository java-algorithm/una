import java.util.*;

class Solution {
    class Node {
        int x, y, v;
        
        Node left, right;
        
        Node (int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }
    
    List<Node> nodes = new ArrayList<>();
    int[][] answer;
    int count = 0;
    
    public int[][] solution(int[][] nodeinfo) {
        int size = nodeinfo.length;
        
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        
        Collections.sort(nodes, (o1, o2) -> {
            return Integer.compare(o2.y, o1.y);
        });
        
        Node root = nodes.get(0);
        
        for (int i = 1; i < size; i++) {
            find(root, nodes.get(i));
        }
        
        answer = new int[2][size];
        pre(root);
        count = 0;
        post(root);
        
        return answer;
    }
    
    public void find(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else find(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else find(parent.right, child);
        }
    }
    
    public void pre(Node root) {
        if (root != null) {
            answer[0][count++] = root.v;
            pre(root.left);
            pre(root.right);
        }
    }
    
    public void post(Node root) {
        if (root != null) {
            post(root.left);
            post(root.right);
            answer[1][count++] = root.v;
        }
    }
}
