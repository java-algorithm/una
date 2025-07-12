import java.util.*;

class SmallestInfiniteSet {
    int current;
    PriorityQueue<Integer> pq;
    Set<Integer> set;

    public SmallestInfiniteSet() {
        current = 1;
        pq = new PriorityQueue<>();
        set = new HashSet<>();
    }

    public int popSmallest() {
        if (!pq.isEmpty()) {
            int smallest = pq.poll();
            set.remove(smallest);
            return smallest;
        } return current++;
    }

    public void addBack(int num) {
        if (num < current && set.add(num)) {
            pq.offer(num);
        }
    }
}