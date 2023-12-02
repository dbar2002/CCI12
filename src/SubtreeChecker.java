// 4.10
// Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
//algorithm to determine if T2 is a subtree of Tl.
//A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
//That is, if you cut off the tree at node n, the two trees would be identical

public class SubtreeChecker {
    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        }

        if (isIdentical(t1, t2)) {
            return true;
        }

        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private static boolean isIdentical(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return (p.value == q.value) && isIdentical(p.left, q.left) && isIdentical(p.right, q.right);
    }

    public static void main(String[] args) {
        // Example usage:
        // Constructing T1
        //         3
        //        / \
        //       4   5
        //      / \
        //     1   2
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(4);
        t1.right = new TreeNode(5);
        t1.left.left = new TreeNode(1);
        t1.left.right = new TreeNode(2);

        // Constructing T2
        //       4
        //      / \
        //     1   2
        TreeNode t2 = new TreeNode(4);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(2);

        // Check if T2 is a subtree of T1
        if (isSubtree(t1, t2)) {
            System.out.println("T2 is a subtree of T1");
        } else {
            System.out.println("T2 is not a subtree of T1");
        }
    }
}
