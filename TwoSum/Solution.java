package TwoSum;

public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length; j++){
                if(i == j){
                    continue;
                }
                else{
                    if((nums[i] + nums[j]) == target){
                        result[0] = j;
                        result[1] = i;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(twoSum({1,2,3,4},5));
    }
}


