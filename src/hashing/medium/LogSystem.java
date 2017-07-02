/*

You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format:
Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

Design a log storage system to implement the following functions:

void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

Example 1:
put(1, "2017:01:01:23:59:59");
put(2, "2017:01:01:22:59:59");
put(3, "2016:01:01:00:00:00");
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from
2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
Note:
There will be at most 300 operations of Put or Retrieve.
Year ranges from [2000,2017]. Hour ranges from [00,23].
Output for Retrieve has no order required.

 */
package hashing.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 02/07/17.
 */
public class LogSystem {

    Map<String,Integer> map;

    public LogSystem() {
        map = new HashMap<>();
    }

    public void put(int id, String timestamp) {
        map.put(timestamp,id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int end = 0;
        if(gra.equals("Year")) {
            end = 4;
        } else if(gra.equals("Month")) {
            end = 7;
        } else if(gra.equals("Day")) {
            end = 10;
        } else if(gra.equals("Hour")) {
            end = 13;
        } else if(gra.equals("Minute")) {
            end = 16;
        } else {
            end = 19;
        }

        s= s.substring(0,end);
        e = e.substring(0,end);

        List<Integer> result = new ArrayList<>();

        for (String key : map.keySet()) {
            if(key.substring(0,end).compareTo(s)>=0 && key.substring(0,end).compareTo(e)<=0) {
                result.add(map.get(key));
            }
        }

        return result;

    }

}
