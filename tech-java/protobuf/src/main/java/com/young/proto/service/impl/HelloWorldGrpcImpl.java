package com.young.proto.service.impl;

import com.young.proto.service.GrpcHelloWorldService.HelloReply;
import com.young.proto.service.GrpcHelloWorldService.HelloRequest;
import com.young.proto.service.HelloWorldGrpc;
import io.grpc.stub.StreamObserver;

public class HelloWorldGrpcImpl extends HelloWorldGrpc.HelloWorldImplBase {

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder().setMessage("Hello"+ request.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();

  }
}
