package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.io.IOException;



public final class DecodeMorse {

    Map<String, Character> morsecode = new HashMap<>();
    Map<Character, String> morsecode1 = new HashMap<>();
    private boolean type;
    private String fileName;

    //--------------------------------------------------
    public DecodeMorse(boolean type, String fileName) {
        this.type = type;
        this.fileName = fileName;
        {
//откроем файл текстовый с азбукой и загоним в map
            BufferedReader reader = null;

            try {
                File file = new File("azbuka.txt");
                //создаем объект FileReader для объекта File
                FileReader fr = new FileReader(file);
                //создаем BufferedReader с существующего FileReader для построчного считывания
                reader = new BufferedReader(fr);
                // считаем сначала первую строку
                String line = reader.readLine();
                while (line != null) {
                    //==файл в формате символ пробел код морзе
                    String[] retval = line.split(" ");
                    if (this.type)
                        this.morsecode.put(retval[1], retval[0].charAt(0));
                    else
                        this.morsecode1.put(retval[0].charAt(0), retval[1]);

                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace(System.err);
                    }
                }
            }
        }


    } //end constructor


    //-----------------------------------
    void decodering(StringBuilder str, String smorse, HashSet<PairStat> freq) {

        Character get = this.morsecode.get(smorse); //букву на код морзе

        PairStat pair = new PairStat(get, 1);
        if (get != null) {
            str.append(get);
           /* if (!freq.add(pair)) {  //частота встречаемости буквы
                for (PairStat p : freq) {
                    if (get.equals(p.getKey())) {
                        p.setValue(p.getValue() + 1);
                    }
                }
            }*/
            freq.add(pair);
        } else str.append(smorse);


    }  //end decodering

    //-------------------------------
    void codering(StringBuilder str, Character ch, HashSet<PairStat> freq) {

        if (Character.isDigit(ch) || Character.isLetter(ch)) {

            String get = this.morsecode1.get(ch);  //код морзе на букву
            //System.out.println(get);
            if (get != null) str.append(get);
            PairStat pair = new PairStat(ch, 1);  //считаем частоту буквы
            freq.add(pair);

        } else str.append(ch);
        str.append(" ");


    }  //end codering


    //------------------------------
    void getTxtOrKode() {
        Reader reader = null;

        try {
            //System.out.println(this.type);
            reader = new InputStreamReader(new FileInputStream(this.fileName));
            StringBuilder encode = new StringBuilder();
            // HashSet<PairStat> freq = new HashSet<>() ;
            HashSet<PairStat> freq = new HashSet<PairStat>() {
                @Override   //переопределение добавления
                public boolean add(PairStat o) {
                    boolean AllOk = super.add(o);  //вызов родительского метода
                    if (!AllOk) {   //уже есть в наборе сочетание такое
                        for (PairStat p : this) {  //итератор
                            if (o.getKey().equals(p.getKey())) {
                                p.setValue(p.getValue() + 1);
                                AllOk = true;
                            }
                        }
                    }
                    return AllOk;
                }

            };


            boolean delline = false;

            if (!this.type) {  //кодирование строку в код морзе
                // читаем посимвольно
                int c;
                Character ch;

                // CodeMorse cm = new CodeMorse( );
                while ((c = reader.read()) != -1) {
                    if (c == 13 || c == 10) { //конец строки
                        if (!delline)
                            encode.append('\n');
                        delline = !delline;
                    } else {
                        ch = Character.toUpperCase((char) c); //в верхн регистр
                        this.codering(encode, ch, freq);
                    }
                }
            } else {        //------------------декодирование кода морзе в строку
                int c, flag = 0;
                StringBuilder str = new StringBuilder();
                //DecodeMorse dm = new DecodeMorse(type, fileName);

                //String delimiter = System.lineSeparator();//System.getProperty("line.separator");

                while ((c = reader.read()) != -1) {
                    //наращивать строку пока не встретится первый пробел и передача для декодировки
                    // елси далее символы управляющие-пробеловЮ переводы строки, то нет декодирования,
                    // а наращивание результирующей строки
                    // System.out.println(c);
                    if (c == ' ') {
                        if (flag > 0) {
                            encode.append(" ");
                            flag = 0;
                        } else {
                            flag = 1;
                            if (!str.isEmpty()) {
                                this.decodering(encode, str.toString(), freq);
                                str.delete(0, str.length());
                            }
                        }

                    }  //не пробел
                    else {

                        if (c == 13 || c == 10) {  //конец строки
                            if (!str.isEmpty()) {

                                this.decodering(encode, str.toString(), freq);
                                str.delete(0, str.length());
                            }

                            if (!delline)
                                encode.append('\n');
                            delline = !delline;
                            flag = 0;
                        } else {
                            flag = 0;
                            //System.out.printf("char- "+ (char) c);
                            str.append((char) c);
                        }

                    }
                }

            }
            System.out.println(encode.toString());
            try (FileWriter writer = new FileWriter("statistic.txt", false)) {
                for (PairStat p : freq) {
                    // System.out.printf(" %c - %d \n", p.getKey(), p.getValue());
                    // запись  строки
                    Integer i = p.getValue();
                    String text = i.toString();
                    writer.append(p.getKey());
                    writer.append("-");
                    writer.append(text);
                    writer.append("\n");

                }
                writer.flush();
                writer.close();
            } catch (IOException ex) {

                System.out.println(ex.getMessage());
            }


        }  //end try

        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }


    }// end getTxtOrKode()

}

