package design.medium;

import java.util.HashMap;

/**
 * Created by poorvank on 08/03/17.
 */
public class TinyUrl {

    HashMap<Integer,String> map;
    private static int id;

    public TinyUrl() {
        this.map = new HashMap<>();
        id = 1;
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String conversionString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int currentId = id;
        StringBuilder stringBuilder = new StringBuilder();
        while (currentId!=0) {
            int mod = currentId%62;
            stringBuilder.append(conversionString.charAt(mod));
            currentId /= 62;
        }
        map.put(id,longUrl);
        id++;
        return stringBuilder.reverse().toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int id = 0,k=62;
        for (int i=0;i<shortUrl.length();i++) {
            char c = shortUrl.charAt(i);
            if('a'<= c && c<='z') {
                id += (id*k) + c-'a';
            }
            if('A'<= c && c<='Z') {
                id += (id*k) + c-'A' + 26;
            }
            if('0'<= c && c<='9') {
                id += (id*k) + c-'0' + 52;
            }
        }
        return map.get(id);
    }

    public static void main(String[] args) {
        String url = "http://example.com/battle/act.aspx";
        TinyUrl tinyUrl = new TinyUrl();
        System.out.println(tinyUrl.decode(tinyUrl.encode(url)));
    }

}
