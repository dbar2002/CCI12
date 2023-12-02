// 4.9
// BST Sequences: A binary search tree was created by traversing through an array from left to right
//and inserting each element. Given a binary search tree with distinct elements, print all possible
//arrays that could have led to this tree.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BSTSequence {
    public static List<List<Integer>> allBSTSequences(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            result.add(new ArrayList<>());
            return result;
        }

        List<Integer> prefix = new ArrayList<>();
        prefix.add(root.value);

        List<List<Integer>> leftSequences = allBSTSequences(root.left);
        List<List<Integer>> rightSequences = allBSTSequences(root.right);

        for (List<Integer> left : leftSequences) {
            for (List<Integer> right : rightSequences) {
                List<List<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveLists(List<Integer> first, List<Integer> second,
                                   List<List<Integer>> results, List<Integer> prefix) {
        if (first.isEmpty() || second.isEmpty()) {
            List<Integer> result = new ArrayList<>(prefix);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.remove(0);
        prefix.add(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.remove(prefix.size() - 1);
        first.add(0, headFirst);

        int headSecond = second.remove(0);
        prefix.add(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.remove(prefix.size() - 1);
        second.add(0, headSecond);
    }

    public static void main(String[] args) {
        // Example usage:
        // Constructing a sample binary search tree
        //        2
        //       / \
        //      1   3
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        List<List<Integer>> sequences = allBSTSequences(root);

        // Print all possible arrays
        for (List<Integer> sequence : sequences) {
            System.out.println(sequence);
        }
    }
}
