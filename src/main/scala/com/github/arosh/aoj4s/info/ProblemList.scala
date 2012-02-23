package com.github.arosh.AOJ4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

import XMLUtils.NodeSeq2AOJXML

case class ProblemList(problem_listXml: Elem) {

  case class ProblemStruct(problemXml: NodeSeq) {
    lazy val id: String = problemXml \ "id" content ()

    lazy val name: String = problemXml \ "name" content ()

    lazy val problemtimelimit: Int = (problemXml \ "problemtimelimit" content ()).toInt

    lazy val problemmemorylimit: Int = (problemXml \ "problemmemorylimit" content ()).toInt
  }

  lazy val problem_list = problem_listXml \ "problem_list" \ "problem" map (x => ProblemStruct(x))
}
