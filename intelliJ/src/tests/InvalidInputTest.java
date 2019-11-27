package tests;

import java.util.Scanner;

public class InvalidInputTest {
    public static void main(String[] args){
        Scanner scanner  =new Scanner(System.in);
        float res = 0;
        //float res = scanner.nextInt();
        if(scanner.hasNextInt()){
            res = scanner.nextInt();
        }

        System.out.println(res);


    }
}
