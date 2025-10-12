package presentation;

import dao.ICalcul;
import metier.IGestion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXml {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
        ICalcul calcul = context.getBean("d" , ICalcul.class);
        System.out.println(calcul.calcul());
        IGestion gestion = context.getBean( IGestion.class);
        System.out.println(gestion.calcul());
    }
}
