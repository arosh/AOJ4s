package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class StatusLog(status_logXml: Elem) {

  case class StatusStruct(statusXml: NodeSeq) {

    lazy val run_id: Int = (statusXml \ "run_id" text) toInt

    lazy val user_id: String = statusXml \ "user_id" text

    lazy val problem_id: String = statusXml \ "problem_id" text

    lazy val submission_date: Long = (statusXml \ "submission_date" text) toLong

    lazy val status: String = statusXml \ "status" text

    lazy val language: String = statusXml \ "language" text

    lazy val cpu_time: Int = (statusXml \ "cpu_time" text) toInt

    lazy val memory: Int = (statusXml \ "memory" text).toInt

    lazy val code_size: Int = (statusXml \ "code_size" text) toInt
  }

  lazy val status = status_logXml \ "status" map (x => StatusStruct(x))
}