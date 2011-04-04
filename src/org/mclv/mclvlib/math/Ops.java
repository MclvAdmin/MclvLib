/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.math;
import java.util.Vector;
/**
 *
 * @author god
 */
public class Ops {
    public static double exp(double val, int degree){
        double calc = 1;
        for(int exp = 0; exp<degree; exp++){
            calc = calc*val;
        }
        return calc;
    }
    public static double sumX(Vector points, int degree){
        double calc = 0;
        for(int index = 0; index < points.size(); index++){
            calc = calc + exp(((Point) points.elementAt(index)).x, degree);
        }
        return calc;
    }
    public static double sumY(Vector points, int degree){
        double calc = 0;
        for(int index = 0; index < points.size(); index++){
            calc = calc + exp(((Point) points.elementAt(index)).y, degree);
        }
        return calc;
    }
    public static double sum(Vector points, int xDeg, int yDeg){
        double calcX = 0;
        double calcY = 0;
        double calc = 0;
        for(int index = 0; index < points.size(); index++){
            calcY = exp(((Point) points.elementAt(index)).y, yDeg);
            calcX = exp(((Point) points.elementAt(index)).x, xDeg);
            calc = calcY*calcX;
        }
        return calc;
    }
}
