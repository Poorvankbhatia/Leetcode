/*

You are given a string text. We want to display text on a screen of width w and height h.
You can choose any font size from array fonts, which contains the available font sizes in ascending order.

You can use the FontInfo interface to get the width and height of any character at any available font size.

The FontInfo interface is defined as such:

interface FontInfo {
  // Returns the width of character ch on the screen using font size fontSize.
  // O(1) per call
  public int getWidth(int fontSize, char ch);

  // Returns the height of any character on the screen using font size fontSize.
  // O(1) per call
  public int getHeight(int fontSize);
}
The calculated width of text for some fontSize is the sum of every getWidth(fontSize, text[i]) call for each
0 <= i < text.length (0-indexed). The calculated height of text for some fontSize is getHeight(fontSize).
Note that text is displayed on a single line.

It is guaranteed that FontInfo will return the same value if you call getHeight or getWidth with the same parameters.

It is also guaranteed that for any font size fontSize and any character ch:

getHeight(fontSize) <= getHeight(fontSize+1)
getWidth(fontSize, ch) <= getWidth(fontSize+1, ch)
Return the maximum font size you can use to display text on the screen. If text cannot fit on the display with any font size, return -1.



Example 1:

Input: text = "helloworld", w = 80, h = 20, fonts = [6,8,10,12,14,16,18,24,36]
Output: 6
Example 2:

Input: text = "leetcode", w = 1000, h = 50, fonts = [1,2,4]
Output: 4
Example 3:

Input: text = "easyquestion", w = 100, h = 100, fonts = [10,15,20,25]
Output: -1


Constraints:

1 <= text.length <= 50000
text contains only lowercase English letters.
1 <= w <= 107
1 <= h <= 104
1 <= fonts.length <= 105
1 <= fonts[i] <= 105
fonts is sorted in ascending order and does not contain duplicates.

 */
package binarysearch.medium;

public class MaximumFontSentence {

    interface FontInfo {
        int getWidth(int fontSize, char ch);
        int getHeight(int fontSize);
    }

    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {

        int lo = 0;
        int hi = fonts.length-1;

        while(hi-lo>1) {
            int mid = lo+(hi-lo)/2;
            // if it fits this could be a possible answer but search for higher fonts.
            if(fits(text,fontInfo,fonts[mid],w,h)) {
                lo = mid;
            } else {
                // if not answer definitely lies in the left side.
                hi = mid-1;
            }
        }

        // check for hi first, as it is asking for maximum font size.
        return fits(text,fontInfo,fonts[hi],w,h)?fonts[hi]:(fits(text,fontInfo,fonts[lo],w,h)?fonts[lo]:-1);

        /*
        Since it is upper bound BS, even this can be used:
         int lo = 0;
        int hi = fonts.length-1;

        if(!fits(text,fontInfo,fonts[0],w,h)) return -1;

        int ans = 0;
        while(hi>=lo) {
            int mid = lo+(hi-lo)/2;
            if(fits(text,fontInfo,fonts[mid],w,h)) {
                ans = mid;
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }


        return fonts[ans];
         */

    }

    // width,height
    private boolean fits(String text,FontInfo fontInfo,int val,int w,int h) {
        int height = fontInfo.getHeight(val);
        long widthSum=0;
        for(char c: text.toCharArray()) {
            widthSum+=fontInfo.getWidth(val,c);
        }
        return w>=widthSum && h>=height;
    }

}
