package com.me.test.pinboard.pinboard.Model;

import android.graphics.Path;

/**
 * Created by ayham on 8/7/16.
 */
public class PathPoint {

    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int CURVE = 2;

    private float x;
    private float y;

    private float controlX;
    private float controlY;

    private float control1X;
    private float control1Y;

    private int operation;

    public float getControlX() {
        return controlX;
    }

    public float getControlY() {
        return controlY;
    }

    public float getControl1X() {
        return control1X;
    }

    public float getControl1Y() {
        return control1Y;
    }

    public float getX() {

        return x;
    }

    public float getY() {
        return y;
    }

    public int getOperation() {
        return operation;
    }

    private PathPoint(int operation, float x, float y)
    {
        this.operation = operation;
        this.x = x;
        this.y = y;
    }

    private PathPoint(float control0X, float control0Y, float control1X, float control1Y, float x, float y)
    {
        this.controlX = control0X;
        this.controlY = control0Y;
        this.control1X = control1X;
        this.control1Y = control1Y;
        this.x = x;
        this.y = y;
        this.operation = CURVE;
    }

    public static PathPoint lineTo(float x, float y)
    {
        return new PathPoint(LINE, x, y);
    }

    public static PathPoint curveTo(float control0X, float control0Y, float control1X, float control1Y, float x, float y)
    {
        return new PathPoint(control0X ,control0Y ,control1X ,control1Y ,x ,y);
    }

    public static PathPoint moveTo(float x, float y)
    {
        return new PathPoint(MOVE, x, y);
    }
}
