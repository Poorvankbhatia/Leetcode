package string.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 08/06/18.
 */
public class CanPermutePalindrome {




    public boolean canPermutePalindrome(String s) {
        if(s.length()==0) {
            return true;
        }

        Map<Character,Integer> map = new HashMap<>();
        for(Character c : s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0)+1);
        }

        int oddCount=0;
        for(Map.Entry<Character,Integer> entry : map.entrySet()) {
            if(entry.getValue()%2!=0) {
                oddCount++;
            }
        }

        return oddCount==1 || oddCount==0;
    }

}

/*

public boolean canPermutePalindrome(String s) {
        Set < Character > set = new HashSet < > ();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
        }
        return set.size() <= 1;
    }


 */
