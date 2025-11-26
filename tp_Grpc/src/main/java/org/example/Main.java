package org.example;
import app.grpc.User;
import app.grpc.UserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static app.grpc.UserGrpc.newBlockingStub;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost",9082).usePlaintext().build();
        UserGrpc.userBlockingStub userStub = newBlockingStub(channel);
        User.LoginRequest login= User.LoginRequest.newBuilder().setUserName("GRPC").setPassword("GRPC").build();
        User.LoginResponse response=userStub.login(login);
        System.out.println(response.getMsgResponse());
        channel.shutdown();
    }
}