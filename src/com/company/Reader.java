package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.company.Main.all_Bandwidths;

public class Reader {
    public static void reader() {
        try{
            FileInputStream fstream = new FileInputStream("in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            Main.count_of_All_Vertex = Integer.parseInt(br.readLine());
            String strLine = br.readLine();
            while (strLine != null){
                parser(strLine);
                strLine = br.readLine();
            }
        }catch (IOException e){
            System.out.println("Ошибка чтения файла");
        }
    }

    public static void parser(String str) {
        while (str != "") {
            int num = str.indexOf(" ");
            if (num == -1){
                break;
            }
            String times = str.substring(0, num);
            str = str.substring(num+1);
            all_Bandwidths.add(Integer.parseInt(times));
        }
        all_Bandwidths.add(Integer.parseInt(str));
    }

    public static void getSourceandStock() {
        Main.stock = (int) all_Bandwidths.get(all_Bandwidths.size()-1);
        all_Bandwidths.remove(all_Bandwidths.size()-1);
        Main.source = (int) all_Bandwidths.get(all_Bandwidths.size()-1);
        all_Bandwidths.remove(all_Bandwidths.size()-1);
    }
}
