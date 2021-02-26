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
        Reader reader = null;

        try

        {

            reader = new InputStreamReader(new FileInputStream(fileName));
            StringBuilder encode = new StringBuilder();
            HashSet<Pair> freq = new HashSet<>() ;
            boolean delline = false;

             if (variant.equals("code")) {  //кодирование строку в код морзе
                 // читаем посимвольно
                 int c;
                 Character ch;

                 CodeMorse cm = new CodeMorse( );
                 while ((c = reader.read()) != -1) {
                     if (c == 13 || c == 10) { //конец строки
                         if (!delline)
                           encode.append('\n');
                         delline = !delline;
                     }
                     else {
                         ch = Character.toUpperCase((char) c);
                         cm.codering(encode, ch, freq);
                     }
                 }
             }

                else {                       //декодирование кода морзе в строку
                     int c, flag = 0;
                     StringBuilder str = new StringBuilder();
                     DecodeMorse dm = new DecodeMorse();

                     String delimiter = System.lineSeparator();//System.getProperty("line.separator");

                     while((c=reader.read())!=-1) {
                         //наращивать строку пока не встретится первый пробел и передача для декодировки
                         // елси далее символы управляющие-пробеловЮ переводы строки, то нет декодирования,
                         // а наращивание результирующей строки
                        // System.out.println(c);
                         if (c==' ') {
                             if (flag > 0){
                                 encode.append(" ");
                                 flag = 0;
                             }
                             else {
                                 flag = 1;
                                 if (!str.isEmpty()) {
                                     dm.decodering(encode, str.toString(), freq);
                                     str.delete(0, str.length());
                                 }
                             }

                         }  //не пробел
                         else {
                             //String del = Character.toString((char) c);
                             //System.out.println("del " + del);
                             //System.out.println("delimit " + delimiter);
                             // if  (del.equals(delimiter) || del.isEmpty()){
                             if (c == 13 || c == 10 ) {  //конец строки
                                 if (!str.isEmpty()) {
                                     //System.out.printf("HOHO-" + str.toString());
                                     dm.decodering(encode, str.toString(), freq);
                                     str.delete(0, str.length());
                                 }

                                 //System.out.printf("POP");
                                 if (!delline)
                                     encode.append('\n');
                                 delline = !delline;
                                 flag = 0;
                             }
                             else {
                                 flag = 0;
                                 //System.out.printf("char- "+ (char) c);
                                 str.append((char) c);
                             }

                         }
                 }

            }
            System.out.println(encode.toString());
            try(FileWriter writer = new FileWriter("statistic.txt", false))
            {
                for ( Pair p : freq ) {
                   // System.out.printf(" %c - %d \n", p.getKey(), p.getValue());
                    // запись  строки
                    Integer i = p.getValue() ;
                    String text = i.toString();
                    writer.append(p.getKey());
                    writer.append("-");
                    writer.append(text);
                    writer.append("\n");

                }
                writer.flush();
                writer.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }


        }  //end try

        catch (IOException e)
        {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally
        {
            if (null != reader)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }




    }
}
