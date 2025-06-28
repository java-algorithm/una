import java.util.*;

class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        // 가져오는 값 맨 앞으로 꺼내기
        Node node = map.get(key);
        remove(node);
        insertFront(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        // key가 이미 있으면 update (삭제 후 앞에 넣기)
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            node.value = value;
            insertFront(node);
        } else {
            // 이미 꽉 차 있을 경우 최근에 가장 적게 사용한 Node 삭제
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            // 새 노드 넣기
            Node newNode = new Node(key, value);
            insertFront(newNode);
            map.put(key, newNode);
        }
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insertFront(Node node) {
        // head -> A -> tail인 경우
        // node.next에 head.next = A 넣기
        // head.next.prev = A를 node로 변경

        node.next = head.next;
        head.next.prev = node;

        // head.next = node로 변경
        // head -> node -> A가 됨
        head.next = node;
        node.prev = head;
    }
}
