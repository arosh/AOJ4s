package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class ProblemCategory(problem_categoryXML: Elem) {

  /** List of problems. */
  lazy val problem_category = problem_categoryXML \ "problem_category" \ "problem" map (x => ProblemStruct(x))

  case class ProblemStruct(problemXML: NodeSeq) {

    /** Problem ID. */
    lazy val id: String = (problemXML \ "id").text

    /** Category Name. */
    lazy val category: String = (problemXML \ "category").text

    /** Score assigned to the problem. */
    lazy val score: Float = (problemXML \ "score").text.toFloat

  }

}
