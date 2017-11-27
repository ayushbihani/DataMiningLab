import java.io.*;
import java.util.*;

class confusionmatrix
{
    static double[][] matrix;
    static double res;
    static double n=0;
    confusionmatrix()
    {
        matrix = new double[2][2];
    }
    public static void main(String[] args)throws IOException
    {
        confusionmatrix confusion = new confusionmatrix();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter confusion matrixr");
        for(int i=0;i<2;i++)
        {
	        for(int j=0;j<2;j++)
	        {
                double val = sc.nextDouble();
                matrix[i][j]=val;
                n=n+val;
	        }
        }

    res = matrix[0][0]*100/(matrix[0][0]+matrix[0][1]);
	System.out.println("Sensitivity :"+res+"%");
	res = matrix[1][1]*100/(matrix[1][0]+matrix[1][1]);
	System.out.println("Specificity :"+res+"%");
	res = matrix[0][0]*100/(matrix[0][0]+matrix[0][1]);
	System.out.println("recall :"+res+"%");
	res = matrix[0][0]*100/(matrix[0][0]+matrix[1][0]);
	System.out.println("Precision :"+res+"%");
	res = (matrix[0][0]+matrix[1][1])*100/n;
	System.out.println("Weighted Accuracy :"+res+"%");
    }
}