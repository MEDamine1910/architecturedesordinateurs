import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.SalutationService;

import java.io.IOException;

public class ServerGRPC {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder
                .forPort(8888)
                .addService(new SalutationService())
                .build();

        server.start();
        System.out.println("Server started on port: " + server.getPort());

        server.awaitTermination();
    }
}
