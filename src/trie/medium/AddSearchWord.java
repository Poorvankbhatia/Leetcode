package trie.medium;

/**
 * Created by poorvank on 26/08/16.
 */

public class AddSearchWord {

    private final int R = 26;
    private Node root;

    private class Node {
        Node[] childArray = new Node[R];
        boolean value;
    }

    public AddSearchWord() {
        root = new Node();
    }

    public void addWord(String word) {
        root = addWord(root, word, 0);
    }

    private Node addWord(Node x, String word, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == word.length()) {
            x.value = true;
            return x;
        }
        char c = word.charAt(d);
        x.childArray[c-'a'] = addWord(x.childArray[c-'a'], word, d + 1);
        return x;
    }

    public boolean search(String word) {
        return search(word, root, new StringBuilder());
    }

    private boolean search(String word, Node x, StringBuilder sb) {

        if (x == null) {
            return false;
        }
        int d = sb.length();
        if (d == word.length()) {
            return x.value;
        }

        char c = word.charAt(d);
        if (c == '.') {
            for (char i = 0; i < R; i++) {
                sb.append(i);
                if (search(word, x.childArray[i], sb)) {
                    return true;
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        } else {
            sb.append(c);
            if (search(word, x.childArray[c-'a'], sb)) {
                return true;
            } else {
                sb.deleteCharAt(sb.length() - 1);
            }

        }
        return false;
    }

    public static void main(String[] args) {
        AddSearchWord dictionary = new AddSearchWord();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        System.out.println(dictionary.search("..."));
    }

}
