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

    @Test
    public void testGetQuadrantForPointFirstQuad() {
        RotateViewDemoActivity activity = new RotateViewDemoActivity();
        int position[] = new int[]{15, 5};
        int pivot[] = new int[]{10, 10};
        Assert.assertEquals(1, activity.getQuadrantForPoint(position, pivot));
    }

    @Test
    public void testGetQuadrantForPointFirstQuad2() {
        RotateViewDemoActivity activity = new RotateViewDemoActivity();
        int position[] = new int[]{10, 5};
        int pivot[] = new int[]{10, 10};
        Assert.assertEquals(1, activity.getQuadrantForPoint(position, pivot));
    }

    @Test
    public void testGetQuadrantForPointSecondQuad() {
        RotateViewDemoActivity activity = new RotateViewDemoActivity();
        int position[] = new int[]{5, 5};
        int pivot[] = new int[]{10, 10};
        Assert.assertEquals(2, activity.getQuadrantForPoint(position, pivot));
    }

    @Test
    public void testGetQuadrantForPointThirdQuad() {
        RotateViewDemoActivity activity = new RotateViewDemoActivity();
        int position[] = new int[]{9, 15};
        int pivot[] = new int[]{10, 10};
        Assert.assertEquals(3, activity.getQuadrantForPoint(position, pivot));
    }

    @Test
    public void testGetQuadrantForPointForthQuad() {
        RotateViewDemoActivity activity = new RotateViewDemoActivity();
        int position[] = new int[]{15, 15};
        int pivot[] = new int[]{10, 10};
        Assert.assertEquals(4, activity.getQuadrantForPoint(position, pivot));
    }
}
