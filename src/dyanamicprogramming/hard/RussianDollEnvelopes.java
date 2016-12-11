/*

You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 One envelope can fit into another if and only if both the width and height of one envelope is greater than the
 width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

 */
package dyanamicprogramming.hard;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by poorvank on 10/12/16.
 */
public class RussianDollEnvelopes {

    private class Envelope implements Comparable<Envelope> {
        int width;
        int height;

        public Envelope(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int compareTo(Envelope e) {
            if(this.height>e.height) {
                return 1;
            }  else if(this.height<e.height) {
                return -1;
            } else{
                return 0;
            }
        }
    }

    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes.length==0 || envelopes.length==1) {
            return envelopes.length;
        }

        int n = envelopes.length;
        Envelope[] envelopeArr = new Envelope[n];

        for (int i=0;i<n;i++) {
            envelopeArr[i] = new Envelope(envelopes[i][0],envelopes[i][1]);
        }
        Arrays.sort(envelopeArr);

        int[] count  = new int[n];
        for (int i=0;i<n;i++) {
            count[i] = 1;
        }

        int max=1;

        for (int i=1;i<n;i++) {
            for (int j=0;j<i;j++) {
                if(envelopeArr[i].height>envelopeArr[j].height && envelopeArr[i].width>envelopeArr[j].width && count[i]<count[j] +1) {
                    count[i] = count[j] +1;
                    if(max<count[i]) {
                        max = count[i];
                    }
                }
            }
        }

        return max;

    }

}


/*

A variation of LIS

Sorting can also be done like : Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);

 */