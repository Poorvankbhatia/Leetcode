/*

Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d"
would use more tags, so it is incorrect.

Note:

words has length in range [0, 50].
words[i] has length in range [1, 10].
S has length in range [0, 500].
All characters in words[i] and S are lowercase letters.

 */
package string.medium;

/**
 * Created by poorvank.b on 07/01/18.
 */
public class BoldWords {

    public String boldWords(String[] words, String S) {
        if (words == null || words.length == 0) {
            return "";
        }

        boolean[] visited = new boolean[S.length()];
        for (String word : words) {
            visitWords(S, word, visited);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (visited[i] && (i == 0 || !visited[i - 1])) {
                sb.append("<b>");
            }
            sb.append(S.charAt(i));

            if (visited[i] && (i == S.length() - 1 || !visited[i + 1])) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }

    private void visitWords(String S, String word, boolean[] marked) {
        for (int i = 0; i <= S.length() - word.length(); i++) {
            String substring = S.substring(i, i + word.length());
            if (substring.equals(word)) {
                for (int j = i; j < i + word.length(); j++) {
                    marked[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new BoldWords().boldWords(new String[]{"ab", "bc"},"aabcd"));
    }

}
