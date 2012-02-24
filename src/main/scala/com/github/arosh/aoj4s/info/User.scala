package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class User(userXml: Elem) {

  lazy val id: String = userXml \ "id" text

  lazy val name: String = userXml \ "name" text

  lazy val affiliation: String = userXml \ "affiliation" text

  lazy val registerdate: Long = (userXml \ "registerdate" text) toLong

  lazy val lastsubmitdate: Long = (userXml \ "lastsubmitdate" text) toLong

  case class StatusStruct(statusXml: NodeSeq) {

    lazy val submission: Int = (statusXml \ "submission" text) toInt

    lazy val solved: Int = (statusXml \ "solved" text) toInt

    lazy val accepted: Int = (statusXml \ "accepted" text) toInt

    lazy val wronganswer: Int = (statusXml \ "wronganswer" text) toInt

    lazy val timelimit: Int = (statusXml \ "timelimit" text) toInt

    lazy val memorylimit: Int = (statusXml \ "memorylimit" text) toInt

    lazy val outputlimit: Int = (statusXml \ "outputlimit" text) toInt

    lazy val runtimeerror: Int = (statusXml \ "runtimeerror" text) toInt

    lazy val compileerror: Int = (statusXml \ "compileerror" text) toInt
  }

  lazy val status = StatusStruct(userXml \ "status")

  case class ProblemStruct(problemXml: NodeSeq) {

    lazy val id: String = problemXml \ "id" text

    lazy val submissiondate: Long = (problemXml \ "submissiondate" text) toLong

    lazy val language: String = problemXml \ "language" text

    lazy val cputime: Int = (problemXml \ "cputime" text) toInt

    lazy val memory: Int = (problemXml \ "memory" text) toInt

    lazy val code_size: Int = (problemXml \ "code_size" text) toInt
  }

  lazy val solved_list = userXml \ "solved_list" \ "problem" map (x => ProblemStruct(x))
}
