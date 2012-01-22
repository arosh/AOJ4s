package jp.gr.java_conf.shora_kujira16.aoj4s.test

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers

import jp.gr.java_conf.shora_kujira16.aoj4s.AOJ._

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

  ignore("存在しないIDでNoneを返す") {
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
