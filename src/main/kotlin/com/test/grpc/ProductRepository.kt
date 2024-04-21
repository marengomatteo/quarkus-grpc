package com.test.grpc

import jakarta.enterprise.context.ApplicationScoped
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import com.test.grpc.Product
import java.util.UUID


@ApplicationScoped
class ProductRepository: PanacheRepository<Product> { }