package com.test.auth

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import com.test.auth.Request.RegisterUserRequest
import java.util.Date
import java.util.Base64
import java.security.MessageDigest

@ApplicationScoped
class UserService {

    @Inject
    lateinit var userRepo: UserRepository

    private fun encryptPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(password.toByteArray())
        return Base64.getEncoder().encodeToString(hashedBytes)
    }

    @Transactional
    fun createUser(entity: RegisterUserRequest) {
        val u: User = User()
        u.firstName = entity.firstName
        u.lastName = entity.lastName
        u.email = entity.email
        u.password = encryptPassword(entity.password)
        u.createdDate = Date()
        userRepo.persist(u)
    }
    

}