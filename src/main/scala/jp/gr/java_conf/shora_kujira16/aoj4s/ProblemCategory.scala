package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import XMLUtil.NodeSeq2AOJXML

protected case class ProblemCategory(problem_categoryXml: Elem) {

  protected case class ProblemStruct(problemXml: NodeSeq) {
    lazy val id: String = problemXml \ "id" content ()

    lazy val category: String = problemXml \ "category" content ()

    lazy val score: Float = (problemXml \ "score" content ()).toFloat
  }

  lazy val problem = problem_categoryXml \ "problem" map (x => ProblemStruct(x))
}
