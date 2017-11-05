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


    private HashMap<String,List<String>> emailMapping = new HashMap<>();
    private HashSet<String> visitedSet = new HashSet<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        mapEmailBuckets(accounts);

        //System.out.println(emailMapping.toString());
        List<List<String>> result = new ArrayList<>();

        for (List<String> list : accounts) {
           if(!visitedSet.contains(list.get(1))) {
               List<String> emails = new ArrayList<>();
               dfsList(list.get(1),emails);
               List<String> newList = new ArrayList<>();
               newList.addAll(emails);
               Collections.sort(newList);
               newList.add(0,list.get(0));
               result.add(newList);
           }
        }


        return result;

    }

    private void dfsList(String email,List<String> list) {

        list.add(email);
        visitedSet.add(email);
        for (String s : emailMapping.get(email)) {
            if(!visitedSet.contains(s)) {
                dfsList(s,list);
            }
        }
    }


    private void mapEmailBuckets(List<List<String>> accounts) {

        for (List<String> list : accounts) {
            for (int i=1;i<list.size();i++) {
               List<String> neighbours = new ArrayList<>();
               for (int j=1;j<list.size();j++) {
                   if(i!=j) {
                       neighbours.add(list.get(j));
                   }
               }
               if(emailMapping.containsKey(list.get(i))) {
                   List<String> stringList = emailMapping.get(list.get(i));
                   List<String> union = union(stringList,neighbours);
                   emailMapping.put(list.get(i),union);
               } else {
                   emailMapping.put(list.get(i),neighbours);
               }
            }
        }

    }

    private List<String> union(List<String> list1, List<String> list2) {
        Set<String> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
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
