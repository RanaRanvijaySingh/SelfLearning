package com.selflearning;

import com.selflearning.imagerotation.RotateImageDemoActivity;

import org.junit.Assert;
import org.junit.Test;

public class TestRotateImageDemo {

    @Test
    public void testGetAngleForValidData() {
        RotateImageDemoActivity rotateImageDemoActivity = new RotateImageDemoActivity();
        Assert.assertEquals(45, rotateImageDemoActivity.getAngle(2, 2), 0);
    }

    @Test
    public void testGetAngleForValidData2() {
        RotateImageDemoActivity rotateImageDemoActivity = new RotateImageDemoActivity();
        Assert.assertEquals(56, rotateImageDemoActivity.getAngle(2, 3), 0);
    }

    @Test
    public void testGetAngleForValidData3() {
        RotateImageDemoActivity rotateImageDemoActivity = new RotateImageDemoActivity();
        Assert.assertEquals(90, rotateImageDemoActivity.getAngle(0, 3), 0);
    }
}
