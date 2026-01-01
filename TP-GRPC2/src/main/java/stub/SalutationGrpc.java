package stub;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: Salutation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SalutationGrpc {

  private SalutationGrpc() {}

  public static final String SERVICE_NAME = "Salutation";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<stub.SalutationOuterClass.SalutRequest,
      stub.SalutationOuterClass.SalutResponse> getMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "message",
      requestType = stub.SalutationOuterClass.SalutRequest.class,
      responseType = stub.SalutationOuterClass.SalutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<stub.SalutationOuterClass.SalutRequest,
      stub.SalutationOuterClass.SalutResponse> getMessageMethod() {
    io.grpc.MethodDescriptor<stub.SalutationOuterClass.SalutRequest, stub.SalutationOuterClass.SalutResponse> getMessageMethod;
    if ((getMessageMethod = SalutationGrpc.getMessageMethod) == null) {
      synchronized (SalutationGrpc.class) {
        if ((getMessageMethod = SalutationGrpc.getMessageMethod) == null) {
          SalutationGrpc.getMessageMethod = getMessageMethod =
              io.grpc.MethodDescriptor.<stub.SalutationOuterClass.SalutRequest, stub.SalutationOuterClass.SalutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "message"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stub.SalutationOuterClass.SalutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  stub.SalutationOuterClass.SalutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SalutationMethodDescriptorSupplier("message"))
              .build();
        }
      }
    }
    return getMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SalutationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SalutationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SalutationStub>() {
        @java.lang.Override
        public SalutationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SalutationStub(channel, callOptions);
        }
      };
    return SalutationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SalutationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SalutationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SalutationBlockingStub>() {
        @java.lang.Override
        public SalutationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SalutationBlockingStub(channel, callOptions);
        }
      };
    return SalutationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SalutationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SalutationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SalutationFutureStub>() {
        @java.lang.Override
        public SalutationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SalutationFutureStub(channel, callOptions);
        }
      };
    return SalutationFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<stub.SalutationOuterClass.SalutRequest> message(
        io.grpc.stub.StreamObserver<stub.SalutationOuterClass.SalutResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getMessageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Salutation.
   */
  public static abstract class SalutationImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SalutationGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Salutation.
   */
  public static final class SalutationStub
      extends io.grpc.stub.AbstractAsyncStub<SalutationStub> {
    private SalutationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SalutationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SalutationStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<stub.SalutationOuterClass.SalutRequest> message(
        io.grpc.stub.StreamObserver<stub.SalutationOuterClass.SalutResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getMessageMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Salutation.
   */
  public static final class SalutationBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SalutationBlockingStub> {
    private SalutationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SalutationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SalutationBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Salutation.
   */
  public static final class SalutationFutureStub
      extends io.grpc.stub.AbstractFutureStub<SalutationFutureStub> {
    private SalutationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SalutationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SalutationFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_MESSAGE = 0;

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
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_MESSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.message(
              (io.grpc.stub.StreamObserver<stub.SalutationOuterClass.SalutResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getMessageMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              stub.SalutationOuterClass.SalutRequest,
              stub.SalutationOuterClass.SalutResponse>(
                service, METHODID_MESSAGE)))
        .build();
  }

  private static abstract class SalutationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SalutationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return stub.SalutationOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Salutation");
    }
  }

  private static final class SalutationFileDescriptorSupplier
      extends SalutationBaseDescriptorSupplier {
    SalutationFileDescriptorSupplier() {}
  }

  private static final class SalutationMethodDescriptorSupplier
      extends SalutationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SalutationMethodDescriptorSupplier(String methodName) {
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
      synchronized (SalutationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SalutationFileDescriptorSupplier())
              .addMethod(getMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
