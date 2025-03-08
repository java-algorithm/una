class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            int fCount = 0;
            int bCount = 0;

            for (int day : bloomDay) {
                if (day <= mid) fCount++;
                else fCount = 0;

                if (fCount == k) {
                    fCount = 0;
                    bCount++;
                }
            }
            
            if (bCount >= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
