import sun.jvm.hotspot.types.CIntegerField;

import java.util.*;

/**
 * @author yanl
 * @date 2020-01-09 9:04 上午
 */
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
class CreatTree{
    public TreeNode newTree(String[] nums){
        if (nums.length==0){
            return null;
        }
        // 层序遍历
        TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        int j = 1;
        while(nodes.size()!=0){
            TreeNode node = nodes.poll();
            if (node!=null){
                if(nums[j]!=null){
                    node.left = new TreeNode(Integer.parseInt(nums[j]));
                }
                nodes.add(node.left);
                j+=1;
                if (j==nums.length){
                    return root;
                }
                // 右子树
                if(nums[j]!=null){
                    node.right = new TreeNode(Integer.parseInt(nums[j]));
                }
                nodes.add(node.right);
                j+=1;
                if (j==nums.length){
                    return root;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        String[] nums = {"-10","9","20",null,null,"15","7"};
        CreatTree creatTree = new CreatTree();
        TreeNode tree = creatTree.newTree(nums);
        System.out.println(tree.val);
        System.out.println(tree.left.val);
        System.out.println(tree.right.left.left);
    }
}