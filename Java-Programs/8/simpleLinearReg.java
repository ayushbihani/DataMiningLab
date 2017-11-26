import java.io.*;
import java.util.*;

class simpleLinearReg
{
    static double M;
    static double C;
    simpleLinearReg(){
        M=-1000;
        C=-1000;
    }
    public static double covariance(double[] x, double[] y)
    {
        double xmean = mean(x);
        double ymean = mean(y);
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += (x[i] - xmean) * (y[i] - ymean);
        }
        return result/x.length;
    }
     public static double mean(double[] data)
    {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return sum / data.length;
    }
    public static double variance(double[] data)
    {
        double mean = mean(data);
        double result = 0;
        for (int i = 0; i < data.length; i++) {
            result += Math.pow(data[i] - mean, 2);
        }
        return result / (data.length);
 }

    public static void calcCoeffeicients(double[] x, double[] y)
    {
        //Y=MX+C
        M = covariance(x,y)/variance(x);
        C = mean(y)-M*mean(x);
    } 
    public static double predict(double x)
    {
        return (x*M+C);
    }

    public static void main(String[] args)throws IOException
    {
        double[] x = { 2, 3, 4, 5, 6, 8, 10, 11 };
        double[] y = { 5,7,9,11,13,17,21,23};
        //Equation is Y = 2X+1
        simpleLinearReg linear = new simpleLinearReg();
        linear.calcCoeffeicients(x,y);
        System.out.println(linear.predict(20));
    }   
}