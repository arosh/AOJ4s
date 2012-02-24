package com.github.arosh.aoj4s
package test

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers

import AOJ._

class ProblemTest extends FunSuite with MustMatchers {
  SLEEP_TIME = 1000

  test("require で 無効なIDを弾く") {
    intercept[IllegalArgumentException] {
      problem("-1")
    }

    intercept[IllegalArgumentException] {
      problem("1")
    }
    intercept[IllegalArgumentException] {
      problem("111111")
    }
    intercept[IllegalArgumentException] {
      problem("-1111")
    }
  }

  test("存在しないIDでNoneを返す") {
    problem("3000") must be === None
    problem("30000") must be === None
  }

  test("情報は正しい") {
    val x = problem("0518")
    x must not be (None)
    x.get.id must be === "0518"
    x.get.name must be === "The Oldest Site"
    // println(x.get.solved_list.map(_.id))
  }
}
