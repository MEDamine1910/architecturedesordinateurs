package client;

import stub.EtudiantWebService;
import stub.ServiceWeb;
import stub.Student;

import java.util.List;

public class ClientSOAP {
    public static void main(String[] args) {
        EtudiantWebService wsdl = new ServiceWeb().getEtudiantWebServicePort();
        List<Student> liste = wsdl.listStudents();
        liste.forEach(elm -> {
            System.out.println(elm.getNom()
                    + "----" + elm.getPrenom());
        });
    }
}