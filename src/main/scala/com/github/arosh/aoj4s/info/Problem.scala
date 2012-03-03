package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class Problem(problemXML: Elem) {

  /** Problem ID. */
  lazy val id: String = (problemXML \ "id").text

  /** Problem Name. */
  lazy val name: String = (problemXML \ "name").text

  /** Judge Type (0:Not available, 1:Judge, 2:Judge allowing precision error, 3:Judge with Validator, 4:Reactive judge). */
  lazy val available: Int = (problemXML \ "available").text.toInt

  /** Time limit assigned to the problem (second). */
  lazy val problemtimelimit: Int = (problemXML \ "problemtimelimit").text.toInt

  /** Memory limit assigned to the problem (Kbyte). */
  lazy val problemmemorylimit: Int = (problemXML \ "problemmemorylimit").text.toInt

  /** Problem's status */
  lazy val status = StatusStruct(problemXML \ "status")

  case class StatusStruct(statusXML: NodeSeq) {

    /** The number of submissions. */
    lazy val submission: Int = (statusXML \ "submission").text.toInt

    /** The number of accepted submissions. */
    lazy val accepted: Int = (statusXML \ "accepted").text.toInt

    /** The number of wrong answers. */
    lazy val wronganswer: Int = (statusXML \ "wronganswer").text.toInt

    /** The number of time limit exceeding. */
    lazy val timelimit: Int = (statusXML \ "timelimit").text.toInt

    /** The number of memory limit exceeding. */
    lazy val memorylimit: Int = (statusXML \ "memorylimit").text.toInt

    /** The number of output limit exceeding. */
    lazy val outputlimit: Int = (statusXML \ "outputlimit").text.toInt

    /** The number of runtime errors. */
    lazy val runtimeerror: Int = (statusXML \ "runtimeerror").text.toInt

  }

  /** List of users who solved the problem */
  lazy val solved_list = problemXML \ "solved_list" \ "user" map (x => UserStruct(x))

  case class UserStruct(userXML: NodeSeq) {

    /** User ID. */
    lazy val id: String = (userXML \ "id").text

    /** Date of submission. */
    lazy val submissiondata: Long = (userXML \ "submissiondata").text.toLong

    /** Programming Language. */
    lazy val language: String = (userXML \ "language").text

    /** CPU Time (sentisecond). */
    lazy val cputime: Int = (userXML \ "cputime").text.toInt

    /** Memory usage (Kbyte). */
    lazy val memory: Int = (userXML \ "memory").text.toInt

    /** Code size (byte). */
    lazy val code_size: Int = (userXML \ "code_size").text.toInt

  }

}
