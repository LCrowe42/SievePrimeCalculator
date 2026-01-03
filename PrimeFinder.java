import java.util.Scanner;

public class PrimeFinder {

    public static void main(String args[]) {
        try (Scanner scan = new Scanner(System.in)) {
            int n = 0;
            while (n < 2) {
                System.out.println("Please enter a number \"n\" greater than 1 to print a list of all primes between 0 and \"n\":");
                n = scan.nextInt();
            }
            String choice = "X";
            do {
                System.out.println("Please enter \"Y\" to print the primes or \"N\" to just see the largest and processing time");
                choice = scan.next().toUpperCase();
            } while (!choice.contains("Y") && !choice.contains("N"));
            boolean[] sieve = new boolean[n + 1];
            long startTime = System.nanoTime(); 
            markAllComposites(sieve);
            long endTime = System.nanoTime();
            if (choice.equals("Y")) {
                for (int i = 2; i < sieve.length; i++) {
                    if (!sieve[i]) {
                        System.out.print(i + " ");
                    }
                }
            }
            else {
                for (int i = n; i >= 2; i--) {
                    if (!sieve[i]) {
                        System.out.print(i);
                        break;
                    }
                }
            }
            System.out.println();
            System.out.println("Primes generated in " + ((endTime - startTime) / 1000000) + "ms");
            scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void markAllComposites(boolean[] sieve) {
        int n = sieve.length;
        int i = 2;
        while (i <= Math.sqrt(n)) {
            int j = i*i;
            while (j < n) {
                sieve[j] = true;
                j += i;
            }
            do { 
                i++;
            } while (i < n && sieve[i]);
        }
    }
}