package service;

import io.grpc.stub.StreamObserver;
import stub.SalutationGrpc;
import stub.SalutationOuterClass;

public class SalutationService extends SalutationGrpc.SalutationImplBase {

    @Override
    public StreamObserver<SalutationOuterClass.SalutRequest> message(StreamObserver<SalutationOuterClass.SalutResponse> responseObserver) {
        return new StreamObserver<>() {
            StringBuilder noms = new StringBuilder();
            @Override
            public void onNext(SalutationOuterClass.SalutRequest salutRequest) {
                noms.append(salutRequest.getName()).append(",");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                SalutationOuterClass.SalutResponse response = SalutationOuterClass.SalutResponse.newBuilder()
                        .setReponse("Bonjour " + noms.toString())
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
