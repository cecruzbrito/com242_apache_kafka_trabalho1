package br.unifei.imc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserComponent {
    static private double OPTION_ERROR_DOUBLE_TEMPETURE = 0;
    static private int OPTION_ERROR_INT_QUANTITY = 0;
    static private Boolean OPTION_ERROR_BOOL = false;
    static private int OPTION_ERROR_INT_TEMPERATURE = 3;

    double getTemperature(Scanner scanner){
        double option;
        try{
            option = scanner.nextDouble();
        }catch (InputMismatchException e){
            option = OPTION_ERROR_DOUBLE_TEMPETURE;
        }
        return option;

    }

    int getQuantityTemperatures(Scanner scanner){
        int option;
        try{
            option = scanner.nextInt();
        }catch (InputMismatchException e){
            option = OPTION_ERROR_INT_QUANTITY;
        }


        return option;
    }

     int getOption(Scanner scanner){
        int option;
        try{
            option = scanner.nextInt();
        }catch (InputMismatchException e){
            option = OPTION_ERROR_INT_TEMPERATURE;
        }
        return option;
    }

    Boolean getApplicationStatus(Scanner scanner){
        Boolean option;
        try{
            option = scanner.nextBoolean();
        }catch (InputMismatchException e){
            option = OPTION_ERROR_BOOL;
        }
        return option;
    }
}
