/*

Equations are given in the format A / B = k, where A and B are variables represented as strings,
and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].


The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

 */
package bfsdfs.medium;

import java.util.*;

public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] arr = new double[queries.size()];
        Map<String,Map<String,Double>> map = buildGraph(equations,values);
        for (int i = 0; i < queries.size(); i++) {
            arr[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), map, new HashSet<>());
        }
        return arr;
    }

    private double dfs(String start, String end, Map<String,Map<String,Double>> map, Set<String> visited) {
        if(!map.containsKey(start)) {
            return -1.0;
        }
        if(map.get(start).containsKey(end)) {
            return map.get(start).get(end);
        }
        visited.add(start);
        for(Map.Entry<String,Double> entry : map.get(start).entrySet()) {
            if(!visited.contains(entry.getKey())) {
                double val = dfs(entry.getKey(),end,map,visited);
                if(val!=-1.0) {
                    return entry.getValue() * val;
                }
            }
        }
        return -1.0;

    }

    private Map<String,Map<String,Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String,Double>> map = new HashMap<>();
        for(int i=0;i<equations.size();i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            map.putIfAbsent(start,new HashMap<>());
            map.get(start).put(end,values[i]);
            map.putIfAbsent(end,new HashMap<>());
            map.get(end).put(start,1/values[i]);
        }
        return map;
    }
}

/*

Binary relationship is represented as a graph usually.
Does the direction of an edge matters? -- Yes. Take a / b = 2 for example, it indicates a --2--> b as well as b --1/2--> a.
Thus, it is a directed weighted graph.
In this graph, how do we evaluate division?
Take a / b = 2, b / c = 3, a / c = ? for example,

a --2--> b --3--> c
We simply find a path using DFS from node a to node c and multiply the weights of edges passed, i.e. 2 * 3 = 6.

 */