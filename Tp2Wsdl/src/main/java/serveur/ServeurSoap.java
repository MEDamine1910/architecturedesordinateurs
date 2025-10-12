package serveur;

import jakarta.xml.ws.Endpoint;

public class ServeurSoap {

        public static void main(String[] args) {
            String url="http://localhost:8081/";
            Endpoint.publish(url,new EtudiantWebService());
            System.out.println(url);
        }

}
