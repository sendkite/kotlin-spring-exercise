package com.community.domain

import com.community.global.enums.EntityState
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.MappedSuperclass
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity(
    @Enumerated(EnumType.STRING)
    val state: EntityState = EntityState.ACTIVE,
    val createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now(),
)
