/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.math;

/**
 *
 * @author god
 */
public class Point {
public double x;
public double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public boolean equals(Point p){
        boolean equal = false;
        if(p.x == x && p.y == y){
            equal = true;
        }
        return equal;
    }
}
