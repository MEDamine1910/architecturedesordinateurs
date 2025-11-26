package service;

import io.grpc.stub.StreamObserver;
import stub.SalutationGrpc;
import stub.SalutationOuterClass;

public class SalutationService extends SalutationGrpc.SalutationImplBase {
    @Override
    public StreamObserver<SalutationOuterClass.SalutRequest> message(StreamObserver<SalutationOuterClass.SalutResponse> responseObserver) {
        return new StreamObserver<>() {
            StringBuilder names = new StringBuilder();
            @Override
            public void onNext(SalutationOuterClass.SalutRequest request) {
// Traite chaque message envoyé par le client
                names.append(request.getNom()).append(", ");
            }
            @Override
            public void onError(Throwable t) {
                System.err.println("Erreur reçue : " + t.getMessage());
            }
            @Override
            public void onCompleted() {
// Envoie une réponse au client une fois que tous les messages sont reçus
                String responseMessage = "Salut à tous : " + names.toString();
                SalutationOuterClass.SalutResponse response = SalutationOuterClass.SalutResponse.newBuilder()
                        .setReponse(responseMessage)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();};};}}