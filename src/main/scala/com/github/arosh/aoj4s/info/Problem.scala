package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class Problem(problemXml: Elem) {
  lazy val id: String = problemXml \ "id" text

  lazy val name: String = problemXml \ "name" text

  lazy val available: Int = (problemXml \ "available" text) toInt

  lazy val problemtimelimit: Int = (problemXml \ "problemtimelimit" text) toInt

  lazy val problemmemorylimit: Int = (problemXml \ "problemmemorylimit" text) toInt

  case class StatusStruct(statusXml: NodeSeq) {
    lazy val submission: Int = (statusXml \ "submission" text) toInt

    lazy val accepted: Int = (statusXml \ "accepted" text) toInt

    lazy val wronganswer: Int = (statusXml \ "wronganswer" text) toInt

    lazy val timelimit: Int = (statusXml \ "timelimit" text) toInt

    lazy val memorylimit: Int = (statusXml \ "memorylimit" text) toInt

    lazy val outputlimit: Int = (statusXml \ "outputlimit" text) toInt

    lazy val runtimeerror: Int = (statusXml \ "runtimeerror" text) toInt
  }

  lazy val status = StatusStruct(problemXml \ "status")

  case class UserStruct(userXml: NodeSeq) {
    lazy val id: String = userXml \ "id" text

    lazy val submissiondate: Long = (userXml \ "submissiondate" text) toLong

    lazy val language: String = userXml \ "language" text

    lazy val cputime: Int = (userXml \ "cputime" text) toInt

    lazy val memory: Int = (userXml \ "memory" text) toInt

    lazy val code_size: Int = (userXml \ "code_size" text) toInt
  }

  lazy val solved_list = problemXml \ "solved_list" \ "user" map (x => UserStruct(x))
}
