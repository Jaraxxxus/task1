package com.company;

public final class Pair {
    private Character key;
    private Integer  value;

    public Pair(Character akey, Integer avalue){
        this.key = akey;
        this.value = avalue;
    }
    public Pair(){
      //  this.key = null;
      //  this.value = 0;
    }

    public Character getKey() { return key; }
    public Integer getValue() {return  value; }
    public void setKey(Character akey) {
        this.key = akey;
    }
    public void setValue(Integer avalue) {
        this.value = avalue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + key.hashCode();

        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // если передан объект другого класса, считаем, что он не равен данному

        Pair  pair = (Pair) o;
        // сравниваем значения поля

        if (!this.key.equals(pair.key)) return false;
        return true;

    }
}
