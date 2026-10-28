
public class BinarySearchTree<Type extends Comparable<Type>> {

    // Represents one node in the binary search tree
    private class Node {
        private Type data;
        private Node left;
        private Node right;

        public Node(Type data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // Root of the binary search tree
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Recursively prints the tree in ascending order
    private void inorderPrint(Node node) {
        if (node != null) {
            inorderPrint(node.left);
            System.out.print(node.data + " ");
            inorderPrint(node.right);
        }
    }

    public void print() {
        inorderPrint(root);
        System.out.println();
    }

    // Inserts a value into the appropriate subtree
    private Node insertAt(Type value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (node.data.compareTo(value) < 0) {
            node.right = insertAt(value, node.right);
        } else {
            node.left = insertAt(value, node.left);
        }

        return node;
    }

    public void insert(Type value) {
        if (value != null) {
            root = insertAt(value, root);
        }
    }

    // Recursively searches for a value
    private Node searchAt(Type target, Node node) {
        if (node == null) {
            return null;
        } else if (node.data.equals(target)) {
            return node;
        } else if (node.data.compareTo(target) < 0) {
            return searchAt(target, node.right);
        } else {
            return searchAt(target, node.left);
        }
    }

    // Returns the value if found, otherwise returns null
    public Type search(Type target) {
        if (target == null) {
            return null;
        }

        Node node = searchAt(target, root);

        if (node == null) {
            return null;
        }

        return node.data;
    }

    // Finds the smallest value in a subtree
    private Node min(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    // Recursively removes a value from the tree
    private Node removeAt(Type value, Node node) {
        if (node == null) {
            return null;
        }

        if (node.data.compareTo(value) > 0) {
            node.left = removeAt(value, node.left);
        } else if (node.data.compareTo(value) < 0) {
            node.right = removeAt(value, node.right);
        } else {
            // Node has no children
            if (node.left == null && node.right == null) {
                return null;
            }

            // Node has only one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // Node has two children
            Node swap = min(node.right);
            node.data = swap.data;
            node.right = removeAt(swap.data, node.right);
        }

        return node;
    }

    public void remove(Type value) {
        if (value != null) {
            root = removeAt(value, root);
        }
    }

    // Returns the height of tree
    public int height() {
        return calculateHeight(root);
    }

    // Recursively calculates the longest path to a leaf
    private int calculateHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}