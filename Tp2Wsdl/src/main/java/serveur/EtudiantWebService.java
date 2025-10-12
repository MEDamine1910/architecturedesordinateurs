package serveur;

import enums.Genre;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import model.Student;

import java.util.List;
@WebService(serviceName = "serviceWeb")
public class EtudiantWebService {
    @WebMethod(operationName = "listStudents")
    public List<Student> listEtudiants(){
        return List.of(new Student(1,"nom1","pren1", Genre.HOMME),
                new Student(1,"nom2","pren2", Genre.FEMME),
                new Student(1,"nom3","pren3", Genre.HOMME)
                );
    }
}
