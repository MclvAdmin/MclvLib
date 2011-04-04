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
public class Calc {
    public static Double dYdX(DataSet points, int polynomialDegree){
        Double derivative = null;
        points.freeze();
        double[] function = PolyReg.coefficients(points.dataPoints, polynomialDegree);
        return derivative;
    }
    public static Vector localMax(){ //This vector holds exclusively Points.
        //Needs matrix and grobner basis/linear solver
        Vector points = new Vector(0);
        return points;
    }
    public static Vector localMin(){
       //Needs matrix and grobner basis/linear solver 
       Vector points = new Vector(0);
       return points;
    }
    public static Double defIntegral(DataSet points, int polynomialDegree, double a, double b){
        Double integral = null;
        points.freeze();
        double[] function = PolyReg.coefficients(points.dataPoints, polynomialDegree);
        return integral;
    }
}
