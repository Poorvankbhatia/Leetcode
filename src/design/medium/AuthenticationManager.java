package design.medium;

import java.util.*;

class AuthenticationManager {

    // ttl
    private final int ttl;
    // map containing timestamp and the corresponding tokens.
    private final TreeMap<Integer, Set<String>> treeMap;
    // map of token to expiry tme.
    private final Map<String,Integer> map;
    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
        treeMap = new TreeMap<>();
        map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        int expiryTime = currentTime+ttl;
        treeMap.putIfAbsent(expiryTime,new HashSet<>());
        // add token to the time.
        treeMap.get(expiryTime).add(tokenId);
        map.put(tokenId,currentTime+ttl);
    }

    public void renew(String tokenId, int currentTime) {
        // if the token value of the map>currentTime then renew.
        if(map.getOrDefault(tokenId,Integer.MIN_VALUE)>currentTime) {
            Set<String> tokens = treeMap.get(map.get(tokenId));
            // remove the current token.
            tokens.remove(tokenId);
            if(tokens.size()==0) {
                treeMap.remove(map.get(tokenId));
            }
            int expiryTime = currentTime+ttl;
            treeMap.putIfAbsent(expiryTime,new HashSet<>());
            // add token to new expiry.
            treeMap.get(expiryTime).add(tokenId);
            map.put(tokenId,expiryTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        return treeMap.tailMap(currentTime,false).size();
    }

    public static void main(String[] args) {
        AuthenticationManager am = new AuthenticationManager(13);
        am.generate("fuzxq",5);
        am.generate("izmry",7);
        am.generate("ybiqb",13);
        am.generate("gm",14);
        am.renew("ybiqb",21);
        System.out.println(am.countUnexpiredTokens(23));
    }

}
