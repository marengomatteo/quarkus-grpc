package com.test.auth

import jakarta.enterprise.context.ApplicationScoped
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import com.test.auth.User

@ApplicationScoped
class UserRepository: PanacheRepository<User> { }