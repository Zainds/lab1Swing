import java.util.Scanner;

public class testLN {
    public static double solve1(double x, double accuracy) {
        double result = 0;
        int n = 0;
        double sum = x;
        while(Math.abs(sum) >= accuracy) {
            result += sum;
            sum *= Math.pow(x-1, n+1) / (n + 1)*Math.pow(x, n+1);;
            n++;
        }
        return result;
    }

    public static double getActual1(double x) {
        return Math.log(x);
    }

    public static void main(String[] args) {
        int x = 5;
        double accuracy = Math.pow(10, -1);
        System.out.println(solve1(x, accuracy) + " actual:  " + getActual1(x));

    }

}
