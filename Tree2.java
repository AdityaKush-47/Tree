import java.util.*;
import java.util.LinkedList;

public class Tree2 {
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

    // Level Order Traversal
    public static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(curr);
                }
            } else {
                System.out.println(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    public static void KLevel(Node root, int k, int level) {
        if (root == null) {
            return;
        }
        if (level == k) {
            System.out.print(root.data + " ");
            return;
        }
        KLevel(root.left, k, level + 1);
        KLevel(root.right, k, level + 1);
    }

    public static Node lca(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }

        Node lca = path1.get(i - 1);
        return lca;

    }

    public static boolean getPath(Node root, int n, ArrayList<Node> path) {

        if (root == null) {
            return false;
        }

        path.add(root);

        if (root.data == n) {
            return true;
        }

        boolean foundLeft = getPath(root.left, n, path);
        boolean foundRight = getPath(root.right, n, path);

        if (foundLeft || foundRight) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static Node lca2(Node root, int n1, int n2) {
        if (root == null) {
            return root;
        }
        if (root.data == n1 || root.data == n2) {
            return root;
        }
        Node leftLca = lca2(root.left, n1, n2);
        Node rightLca = lca2(root.right, n1, n2);

        if (leftLca == null) {
            return rightLca;
        }
        if (rightLca == null) {
            return leftLca;
        }
        return root;
    }

    // Minimum Distance
    public static int minDistance(Node root, int n1, int n2) {
        Node lca = lca2(root, n1, n2);
        int dist1 = getDist(lca, n1);
        int dist2 = getDist(lca, n2);

        return dist1 + dist2;
    }

    public static int getDist(Node root, int n) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }
        int leftDist = getDist(root.left, n);
        int rightDist = getDist(root.right, n);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        } else if (leftDist == -1) {
            return rightDist + 1;
        } else {
            return leftDist + 1;
        }
    }

    // Kth Ancestor
    public static int kthAncestor(Node root, int n, int k) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }
        int leftDist = kthAncestor(root.left, n, k);
        int rightDist = kthAncestor(root.right, n, k);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        }
        int max = Math.max(leftDist, rightDist);
        if (max + 1 == k) {
            System.out.println(root.data);
        }
        return max + 1;
    }

    // Sum Tree
    public static int sumTree(Node root) {
        if (root == null) {
            return 0;
        }

        int leftSum = sumTree(root.left);
        int rightSum = sumTree(root.right);
        int data = root.data;

        int newLeft = root.left == null ? 0 : root.left.data;
        int newRight = root.right == null ? 0 : root.right.data;

        root.data = leftSum + rightSum + newLeft + newRight;
        return data;

    }
    public static void preOrder(Node root) {
        if(root == null) {
            return;
        }

        System.out.print(root.data +" ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // int k = 2;
        // KLevel(root, k, 1);
        // System.out.println();
        int n1 = 4, n2 = 7;
        // System.out.println("LCA is : " + lca2(root, n1, n2).data);
        System.out.println(minDistance(root, n1, n2));
        kthAncestor(root, 5, 2);
        sumTree(root);
        preOrder(root);

    }
}
