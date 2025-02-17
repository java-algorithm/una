import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            int size = merged.size();
            if (merged.isEmpty() || merged.get(size - 1)[1] < interval[0]) merged.add(interval);
            else merged.get(size - 1)[1] = Math.max(merged.get(size - 1)[1], interval[1]);
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
