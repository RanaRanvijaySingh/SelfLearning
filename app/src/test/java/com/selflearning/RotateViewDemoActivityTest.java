package com.selflearning;

import com.selflearning.rotateviewdemo.RotateViewDemoActivity;

import org.junit.Assert;
import org.junit.Test;

public class RotateViewDemoActivityTest {
    @Test
    public void getAngleTest() {
        RotateViewDemoActivity rotateViewDemoActivity = new RotateViewDemoActivity();
        Assert.assertEquals(45, rotateViewDemoActivity
                .getAngle(new int[]{5, 5}, new int[]{0, 0}), 0);
    }

    @Test
    public void getAngleTest2() {
        RotateViewDemoActivity rotateViewDemoActivity = new RotateViewDemoActivity();
        Assert.assertEquals(0, rotateViewDemoActivity
                .getAngle(new int[]{0, 5}, new int[]{0, 0}), 0);
    }
}
