package com.timbuchalka;

public class Main {
    public static void main(String[] args) {
        System.out.println("Z Math " + Math.log(1.3));
        System.out.println("Taylor w kolejnosci od poczatku " + taylorU(1000000, 0.3));
        System.out.println("Taylor w kolejnosci od konca " + taylorD(1000000, 0.3));
        System.out.println("Next from prev od poczatku " + nextFromPrevU(1000000, 0.3));
        System.out.println("Next from prev od konca " + nextFromPrevD(1000000, 0.3));
    }

    public static double taylorU(int n, double x) {
        double sum = 0;
        double[] tab = createT(n, x);
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            sum += tab[i];
        }
        return sum;
    }

    public static double taylorD(int n, double x) {
        double sum = 0;
        double[] tab = createT(n, x);
        for (int i = n - 1; i > 0; i--) {
            sum += tab[i];
        }
        return sum;
    }

    public static double nextFromPrevU(int n, double x) {
        double[] tab = createNfP(n, x);
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += tab[i];
        }
        return sum;
    }

    public static double nextFromPrevD(int n, double x) {
        double[] tab = createNfP(n, x);
        double sum = 0;
        for (int i = n-1; i >= 0; i--) {
            sum += tab[i];
        }
        return sum;
    }

    public static double[] createT(int n, double x) {
        double[] tab = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.println(i);
            tab[i] = (pow(-1, i + 1) / i) * pow(x, i);
        }
        return tab;
    }


    public static double[] createNfP(int n, double x) {
        double first = x;
        double next = 0;
        double[] tab = new double[n];
        tab[0] = first;
        for (int i = 1; i < n; i++) {
            next = first * ((-1) * i * x) / (i + 1);
            tab[i] = next;
            first = next;
        }
        return tab;
    }

    public static double pow(double x, double n) {
        double wyn = 1;
        for (int i = 0; i < n; i++) {
            wyn *= x;
        }
        return wyn;
    }


    //bez silni i poteg
    //zamiana kolejnosci sumowania
}
