package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class ProblemList(problem_listXml: Elem) {

  case class ProblemStruct(problemXml: NodeSeq) {
    lazy val id: String = problemXml \ "id" text

    lazy val name: String = problemXml \ "name" text

    lazy val problemtimelimit: Int = (problemXml \ "problemtimelimit" text) toInt

    lazy val problemmemorylimit: Int = (problemXml \ "problemmemorylimit" text) toInt
  }

  lazy val problem_list = problem_listXml \ "problem_list" \ "problem" map (x => ProblemStruct(x))
}
