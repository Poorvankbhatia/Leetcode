/*

Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there
will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and
"alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain
emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.

 */
package hashing.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by poorvank.b on 31/10/18.
 */
public class UniqueEmail {

    public int numUniqueEmails(String[] emails) {
        Map<String,Set<String>> map = new HashMap<>();
        int count=0;
        for(String email : emails) {
            String domain = email.split("@")[1];
            if(!map.containsKey(domain)) {
                map.put(domain,new HashSet<>());
            }
            map.get(domain).add(getEmail(email));
        }

        for(Map.Entry<String,Set<String>> entry : map.entrySet()) {
            count+=entry.getValue().size();
        }

        return count;

    }

    private String getEmail(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='+' || s.charAt(i)=='@') {
                return sb.toString();
            }else if(s.charAt(i)=='.') {
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

}

/*

Small Solution:

public int numUniqueEmails(String[] emails) {
        Set<String> normalized = new HashSet<>();
        for (String email : emails) {
            String[] parts = email.split("@"); // split into local and domain parts.
            String[] local = parts[0].split("\\+"); // split local by '+'.
            normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.
        }
        return normalized.size();
    }


 */
