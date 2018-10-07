package string.easy;

/**
 * Created by poorvank.b on 07/10/18.
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {

        char[] a = S.toCharArray();
        int i=0,j=S.length()-1;

        while (i<j) {
            if(Character.isLetter(S.charAt(i)) && Character.isLetter(S.charAt(j))) {
                swap(i,j,a);
                i++;
                j--;
            } else if(!Character.isLetter(S.charAt(i)) && !Character.isLetter(S.charAt(j))) {
                i++;
                j--;
            } else if(!Character.isLetter(S.charAt(i))) {
                i++;
            } else if(!Character.isLetter(S.charAt(j))) {
                j--;
            }
        }

        return new String(a);

    }

    private void swap(int i,int j,char[] a) {
        char temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }

}
