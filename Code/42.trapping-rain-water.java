// Category: algorithms
// Level: Hard
// Percent: 65.94223%



// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
// 
//  
// Example 1:
// 
// Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
// 
// 
// Example 2:
// 
// Input: height = [4,2,0,3,2,5]
// Output: 9
// 
// 
//  
// Constraints:
// 
// 
// 	n == height.length
// 	1 <= n <= 2 * 10⁴
// 	0 <= height[i] <= 10⁵
// 
 

class Solution {
    public int trap(int[] height) {
        int leftp = 0;
        int rightp = height.length - 1;

        int max_left = 0;
        int max_right = 0;

        int net_water = 0;

        while (rightp >= leftp) {
            if (max_right > max_left) {
                // Water is dependent on the left pointer


                if (height[leftp] > max_left) {
                    max_left = height[leftp];
                } else {
                    net_water += max_left - height[leftp];
                }
                leftp++;
            } else {
                if (height[rightp] > max_right) {
                    max_right = height[rightp];
                } else {
                    net_water += max_right - height[rightp];
                }
                rightp--;
            }
        }

        return net_water;
    }
}
