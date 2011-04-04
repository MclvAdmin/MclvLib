/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mclv.mclvlib.math;
import org.mclv.mclvlib.exception.*;
/**
 *
 * @author god
 */
public class Matrix {
public double[][] matrix;
public int width;
public int height;
    public Matrix(int width, int height){
        matrix = new double[width][height];
        this.width = width;
        this.height = height;
    }
    public void set(int width, int height, double val){
        
    }
    public double get(int width, int height){
        return matrix[width][height];
    }
    public double[] getRow(int height){
        double[] row = new double[height];
        for(int index = 0; index < height; index++){
            row[index] = matrix[index][height];
        }
        return row;
    }
    public double[] getCol(int width){
        double[] column = new double[width];
        for(int index = 0; index < width; index++){
            column[index] = matrix[width][index];
        }
        return column;
    }
          
    public Matrix inverse() throws AsymmetricalMatrixException{
        if(height != width){
            throw new AsymmetricalMatrixException(width, height);
        }
        
        return new Matrix(0,0);
    }
    public Matrix product(Matrix mult) throws ImpossibleProductException{
        //Future: Check if heightA = heightB and throw exception if unequal
        if(height != mult.height){
            throw new ImpossibleProductException(height, mult.height);
        }
        Matrix product = new Matrix(mult.width, height);
        int curWidth = 0;
        for(int heightA = 0; heightA < height; heightA++){
            for(int widthA = 0; widthA < width; widthA++){
                if(heightA < mult.width){
                product.matrix[curWidth][heightA] = matrix[widthA][heightA]*mult.matrix[heightA][widthA] + product.matrix[curWidth][heightA];    
                }
            }
            if(curWidth != mult.width - 1){
            curWidth++;
            }
        }
        return product;
    }
}
