package lesson1.step1;

import java.util.Scanner;

public class ChangeIntegers {

    public static void main(String[] args) {

        int a = 1;
        int b = 2;

        System.out.println("Два числа на входе a:" + a + " b:" + b);


        swap(a, b);

        System.out.printf("Два числа на выходе a:%d b:%d", a, b);

    }

    private static void swap(int a, int b) {
        int c = a;
        a = b;
        b = c;
    }
    
    
}
