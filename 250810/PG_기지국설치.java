class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1; // 탐색 시작 지점
        
        for (int station : stations) {
            if (station - w > start) {
                int section = station - w - start; // 전파가 닿지 않는 구간
                answer += section / (2 * w + 1);
                if (section % (2 * w + 1) != 0) answer += 1;
            }
            
            start = station + w + 1; // start 갱신
        }
        
        // 나머지 구간 탐색
        if (n >= start) {
            int section = n - start + 1;
            answer += section / (2 * w + 1);
            if (section % (2 * w + 1) != 0) answer += 1;
        }
        
        return answer;
    }
}
