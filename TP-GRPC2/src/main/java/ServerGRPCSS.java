import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.MultiplicationService;

import java.io.IOException;

public class ServerGRPCSS {
    public static void main(String[] args) throws InterruptedException, IOException {
        Server server = ServerBuilder.forPort(8889).addService(new MultiplicationService()).build();
        server.start();
        server.awaitTermination();
    }
}
