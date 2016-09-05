/*

Design a simplified version of Twitter where users can post tweets, follow/unfollow
another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
Each item in the news feed must be posted by users who the user followed or by the user herself.
Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);

 */

package heap;

import java.util.*;

/**
 * Created by poorvank on 05/09/16.
 */
public class Twitter {

    //A static variable used to calculate timing of tweets
    private static int tweetVariable = 0;

    private class Tweet implements Comparable<Tweet> {
        int tweet;
        int id;

        public Tweet(int tweet, int id) {
            this.tweet = tweet;
            this.id = id;
        }

        public int compareTo(Tweet t) {
            if(this.id>t.id) {
                return 1;
            } else if(this.id<t.id) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    private MinPriorityQueue<Tweet> minPriorityQueue;
    private HashMap<Integer,List<Tweet>> usersTweet;
    private HashMap<Integer,Set<Integer>> followUsers;

    /** Initialize your data structure here. */
    public Twitter() {
        usersTweet = new HashMap<>();
        followUsers = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweetVariable++;
        Tweet newTweet = new Tweet(tweetId,tweetVariable);
        if(usersTweet.containsKey(userId)) {
            List<Tweet> tweetList = usersTweet.get(userId);
            tweetList.add(newTweet);
            usersTweet.put(userId,tweetList);
        } else {
            List<Tweet> tweetList = new ArrayList<>();
            tweetList.add(newTweet);
            usersTweet.put(userId,tweetList);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        minPriorityQueue = new MinPriorityQueue<>(10);
        List<Integer> result = new ArrayList<>();
        List<Integer> followeeList = new ArrayList<>();
        if(followUsers.containsKey(userId)) {
            followeeList = new ArrayList<>(followUsers.get(userId));
        }
        List<Tweet> tweetIds = new ArrayList<>();
        followeeList.add(userId);
        for (Integer everyUser: followeeList) {
            if(usersTweet.containsKey(everyUser)) {
                for(Tweet tweets : usersTweet.get(everyUser)) {
                    tweetIds.add(tweets);
                }
            }
        }
        int count = 0;
        for(Tweet tweet : tweetIds) {
            if(count<=9) {
                minPriorityQueue.insert(tweet);
            }
            count++;
        }

        for(int i=10;i<tweetIds.size();i++) {
            if(minPriorityQueue.getMinimumElement().id<tweetIds.get(i).id) {
                minPriorityQueue.replaceRoot(tweetIds.get(i));
            }
        }

        Stack<Integer> stack = new Stack<>();

        Iterator<Tweet> itr = minPriorityQueue.iterator();

        while (itr.hasNext()) {
            stack.push(itr.next().tweet);
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }

        return result;

    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId==followerId) {
            return;
        }
        if(followUsers.containsKey(followerId)) {
            Set<Integer> foloweeList = followUsers.get(followerId);
            if(!foloweeList.contains(followeeId)) {
                foloweeList.add(followeeId);
                followUsers.put(followerId,foloweeList);
            }
        } else {
            Set<Integer> foloweeList = new HashSet<>();
            foloweeList.add(followeeId);
            followUsers.put(followerId,foloweeList);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followUsers.containsKey(followerId)) {
            Set<Integer> foloweeList = followUsers.get(followerId);
            foloweeList.remove(followeeId);
        }
    }

    public static void main(String[] args) {

        Twitter twitter = new Twitter();
        twitter.postTweet(2,5);
        twitter.postTweet(2,3);
        System.out.println(twitter.getNewsFeed(2));
        twitter.follow(2,1);
        System.out.println(twitter.getNewsFeed(2));

    }

}
