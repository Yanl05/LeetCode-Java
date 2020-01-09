/**
 * @author yanl
 * @date 2020-01-09 8:50 上午
 */
public class OneHundredTwentyFour {
    private int maxValue = Integer.MIN_VALUE;
    public int max_gain(TreeNode node){
        if(node==null)return 0;

        // max sum on the left and right sub-trees of node
        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);
        // the price to start a new path where node is a highest node
        int price_newpath = node.val + left_gain + right_gain;
        // update max_sum if it's better to start a bew path
        maxValue = Math.max(maxValue, price_newpath);
        return node.val+Math.max(left_gain, right_gain);
    }
    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return maxValue;
    }
    public static void main(String[] args) {
        String[] nums = {"-10","9","20",null,null,"15","7"};
        CreatTree creatTree = new CreatTree();
        TreeNode root = creatTree.newTree(nums);
        OneHundredTwentyFour oneHundredTwentyFour = new OneHundredTwentyFour();
        int i = oneHundredTwentyFour.maxPathSum(root);
        System.out.println(i);
    }
}
