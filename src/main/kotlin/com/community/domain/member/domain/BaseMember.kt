package com.community.domain.member.domain

import com.community.domain.BaseEntity
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.Table

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "member")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties(value = ["hibernateLazyInitializer", "handler"]) // lazy loading 때문에 필요
abstract class BaseMember(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false, unique = true, length = 20)
    val loginId: String,
    @Column(nullable = false)
    var passwordHash: String = "1234",
    @Column(unique = true)
    var email: String,
    var role: String = "ROLE_MEMBER",
) : BaseEntity()
