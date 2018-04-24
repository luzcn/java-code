package leetcode;


// implement Pow(x, n)
// if n > 0, pow(x,n) = x * x * ... * x
// if n < 0, pow(x, n) = 1/( x * x ... x)
public class PowXofN {

    // divide conquer  recursive solution
    // As we know, x^n = x^(n/2) * x^(n/2) * x^(n%2)
    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        double result = pow(x, n / 2);

        if (n % 2 == 0) {
            return result * result;
        } else {
            return result * result * x;
        }
    }

    public double myPow(double x, int n) {
        if (n < 0) {
            return 1 / this.pow(x, -n);
        } else {
            return this.pow(x, n);
        }
    }
}
