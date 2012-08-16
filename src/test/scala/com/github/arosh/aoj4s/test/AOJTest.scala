package com.github.arosh.aoj4s.test

import scala.Console.err

import java.util.concurrent.TimeUnit.SECONDS.sleep

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import com.github.arosh.aoj4s.AOJ

class AOJTest extends Specification {
  // pendingUntilFixed

  args(sequential = true)

  "AOJ.user" should {
    "id: sho518 はSomeを返すこと" in {
      var obj = AOJ.user.id("sho518").instance

      sleep(5)

      obj !== None
    }

    "id: sho519 はNoneを返すこと" in {
      var obj = AOJ.user().id("sho519").instance

      sleep(5)

      obj === None
    }

    "id: sho518 のnameが @shora_kujira16 であること" in {
      var obj = AOJ.user().id("sho518").instance

      sleep(5)

      obj.name === "@shora_kujira16"
    }.pendingUntilFixed
  }

  "AOJ.problem" should {
    "id: 10000 はSomeを返すこと" in {
      var obj = AOJ.problem.id("10000").instance

      sleep(5)

      obj === None
    }.pendingUntilFixed
  }
}