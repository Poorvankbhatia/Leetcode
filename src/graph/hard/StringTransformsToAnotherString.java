/*

Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.

In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.

Return true if and only if you can transform str1 into str2.



Example 1:

Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:

Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.


Note:

1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.

 */

package graph.hard;

import java.util.HashMap;
import java.util.Map;

public class StringTransformsToAnotherString {

    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        if (getFrequency(str2).size() >= 26) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        for (int i = str1.length() - 1; i >= 0; i--) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            map.put(c1, c2);
        }
        return true;
    }

    private Map<Character, Integer> getFrequency(String str) {
        Map<Character, Integer> res = new HashMap<>();
        for (final char c : str.toCharArray()) {
            res.putIfAbsent(c, 0);
            res.put(c, res.get(c) + 1);
        }
        return res;
    }

}

/*

Strings are equal
Not enough characters in between for conversion


Scan s1 and s2 at the same time,
record the transform mapping into a map/array.
The same char should transform to the same char.
Otherwise we can directly return false.

To realise the transformation:

transformation of link link ,like a -> b -> c:
we do the transformation from end to begin, that is b->c then a->b

transformation of cycle, like a -> b -> c -> a:
in this case we need a tmp
c->tmp, b->c a->b and tmp->a
Same as the process of swap two variable.

In both case, there should at least one character that is unused,
to use it as the tmp for transformation.
So we need to return if the size of set of unused characters < 26.

public boolean canConvert(String s1, String s2) {
        if (s1.equals(s2)) return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
    }


// first idea :  1. each char can not have outdegree > 1
    //               2. if there is circle a -> b -> a, we cannot transform
    // but the second idea is WRONG! we can use a other char as tmp bridge
    // ex, ab -> ba. ab->at->bt->ba
    // so for circle, if we have unused char to use as tmp brige, we can do that transform
    // so now second idea should be : if there is circle , check if we used all 26 chars

//if used char == 26 means there is circle and no unused char
         // e.x. if we can only use 4 chars [a,b,c,d], if uesd size == 3: abc->bcd or abc->bca both is fine, because we can ues d as tmp bridge
         // if used size == 4: because total chars is 4, there must be cicle except str1 == str2
         // if str1 != str2 && used siez == 4, no more unsed cahr as tmp bridge, so we cannot transform
         // but why only one unused char is enough?
         // consider if there are multi circles in chars ex. [a,b,c,d,e] abcd->badc, although ther are
         // two ciecles a->b->a and c->d->c, only one unused char e as tmp bridge is enough
         // because we can transform cicle a->b->a first with e, and this operation doen't have influence on the second circle c->d->c,
         //which means we can break each circle individually
         // so in that case, we can still do the transform

 */