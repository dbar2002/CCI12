//4.12
//  Paths with Sum: You are given a binary tree in which each node contains an integer value (which
//might be positive or negative). Design an algorithm to count the number of paths that sum to a
//given value. The path does not need to start or end at the root or a leaf, but it must go downwards
//(traveling only from parent nodes to child nodes).

class BinaryTreeNode2 {
    int value;
    TreeNode left, right;

    public BinaryTreeNode2(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class PathsWithSum {
    public static int countPathsWithSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        // Count paths starting from the root
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

        // Recursively count paths in the left and right subtrees
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);

        // Combine counts
        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    private static int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return 0;
        }

        currentSum += node.value;

        // Check if the current path contributes to the target sum
        int totalPaths = (currentSum == targetSum) ? 1 : 0;

        // Recursively check paths in the left and right subtrees
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);

        return totalPaths;
    }

    public static void main(String[] args) {
        // Example usage:
        // Constructing a sample binary tree
        //         10
        //        /  \
        //       5   -3
        //      / \    \
        //     3   2   11
        //    / \   \
        //   3  -2   1
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        int targetSum = 8;
        int result = countPathsWithSum(root, targetSum);
        System.out.println("Number of paths with sum " + targetSum + ": " + result);
    }
}
