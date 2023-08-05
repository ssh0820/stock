package com.zerobase.stock.model.constants;

public enum Month {

    JAN("Jan",1),
    FEB("FEB",2),
    MAR("MAR",3),
    APR("APR",4),
    MAY("MAY",5),
    JUN("JUN",6),
    JUL("JUL",7),
    AUG("AUG",8),
    SEP("SEP",9),
    OCT("OCT",10),
    NOV("NOV",11),
    DEC("DEC",12)
            ;

    private String s;
    private int number;

    Month(String s, int n){
        this.s = s;
        this.number = n;
    }

    public static int strToNumber(String s){
        for(var m : Month.values()){
            if(m.s.equals(s)){
                return m.number;
            }
        }
        return -1;
    }
}
