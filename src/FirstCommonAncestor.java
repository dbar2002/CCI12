// 4.8
// First Common Ancestor: Design an algorithm and write code to find the first common ancestor
//of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
//necessarily a binary search tree.

class TreeNode {
    public int size;
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class FirstCommonAncestor {
    public static TreeNode findCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return null;
        }

        // If either node1 or node2 is the root, then root is the common ancestor
        if (root == node1 || root == node2) {
            return root;
        }

        // Recursively search for the common ancestor in the left and right subtrees
        TreeNode leftAncestor = findCommonAncestor(root.left, node1, node2);
        TreeNode rightAncestor = findCommonAncestor(root.right, node1, node2);

        // If both nodes are found in different subtrees, then the current root is the common ancestor
        if (leftAncestor != null && rightAncestor != null) {
            return root;
        }

        // If one of the nodes is found, return it (it might be the common ancestor further up the tree)
        return (leftAncestor != null) ? leftAncestor : rightAncestor;
    }

    public static void main(String[] args) {
        // Constructing a sample binary tree
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Nodes to find the common ancestor for
        TreeNode node1 = root.left.left;   // Node with value 4
        TreeNode node2 = root.left.right;  // Node with value 5

        TreeNode commonAncestor = findCommonAncestor(root, node1, node2);
        if (commonAncestor != null) {
            System.out.println("The first common ancestor is: " + commonAncestor.value);
        } else {
            System.out.println("No common ancestor found.");
        }
    }
}
