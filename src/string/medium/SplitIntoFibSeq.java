package string.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 27/05/18.
 */
public class SplitIntoFibSeq {

    public List<Integer> splitIntoFibonacci(String S) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < S.length() - 2; i++) {
            res.clear();
            int first;
            try {
                first = Integer.valueOf(S.substring(0, i));
            } catch (Exception e) {
                break;
            }
            res.add(first);
            for (int j = i + 1; j < S.length(); j++) {
                int second;
                try {
                    second = Integer.valueOf(S.substring(i, j));
                } catch (Exception e) {
                    break;
                }
                res.add(second);
                if (isFibSeq(S.substring(j, S.length()), res)) {
                    return res;
                }
                else {
                    res.clear();
                    res.add(first);
                }

            }

        }
        res.clear();
        return res;

    }

    private boolean isFibSeq(String s, ArrayList<Integer> res) {
        if (s.length() == 0) {
            return true;
        }
        if (res.size() >= 2) {
            int a = res.get(res.size() - 1);
            int b = res.get(res.size() - 2);
            String tmp = a + b + "";
            if (s.startsWith(tmp)) {
                res.add(a + b);
                return isFibSeq(s.substring(tmp.length(), s.length()), res);
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "123456579";
        System.out.println(new SplitIntoFibSeq().splitIntoFibonacci(s));
    }

}
