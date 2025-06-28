import java.util.*;

class Solution {
    public String stringHash(String s, int k) {
        int n = s.length();

        StringBuilder sb = new StringBuilder(); 

        int index = 0;
        while (index < n) {
            int sum = 0;
            for (int i = index; i < k + index; i++) {
                sum += s.charAt(i) - 'a';
            }
            
            index += k;

            char newAplhabet = (char) (sum % 26 + 'a');
            sb.append(newAplhabet);
        }

        return sb.toString();
    }
}
