// 첫번째 풀이 : 29ms

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int hour = 0;
            for (int pile : piles) {
                hour += Math.ceil((double) pile / mid);
            }

            if (hour <= h) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

// 두번째 풀이 : 7ms

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile : piles) right = Math.max(right, pile);

        while (left < right) {
            int mid = left + (right - left) / 2;

            int hour = 0;
            for (int pile : piles) {
                hour += (pile + mid - 1) / mid;
            }

            if (hour <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
