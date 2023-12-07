import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        int time = 62737565;
        BigInteger distance = new BigInteger("644102312401023"); // Use BigInteger for distance
        int count = 1;

        for (int i = 0; i <= time; i++) {
            BigInteger product = BigInteger.valueOf(i).multiply(BigInteger.valueOf(time - i));

            if (distance.compareTo(product) < 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}