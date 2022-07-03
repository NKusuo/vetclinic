package command.checking;

import java.util.Scanner;


public class NumberEnter {
    /*
     * Метод для ввода id для поиска в базе данных и проверка ввода на число
     */
    public static int check(){
        int num;
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();

        try {
            num = Integer.parseInt(str);
            return num;
        } catch (NumberFormatException e) {
            System.out.println("Input data cannot be Id.");
        }
        return -1;
    }
}
