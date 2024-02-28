public class TreesPractice {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    // Check if Tree is Univalued or not
    public static boolean check(Node root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.data != root.data) {
            return false;
        }
        if (root.right != null && root.right.data != root.data) {
            return false;
        }
        return check(root.left) && check(root.right);

    }

    // Invert Binary tree
    public static boolean checkInvert(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        boolean curr = (root1 != null && root2 != null) && (root1.data == root2.data);
        boolean left = checkInvert(root1.left, root2.right);
        boolean right = checkInvert(root1.right, root2.left);

        return curr && left && right;

    }

    // Delete Leaf Node with value x
    public static Node delete(Node root, int x) {
        if (root == null) {
            return null;
        }
        root.left = delete(root.left, x);
        root.right = delete(root.right, x);

        if (root.left == null && root.right == null && root.data == x) {
            root = null;
        }
        // if(root.left == null && root.right == null && root.data != x) {
        // return root;
        // }
        return root;
    }

    // InOrder Traversal
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        inOrder(root.left);
        inOrder(root.right);

    }

    // Maximum Path Sum
    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(Node root) {
        clacMaxPathSum(root);
        return maxSum;
    }

    public static int clacMaxPathSum(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = Math.max(clacMaxPathSum(root.left), 0);
        int rightSum = Math.max(clacMaxPathSum(root.right), 0);

        maxSum = Math.max(maxSum, leftSum + rightSum + root.data);
        return root.data + Math.max(leftSum, rightSum);
    }

    public static void main(String[] args) {
        // 1st Tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left = new Node(6);
        root.left.right = new Node(5);
        // 2nd Tree
        Node root2 = new Node(1);
        root2.left = new Node(3);
        root2.right = new Node(2);
        root2.right.left = new Node(5);
        root2.right.right = new Node(4);
        root2.left.left = new Node(7);
        root2.left.right = new Node(6);
        // 3rd Tree
        Node root3 = new Node(-10);
        root3.left = new Node(9);
        root3.right = new Node(20);
        root3.right.right = new Node(7);
        root3.right.left = new Node(15);
        System.out.println(check(root));

        System.out.println(checkInvert(root, root2));
        System.out.println("<------------------>");
        inOrder(root);
        delete(root, 6);
        System.out.println();
        inOrder(root);
        System.out.println();
        System.out.println("<------------>");
        System.out.println(maxPathSum(root2));
    }
}
