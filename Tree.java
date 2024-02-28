import java.util.*;
import java.util.LinkedList;

public class Tree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }
    }

    // PreOrder
    public static void printPreorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // InOrder
    public static void printInorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    // PostOrder
    public static void printPostorder(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    // LevelOrder
    public static void levelOrder(Node root) {
        if (root == null) {
            return;
        }
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
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    // Height Of Tree
    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    // Count Of Node Of a Tree
    public static int count(Node root) {
        if (root == null) {
            return 0;
        }
        int leftCount = count(root.left);
        int rightCount = count(root.right);

        return leftCount + rightCount + 1;
    }

    // Sum Of Nodes
    public static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int ls = sumOfNodes(root.left);
        int rs = sumOfNodes(root.right);
        return ls + rs + root.data;
    }

    // Diameter of Tree
    public static int diameterOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftD = diameterOfTree(root.left);
        int rightD = diameterOfTree(root.right);
        int leftH = height(root.left);
        int rightH = height(root.right);
        int selfD = leftH + rightH + 1;

        return Math.max(selfD, Math.max(rightD, leftD));
    }

    // Using Optimized Approach
    static class Info {
        int ht;
        int diam;

        public Info(int diam, int ht) {
            this.diam = diam;
            this.ht = ht;
        }
    }

    public static Info diameter(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = diameter(root.left);
        Info rightInfo = diameter(root.right);

        int diam = Math.max(leftInfo.ht + rightInfo.ht + 1, Math.max(leftInfo.diam, rightInfo.diam));
        int ht = Math.max(leftInfo.ht, rightInfo.ht) + 1;
        return new Info(diam, ht);

    }

    // Check For SubTree
    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }
        if (root.data == subRoot.data) {
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isIdentical(Node node, Node subRoot) {
        if (node == null && subRoot == null) {
            return true;
        } else if (node == null || subRoot == null || node.data != subRoot.data) {
            return false;
        }
        if (!isIdentical(node.left, subRoot.left)) {
            return false;
        }
        if (!isIdentical(node.right, subRoot.right)) {
            return false;
        }
        return true;
    }
    //Top View
    static class Info1 {
        Node node;
        int hd;

        public Info1(Node root, int hd) {
            this.node = root;
            this.hd = hd;
        }
    }

    public static void topView(Node root) {
        Deque<Info1> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int min = 0, max = 0;
        q.add(new Info1(root, 0));
        q.add(null);

        while (!q.isEmpty()) {
            Info1 curr = q.remove();
            if (curr == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                if (!map.containsKey(curr.hd)) {
                    map.put(curr.hd, curr.node.data);
                }

                if (curr.node.left != null) {
                    q.add(new Info1(curr.node.left, curr.hd - 1));
                    min = Math.min(min, curr.hd - 1);
                }

                if (curr.node.right != null) {
                    q.add(new Info1(curr.node.right, curr.hd + 1));
                    max = Math.max(max, curr.hd + 1);
                }
            }

        }
        for (int i = min; i <= max; i++) {
            System.out.print(map.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        // System.out.println(height(root));
        // System.out.println(count(root));
        // System.out.println("Sum is : "+sumOfNodes(root));

        // System.out.println("Diameter is :"+diameterOfTree(root));
        // System.out.println("Diameter is : " + diameter(root).diam);

        // Node subRoot = new Node(2);
        // subRoot.left = new Node(4);
        // subRoot.right = new Node(5);

        // System.out.println(isSubtree(root, subRoot));

        topView(root);

    }
}
