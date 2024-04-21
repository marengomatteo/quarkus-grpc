package com.test.auth

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Column
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.sql.Date

@Table(name="user")
@Entity
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null
    
    @Column(name = "first_name", nullable = false)
    var firstName: String? = null

    @Column(name = "last_name", nullable = false)
    var lastName: String? = null
    
    @Column(name = "email", nullable = false)
    var email: String? = null
    
    @Column(name = "password", nullable = false)
    var password: String? = null

    @Column(name = "created_at", nullable = false)
    var createdDate: Date? = null
}