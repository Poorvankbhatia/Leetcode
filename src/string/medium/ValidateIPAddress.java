/*

In this problem, your job to write a function to check whether a input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, you need to keep in mind that leading zeros in the IPv4 is illegal. For example, the address 172.16.254.01 is illegal.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are
 separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one. Also, we could omit
 some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones,
 so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::)
to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. For example, the address
 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.

Note: You could assume there is no extra space in the test cases and there may some special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

 */

package string.medium;

/**
 * Created by poorvank on 11/12/16.
 */
public class ValidateIPAddress {

    public String validIPAddress(String IP) {

        if(IP.contains(".") && checkIPV4(IP)) {
            return "IPv4";
        } else if(IP.contains(":") && checkIPV6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }

    }

    private boolean checkIPV4(String ipAddress) {

        if(ipAddress.charAt(0)=='.' || ipAddress.charAt(ipAddress.length()-1)=='.') {
            return false;
        }

        String[] blocks = ipAddress.split("\\.");
        if(blocks.length<4 || blocks.length>4) {
            return false;
        }
        for (String s : blocks) {
            if(isNumeric(s) && s.length()!=0 && Character.isLetterOrDigit(s.charAt(0)) && s.length()<4) {

                if(s.length()>1 && s.charAt(0)=='0') {
                    return false;
                }

                int n  = Integer.parseInt(s);
                if(n<0 || n>255) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;

    }

    private boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }


    private boolean checkIPV6(String ipAddress) {

        if(ipAddress.charAt(0)==':' || ipAddress.charAt(ipAddress.length()-1)==':') {
            return false;
        }

        String[] blocks = ipAddress.split(":");
        if(blocks.length<2 || blocks.length>8) {
            return false;
        }

        for (String s : blocks) {
            if(s.length()!=0) {

                if(s.length()>4) {
                    return false;
                }

                if(!Character.isLetterOrDigit(s.charAt(0)) || !isHexaDecimal(s)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;

    }

    private boolean isHexaDecimal(String s) {
        try {
            Long.parseLong(s, 16);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ValidateIPAddress().validIPAddress("15.16.-0.1"));
    }

}
