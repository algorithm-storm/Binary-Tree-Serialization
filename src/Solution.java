import java.util.*;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {

    public static void main(String[] args) {

        String data = "{3,9,20,#,#,15,7}";

        String[] tree = data.substring(1, data.length() - 1).split(",");
        Queue<String> treeQ = new LinkedList<>(Arrays.asList(tree));
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(treeQ.poll()));
        queue.add(root);

        while (!treeQ.isEmpty()) {
            TreeNode node = queue.poll();
            String leftChildVal = treeQ.poll();

            if (!leftChildVal.equals("#")) {
                node.left = new TreeNode(Integer.parseInt(leftChildVal));
                queue.offer(node.left);
            }

            if (treeQ.isEmpty()) break;
            String rightChildVal = treeQ.poll();
            if (!rightChildVal.equals("#")) {
                node.right = new TreeNode(Integer.parseInt(rightChildVal));
                queue.offer(node.right);
            }
        }
        Solution a = new Solution();
        System.out.println(a.deserialize(data).val);
    }

    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {

        if (root == null) {
            return "{}";
        }
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        for (int i = 0; i < nodeList.size(); i++) {

            TreeNode node = nodeList.get(i);
            if (node == null) {
                continue;
            }
            nodeList.add(node.left);
            nodeList.add(node.right);
        }
        while (nodeList.get(nodeList.size() - 1) == null) {
            nodeList.remove(nodeList.size() - 1);
        }
        StringBuilder nodeStr = new StringBuilder();
        nodeStr.append("{" + nodeList.get(0).val);

        for (int i = 1; i < nodeList.size(); i++) {
            if (nodeList.get(i) == null) {
                nodeStr.append(",#");
            } else {
                nodeStr.append("," + nodeList.get(i).val);
            }
        }
        nodeStr.append("}");
        return nodeStr.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {

        if (data == "{}") {
            return null;
        }

        List<String> treeList = new ArrayList<>(Arrays.asList(data.substring(1, data.length() - 1).split(",")));
        List<TreeNode> tree = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(treeList.get(0)));
        tree.add(root);
        treeList.remove(0);

        for (int i = 0; i < treeList.size(); i++) {

            if (!treeList.get(i).equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(treeList.get(i)));
                if (i % 2 == 0) {
                    tree.get(i / 2).left = node;
                }
                else {
                    tree.get(i / 2).right = node;
                }
                tree.add(node);
            }
        }
        return root;
    }
}