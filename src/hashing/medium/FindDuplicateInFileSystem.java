/*

Given a list of directory info including directory path, and all the files with contents in this directory,
you need to find out all the groups of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory
root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content.
A file path is a string that has the following format:

"directory_path/file_name.txt"

Example 1:
Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

 */

package hashing.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by poorvank on 04/06/17.
 */
public class FindDuplicateInFileSystem {

    public List<List<String>> findDuplicate(String[] paths) {

        List<List<String>> lists = new ArrayList<>();

        if(paths==null || paths.length==0) {
            return lists;
        }

        HashMap<String,List<String>> contentToPathMapping = new HashMap<>();

        for (String path : paths) {
            String[] splitPaths = path.split(" ");
            for (int i=1;i<splitPaths.length;i++) {
                String content = getStringBetweenBraces(splitPaths[i]);
                if (content.length()!=0) {
                    String currentPath = splitPaths[0] + "/" + splitPaths[i].substring(0, splitPaths[i].indexOf("("));
                    if (contentToPathMapping.containsKey(content)) {
                        contentToPathMapping.get(content).add(currentPath);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(currentPath);
                        contentToPathMapping.put(content, list);
                    }
                }

            }
        }


        for (String key : contentToPathMapping.keySet()) {
            if(contentToPathMapping.get(key).size()>1) {
                lists.add(contentToPathMapping.get(key));
            }
        }


        return lists;
    }


    private String getStringBetweenBraces(String s) {
        if(s.contains("(")) {
            return s.substring(s.indexOf("(")+1,s.indexOf(")"));
        }
        return "";
    }

    public static void main(String[] args) {
        String[] paths = {"root/a 1.txt(abcd) 2.txt(abcd)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"};
        System.out.println(new FindDuplicateInFileSystem().findDuplicate(paths));

    }

}
