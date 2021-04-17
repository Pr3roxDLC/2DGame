package me.pr3.util;

public class MathUtils {

    public static int clampInt(int number, int min, int max){
    if(number > max)return max;
    return Math.max(number, min);
    }

    public static float clampFloat(float number, float min, float max){
        if(number > max)return max;
        return Math.max(number, min);
    }

    public static double clampDouble(double number, double min, double max){
        if(number > max)return max;
        return Math.max(number, min);

    }
}
