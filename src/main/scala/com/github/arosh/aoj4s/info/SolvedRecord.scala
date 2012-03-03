package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class SolvedRecord(solved_recordXML: Elem) {

  /** List of solved records */
  lazy val solved_record_list = solved_recordXML \ "solved_record_list" \ "solved" map (x => SolvedStruct(x))

  case class SolvedStruct(solvedXML: NodeSeq) {

    /** Run ID. */
    lazy val run_id: Int = (solvedXML \ "run_id").text.toInt

    /** User ID. */
    lazy val user_id: String = (solvedXML \ "user_id").text

    /** Problem ID. */
    lazy val problem_id: String = (solvedXML \ "problem_id").text

    /** Date of submission. */
    lazy val date: Long = (solvedXML \ "date").text.toLong

    /** Programming language. */
    lazy val language: String = (solvedXML \ "language").text

    /** CPU Time (sentisecond). */
    lazy val cputime: Int = (solvedXML \ "cputime").text.toInt

    /** Memory usage (Kbyte). */
    lazy val memory: Int = (solvedXML \ "memory").text.toInt

    /** Code size (byte). */
    lazy val code_size: Int = (solvedXML \ "code_size").text.toInt

  }

}
