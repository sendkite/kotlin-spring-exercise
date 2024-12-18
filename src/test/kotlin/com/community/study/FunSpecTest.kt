package com.community.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FunSpecTest :
    FunSpec({
        // single test
        test("String length should return the length of the string") {
            val str = "Hello, World!"
            str.length shouldBe 13
        }

        // nested test
        context("String substring function") {
            test("should return the substring of the hello") {
                val str = "Hello, World!"
                str.substring(0, 5) shouldBe "Hello"
            }

            test("should return the substring of world") {
                val str = "Hello, World!"
                str.substring(7, 13) shouldBe "World!"
            }

            test("throws an exception if the start index is greater than the end index") {
                val str = "Hello, World!"
                val exception =
                    shouldThrow<StringIndexOutOfBoundsException> {
                        str.substring(7, 5)
                    }
                exception.message shouldBe "begin 7, end 5, length 13"
            }
        }
    })
