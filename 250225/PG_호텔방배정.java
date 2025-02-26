import java.util.*;

class Solution {
    Map<Long, Long> rooms = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
    
    public long findEmptyRoom(long roomNum) {
        if (!rooms.containsKey(roomNum)) {
            rooms.put(roomNum, roomNum + 1);
            return roomNum;
        }
        
        long nextRoom = rooms.get(roomNum);
        long emptyRoom = findEmptyRoom(nextRoom);
        rooms.put(roomNum, emptyRoom);
        return emptyRoom;
    }
}
profile
U
