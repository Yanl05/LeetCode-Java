/**
 * @author yanl
 * @date 2020-01-13 11:40 上午
 *
 * 维护两个指针，来存储左右两边的最大柱子高度。
 */
public class FourteenTwo {
    public int trap(int[] height) {
        if (height==null || height.length<3) return 0;
        int max = 0;
        int leftmax = 0,rightmax = 0;
        int i = 0, j=height.length-1;
        while (i<j){
            leftmax = Math.max(leftmax,height[i]);
            rightmax = Math.max(rightmax, height[j]);
            if(leftmax<rightmax){
                max += leftmax-height[i];
                i++;
            }else{
                max += rightmax-height[j];
                j--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new FourteenTwo().trap(nums));
    }
}
