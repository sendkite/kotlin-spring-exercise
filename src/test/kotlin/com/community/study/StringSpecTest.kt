package com.community.study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringSpecTest :
    StringSpec(
        {
            "String length should return the length of the string" {
                val str = "Hello, World!"
                str.length shouldBe 13
            }
        },
    )
