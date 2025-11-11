package serveur;

import enums.Genre;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import model.Student;

import java.util.List;

@WebService(serviceName = "EtudiantWService")
public class EtudiantWebService {
    @WebMethod(operationName = "listStudents")
    public List<Student> students(){
        return List.of(
                new Student(1,"Doe","John", Genre.HOMME),
                new Student(2,"Smith","Jane", Genre.FEMME),
                new Student(3,"Brown","Alex", Genre.AUTRE)
        );
    }
}
