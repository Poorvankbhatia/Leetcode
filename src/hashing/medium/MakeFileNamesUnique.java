/*
Given an array of strings names of size n. You will create n folders in your file system such that, at the ith minute,
you will create a folder with the name names[i].

Since two files cannot have the same name, if you enter a folder name which is previously used, the system will have a
suffix addition to its name in the form of (k), where, k is the smallest positive integer such that the obtained name remains unique.

Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.



Example 1:

Input: names = ["pes","fifa","gta","pes(2019)"]
Output: ["pes","fifa","gta","pes(2019)"]
Explanation: Let's see how the file system creates folder names:
"pes" --> not assigned before, remains "pes"
"fifa" --> not assigned before, remains "fifa"
"gta" --> not assigned before, remains "gta"
"pes(2019)" --> not assigned before, remains "pes(2019)"
Example 2:

Input: names = ["gta","gta(1)","gta","avalon"]
Output: ["gta","gta(1)","gta(2)","avalon"]
Explanation: Let's see how the file system creates folder names:
"gta" --> not assigned before, remains "gta"
"gta(1)" --> not assigned before, remains "gta(1)"
"gta" --> the name is reserved, system adds (k), since "gta(1)" is also reserved, systems put k = 2. it becomes "gta(2)"
"avalon" --> not assigned before, remains "avalon"
Example 3:

Input: names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
Output: ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
Explanation: When the last folder is created, the smallest positive valid k is 4, and it becomes "onepiece(4)".
Example 4:

Input: names = ["wano","wano","wano","wano"]
Output: ["wano","wano(1)","wano(2)","wano(3)"]
Explanation: Just increase the value of k each time you create folder "wano".
Example 5:

Input: names = ["kaido","kaido(1)","kaido","kaido(1)"]
Output: ["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
Explanation: Please note that system adds the suffix (k) to current name even it contained the same suffix before.


Constraints:

1 <= names.length <= 5 * 10^4
1 <= names[i].length <= 20
names[i] consists of lower case English letters, digits and/or round brackets.
 */
package hashing.medium;

import java.util.*;

public class MakeFileNamesUnique {

    public String[] getFolderNames(String[] names) {
        String[] arr = new String[names.length];
        int i=0;
        Map<String,Integer> nextSuffix = new HashMap<>();
        for (String name : names) {
            if(nextSuffix.containsKey(name)) {
                int k = nextSuffix.get(name); // get next suffix to be used.
                String newName = name+"("+k+")";
                while (nextSuffix.containsKey(newName)) {
                    newName = name+"("+(++k)+")";
                }
                arr[i++]=newName;
                nextSuffix.put(newName,1);
                nextSuffix.put(name,nextSuffix.getOrDefault(name,0)+1);
            } else {
                arr[i++]=name;
                nextSuffix.put(name,1);
            }
        }
        return arr;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MakeFileNamesUnique().getFolderNames(new String[]{"a","a","a","a"})));
    }

}
