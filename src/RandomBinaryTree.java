// 4.11
// Random Node: You are implementing a binary tree class from scratch which, in addition to
//insert, find, and delete, has a method getRandomNode() which returns a random node
//from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
//for getRandomNode, and explain how you would implement the rest of the methods.

import java.util.Random;

class BinaryTreeNode {
    int value;
    int size;  // Number of nodes in the subtree rooted at this node
    TreeNode left, right;

    public BinaryTreeNode(int value) {
        this.value = value;
        this.size = 1;
        this.left = null;
        this.right = null;
    }
}

public class RandomBinaryTree {
    private TreeNode root;
    private Random random;

    public RandomBinaryTree() {
        this.root = null;
        this.random = new Random();
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }

        if (value <= node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        node.size++;  // Increment the size of the subtree
        return node;
    }

    public TreeNode find(int value) {
        return find(root, value);
    }

    private TreeNode find(TreeNode node, int value) {
        if (node == null || node.value == value) {
            return node;
        }

        if (value < node.value) {
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }
    }

    public TreeNode getRandomNode() {
        if (root == null) {
            return null;
        }

        int randomIndex = random.nextInt(root.size) + 1;  // Random index between 1 and size

        return getNodeByIndex(root, randomIndex);
    }

    private TreeNode getNodeByIndex(TreeNode node, int index) {
        int leftSize = (node.left != null) ? node.left.size : 0;

        if (index == leftSize + 1) {
            return node;
        } else if (index <= leftSize) {
            return getNodeByIndex(node.left, index);
        } else {
            return getNodeByIndex(node.right, index - leftSize - 1);
        }
    }

    public static void main(String[] args) {
        // Example usage:
        RandomBinaryTree tree = new RandomBinaryTree();
        tree.insert(5);
        tree.insert(2);
        tree.insert(8);
        tree.insert(1);
        tree.insert(3);
        tree.insert(7);
        tree.insert(9);

        TreeNode randomNode = tree.getRandomNode();
        System.out.println("Random Node: " + randomNode.value);
    }
}
