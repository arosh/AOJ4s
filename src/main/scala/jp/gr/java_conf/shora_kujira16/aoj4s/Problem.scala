package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import AOJXMLContents.NodeSeq2AOJXML

case class Problem(problemXml: Elem) {
  lazy val id: String = problemXml \ "id" content ()

  lazy val name: String = problemXml \ "name" content ()

  lazy val available: Int = (problemXml \ "available" content ()).toInt

  lazy val problemtimelimit: Int = (problemXml \ "problemtimelimit" content ()).toInt

  lazy val problemmemorylimit: Int = (problemXml \ "problemmemorylimit" content ()).toInt

  case class StatusStruct(statusXml: NodeSeq) {
    lazy val submission: Int = (statusXml \ "submission" content ()).toInt

    lazy val accepted: Int = (statusXml \ "accepted" content ()).toInt

    lazy val wronganswer: Int = (statusXml \ "wronganswer" content ()).toInt

    lazy val timelimit: Int = (statusXml \ "timelimit" content ()).toInt

    lazy val memorylimit: Int = (statusXml \ "memorylimit" content ()).toInt

    lazy val outputlimit: Int = (statusXml \ "outputlimit" content ()).toInt

    lazy val runtimeerror: Int = (statusXml \ "runtimeerror" content ()).toInt
  }

  lazy val status = StatusStruct(problemXml \ "status")

  case class UserStruct(userXml: NodeSeq) {
    lazy val id: String = userXml \ "id" content ()

    lazy val submissiondate: Long = (userXml \ "submissiondate" content ()).toLong

    lazy val language: String = userXml \ "language" content ()

    lazy val cputime: Int = (userXml \ "cputime" content ()).toInt

    lazy val memory: Int = (userXml \ "memory" content ()).toInt

    lazy val code_size: Int = (userXml \ "code_size" content ()).toInt
  }

  lazy val solved_list = problemXml \ "solved_list" \ "user" map (x => UserStruct(x))
}
