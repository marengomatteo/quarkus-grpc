package com.test.grpc

import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import io.grpc.StatusRuntimeException
import io.grpc.Status
import kotlin.math.roundToInt

import com.test.grpc.Products.ProductResponse
import com.test.grpc.Products.ProductRequest
import com.test.grpc.Product

@GrpcService
// class MutinyProductService(private val productService: ProductService): ProductsService {
class MutinyProductService(private val productService: ProductService) {

    // override fun getProduct(req: ProductRequest): Uni<ProductResponse> { 
    //    return Uni.createFrom().item(req.productId)
    //             .map { productService.getProductById(it) }
    //             .onItem().ifNull().failWith(StatusRuntimeException(Status.NOT_FOUND))
    //             .map { it?.toResponse() }
    // }
    fun getProduct(req: ProductRequest) { 
        productService.getHello()
    }
}

// fun Product.toResponse(): ProductResponse = ProductResponse.newBuilder().apply { 
//     id = productId.toString()
//     name = productName
//     priceCents = price.roundToInt()
//     orderable = order
// }.build()

