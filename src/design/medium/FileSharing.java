/*
We will use a file-sharing system to share a very large file which consists of m small chunks with IDs from 1 to m.

When users join the system, the system should assign a unique ID to them. The unique ID should be used once for each user,
but when a user leaves the system, the ID can be reused again.

Users can request a certain chunk of the file, the system should return a list of IDs of all the users who have this chunk.
After that, if at least one other has this chunk, the user who requested the chunk will get it.


Implement the FileSharing class:

FileSharing(int m) Initializes the object with the number of the chunks of the file m.
int join(int[] ownedChunks): A new user joined the system owning some chunks of the file, the system should assign an id
to the user which is the smallest positive integer not taken by any other user. Return the assigned id.
void leave(int userID): The user with userID will leave the system, you cannot take file chunks from them anymore.
int[] request(int userID, int chunkID): The user with userID requested the file chunk with chunkID. Return a list of the
IDs of all users that own this chunk sorted in ascending order.


Follow-ups:

What happens if the system identifies the user by their IP address instead of their unique ID and users disconnect and
connect from the system with the same IP?
If the users in the system join and leave the system frequently without requesting any chunks, will your solution still be efficient?
If all each user join the system one time, request all files and then leave, will your solution still be efficient?
If the system will be used to share n files where the ith file consists of m[i], what are the changes you have to do?


Example:

Input:
["FileSharing","join","join","join","request","request","leave","request","leave","join"]
[[4],[[1,2]],[[2,3]],[[4]],[1,3],[2,2],[1],[2,1],[2],[[]]]
Output:
[null,1,2,3,[2],[1,2],null,[],null,1]
Explanation:
FileSharing fileSharing = new FileSharing(4); // We use the system to share a file of 4 chunks.
fileSharing.join([1, 2]);    // A user who has chunks [1,2] joined the system, assign id = 1 to them and return 1.
fileSharing.join([2, 3]);    // A user who has chunks [2,3] joined the system, assign id = 2 to them and return 2.
fileSharing.join([4]);       // A user who has chunk [4] joined the system, assign id = 3 to them and return 3.
fileSharing.request(1, 3);   // The user with id = 1 requested the third file chunk, as only the user with id = 2 has the file, return [2] .
 Notice that user 1 now has chunks [1,2,3].
fileSharing.request(2, 2);   // The user with id = 2 requested the second file chunk, users with ids [1,2] have this chunk,
thus we return [1,2]. We don't care if the user has the file and request it, we just return all the users that can give them the file.
fileSharing.leave(1);        // The user with id = 1 left the system, all the file chunks with them are no longer available for other users.
fileSharing.request(2, 1);   // The user with id = 2 requested the first file chunk, no one in the system has this chunk, we return
empty list [].
fileSharing.leave(2);        // The user with id = 2 left the system, all the file chunks with them are no longer available for other users.
fileSharing.join([]);        // A user who doesn't have any chunks joined the system, assign id = 1 to them and return 1.
Notice that ids 1 and 2 are free and we can reuse them.


Constraints:

1 <= m <= 10^5
0 <= ownedChunks.length <= min(100, m)
1 <= ownedChunks[i] <= m
Values of ownedChunks are unique.
1 <= chunkID <= m
userID is guaranteed to be a user in the system if you assign the IDs correctly.
At most 10^4 calls will be made to join, leave and request.
Each call to leave will have a matching call for join.
 */
package design.medium;

import java.util.*;

public class FileSharing {
    Map< Integer, Set< Integer >> map; // User id, a list of blocks owned by the user
    int uid = 1 ; // increase user id
    PriorityQueue< Integer > leavedIds; // List of reusable user ids
    public FileSharing(int m) {
        map=new TreeMap<>();
        leavedIds=new PriorityQueue<>();
    }
    public int join(List<Integer> ownedChunks) {
        int userID=uid; // use self-incremented user id by default
        // If the reuse list is not empty, the smallest one in the taking list will be used first
        if(leavedIds.size()>0) userID=leavedIds.poll();
            // Otherwise, use auto-increment id, add one after use
        else uid++;
        // Save the current user and the list of blocks he owns into the map
        map.put(userID, new HashSet<>(ownedChunks));
        return userID;
    }
    public void leave(int userID) {
        map. remove ( userID ) ; // delete the user information from the map
        leavedIds. offer ( userID ) ; // add the user id to the queue list of reusable id
    }
    public List<Integer> request(int userID, int chunkID) {
        List < Integer > res = new ArrayList <>() ; // return result
        for ( int user : map. keySet ()){ // loop the key (user id) in the map
            // If the current user owns the block, add the user to the returned result
            if(map.get(user).contains(chunkID)) res.add(user);
        }
        // If the number of elements in the returned result is greater than 0,
        // Explain that the user userID can get the chunkID
        if(res.size()>0){
            // Add chunkID to the user's block list of userID
            Set<Integer> set=map.get(userID);
            set.add(chunkID);
            map.put(userID,set);
        }
        return res;
    }


}

/*
 The main purpose of the question is to allow us to design a reasonable and efficient data storage method.
 This question actually has two complications:

Ensure that the id of the new user must be the smallest among the unused id.
How to count the list of users who currently own each block and sort them.
For the first question, the difficulty mainly occurs when the user logs out of the system, the id that the user
has used will be re-opened to others. Without this condition, the problem will become much simpler, as long as the
user id has been added up. can. But this problem is not complicated. We define an auto-incremented id to be used as
the default id for new users. In addition, a set is used to store the id list list that is restored to the available
state after the user exits the system. When a user enters the system, if there is no element in the list, the default
self-increment id is used. If there are elements in the list, then take the smallest use from the list. Here we can use
PriorityQueue to store the list. Since PriorityQueue automatically has a sorting attribute, the id retrieved each time must
be the smallest one in the queue.

The second question is mainly about how to store the relationship between users and their own blocks in the current system.
This problem is not difficult, just use a Map. The key of the map is the user id, and the value is the block list he currently owns.
We use Set to store the list. There are two reasons for using Set. One is to remove duplicates. Second, we can easily determine
whether it contains a certain element (contains) through the Set class. The time complexity of this judgment is O(1).
(Note that the time complexity of the contains method of List is O(n).) In addition, because the title requires user id
to be sorted, we can use TreeMap here. TreeMap is similar to PriorityQueue, with its own sorting attribute.
By default, the map is The keys are sorted in ascending order.

Next, let's take a look at the implementation of each method:

FileSharing(int m): There is no need to do too much work in the constructor, just initialize the PriorityQueue and TreeMap mentioned above.

int join(int[] ownedChunks): First check whether there is an id that can be reused in PriorityQueue. If the number of
elements in the queue is greater than 0, poll will produce the smallest id as the current user's id. On the contrary,
if there are no elements in the queue, then we will use the current auto-increment id as the current user id, and the
auto-increment id will be incremented by one. After obtaining the user id, we use the user id as the key and the block
list owned by the user as the value, and store it in the TreeMap.

void leave(int userID): After the user logs out of the system, we use the user ID to clear the corresponding data in the TreeMap.
At the same time, this id is added to the PriorityQueue, waiting to be reused.

int[] request(int userID, int chunkID): first loop through each key (user id) in the TreeMap to check whether each user’s
block list contains the target block id, if it does, we will add the current user id Back to list. After the loop is over,
since the keys are automatically sorted, the elements added to the list are also sorted. In addition, if the list is not empty,
it means that the current user can get the block, and we need to add the block to the user's block list.
 */