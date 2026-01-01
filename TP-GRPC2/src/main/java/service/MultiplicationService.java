package service;

import proxy.MultiplicationGrpc;
import proxy.MultiplicationOuterClass;

public class MultiplicationService extends MultiplicationGrpc.MultiplicationImplBase {
    @Override
    public void calcul(proxy.MultiplicationOuterClass.MultiRequest request,
                       io.grpc.stub.StreamObserver<proxy.MultiplicationOuterClass.MultiResponse> responseObserver){
        int nombre = request.getNumber();
        int limite = request.getLimit();
        for (int i = 1; i <= limite; i++) {
            String result = nombre + " x " + i + " = " + (nombre * i);
            MultiplicationOuterClass.MultiResponse response = MultiplicationOuterClass.MultiResponse.newBuilder()
                    .setResult(Integer.parseInt(result))
                    .build();

            responseObserver.onNext(response);
        }
// Terminer le streaming
        responseObserver.onCompleted();
    }

}
