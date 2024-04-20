package com.test.grpc

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProductService {

    // fun getProductById(productId: String): Product? {
    //     return 
    // }

    fun getHello():String {
        return "hello"
    }

}

