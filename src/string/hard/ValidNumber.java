/*

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

 */
package string.hard;

/**
 * Created by poorvank.b on 18/03/17.
 */
public class ValidNumber {

    public boolean isNumber(String s) {

        if(null==s || s.length()==0 || s.trim().length()==0) {
            return false;
        }

        s = s.trim();
        int pointIndex = s.indexOf('.');

        char startChar = s.charAt(0);
        if((pointIndex!=s.lastIndexOf('.')) || (s.length()==1 && !Character.isDigit(startChar))) {
            return false;
        }

        int eIndex = s.indexOf('e');
        if(eIndex!=-1) {
            String afterE = s.substring(eIndex+1);
            // Decimal point should always occur before e
            if(eIndex<pointIndex) {
                return false;
            }
            if(startChar=='-' || startChar=='+') {
                return isNumberUtil(s.substring(1,eIndex)) && isNumberUtil(afterE);
            } else {
                return isNumberUtil(s.substring(0,eIndex)) && isNumberUtil(afterE);
            }
        } else {
            if(startChar=='-' || startChar=='+') {
                return isNumberUtil(s.substring(1));
            } else {
                return isNumberUtil(s);
            }
        }

    }

    private boolean isNumberUtil(String s) {

        if(s.length()==0 ||  (s.length()==1 && !Character.isDigit(s.charAt(0)))) {
            return false;
        }

        for (int i=0;i<s.length();i++) {

            // For cases like "+-."
            if(i>0 && !Character.isDigit(s.charAt(i)) && !Character.isDigit(s.charAt(i-1))) {
                 return false;
            }

            char c =s.charAt(i);
            if((c<'0' || c>'9')) {
                //3. a valid number
                if(c=='.' || ((c=='+' || c=='-') && i==0)) {
                    continue;
                }
                return false;
            }

        }

        return true;

    }

    public static void main(String[] args) {
        String s = "+-.";
        System.out.print(new ValidNumber().isNumber(s));
    }


}
