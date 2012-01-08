package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import AOJXMLContents.NodeSeq2AOJXML

case class User(userXml: Elem) {
  lazy val id: String = userXml \ "id" content ()

  lazy val name: String = userXml \ "name" content ()

  lazy val affiliation: String = userXml \ "affiliation" content ()

  lazy val registerdate: Long = (userXml \ "registerdate" content ()).toLong

  lazy val lastsubmitdate: Long = (userXml \ "lastsubmitdate" content ()).toLong

  case class StatusStruct(statusXml: NodeSeq) {
    lazy val submission: Int = (statusXml \ "submission" content ()).toInt

    lazy val solved: Int = (statusXml \ "solved" content ()).toInt

    lazy val accepted: Int = (statusXml \ "accepted" content ()).toInt

    lazy val wronganswer: Int = (statusXml \ "wronganswer" content ()).toInt

    lazy val timelimit: Int = (statusXml \ "timelimit" content ()).toInt

    lazy val memorylimit: Int = (statusXml \ "memorylimit" content ()).toInt

    lazy val outputlimit: Int = (statusXml \ "outputlimit" content ()).toInt

    lazy val runtimeerror: Int = (statusXml \ "runtimeerror" content ()).toInt

    lazy val compileerror: Int = (statusXml \ "compileerror" content ()).toInt
  }

  lazy val status = StatusStruct(userXml \ "status")

  case class ProblemStruct(problemXml: NodeSeq) {
    lazy val id: String = problemXml \ "id" content ()

    lazy val submissiondate: Long = (problemXml \ "submissiondate" content ()).toLong

    lazy val language: String = problemXml \ "language" content ()

    lazy val cputime: Int = (problemXml \ "cputime" content ()).toInt

    lazy val memory: Int = (problemXml \ "memory" content ()).toInt

    lazy val code_size: Int = (problemXml \ "code_size" content ()).toInt
  }

  lazy val solved_list = userXml \ "solved_list" \ "problem" map (x => ProblemStruct(x))
}
