/*

Given a list accounts, each element accounts[i] is a list of strings,
where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account, in sorted order.

Now, we would like to merge these accounts. Two accounts definitely belong to the same
person if there is some email that is common to both accounts. Note that even if two accounts have the same name,
they may belong to different people as people could have the same name. A person can have any number of accounts initially,
but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the format they were given: the first
element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

 */
package bfsdfs.medium;

import java.util.*;

/**
 * Created by poorvank.b on 05/11/17.
 */
public class AccountsMerge {


    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name.equals("")) {
                    name = email;
                    continue;
                }
                graph.putIfAbsent(email,new ArrayList<>());
                graph.putIfAbsent(account.get(1),new ArrayList<>());
                graph.get(email).add(account.get(1));
                graph.get(account.get(1)).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> visited = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        for (String email: graph.keySet()) {
            if (!visited.contains(email)) {
                visited.add(email);
                Stack<String> stack = new Stack<>();
                stack.push(email);
                List<String> component = new ArrayList<>();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!visited.contains(nei)) {
                            visited.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> l1 = Arrays.asList("David","David0@m.co","David1@m.co");
        List<String> l2 = Arrays.asList("David","David3@m.co","David4@m.co");
        List<String> l3 = Arrays.asList("David","David4@m.co","David5@m.co");
        List<String> l4 = Arrays.asList("David","David3@m.co","David2@m.co");
        List<String> l5 = Arrays.asList("David","David1@m.co","David2@m.co");
        List<List<String>> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        lists.add(l4);
        lists.add(l5);
        System.out.println(new AccountsMerge().accountsMerge(lists));
    }


}
