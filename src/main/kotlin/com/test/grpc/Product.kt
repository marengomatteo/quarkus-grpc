package com.test.grpc

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Column
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity

@Table(name = "product")
@Entity
open class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var productId: Long? = null
    
    @Column(name = "product_name", nullable = false)
    open var productName: String? = null
    
    @Column(name = "price", nullable = false)
    open var price: Double = 0.0
    
    @Column(name = "orderable", nullable = false)
    open var order: Boolean = false
}