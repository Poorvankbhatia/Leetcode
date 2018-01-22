/*

Given a digit string, return all possible letter combinations that the number could represent.

 */
package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 27/08/16.
 */
public class PhoneLetterCombinations {

    HashMap<Character,ArrayList<Character>> map = new HashMap<>();

    public PhoneLetterCombinations() {
        map.put('2',new ArrayList<>(Arrays.asList('a','b','c')));
        map.put('3',new ArrayList<>(Arrays.asList('d','e','f')));
        map.put('4',new ArrayList<>(Arrays.asList('g','h','i')));
        map.put('5',new ArrayList<>(Arrays.asList('j','k','l')));
        map.put('6',new ArrayList<>(Arrays.asList('m','n','o')));
        map.put('7',new ArrayList<>(Arrays.asList('p','q','r','s')));
        map.put('8',new ArrayList<>(Arrays.asList('t','u','v')));
        map.put('9',new ArrayList<>(Arrays.asList('w','x','y','z')));

    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        letterCombUtil(digits,0,list,new StringBuilder());
        return list;
    }

    private void letterCombUtil(String digits,int index,List<String> list,StringBuilder sb) {

        if(digits.isEmpty()) {
            return;
        }

        if(index==digits.length()) {
            list.add(sb.toString());
            return;
        }
        char c = digits.charAt(index);
        ArrayList<Character> arrayList = map.get(c);

        if(arrayList!=null) {
            for (Character ch : arrayList) {
                sb.append(ch);
                letterCombUtil(digits, index + 1, list, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

    public static void main(String[] args) {
        PhoneLetterCombinations p = new PhoneLetterCombinations();
        System.out.println(p.letterCombinations("482"));
    }

}
