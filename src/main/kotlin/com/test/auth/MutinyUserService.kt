package com.test.auth

import io.quarkus.grpc.GrpcService
import io.grpc.stub.StreamObserver
import com.test.auth.AuthServiceGrpc
import com.google.protobuf.Empty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@GrpcService
class MutinyUserService(private val userService: UserService): AuthServiceGrpc.AuthServiceImplBase() {

    override fun registerUser(request: Request.RegisterUserRequest, responseObserver: StreamObserver<Empty>) {
        CoroutineScope(Dispatchers.IO).launch {
                userService.createUser(request)
                responseObserver.onNext(Empty.getDefaultInstance())
                responseObserver.onCompleted()
        }
    }

}
