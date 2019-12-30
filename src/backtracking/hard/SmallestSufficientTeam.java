/*

In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.
We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].

Return any sufficient team of the smallest possible size, represented by the index of each person.

You may return the answer in any order.  It is guaranteed an answer exists.



Example 1:

Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]
Example 2:

Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],
["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]


Constraints:

1 <= req_skills.length <= 16
1 <= people.length <= 60
1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
Elements of req_skills and people[i] are (respectively) distinct.
req_skills[i][j], people[i][j][k] are lowercase English letters.
It is guaranteed a sufficient team exists.

 */
package backtracking.hard;

import java.util.*;

public class SmallestSufficientTeam {

    int teamSize;
    HashSet<Integer> resTeam;
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        teamSize = people.size()+1;
        resTeam = new HashSet<>();
        HashMap<String,List<Integer>> skillMap = new HashMap<>();
        for(int i=0; i<people.size(); i++){
            for(String skill : people.get(i)){
                if(!skillMap.containsKey(skill)){
                    skillMap.put(skill, new ArrayList<>());
                }
                skillMap.get(skill).add(i);
            }
        }
        find(skillMap, req_skills, 0, new HashSet<>());
        int[] res = new int[resTeam.size()];
        int index = 0;
        for(int person : resTeam){
            res[index++] = person;
        }
        return res;
    }

    public void find(HashMap<String,List<Integer>> skillMap, String[] req_skills, int cur, HashSet<Integer> team){
        if(team.size()>teamSize) return;
        if(cur==req_skills.length){
            if(team.size()<teamSize){
                teamSize = team.size();
                resTeam = new HashSet<>();
                resTeam.addAll(team);
            }
            return;
        }
        for(int person : skillMap.get(req_skills[cur])){
            boolean isRemove = !team.contains(person);
            team.add(person);
            find(skillMap, req_skills, cur+1, team);
            if(isRemove) team.remove(person);
        }
    }


}
