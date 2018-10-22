/*

An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
The black pixels are connected, i.e., there is only one black region.
Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels,
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
For example, given the following image:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.

 */
package bfsdfs.hard;

/**
 * Created by poorvank.b on 08/09/17.
 */
public class SmallestRectangle {

    private int top;
    private int bottom;
    private int left;
    private int right;
    private int area = 0;

    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0) {
            return 0;
        }

        this.top = y;
        this.bottom = y;
        this.left = x;
        this.right = x;

        int m = image.length;
        int n = image[0].length;

        boolean[][] visited = new boolean[m][n];

        minAreaHelper(image, x, y, visited,m,n);

        return area;
    }

    private void minAreaHelper(char[][] image, int x, int y, boolean[][] visited,int m,int n) {

        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
            return;
        }

        if (image[x][y] == '0') {
            return;
        }

        visited[x][y] = true;

        // update the border
        top = Math.max(top, y);
        bottom = Math.min(bottom, y);
        left = Math.min(left, x);
        right = Math.max(right, x);

        int curArea = (top - bottom + 1) * (right - left + 1);
        area = Math.max(area, curArea);

        minAreaHelper(image, x, y - 1, visited,m,n);
        minAreaHelper(image, x, y + 1, visited,m,n);
        minAreaHelper(image, x - 1, y, visited,m,n);
        minAreaHelper(image, x + 1, y, visited,m,n);
    }

    public static void main(String[] args) {
        char[][] arr = new char[][] {
                {'0','0','1','0'},
                {'0','1','1','0'},
                {'0','1','0','0'}
        };
        System.out.println(new SmallestRectangle().minArea(arr,0,2));
    }

}

/*
Look for the borders for the black pixels.
 */