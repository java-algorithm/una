/**
 * 프로그래머스 셔틀버스
 * - 우선순위 큐 사용
 */

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (String time : timetable) {
            queue.add(changeMin(time));
        }
        
        int start = 540;
        int lastTime = 0;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            count = 0;
            
            while (!queue.isEmpty()) {
                int cur = queue.peek();
                
                if (cur <= start && count < m) {
                    queue.poll();
                    count++;
                } else break;
                
                lastTime = cur - 1;
            }
            
            start += t;
        }
        
        if (count < m) lastTime = start - t;
        
        if (lastTime / 60 < 10) answer += "0" + (lastTime / 60) + ":";
        else answer += (lastTime / 60) + ":";
        
        if (lastTime % 60 < 10) answer += "0" + (lastTime % 60);
        else answer += (lastTime % 60);
        
        return answer;
    }
    
    public int changeMin(String time) {
        String str[] = time.split(":");
        int hour = Integer.parseInt(str[0]) * 60;
        int min = Integer.parseInt(str[1]);
        return hour + min;
    }
}
