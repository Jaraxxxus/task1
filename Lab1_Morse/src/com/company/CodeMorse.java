package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
//import java.util.Set;

public class CodeMorse {

    public Map<Character, String> morsecode  = new HashMap<>();
    public HashSet<Pair> freq = new HashSet<>() ;

    public CodeMorse(){

        this.morsecode.put('А', ".-");
        this.morsecode.put('Б', "-...");
        this.morsecode.put('В', ".––");
        this.morsecode.put('Г', "––.");
        this.morsecode.put('Д', "–..");
        this.morsecode.put('Е', ".");
        this.morsecode.put('Ж', "...–");
        this.morsecode.put('З', "––..");
        this.morsecode.put('И', "..");
        this.morsecode.put('Й', ".–––");
        this.morsecode.put('К', "–.–");
        this.morsecode.put('Л', ".–..");
        this.morsecode.put('М', "––");
        this.morsecode.put('Н', "–.");
        this.morsecode.put('О', "–––");
        this.morsecode.put('П', ".––.");
        this.morsecode.put('Р', ".–.");
        this.morsecode.put('С', "...");
        this.morsecode.put('Т', "-");
        this.morsecode.put('У', "..–");
        this.morsecode.put('Ф', "..–.");
        this.morsecode.put('Х', "....");
        this.morsecode.put('Ц', "–.–.");
        this.morsecode.put('Ч', "–––.");
        this.morsecode.put('Ш', "––––");
        this.morsecode.put('Щ', "−−.−");
        this.morsecode.put('Ы', "−.−−");
        this.morsecode.put('Ь', "−..−");
        this.morsecode.put('Э', "..−..");
        this.morsecode.put('Ю', "..−−");
        this.morsecode.put('Я', ".−.−");
        this.morsecode.put('1', ".−−−−");
        this.morsecode.put('2', "..−−−");
        this.morsecode.put('3', "...−−");
        this.morsecode.put('4', "....−");
        this.morsecode.put('5', ".....");
        this.morsecode.put('6', "−....");
        this.morsecode.put('7', "−−...");
        this.morsecode.put('8', "−−−..");
        this.morsecode.put('9', "−−−−.");
        this.morsecode.put('0', "−−−−−");



    }
    void codering(StringBuilder str, Character ch, HashSet<Pair> freq){

        if (Character.isDigit(ch) || Character.isLetter(ch)) {
            //System.out.println("И вот я здесь ");

            String get = this.morsecode.get(ch);  //код морзе на букву
            //System.out.println(get);
            if (get!=null) str.append(get);
            Pair pair = new Pair(ch, 1);  //считаем частоту буквы
            if (!freq.add(pair)) {
                for (Pair p : freq) {
                    if (ch.equals(p.getKey())) {
                        p.setValue(p.getValue() + 1);
                    }
                }
            }


        }

        else str.append(ch);
        str.append(" ");



    }
}
