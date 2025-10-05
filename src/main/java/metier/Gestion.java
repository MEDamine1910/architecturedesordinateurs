package metier;

import dao.ICalcul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("gestion")
public class Gestion implements IGestion {
    @Autowired
    @Qualifier("d")
    ICalcul cal;

    @Override
    public double calcul() {
        return cal.calcul()*2;
    }
    public void setCalcul(ICalcul calcul) {
        this.cal = calcul;
    }
    public Gestion() {
        super();
    }
}
