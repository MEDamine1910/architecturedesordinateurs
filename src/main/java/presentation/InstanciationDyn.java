package presentation;

import dao.ICalcul;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InstanciationDyn {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new File("config.txt"));
        String eCalcul = scanner.nextLine();
        Class c = Class.forName(eCalcul);
        ICalcul calcul = (ICalcul) c.getDeclaredConstructor().newInstance();
        System.out.println(calcul.calcul());

    }
}
