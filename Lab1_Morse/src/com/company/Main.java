package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите команду (code/decode) и" +
                " имя текстового файла: ");
        String variant = in.next();
        String fileName = in.next();
        boolean type = variant.equals("decode");
        DecodeMorse dc = new DecodeMorse(type, fileName );
        dc.getTxtOrKode();



    }
}
