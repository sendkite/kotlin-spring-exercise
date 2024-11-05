package com.community.domain

import com.community.enums.EntityState
import java.time.Instant

open class BaseEntity(
    val state: EntityState = EntityState.ACTIVE,
    val createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now(),
    val createdBy: Long = 0,
    var modifiedBy: Long = 0,
)
