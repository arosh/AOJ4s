package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class ProblemList(problem_listXML: Elem) {

  /** List of problems in the specified volume */
  lazy val problem_list = problem_listXML \ "problem_list" \ "problem" map (x => ProblemStruct(x))

  case class ProblemStruct(problemXML: NodeSeq) {

    /** Problem ID. */
    lazy val id: String = (problemXML \ "id").text

    /** Name of the problem. */
    lazy val name: String = (problemXML \ "name").text

    /** Time limit assigned to the problem (second). */
    lazy val problemtimelimit: Int = (problemXML \ "problemtimelimit").text.toInt

    /** Memory limit assigned to the problem (Kbyte). */
    lazy val problemmemorylimit: Int = (problemXML \ "problemmemorylimit").text.toInt

  }

}
