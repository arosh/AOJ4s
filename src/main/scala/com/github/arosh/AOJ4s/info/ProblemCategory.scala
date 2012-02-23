package com.github.arosh.AOJ4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class ProblemCategory(problem_categoryXml: Elem) {

  case class ProblemStruct(problemXml: NodeSeq) {
    lazy val id: String = problemXml \ "id" text

    lazy val category: String = problemXml \ "category" text

    lazy val score: Float = (problemXml \ "score" text) toFloat
  }

  lazy val problem = problem_categoryXml \ "problem" map (x => ProblemStruct(x))
}
