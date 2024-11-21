package com.banking.banking.Utils;

public class AccountUtils {


    public  static final String ACCOUNT_EXISTS="001";
    public static final String ACCOUNT_EXISTS_MESSAGES= "User Already has An Account Created";
    public  static  final String ACCOUNT_CREATION_SUCCESS="002";
    public static final String ACCOUNT_CREATION_MESSAGES="Account has been successfully created";
    public  static final String ACCOUNT_NOT_EXIST_CODE= "003";
    public  static  final  String ACCOUNT_NOT_EXIST_MESSAGE="User with the provided account_number does not exist";
    public  static  final String INSUFFICIENT_BALANCE_CODE= "006";
    public  static  final  String INSUFFICIENT_BALANCE_MESSAGE="Insufficient Balance";
    public  static  final  String TRANSFER_SUCCESSFUL_CODE= "008";
    public  static  final  String TRANSFER_SUCCESSFUL_MESSAGE="Transfer Successful";
    public static final String ACCOUNT_FOUND_CODE ="004" ;
    public static final String ACCOUNT_FOUND_SUCCESS ="User account found" ;


    public static String generateAccountNumber() {

        int min = 1000000;
        int max = 1000000000;


        int random_int= (int) Math.floor(Math.random()  * (max - min +1 )+ min);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber.append("3").append(random_int).toString();


    }


    public  static long generateCardNumber(){
        double min = 100000000L;
        double max = 1000000000000000L;

        System.out.println(min + ""  + max);

        double random_int=  Math.floor(Math.random()  * (max - min +8 )+ min);
        return (long) random_int;

    }

    public  static int generateCardCvvNumber(){

        int min = 10;
        int max = 1000;

        System.out.println(min + ""  + max);

        int random_int= (int) Math.floor(Math.random()  * (max - min +8 )+ min);
        return random_int;
    }


}
