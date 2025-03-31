import java.util.Scanner;

public class StrassonsMatrixMultiplication {

    public static int[][] multiply(int[][] A,int[][] B)
    {
        int n=A.length;
        int[][] result=new int[n][n];
        if(n==1)
        {
            result[0][0]=A[0][0]*B[0][0];
            return result;
        }

        int newSize=n/2;
        int[][] A11=new int[newSize][newSize];
        int[][] A12=new int[newSize][newSize];
        int[][] A21=new int[newSize][newSize];
        int[][] A22=new int[newSize][newSize];


        int[][] B11=new int[newSize][newSize];
        int[][] B12=new int[newSize][newSize];
        int[][] B21=new int[newSize][newSize];
        int[][] B22=new int[newSize][newSize];

        for (int i=0;i<newSize;i++)
        {
            for (int j=0;j<newSize;j++)
            {
                A11[i][j]=A[i][j];
                A12[i][j]=A[i][j+newSize];
                A21[i][j]=A[i+newSize][j];
                A22[i][j]=A[i+newSize][j+newSize];

                B11[i][j]=B[i][j];
                B12[i][j]=B[i][j+newSize];
                B21[i][j]=B[i+newSize][j];
                B22[i][j]=B[i+newSize][j+newSize];
            }
        }

        int[][] M1=multiply(add(A11,A22),add(B11,B22));
        int[][] M2=multiply(add(A21,A22),B11);
        int[][] M3=multiply(A11, subtract(B12,B22));
        int[][] M4=multiply(A22, subtract(B21,B11));
        int[][] M5=multiply(add(A11,A12),B22);
        int[][] M6=multiply(subtract(A21,A11),add(B11,B12));
        int[][] M7=multiply(subtract(A12,A22),add(B21,B22));


        // Calculating the result submatrices
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        for (int i=0;i<newSize;i++)
        {
            for (int j=0;j<newSize;j++)
            {
                result[i][j]=C11[i][j];
                result[i][j+newSize]=C12[i][j];
                result[i+newSize][j]=C21[i][j];
                result[i+newSize][j+newSize]=C22[i][j];


            }
        }
        return result;
    }

    public static int[][] add(int[][] A,int[][] B)
    {
        int n=A.length;
        int[][] res=new int[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                res[i][j]=A[i][j]+B[i][j];
            }
        }
        return res;
    }

    public static int[][] subtract(int[][] A, int[][] B)
    {
        int n=A.length;
        int[][] res=new int[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                res[i][j]=A[i][j]-B[i][j];
            }
        }
        return res;
    }

    public static void printMatrix(int[][] matrix)
    {
        int n=matrix.length;
        int[][] res=new int[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter matrix size (power of 2): ");
        int n = sc.nextInt();

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        System.out.println("Enter Matrix 1:");
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                System.out.println("Enter matrix 1 element "+(i+1) +" X "+(j+1) );
                A[i][j]=sc.nextInt();
            }

        }

        System.out.println("Enter Matrix 2:");

        for (int i=0;i<2;i++)
        {
            for (int j=0;j<2;j++)
            {
                System.out.println("Enter matrix 2 element "+(i+1) +" X "+(j+1) );
                B[i][j]=sc.nextInt();
            }

        }

        int[][] res=multiply(A,B);
        System.out.println("Resultant Matrix");

        printMatrix(res);






    }

}
