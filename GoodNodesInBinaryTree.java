public class GoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        return goodNodes(root, root.val);
    }

    private int goodNodes(TreeNode n, int max) {
        if (n == null) {
            return 0;
        }

        int goodCount = 0;

        if (n.val >= max) {
            goodCount = 1;
            max = n.val;
        }

        return goodCount + goodNodes(n.left, max) + goodNodes(n.right, max);
    }
}
