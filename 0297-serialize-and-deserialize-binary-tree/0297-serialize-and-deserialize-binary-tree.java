/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Serialize
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {

        if (node == null) {
            sb.append("N,");
            return;
        }

        sb.append(node.val).append(",");

        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    // Deserialize
    int index = 0;

    public TreeNode deserialize(String data) {

        String[] arr = data.split(",");
        index = 0;

        return build(arr);
    }

    private TreeNode build(String[] arr) {

        if (arr[index].equals("N")) {
            index++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[index++]));

        node.left = build(arr);
        node.right = build(arr);

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));