// 4.7
// Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
// projects, where the second project is dependent on the first project). All of a project's dependencies
// must be built before the project is. Find a build order that will allow the projects to be built. If there
// is no valid build order, return an error.

import java.util.*;

public class BuildOrder {

    public static List<String> findBuildOrder(String[] projects, String[][] dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // Build the graph and calculate in-degrees
        for (String project : projects) {
            graph.put(project, new ArrayList<>());
            inDegree.put(project, 0);
        }

        for (String[] dependency : dependencies) {
            String dependent = dependency[0];
            String dependencyOf = dependency[1];
            graph.get(dependent).add(dependencyOf);
            inDegree.put(dependencyOf, inDegree.get(dependencyOf) + 1);
        }

        // Initialize a queue with projects having in-degree 0
        Queue<String> queue = new LinkedList<>();
        for (String project : projects) {
            if (inDegree.get(project) == 0) {
                queue.add(project);
            }
        }

        List<String> result = new ArrayList<>();

        // Perform topological sorting
        while (!queue.isEmpty()) {
            String currentProject = queue.poll();
            result.add(currentProject);

            for (String neighbor : graph.get(currentProject)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If all projects are visited, return the result; otherwise, there is a cycle
        if (result.size() == projects.length) {
            return result;
        } else {
            throw new IllegalArgumentException("No valid build order exists due to dependencies cycle.");
        }
    }

    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] dependencies = {{"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}};

        try {
            List<String> result = findBuildOrder(projects, dependencies);
            System.out.println("Valid build order: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
