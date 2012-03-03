package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class UserList(user_listXML: Elem) {

  /** List of users */
  lazy val user_list = user_listXML \ "user_list" \ "user" map (x => UserStruct(x))

  case class UserStruct(userXML: NodeSeq) {

    /** The rank based on the specified criteria. */
    lazy val rank: Int = (userXML \ "rank").text.toInt

    /** User ID. */
    lazy val id: String = (userXML \ "id").text

    /** Name of the user. */
    lazy val name: String = (userXML \ "name").text

    /** Affiliation of the user. */
    lazy val affiliation: String = (userXML \ "affiliation").text

    /** The number of solved problems. */
    lazy val solved: Int = (userXML \ "solved").text.toInt

    /** Rating of the user.   */
    lazy val rating: Float = (userXML \ "rating").text.toFloat

  }

}
