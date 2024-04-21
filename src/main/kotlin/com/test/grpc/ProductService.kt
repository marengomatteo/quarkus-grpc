package com.test.grpc

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import com.test.grpc.ProductRepository
import com.test.grpc.Products.ProductRequest
import com.test.grpc.Products.CreateProductRequest

@ApplicationScoped
class ProductService {

    @Inject
    lateinit var productRepo: ProductRepository

    fun getProductById(productId: Long): Product? {
        return productRepo.findById(productId)
    }

    @Transactional
    fun createProduct(entity: CreateProductRequest) {
        val p: Product = Product()
        p.productName = entity.name
        p.price = entity.priceCents.toDouble()
        p.order = entity.orderable
        productRepo.persist(p)
       
    }

}