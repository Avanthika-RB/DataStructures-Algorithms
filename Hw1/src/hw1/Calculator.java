package hw1;

public class Calculator {

    /* 1. Evaluate a mathematical string that consists of addition and subtraction operators.
          Example: "12+5-3" ----> 14

       Extra Credit: Have your function also handle multiplication and division. Remember the
       order of operations matters!
     */
    public int evaluateString(String s) {
        int answer = 0;
        int currNum = 0;
        int lastNum = 0;
        int length = s.length();
        char sign = '+';

        for (int i = 0; i < length; i++) {
            char theChar = s.charAt(i);

        if (Character.isDigit(theChar)) {
            currNum = currNum * 10 + (theChar - '0');
        }

        if (!Character.isDigit(theChar) || i == length - 1) {
            if (sign == '+') {
                answer += lastNum;
                lastNum = currNum;
            } else if (sign == '-') {
                answer += lastNum;
                lastNum = -currNum;
            } else if (sign == '*') {
                lastNum = lastNum * currNum;
            } else if (sign == '/') {
                lastNum = lastNum / currNum;
            }
            sign = theChar;
            currNum = 0;
        }
    }
        answer += lastNum;
        return answer;
}

    // 2. Return a polynomial that represents the derivative of the Polynomial
    public Polynomial getDerivative(Polynomial p) {
        // Do not remove this condition
        if (p.getPolynomialDegree() == 0) {
            return new Polynomial(0, new double[0]);
        }
        int degree = p.getPolynomialDegree() - 1;
        double[] coefficients = new double[degree + 1];

        for (int i = 1; i <= degree + 1; i++) {
            coefficients[i - 1] = p.getKthCoefficient(i) * i;
        }
        return new Polynomial(degree, coefficients);
    }

    // 3. Evaluate the polynomial at the given point x
    public double evaluatePolynomial(Polynomial p, double x) {
        double answer = 0.0;
        int degree = p.getPolynomialDegree();
        double power = 1.0;

        for (int i = 0; i <= degree; i++) {
            answer += p.getKthCoefficient(i) * power;
            power *= x;
        }
        return answer;
    }

    /*
        4. Find the root of the Polynomial using the Newton-Raphson method starting at the point x.
        Note that we defined a variable called tolerance. Use this threshold when determining whether
        your algorithm has converged or not.
     */
    public double newtonRaphson(Polynomial p, double initialGuess) {
        double tolerance = 0.001;
        double prevGuess;

        do {
            prevGuess = initialGuess;
            // evaluates the polynomial
            double evaluate = evaluatePolynomial(p, initialGuess);
            Polynomial derivative = getDerivative(p); // evalDeriv derivative of the polynomial
            double evalDeriv = evaluatePolynomial(derivative, initialGuess);
            //current guess using formula
            initialGuess -= evaluate / evalDeriv;
        } while (Math.abs(initialGuess - prevGuess) >= tolerance);
        // when converged, return
        return initialGuess;
    }
}
