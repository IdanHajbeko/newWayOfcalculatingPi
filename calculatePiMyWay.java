import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class calculatePiMyWay {
    private final static MathContext MC = new MathContext(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter how accurate to be (the larger the better): ");
        int maxTerms = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime();

        BigDecimal HALF = BigDecimal.valueOf(0.5);
        BigDecimal factorial = BigDecimal.ONE;
        BigDecimal product = factorial;
        BigDecimal twoNPlusOne = factorial;
        BigDecimal piOver4 = BigDecimal.ZERO;

        for (int n = 0; n <= maxTerms; n++) {
            if (n > 0) {
                factorial = factorial.multiply(BigDecimal.valueOf(n), MC);
                product = product.multiply(HALF.subtract(BigDecimal.valueOf(n - 1), MC), MC);
                twoNPlusOne = twoNPlusOne.add(BigDecimal.valueOf(2), MC);
            }

            BigDecimal term = product.divide(factorial.multiply(twoNPlusOne, MC), MC);

            piOver4 = (n % 2) == 0 ? piOver4.add(term, MC) : piOver4.subtract(term, MC);//who would believe that (n % 2) == 0 would be faster than (n & 1) == 1
        }

        BigDecimal pi = piOver4.multiply(BigDecimal.valueOf(4), MC);

        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.println("π/4 = " + piOver4);
        System.out.println("π = " + pi);
        System.out.println("\nThe real π\n  = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
        System.out.printf("\nExecution Time: %.6f seconds\n", elapsedTimeInSeconds);
    }

}
