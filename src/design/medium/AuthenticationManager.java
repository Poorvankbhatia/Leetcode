/*
There is an authentication system that works with authentication tokens. For each session, the user will receive a new
authentication token that will expire timeToLive seconds after the currentTime. If the token is renewed, the expiry time
will be extended to expire timeToLive seconds after the (potentially different) currentTime.

Implement the AuthenticationManager class:

AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime in seconds.
If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens),
the expiration takes place before the other actions.

Input
["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew", "countUnexpiredTokens"]
[[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
Output
[null, null, null, 1, null, null, null, 0]

Explanation
AuthenticationManager authenticationManager = new AuthenticationManager(5); // Constructs the AuthenticationManager with timeToLive = 5 seconds.
authenticationManager.renew("aaa", 1); // No token exists with tokenId "aaa" at time 1, so nothing happens.
authenticationManager.generate("aaa", 2); // Generates a new token with tokenId "aaa" at time 2.
authenticationManager.countUnexpiredTokens(6); // The token with tokenId "aaa" is the only unexpired one at time 6, so return 1.
authenticationManager.generate("bbb", 7); // Generates a new token with tokenId "bbb" at time 7.
authenticationManager.renew("aaa", 8); // The token with tokenId "aaa" expired at time 7, and 8 >= 7, so at time 8 the renew request is ignored, and nothing happens.
authenticationManager.renew("bbb", 10); // The token with tokenId "bbb" is unexpired at time 10, so the renew request is fulfilled and now the token will expire at time 15.
authenticationManager.countUnexpiredTokens(15); // The token with tokenId "bbb" expires at time 15, and the token with tokenId "aaa" expired at time 7, so currently no token is unexpired, so return 0.


Constraints:

1 <= timeToLive <= 108
1 <= currentTime <= 108
1 <= tokenId.length <= 5
tokenId consists only of lowercase letters.
All calls to generate will contain unique values of tokenId.
The values of currentTime across all the function calls will be strictly increasing.
At most 2000 calls will be made to all functions combined.

 */
package design.medium;

import java.util.*;

class AuthenticationManager {

    // ttl
    private final int ttl;
    // map containing timestamp and the corresponding tokens.
    private final TreeMap<Integer, Set<String>> treeMap;
    // map of token to expiry tme.
    private final Map<String, Integer> map;

    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
        treeMap = new TreeMap<>();
        map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
        int expiryTime = currentTime + ttl;
        treeMap.putIfAbsent(expiryTime, new HashSet<>());
        // add token to the time.
        treeMap.get(expiryTime).add(tokenId);
        map.put(tokenId, currentTime + ttl);
    }

    public void renew(String tokenId, int currentTime) {
        // if the token value of the map>currentTime then renew.
        if (map.getOrDefault(tokenId, Integer.MIN_VALUE) > currentTime) {
            Set<String> tokens = treeMap.get(map.get(tokenId));
            // remove the current token.
            tokens.remove(tokenId);
            if (tokens.size() == 0) {
                treeMap.remove(map.get(tokenId));
            }
            int expiryTime = currentTime + ttl;
            treeMap.putIfAbsent(expiryTime, new HashSet<>());
            // add token to new expiry.
            treeMap.get(expiryTime).add(tokenId);
            map.put(tokenId, expiryTime);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        return treeMap.tailMap(currentTime, false).size();
    }

    public static void main(String[] args) {
        AuthenticationManager am = new AuthenticationManager(13);
        am.generate("fuzxq", 5);
        am.generate("izmry", 7);
        am.generate("ybiqb", 13);
        am.generate("gm", 14);
        am.renew("ybiqb", 21);
        System.out.println(am.countUnexpiredTokens(23));
    }

}
