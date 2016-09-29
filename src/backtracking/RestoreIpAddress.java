package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 28/08/16.
 */
public class RestoreIpAddress {

    public List<String> restoreIpAddresses(String s) {

        List<List<String>> intermediateResult = new ArrayList<>();

        restoreAddressUtil(s,0,new ArrayList<>(),intermediateResult);

        List<String> result = new ArrayList<>();

        for (List<String> list : intermediateResult) {
            StringBuilder sb = new StringBuilder();
            for (String str: list) {
                sb.append(str);
                sb.append('.');
            }
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
        }

        return result;
    }


    private void restoreAddressUtil(String s,int startIndex,ArrayList<String> oneIpAddress,List<List<String>> collection) {

        if(oneIpAddress.size()>=4 && startIndex!=s.length()) {
            return;
        }

        if((oneIpAddress.size()+s.length()-startIndex+1)<4) {
            return;
        }


        if(oneIpAddress.size()==4 && startIndex==s.length()) {
            ArrayList<String> temp = new ArrayList<>(oneIpAddress);
            collection.add(temp);
            return;
        }

        for (int i=1;i<=3;i++) {

            if(startIndex+i>s.length()){
                return;
            }

            String subString = s.substring(startIndex,startIndex+i);

            //handle case like 06.
            if(i>1 && s.charAt(startIndex)=='0'){
                break;
            }

            if(Integer.parseInt(subString)>255) {
                break;
            }

            oneIpAddress.add(subString);
            restoreAddressUtil(s,startIndex+i,oneIpAddress,collection);
            oneIpAddress.remove(oneIpAddress.size()-1);

        }


    }

    public static void main(String[] args) {

        RestoreIpAddress rp = new RestoreIpAddress();
        System.out.println(rp.restoreIpAddresses("0000"));

    }

}
