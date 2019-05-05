/*

Given a C++ program, remove comments from it.
The program source is an array where source[i] is the i-th line of the source code.
This represents the result of splitting the original source code string by the newline character \n.

 */
package string.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 05/11/17.
 */
public class RemoveComments {

    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean mode = false;
        for (String s : source) {
            int i;
            for (i = 0; i < s.length(); i++) {
                if (mode) {
                    if (s.charAt(i) == '*' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        mode = false;
                        i++;
                    }
                } else {
                    if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '/') {
                        if (sb.length() > 0) {
                            res.add(sb.toString());
                        }
                        sb = new StringBuilder();
                        break;
                    } else if (s.charAt(i) == '/' && i < s.length() - 1 && s.charAt(i + 1) == '*') {
                        mode = true;
                        i++;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (i == s.length() && !mode && sb.length() > 0) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"a/*comment", "line", "more_comment*/b"};
        System.out.println(new RemoveComments().removeComments(arr));
    }

}

/*

We only need to check for two things:

If we see '//' we stop reading the current line, and add whatever characters we have seen to the result
If we see '/' then we start the multiline comment mode and we keep on ignoring characters until we see '/'
If the current character is neither of the above two and the multiline comment mode is off, then we add that character to the current line.


 */