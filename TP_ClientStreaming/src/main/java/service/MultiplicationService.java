package service;

import io.grpc.stub.StreamObserver;
import proxy.MultiplicationGrpc;
import proxy.MultiplicationOuterClass;

public class MultiplicationService extends MultiplicationGrpc.MultiplicationImplBase{
    @Override
    public void calcul(MultiplicationOuterClass.MultiRequest request,

                                       StreamObserver<MultiplicationOuterClass.MultiResponse> responseObserver) {

        int nombre = request.getNombre();
        int limite = request.getLimite();
        for (int i = 1; i <= limite; i++) {
            String result = nombre + " x " + i + " = " + (nombre * i);
            MultiplicationOuterClass.MultiResponse response = MultiplicationOuterClass.MultiResponse.newBuilder()
                    .setResultat(result)
                    .build();
// Envoyer le message au client
            responseObserver.onNext(response);
        }
// Terminer le streaming
        responseObserver.onCompleted();
    }
}