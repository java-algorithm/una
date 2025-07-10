// 49ms

import java.util.*;

/**
 * 리트코드 2521번 Distinct Prime Factors of Product of Array
 * - 소인수분해 하여 Set에 넣기
 */
class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            int divide = 2;
            while (divide <= num) {
                if (num % divide == 0) {
                    set.add(divide);
                    num /= divide;
                } else divide++;
            }
        }

        return set.size();
    }
}

// 9ms

import java.util.*;

/**
 * 리트코드 2521번 Distinct Prime Factors of Product of Array
 * - 소인수분해 하여 Set에 넣기
 */

class Solution {
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            while (num % 2 == 0) {
                set.add(2);
                num /= 2;
            }

            for (int i = 3; i * i <= num; i += 2) {
                while (num % i == 0) {
                    set.add(i);
                    num /= i;
                }
            }

            if (num > 2) set.add(num);
        }

        return set.size();
    }
}
