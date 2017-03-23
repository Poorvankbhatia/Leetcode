/*

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"

 */

package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank.b on 22/03/17.
 */
public class SimplifyPath {

    public String simplifyPath(String path) {

        if(path==null || path.length()==0) {
            return path;
        }

        String simplifiedPath = "";

        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String directory : arr) {

            switch (directory) {
                case "..":
                    if (stack.isEmpty()) {
                        continue;
                    } else {
                        stack.pop();
                    }
                    break;
                case ".":
                    continue;
                case "":
                    continue;
                default:
                    stack.push(directory);
                    break;
            }

        }

        while (!stack.isEmpty()) {
            simplifiedPath = "/"+stack.pop()+simplifiedPath;
        }

        if(simplifiedPath.length()==0) {
            simplifiedPath = "/";
        }

        return simplifiedPath;

    }

    public static void main(String[] args) {

        String path = "/...";
        System.out.print(new SimplifyPath().simplifyPath(path));

    }

}
