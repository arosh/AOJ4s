package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class StatusLog(status_logXML: Elem) {

  /** List of status records */
  lazy val status_list = status_logXML \ "status_list" \ "status" map (x => StatusStruct(x))

  case class StatusStruct(statusXML: NodeSeq) {

    /** Run ID. */
    lazy val run_id: Int = (statusXML \ "run_id").text.toInt

    /** User ID. */
    lazy val user_id: String = (statusXML \ "user_id").text

    /** Problem ID. */
    lazy val problem_id: String = (statusXML \ "problem_id").text

    /** Date of submission. */
    lazy val submission_date: Long = (statusXML \ "submission_date").text.toLong

    /** Judge status. */
    lazy val status: String = (statusXML \ "status").text

    /** Programming language. */
    lazy val language: String = (statusXML \ "language").text

    /** CPU Time (sentisecond). */
    lazy val cputime: Int = (statusXML \ "cputime").text.toInt

    /** Memory usage (Kbyte). */
    lazy val memory: Int = (statusXML \ "memory").text.toInt

    /** Code size (byte). */
    lazy val code_size: Int = (statusXML \ "code_size").text.toInt

  }

}
