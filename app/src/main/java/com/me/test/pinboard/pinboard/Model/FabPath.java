package com.me.test.pinboard.pinboard.Model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ayham on 8/7/16.
 */
public class FabPath {

    ArrayList<PathPoint> points = new ArrayList<>();

    public void moveTo(float x, float y)
    {
        this.points.add(PathPoint.moveTo(x, y));
    }

    public void lineTo(float x, float y) {
        this.points.add(PathPoint.lineTo(x, y));
    }

    public void curveTo(float control0X, float control0Y, float control1X, float control1Y, float x, float y) {
        this.points.add(PathPoint.curveTo(control0X, control0Y, control1X, control1Y, x, y));
    }

    public Collection<PathPoint> getPoints() {
        return this.points;
    }
}
