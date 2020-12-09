package com.lsf.studymybatis;

import org.junit.Assert;
import org.junit.Test;

public class Study {
    public int maxArea1(int[] height) {
        int result = 0;

        int areaSize = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                areaSize = (j - i) * (height[i] < height[j] ? height[i] : height[j]);
                if (result < areaSize)
                    result = areaSize;
            }
        }
        return result;
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int result = 0;
        while (i < j) {
            int minHeight = Math.min(height[i], height[j]);
            result = Math.max(result, minHeight * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return result;
    }

    @Test
    public void testMaxArea() {
        int[] arrary1 = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result1 = maxArea(arrary1);
        Assert.assertEquals(49, result1);
    }

    public int trap(int[] height) {
        int result = 0;
        int left = 0, right = left + 2;
        while (left < height.length - 1) {
            int minHeight = Math.min(height[left], height[right]);

        }
        return result;
    }

    @Test
    public void testTrap() {
        int[] arrary1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int result1 = trap(arrary1);
        Assert.assertEquals(6, result1);

        int[] arrary2 = new int[]{4, 2, 0, 3, 2, 5};
        int result2 = trap(arrary2);
        Assert.assertEquals(9, result2);
    }
}
