package proxy;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: Multiplication.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MultiplicationGrpc {

  private MultiplicationGrpc() {}

  public static final String SERVICE_NAME = "Multiplication";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<proxy.MultiplicationOuterClass.MultiRequest,
      proxy.MultiplicationOuterClass.MultiResponse> getCalculMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calcul",
      requestType = proxy.MultiplicationOuterClass.MultiRequest.class,
      responseType = proxy.MultiplicationOuterClass.MultiResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<proxy.MultiplicationOuterClass.MultiRequest,
      proxy.MultiplicationOuterClass.MultiResponse> getCalculMethod() {
    io.grpc.MethodDescriptor<proxy.MultiplicationOuterClass.MultiRequest, proxy.MultiplicationOuterClass.MultiResponse> getCalculMethod;
    if ((getCalculMethod = MultiplicationGrpc.getCalculMethod) == null) {
      synchronized (MultiplicationGrpc.class) {
        if ((getCalculMethod = MultiplicationGrpc.getCalculMethod) == null) {
          MultiplicationGrpc.getCalculMethod = getCalculMethod =
              io.grpc.MethodDescriptor.<proxy.MultiplicationOuterClass.MultiRequest, proxy.MultiplicationOuterClass.MultiResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calcul"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proxy.MultiplicationOuterClass.MultiRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proxy.MultiplicationOuterClass.MultiResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MultiplicationMethodDescriptorSupplier("calcul"))
              .build();
        }
      }
    }
    return getCalculMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MultiplicationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultiplicationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultiplicationStub>() {
        @java.lang.Override
        public MultiplicationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultiplicationStub(channel, callOptions);
        }
      };
    return MultiplicationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MultiplicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultiplicationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultiplicationBlockingStub>() {
        @java.lang.Override
        public MultiplicationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultiplicationBlockingStub(channel, callOptions);
        }
      };
    return MultiplicationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MultiplicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MultiplicationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MultiplicationFutureStub>() {
        @java.lang.Override
        public MultiplicationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MultiplicationFutureStub(channel, callOptions);
        }
      };
    return MultiplicationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void calcul(proxy.MultiplicationOuterClass.MultiRequest request,
        io.grpc.stub.StreamObserver<proxy.MultiplicationOuterClass.MultiResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalculMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Multiplication.
   */
  public static abstract class MultiplicationImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MultiplicationGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Multiplication.
   */
  public static final class MultiplicationStub
      extends io.grpc.stub.AbstractAsyncStub<MultiplicationStub> {
    private MultiplicationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultiplicationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultiplicationStub(channel, callOptions);
    }

    /**
     */
    public void calcul(proxy.MultiplicationOuterClass.MultiRequest request,
        io.grpc.stub.StreamObserver<proxy.MultiplicationOuterClass.MultiResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getCalculMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Multiplication.
   */
  public static final class MultiplicationBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MultiplicationBlockingStub> {
    private MultiplicationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultiplicationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultiplicationBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<proxy.MultiplicationOuterClass.MultiResponse> calcul(
        proxy.MultiplicationOuterClass.MultiRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getCalculMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Multiplication.
   */
  public static final class MultiplicationFutureStub
      extends io.grpc.stub.AbstractFutureStub<MultiplicationFutureStub> {
    private MultiplicationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MultiplicationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MultiplicationFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CALCUL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALCUL:
          serviceImpl.calcul((proxy.MultiplicationOuterClass.MultiRequest) request,
              (io.grpc.stub.StreamObserver<proxy.MultiplicationOuterClass.MultiResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCalculMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              proxy.MultiplicationOuterClass.MultiRequest,
              proxy.MultiplicationOuterClass.MultiResponse>(
                service, METHODID_CALCUL)))
        .build();
  }

  private static abstract class MultiplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MultiplicationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proxy.MultiplicationOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Multiplication");
    }
  }

  private static final class MultiplicationFileDescriptorSupplier
      extends MultiplicationBaseDescriptorSupplier {
    MultiplicationFileDescriptorSupplier() {}
  }

  private static final class MultiplicationMethodDescriptorSupplier
      extends MultiplicationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MultiplicationMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MultiplicationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MultiplicationFileDescriptorSupplier())
              .addMethod(getCalculMethod())
              .build();
        }
      }
    }
    return result;
  }
}
