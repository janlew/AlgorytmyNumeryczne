package com.timbuchalka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<List<String>>rows=new ArrayList<>();

        Maths.generateListT(rows, 100, 1, "taylor.csv");
        Maths.generateListNfP(rows, 100, 1, "nextFromPrev.csv");
        Maths.generateListFromEnd(rows, 100, 1, "end.csv");
        Maths.generateListDiffN(rows, 1, "diffN.csv");
    }

}