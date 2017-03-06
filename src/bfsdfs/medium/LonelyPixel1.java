/*

Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.

 */

package bfsdfs.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 05/03/17.
 */
public class LonelyPixel1 {

    public int findLonelyPixel(char[][] picture) {

        int m  = picture.length;

        if(m==0) {
            return 0;
        }

        int n  = picture[0].length;

        int count=0;
        Set<Integer> noLonelyRow = new HashSet<>();
        Set<Integer> noLonelyCol = new HashSet<>();

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if(picture[i][j]=='B') {
                    if(!noLonelyRow.contains(i) && !noLonelyCol.contains(j)) {
                        boolean currentRowContainsOtherB = currentRowContains(i,j,picture,m,n);
                        boolean currentColContainsOtherB = currentColContains(i,j,picture,m,n);
                        if(!currentRowContainsOtherB && !currentColContainsOtherB) {
                            count++;
                        } else {
                            if(currentRowContainsOtherB) {
                                noLonelyRow.add(i);
                            }
                            if(currentColContainsOtherB) {
                                noLonelyCol.add(j);
                            }
                        }
                    }
                }
            }
        }

        return count;

    }

    private boolean currentRowContains(int currentRow,int currentCol,char[][] picture,int m,int n) {

        for (int j=0;j<n;j++) {
            if(picture[currentRow][j]=='B' && j!=currentCol) {
                return true;
            }
        }

        return false;

    }

    private boolean currentColContains(int currentRow,int currentCol,char[][] picture,int m,int n) {

        for (int i=0;i<m;i++) {
            if(picture[i][currentCol]=='B' && i!=currentRow) {
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        char[][] arr = new char[][] {
                {'W', 'W', 'B'},
                {'W', 'B', 'B'},
                {'B', 'W', 'W'}
        };
        System.out.println(new LonelyPixel1().findLonelyPixel(arr));
    }

}
