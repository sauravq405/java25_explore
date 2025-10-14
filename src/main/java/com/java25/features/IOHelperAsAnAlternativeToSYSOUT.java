package com.java25.features;

import java.util.Scanner;

public class IOHelperAsAnAlternativeToSYSOUT {

    void main(){
        IO.println("Hello world. Enter your input in next line");
        //takeInputAndPrint();
        String input = IO.readln();
        System.out.println("Output echo: "+input);

    }
    public void takeInputAndPrint(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input");
        String input = scanner.nextLine();
        System.out.println("Output echo: "+input);
    }
}
