/*

Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.

 */
package math.medium;

/**
 * Created by poorvank.b on 26/03/17.
 */
public class ComplexNoMul {

    private class Complex {

        private final int real;   // the real part
        private final int imaginary;   // the imaginary part

        // create a new object with the given real and imaginary parts
        public Complex(int real, int imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        public String toString() {
            if (imaginary == 0) return real + "" + "+0i";
            if (real == 0) return "0+"+ imaginary + "i";
            if (imaginary <  0) return real + "+" + (imaginary) + "i";
            return real + "+" + imaginary + "i";
        }

        public Complex times(Complex b) {
            Complex a = this;
            int real = a.real * b.real - a.imaginary * b.imaginary;
            int imag = a.real * b.imaginary + a.imaginary * b.real;
            System.out.print(real + " " + imag);
            return new Complex(real, imag);
        }

    }

    public String complexNumberMultiply(String a, String b) {

        String[] aVal = a.split("\\+");
        String[] bVal = b.split("\\+");

        int aReal = Integer.parseInt(aVal[0]);
        int aComplex = Integer.parseInt(removeLastChar(aVal[1]));
        Complex c1 = new Complex(aReal,aComplex);


        int bReal = Integer.parseInt(bVal[0]);
        int bComplex = Integer.parseInt(removeLastChar(bVal[1]));
        Complex c2 = new Complex(bReal,bComplex);

        Complex result = c1.times(c2);

        return result.toString();

    }

    private String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }

    public static void main(String[] args) {

        String a = "1+-1i";
        String b = "1+-1i";
        System.out.print(new ComplexNoMul().complexNumberMultiply(a,b));

    }

}
