package ru.olgavlzv.lab1;

public class Primes {

    public static void main(String[] args) {
        boolean p;
        for (int i = 2; i < 100; i++) {
            p = isPrime(i);
            if (p) {
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int n) {
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                m++;
            }
        }
        if (m < 2) {
            return true;
        }
        else {
            return false;
        }
    }
}
