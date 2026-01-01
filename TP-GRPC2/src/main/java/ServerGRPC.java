import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.SalutationService;

import java.io.IOException;

public class ServerGRPC {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8888)
                .addService(new SalutationService())
                .build();
        server.start();
        System.out.println("Server started on port 8888");
        server.awaitTermination();
    }
}
