package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import AOJXMLContents.NodeSeq2AOJXML

case class StatusLog(status_logXml: Elem) {

  case class StatusStruct(statusXml: NodeSeq) {
    lazy val run_id: Int = (statusXml \ "run_id" content ()).toInt

    lazy val user_id: String = statusXml \ "user_id" content ()

    lazy val problem_id: String = statusXml \ "problem_id" content ()

    lazy val submission_date: Long = (statusXml \ "submission_date" content ()).toLong

    lazy val status: String = statusXml \ "status" content ()

    lazy val language: String = statusXml \ "language" content ()

    lazy val cpu_time: Int = (statusXml \ "cpu_time" content ()).toInt

    lazy val memory: Int = (statusXml \ "memory" content ()).toInt

    lazy val code_size: Int = (statusXml \ "code_size" content ()).toInt
  }

  lazy val status = status_logXml \ "status" map (x => StatusStruct(x))
}