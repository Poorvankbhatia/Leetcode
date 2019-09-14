/*

You are asked to design a file system which provides two functions:

createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

Implement the two functions.

Please refer to the examples for clarifications.



Example 1:

Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.


Constraints:

The number of calls to the two functions is less than or equal to 10^4 in total.
2 <= path.length <= 100
1 <= value <= 10^9

 */
package design.medium;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {

    Map<String,Integer> map;

    public FileSystem() {
        map = new HashMap<>();
    }

    public boolean createPath(String path, int value) {
        if(map.containsKey(path)) {
           return false;
        }
        String[] split = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<split.length-1;i++ ) {
            if(!split[i].equals("")) {
                sb.append("/").append(split[i]);
                if(!map.containsKey(sb.toString())) {
                    return false;
                }
            }
        }
        map.put(path, value);
        return true;
    }

    public int get(String path) {
        return map.getOrDefault(path, -1);
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.createPath("/a",1);
        System.out.println(fileSystem.get("/a"));
    }

}
