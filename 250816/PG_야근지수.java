/**
 * 프로그래머스 야근 지수
 * - 우선순위 큐로 내림차순 정렬해서 n만큼 가장 앞의 값에서 -1 빼기
 */

import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        
        for (int work : works) queue.add(work);
        
        while (n-- > 0) {
            int cur = queue.poll();
            if (cur - 1 > 0) queue.add(cur - 1);
            else queue.add(0);
        }
        
        while (!queue.isEmpty()) {
            int num = queue.poll();
            answer += num * num;
        }
        
        return answer;
    }
}
