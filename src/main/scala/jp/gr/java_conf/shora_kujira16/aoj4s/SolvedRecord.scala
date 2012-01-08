package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import XMLUtil.NodeSeq2AOJXML

protected case class SolvedRecord(solved_recordXml: Elem) {

  protected case class SolvedStruct(solvedXml: NodeSeq) {
    lazy val run_id: Int = (solvedXml \ "run_id" content ()).toInt

    lazy val user_id: String = solvedXml \ "user_id" content ()

    lazy val problem_id: String = solvedXml \ "problem_id" content ()

    lazy val date: Long = (solvedXml \ "date" content ()).toLong

    lazy val languate: String = solvedXml \ "languate" content ()

    lazy val cputime: Int = (solvedXml \ "cputime" content ()).toInt

    lazy val memory: Int = (solvedXml \ "memory" content ()).toInt

    lazy val code_size: Int = (solvedXml \ "code_size" content ()).toInt
  }

  lazy val solved = solved_recordXml \ "solved" map (x => SolvedStruct(x))
}
