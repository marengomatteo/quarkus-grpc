package com.test.grpc

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Column
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import com.test.grpc.Products.ProductResponse
import kotlin.math.roundToInt

@Table(name = "product")
@Entity
open class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productId: Long? = null
    
    @Column(name = "product_name", nullable = false)
    var productName: String? = null
    
    @Column(name = "price", nullable = false)
    var price: Double = 0.0
    
    @Column(name = "orderable", nullable = false)
    var order: Boolean = false

    fun togRPC(): ProductResponse = ProductResponse.newBuilder().apply { 
        id = productId.toString()
        name = productName
        priceCents = price.roundToInt()
        orderable = order
    }.build()


}