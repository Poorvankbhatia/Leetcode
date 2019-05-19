/*

Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.
 A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would
 be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and
 "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.
Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy.

Example:
Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

 */
package string.medium;

import java.util.*;

/**
 * Created by poorvank.b on 06/04/18.
 */
public class ExpressiveWords {

    Map<String,List<Integer>> listMap = new HashMap<>();
    Map<String,String> encodedStringMap = new HashMap<>();

    public int expressiveWords(String S, String[] words) {

        int result=0;

        fillRunLengthEncoding(S);

        for (String word : words) {

            boolean flag=true;
            fillRunLengthEncoding(word);
            if(!encodedStringMap.get(word).equals(encodedStringMap.get(S))) {
                break;
            } else {
                for (int i=0;i<listMap.get(word).size();i++) {
                    if((listMap.get(S).get(i)<3 && !Objects.equals(listMap.get(word).get(i), listMap.get(S).get(i)) ||
                            (listMap.get(S).get(i)>=3 && listMap.get(word).get(i)>listMap.get(S).get(i)))) {
                        flag = false;
                        break;
                    }
                }
            }

            if(flag) {
                result++;
            }

        }

        return result;


    }
    private void fillRunLengthEncoding(String input){

        List<Integer> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int count = 1;
            while (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                count++;
                i++;
            }
            list.add(count);
            s.append(input.charAt(i));
        }
        listMap.put(input,list);
        encodedStringMap.put(input, s.toString());
    }

    public static void main(String[] args) {
        /*String s = "aaaa";
        String[] arr = new String[]{"yrrjjappooybbebriiyynvvwtwwoeexkauu","yrjjappooybbebrriyynnvwwttwoeexkaauu","yyrrjapoybbebriiynnvvwwtwoeexkaauu","yyrjappooyybebbrriyynnvwttwwooeexxkkau","yrjaapooybbebrriyynnvvwwttwooexkaau","yyrjjapooyybeebbrriiyynvwwttwoexxkau","yrrjaappoyybbeebbriynnvwwtwooexxkauu","yrrjjaapooybebriynnvvwwttwwooexkaau","yyrrjjappooyybebriiyynvvwttwoeexxkkaau","yrrjaappooybbebrriynvwwtwooeexkau","yyrjjaapooyybebrriiynvvwttwwooeexxkkaau","yyrrjappooyybbebriyynnvwwttwwoeexkkauu","yyrrjjaapoyybbeebriiyynnvwwtwwooexkkaau","yrjjaappooybbeebriiyynnvwwtwwoexkau","yrrjjappoyybbeebbrriiyynnvwttwwooexxkkaauu","yyrrjjapooyybbebbrriyynvwtwoexxkkaauu","yyrrjappoybebrriynvwwttwooeexkkauu","yrrjaappooybbeebriiyynnvvwwttwoexxkauu","yrrjapoybebbrriyynvvwwttwwoexkaau","yyrrjjapoybbeebbrriynnvwwtwwooexkaauu","yyrrjjapooyybbeebbriyynnvwtwwoexkaau","yrjjaapooyybebriynnvwwttwooeexxkkaauu","yyrjjaapooybbebbriiynvvwttwwoexxkkauu","yrjjaapooyybeebbriiyynvvwwttwoeexxkau","yrjjappooyybbebbrriiynvvwtwooeexxkkau","yyrrjjapoyybbebbrriiyynvwwtwwoexxkkaau","yrjjapooyybbeebriyynnvvwwtwoeexkkau","yrjapooyybebriiynnvvwwtwwooeexkauu","yyrjaapoyybbebbrriynnvwtwwoeexkauu","yrrjjappoybeebrriiynvvwwtwwoeexxkkaau","yrrjjapoybbeebrriiyynnvwwttwwoexxkaau","yyrrjaapoybeebrriiyynvwttwwooeexkauu","yyrjapoybbeebbrriyynnvvwwttwwooeexkaauu","yyrjappooybebrriiynvwtwwoeexxkaauu","yrrjjappooybebrriynnvvwttwooexkau","yrjjaapoybbeebbriiynnvvwttwooexkauu","yyrrjapooyybbeebriiyynnvvwtwwoeexxkaauu","yyrjjaappooybeebbrriiyynnvvwwtwwoeexkkau","yrrjappoyybbeebrriiynvvwwtwwoeexxkauu","yrjapooyybeebriiyynvvwttwwooeexxkaauu","yrjjappooyybbebbriiynnvwwtwooeexxkauu","yyrrjjappooybbeebbriyynnvwtwwooexxkkau","yyrrjjaapooybebriiyynvwwtwooeexxkkaauu","yrjjappooyybbeebbriiyynvwwtwwoeexkkau","yrrjjappooybbebrriiynvvwwtwwoexxkkaau","yrjjapooybebbriyynnvvwwttwwooeexxkkaau","yyrjjapoyybebbrriynvvwwttwoexkauu","yyrjappoyybebriiynnvvwttwwoexxkaauu","yyrjaapoybbeebriyynvvwwttwoeexkau","yrjjaappooyybbebbriiynnvvwtwooexxkau","yyrjjaappooyybbebrriiyynvvwttwooexkau","yrjjappoybbeebriyynnvvwwttwwooexxkkaau","yyrrjaapooybbebbriiyynnvwwtwwooexxkkaauu","yrrjaapooybbeebrriynnvvwwtwoeexxkkauu","yrjjaappooyybeebbrriyynnvvwttwwoexxkkauu",
        "yrrjapooyybebriyynnvwwttwooeexkau","yyrjjaapooyybeebrriiynnvvwwttwoeexxkkau","yrjappooybebriyynnvvwttwwooeexkau","yrrjjaappoyybebbrriiyynvwwtwooexxkauu","yrjjappooybeebriynnvwwtwoeexkaauu","yrjaappoybbebbriiynnvwwttwooexxkaau","yyrrjappooyybeebbriiyynvwwttwwoexxkau","yyrjappoyybbeebrriynvwtwoeexkaau","yrrjjaapooybbeebbriyynvwwtwooeexkkaau","yrjapoybebbrriiynvwttwwoeexxkaau","yrjapooybebbrriiynnvwwtwwoexxkaau","yrrjjaappoybeebbriiyynvwwtwooexxkkaauu","yrjappooybeebrriynvwwtwooeexkaauu","yrrjaapooybeebbriiynvvwtwwoexxkkaauu","yyrrjaappooyybebbrriiyynvwwtwwooexxkkau","yyrjaappoybbeebriynnvvwwtwwooeexkaauu","yyrjaappooyybbebbriynvvwwttwwooexkauu","yrjappooybeebbrriiynnvwttwwooexkkau","yrrjjappooyybebbriiyynnvvwttwwoexkkau","yrrjjaapooybeebbriynnvvwwtwooexkaau","yyrjjappoybeebbrriiynnvwtwwoexkaauu","yyrjjaapoybbebbrriiyynnvvwtwwoexkaau","yyrrjjaappoyybbebbriyynvwwtwwooeexkkaau","yrrjjaappooybbebriiyynvvwttwwooexxkau","yyrjjaapoyybebriiynnvwtwwooeexkauu","yrrjjappoyybeebbriiyynnvwttwoexkkau","yrjjappoyybbebbrriynnvvwttwwooeexkkaauu","yyrjappooybeebrriiynnvwwttwwooexxkkaauu","yrrjaappoybbeebrriyynnvvwwtwwooeexxkaauu","yyrjaappooybeebbriiynvwttwoexxkkauu","yyrrjjapooyybbeebbrriyynvwttwwooeexxkkau","yrrjapoybbebbrriiynvwtwwoeexxkaau","yyrrjapoybbeebbriiyynnvvwttwooexkkauu","yyrjaapooyybebbrriiyynnvvwwtwooeexkkauu","yyrrjjaappoybbeebrriyynnvwwtwwoexkkaauu","yyrjappooybbeebrriiyynvwwttwwoexkkau","yyrjaapooyybebbriiyynnvvwwtwoeexkkaau","yyrrjjappoyybbeebbriiyynvwtwooexxkaauu","yrrjjaapoyybbeebriynvvwtwwoexxkaau","yyrrjjapoybbebbrriyynnvwwtwoeexxkkaau","yyrrjapooyybebrriiyynvwttwwooeexxkkauu","yrjappooyybebriiynnvwwtwoeexkkaauu","yrjjaapooyybeebriiynvwtwooexkauu","yyrrjjapoybeebbrriiynnvwttwwoexkaau","yyrrjaappoyybebbrriiyynvwwtwooeexkaau"};*/
        String s = "aaa";
        String[] arr = new String[]{"aaaa"};
        System.out.println(new ExpressiveWords().expressiveWords(s,arr));
    }

}

/*

For some word, write the head character of every group, and the count of that group.
For example, for "abbcccddddaaaaa", we'll write the "key" of "abcda", and the "count" [1,2,3,4,5].

Let's see if a word is stretchy. Evidently, it needs to have the same key as S.

Now, let's say we have individual counts c1 = S.count[i] and c2 = word.count[i].

If c1 < c2, then we can't make the ith group of word equal to the ith word of S by adding characters.

If c1 >= 3, then we can add letters to the ith group of word to match the ith group of S, as the latter is extended.

Else, if c1 < 3, then we must have c2 == c1 for the ith groups of word and S to match.

 */
