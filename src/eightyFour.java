/**
 * @author yanl
 * @date 2020-01-08 8:38 上午
 */
public class eightyFour {
    public int largestRectangleArea(int[] heights) {
        // 方法一：暴力  --- 会超时
//        int maxarea = 0;
//        for(int i=0;i<heights.length;i++){
//            for(int j=i;j<heights.length;j++){
//                int minheight = Integer.MAX_VALUE;
//                for(int k=i;k<=j;k++){
//                    minheight = Math.min(minheight, heights[k]);
//                }
//                maxarea = Math.max(minheight*(j-i+1), maxarea);
//            }
//        }
//        return maxarea;

//        int size = heights.length;
//        if (size==0){
//            return 0;
//        }
//        if(size==1){
//            return heights[0];
//        }
//        int ret = 0;
//        int minNum = Integer.MAX_VALUE;
//        int ans = 0;
//        for(int i=0;i<size;i++){
//            for(int j=i;j<size;j++){
//                minNum = heights[i];
//                for(int tmp=i;tmp<=j;tmp++){
//                    if(minNum>heights[tmp]){
//                        minNum=heights[tmp];
//                    }
//                }
//                ans = minNum*(j-i+1);
//                if (ret<ans){
//                    ret = ans;
//                }
//            }
//        }
//        return ret;
//    }

        // 方法二： 对于方法一的优化
//        int maxarea = 0;
//        for (int i = 0; i < heights.length; i++) {
//            int minheight = Integer.MAX_VALUE;
//            for (int j = i; j < heights.length; j++) {
//                minheight = Math.min(minheight, heights[j]);
//                maxarea = Math.max(minheight * (j - i + 1), maxarea);
//            }
//        }
//
//        return maxarea;
//    }

        // 方法三： 分治法
        //    确定了最矮柱子以后，矩形的宽尽可能往两边延伸。
        //    在最矮柱子左边的最大面积矩形（子问题）。
        //    在最矮柱子右边的最大面积矩形（子问题）。
        return calculateArea(heights, 0, heights.length-1);
    }
    public int calculateArea(int[] heights, int start, int end){
        if(start>end){
            return 0;
        }
        int minindex = start;
        for(int i=start; i<=end; i++){
            if(heights[minindex]>heights[i]){
                minindex=i;
            }
        }
        return Math.max(heights[minindex]*(end-start+1), Math.max(calculateArea(heights,start,minindex-1),calculateArea(heights,minindex+1,end)));
    }

        // 方法四： 优化的分治法
//        使用线段树来查找最小值，以优化代码



    public static void main(String[] args) {
        int[] heights = {4,2};
        eightyFour eightyFour = new eightyFour();
        System.out.println(eightyFour.largestRectangleArea(heights));
    }
}
