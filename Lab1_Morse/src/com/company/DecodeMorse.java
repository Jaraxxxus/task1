package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DecodeMorse {

    Map<String, Character> morsecode = new HashMap<>();
    public DecodeMorse(){
        {

            this.morsecode.put(".-", 'А');
            this.morsecode.put("-...", 'Б');
            this.morsecode.put( ".––", 'В');
            this.morsecode.put( "––.", 'Г');
            this.morsecode.put("–..", 'Д');
            this.morsecode.put(".", 'Е');
            this.morsecode.put("...–", 'Ж');
            this.morsecode.put( "––..", 'З');
            this.morsecode.put( "..", 'И');
            this.morsecode.put( ".–––", 'Й');
            this.morsecode.put( "–.–", 'К');
            this.morsecode.put( ".–..", 'Л');
            this.morsecode.put( "––", 'М');
            this.morsecode.put( "–.", 'Н');
            this.morsecode.put( "–––", 'О');
            this.morsecode.put( ".––.", 'П');
            this.morsecode.put( ".–.", 'Р');
            this.morsecode.put( "...", 'С');
            this.morsecode.put( "-", 'Т');
            this.morsecode.put( "..–", 'У');
            this.morsecode.put( "..–.", 'Ф');
            this.morsecode.put( "....", 'Х');
            this.morsecode.put( "–.–.", 'Ц');
            this.morsecode.put( "–––.", 'Ч');
            this.morsecode.put( "––––", 'Ш');
            this.morsecode.put( "−−.−", 'Щ');
            this.morsecode.put( "−.−−", 'Ы');
            this.morsecode.put( "−..−", 'Ь');
            this.morsecode.put( "..−..", 'Э');
            this.morsecode.put( "..−−", 'Ю');
            this.morsecode.put( ".−.−", 'Я');
            this.morsecode.put( ".−−−−", '1');
            this.morsecode.put( "..−−−", '2');
            this.morsecode.put( "...−−", '3');
            this.morsecode.put( "....−", '4');
            this.morsecode.put( ".....", '5');
            this.morsecode.put( "−....", '6');
            this.morsecode.put( "−−...", '7');
            this.morsecode.put( "−−−..", '8');
            this.morsecode.put( "−−−−.", '9');
            this.morsecode.put( "−−−−−", '0');
        }


    }
    void decodering(StringBuilder str,  String smorse, HashSet<Pair> freq){

       // System.out.println("smorse " + smorse.toString());
        Character get = this.morsecode.get(smorse); //букву на код морзе


       // System.out.println("get " + get);
        Pair pair = new Pair(get, 1);
            //System.out.println(get);
        if (get!=null) {
            str.append(get);
            if (!freq.add(pair)) {  //частота встречаемости буквы
                for (Pair p : freq) {
                    if (get.equals(p.getKey())) {
                        p.setValue(p.getValue() + 1);
                    }
                }
            }

        }

        else str.append(smorse);


    }
}

