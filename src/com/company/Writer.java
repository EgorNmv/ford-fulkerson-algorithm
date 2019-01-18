package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writeResult () throws IOException {
        File file = new File("out.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (int i=0; i<FordFalkerson.flow.length;i++) {
            if (i != 0) {
                writer.write("\n");
            }
            for (int j=0; j<FordFalkerson.flow.length; j++) {
                writer.write(FordFalkerson.flow[i][j] + " ");
            }
        }
        writer.write("\n" + String.valueOf(FordFalkerson.findMaxFlow()));
        writer.flush();
        writer.close();
    }
}
