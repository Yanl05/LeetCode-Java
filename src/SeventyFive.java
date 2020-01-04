import java.util.Arrays;

/**
 * @author yanl
 * @date 2020-01-04 3:18 下午
 */
public class SeventyFive {
    public void sortColors(int[] nums) {
//        方法一：使用计数法的两趟扫描算法
//        int countOne = 0;
//        int countZero = 0;
//        int countTwo = 0;
//
//        for (int tmp : nums) {
//            if(tmp == 0){
//                countZero += 1;
//            }
//            if(tmp == 1){
//                countOne += 1;
//            }
//            if (tmp == 2){
//                countTwo += 1;
//            }
//        }
//        for(int i = 0; i < nums.length; i++){
//            if(countZero != 0){
//                nums[i] = 0;
//                countZero -= 1;
//                continue;
//            }
//            if(countOne != 0){
//                nums[i] = 1;
//                countOne -=1;
//                continue;
//            }
//            if(countTwo != 0){
//                nums[i] = 2;
//                countTwo-=1;
//            }
//        }

        //  方法二： 仅使用常数空间的一趟扫描算法
        // 对于所有idx < i;  nums[idx<i]=0
        // j is index of current element
        int p0 = 0, curr = 0;
        // 对于所有idx>k： nums[idx>k]=2
        int p2 = nums.length - 1;

        int tmp;
        while (curr<p2){
            if (nums[curr] == 0){
                // switch the value of p0 and curr
                tmp = nums[p0];
                nums[p0] = nums[curr];
                nums[curr] = tmp;
                p0++;
                curr++;
            }else if(nums[curr] == 2){
                // switch the value of p2 and curr
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2] = tmp;
                p2--;
            }else{
                curr++;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SeventyFive seventyFive = new SeventyFive();
        seventyFive.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
