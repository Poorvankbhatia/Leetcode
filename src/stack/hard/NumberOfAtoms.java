/*

Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow.
For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count
(if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input:
formula = "H2O"
Output: "H2O"
Explanation:
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input:
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation:
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input:
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation:
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

 */
package stack.hard;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 28/04/18.
 */
public class NumberOfAtoms {

    public String countOfAtoms(String formula) {
        Map<String, Integer> atoms = new TreeMap<>();

        int mul = 1;
        Stack<Integer> stack = new Stack<>();
        int pos = formula.length() - 1;
        while (pos >= 0) {
            String token = getToken(formula, pos);
            pos -= token.length();

            if (isAtom(token)) {
                atoms.put(token, atoms.getOrDefault(token, 0) + mul);
            } else if (isNum(token)) {
                int num = Integer.valueOf(token);
                String next = getToken(formula, pos);
                pos -= next.length();

                if (isAtom(next)) {
                    atoms.put(next, atoms.getOrDefault(next, 0) + num * mul);
                } else {
                    mul *= num;
                    stack.push(num);
                }
            } else if (token.equals(")")) {
                stack.push(1);
            } else {
                mul /= stack.peek();
                stack.pop();
            }
        }

        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> e : atoms.entrySet()) {
            res.append(e.getKey());
            if (e.getValue() != 1) {
                res.append(e.getValue());
            }
        }
        return res.toString();
    }

    private String getToken(String formula, int pos) {
        if (Character.isLetter(formula.charAt(pos))) {
            int i = pos;
            while (i >= 0 && Character.isLowerCase(formula.charAt(i))) {
                --i;
            }
            return formula.substring(i, pos + 1);
        }

        if (Character.isDigit(formula.charAt(pos))) {
            int i = pos;
            while (i >= 0 && Character.isDigit(formula.charAt(i))) {
                --i;
            }
            return formula.substring(i + 1, pos + 1);
        }

        return formula.substring(pos, pos + 1);
    }

    private boolean isNum(String token) {
        return Character.isDigit(token.charAt(0));
    }

    private boolean isAtom(String token) {
        return Character.isLetter(token.charAt(0));
    }

}
