package presentation;

import dao.CalculV1;
import dao.CalculV2;
import dao.ICalcul;
import metier.Gestion;

public class Inst {
    public static void main(String[] args) {
        ICalcul calcul = new CalculV1();
        System.out.println(calcul.calcul());
        Gestion gestion = new Gestion();
        gestion.setCalcul(calcul);
        System.out.println(gestion.calcul());
    }
}
