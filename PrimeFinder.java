import java.util.BitSet;
import java.util.Random;
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
                System.out.println("Please enter \"Y\" to print the primes, \"N\" to just see the largest, or \"R\" for a random prime and processing time");
                choice = scan.next().trim().toUpperCase();
            } while (!choice.equals("Y") && !choice.equals("N") && !choice.equals("R"));
            BitSet sieve = new BitSet(n + 1);
            long startTime = System.nanoTime(); 
            markAllComposites(sieve);
            long endTime = System.nanoTime();
            if (choice.equals("Y")) {
                for (int i = 2; i <= n; i++) {
                    if (!sieve.get(i)) {
                        System.out.print(i + " ");
                    }
                }
            }
            else if (choice.equals("N")) {
                for (int i = n; i >= 2; i--) {
                    if (!sieve.get(i)) {
                        System.out.print(i);
                        break;
                    }
                }
            }
            else {
                System.out.print(printRandom(sieve));
            }
            System.out.println();
            System.out.println("Sieve completed in " + ((endTime - startTime) / 1000000) + "ms");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int printRandom(BitSet sieve) {
        int n = sieve.size();
        Random rand = new Random();
        int randomPoint = rand.nextInt(n);
        int nearestAbove = sieve.nextClearBit(randomPoint);
        int nearestBelow = sieve.previousClearBit(randomPoint);
        if (nearestAbove - randomPoint < randomPoint - nearestBelow) {
            return nearestAbove;
        }
        else {
            return nearestBelow;
        }
    }

    static void markAllComposites(BitSet sieve) {
        int n = sieve.size();
        int i = 2;
        while (i * i < n) {
            int j = i*i;
            while (j < n) {
                sieve.set(j);
                j += i;
            }
            do { 
                i++;
            } while (i < n && sieve.get(i));
        }
    }
}