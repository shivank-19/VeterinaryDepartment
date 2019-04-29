package com.e.veterinarydepartment;

public class TestState {
    public static Boolean pragnancy=false;
    public static Boolean castiation=false;

    public static void setPragnancy(Boolean b){
        pragnancy=b;
    }

    public static Boolean getPragnancy(){
        return pragnancy;
    }

    public static void setCastiation(Boolean b){
        castiation=b;
    }

    public static Boolean getCastiation(){
        return castiation;
    }
}
