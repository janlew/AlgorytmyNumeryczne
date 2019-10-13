package com.timbuchalka;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Maths {

    //Potegowanie
    public static double pow(double x, double n) {
        double wyn = 1;
        for (int i = 0; i < n; i++) {
            wyn *= x;
        }
        return wyn;
    }

    //Tworzenie tablicy ze wzoru
    public static double[] createT(int n, double x) {
        double[] tab = new double[n];
        for (int i = 1; i <= n; i++) {
            tab[i - 1] = (pow(-1, i + 1) / i) * pow(x, i);
        }
        return tab;
    }

    //Sumowanie od poczatku
    public static double taylorU(int n, double x) {
        double sum = 0;
        double[] tab = createT(n, x);
        for (int i = 0; i < n; i++) {
            sum += tab[i];
        }
        return sum;
    }

    //Sumowanie od konca
    public static double taylorD(int n, double x) {
        double sum = 0;
        double[] tab = createT(n, x);
        for (int i = n - 1; i >= 0; i--) {
            sum += tab[i];
        }
        return sum;
    }

    //Tworzenie tablicy metoda kolejny z poprzedniego
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

    //Sumowanie od poczatku
    public static double nextFromPrevU(int n, double x) {
        double[] tab = createNfP(n, x);
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += tab[i];
        }
        return sum;
    }

    //Sumowanie od konca
    public static double nextFromPrevD(int n, double x) {
        double[] tab = createNfP(n, x);
        double sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += tab[i];
        }
        return sum;
    }

    //generowanie listy za pomoca wzoru
    public static void generateListT(List<List<String>> list, int n, double x, String fileName) throws IOException {
        double avgError = 0;
        double avgErrorR = 0;

        list.add(Arrays.asList("x", "od poczatku", "od konca"));
        for (int i = 0; i < 1000000; i++) {
            avgError += Math.abs(Math.log(x + 1) - taylorU(n, x));
            avgErrorR += Math.abs(Math.log(x + 1) - taylorD(n, x));

            if (i % 1000 == 0 && i != 0) {
                avgError = avgError / 1000;
                avgErrorR = avgErrorR / 1000;
                list.add(Arrays.asList(
                        Double.toString(x + 1),
                        Double.toString(avgError),
                        Double.toString(avgErrorR)));

                avgError = 0;
                avgErrorR = 0;
            }
            x -= 0.00000157;
        }
        Csv.saveToFile(list, fileName);
        list.clear();
    }

    //generowanie listy za pomoca next from prev
    public static void generateListNfP(List<List<String>> list, int n, double x, String fileName) throws IOException {
        double avgError = 0;
        double avgErrorR = 0;

        list.add(Arrays.asList("x", "od poczatku", "od konca"));
        for (int i = 0; i < 1000000; i++) {
            avgError += Math.abs(Math.log(x + 1) - nextFromPrevU(n, x));
            avgErrorR += Math.abs(Math.log(x + 1) - nextFromPrevD(n, x));

            if (i % 1000 == 0 && i != 0) {
                avgError = avgError / 1000;
                avgErrorR = avgErrorR / 1000;
                list.add(Arrays.asList(
                        Double.toString(x + 1),
                        Double.toString(avgError),
                        Double.toString(avgErrorR)));

                avgError = 0;
                avgErrorR = 0;
            }
            x -= 0.00000157;
        }
        Csv.saveToFile(list, fileName);
        list.clear();
    }

    //funkcja do sprawdzenia czy sumowanie od konca jest lepsze ze wzoru czy z NfP
    public static void generateListFromEnd(List<List<String>> list, int n, double x, String fileName) throws IOException {
        list.add(Arrays.asList("x", "z Taylora", "next from prev"));
        double avgTError = 0;
        double avgNfPError = 0;

        for (int i = 0; i < 1000000; i++) {
            avgTError += Math.abs(Math.log(x + 1) - taylorD(n, x));
            avgNfPError += Math.abs(Math.log(x + 1) - nextFromPrevD(n, x));

            if (i % 1000 == 0 && i != 0) {
                avgTError = avgTError / 1000;
                avgNfPError = avgNfPError / 1000;
                list.add(Arrays.asList(
                        Double.toString(x + 1),
                        Double.toString(avgTError),
                        Double.toString(avgNfPError)));

                avgTError = 0;
                avgNfPError = 0;
            }
            x -= 0.00000157;
        }
        Csv.saveToFile(list, fileName);
        list.clear();
    }

    public static void generateListDiffN(List<List<String>> list, double x, String fileName) throws IOException {

        double avgErr10 = 0;
        double avgErr25 = 0;
        double avgErr50 = 0;
        double avgErr100 = 0;

        list.add(Arrays.asList("x", "n = 10", "n = 25", "n = 50", "n = 100"));

        for (int i = 0; i < 1000000; i++) {
            avgErr10 += Math.abs(Math.log(x + 1) - taylorU(10, x));
            avgErr25 += Math.abs(Math.log(x + 1) - taylorU(25, x));
            avgErr50 += Math.abs(Math.log(x + 1) - taylorU(50, x));
            avgErr100 += Math.abs(Math.log(x + 1) - taylorU(100, x));

            if (i % 1000 == 0 && i != 0) {
                avgErr10 = avgErr10 / 1000;
                avgErr25 = avgErr25 / 1000;
                avgErr50 = avgErr50 / 1000;
                avgErr100 = avgErr100 / 1000;

                list.add(Arrays.asList(
                        Double.toString(x + 1),
                        Double.toString(avgErr10),
                        Double.toString(avgErr25),
                        Double.toString(avgErr50),
                        Double.toString(avgErr100)));
                avgErr10 = 0;
                avgErr25 = 0;
                avgErr50 = 0;
                avgErr100 = 0;
            }
            x -= 0.00000157;
        }
        Csv.saveToFile(list, fileName);
        list.clear();
    }

}
