package com.github.arosh.AOJ4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class SolvedRecord(solved_recordXml: Elem) {

  case class SolvedStruct(solvedXml: NodeSeq) {
    lazy val run_id: Int = (solvedXml \ "run_id" text) toInt

    lazy val user_id: String = solvedXml \ "user_id" text

    lazy val problem_id: String = solvedXml \ "problem_id" text

    lazy val date: Long = (solvedXml \ "date" text).toLong

    lazy val languate: String = solvedXml \ "languate" text

    lazy val cputime: Int = (solvedXml \ "cputime" text) toInt

    lazy val memory: Int = (solvedXml \ "memory" text) toInt

    lazy val code_size: Int = (solvedXml \ "code_size" text) toInt
  }

  lazy val solved = solved_recordXml \ "solved" map (x => SolvedStruct(x))
}
