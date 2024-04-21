package com.test.grpc

import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import io.smallrye.common.annotation.Blocking
import io.grpc.StatusRuntimeException
import io.grpc.Status
import com.google.protobuf.Empty


import com.test.grpc.Products.ProductResponse
import com.test.grpc.Products.ProductRequest
import com.test.grpc.Products.CreateProductRequest
import com.test.grpc.Product

@GrpcService
class MutinyProductService(private val productService: ProductService): ProductsService {

    @Blocking
    override fun getProduct(req: ProductRequest): Uni<ProductResponse> { 
      return Uni.createFrom().item(req.productId)
                 .map { productService.getProductById(it.toLong()) }
                 .onItem().ifNull().failWith(StatusRuntimeException(Status.NOT_FOUND))
                 .map { it?.togRPC() }
    }

    @Blocking
    override fun createProduct(req: CreateProductRequest): Uni<Empty> {
       return Uni.createFrom().item {
            productService.createProduct(req)
            Empty.getDefaultInstance()
        }
    }

  
}



