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
        int max = -1;
        for (int i = 0; i<height.length; i++) {
            if (height[i] > max) {
                max = height[i];
            }
        }

        int netWater = 0;

        for (int level = 1; level <= max; level++) {
            int i = 0;
            while (i < height.length) {
                if (height[i] >= level) {
                    int water = 0;
                    i++; // Increment to next space
                    while (i < height.length && height[i] < level) {
                        water++;
                        i++;
                    }

                    if (i >= height.length) {
                        break; // Ignore water spaces on the right 
                    }

                    netWater += water;
                } else {
                    i++;
                }
            }
        }

        return netWater;
    }
}
