package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class UserList(user_listXml: Elem) {

  case class UserStruct(userXml: NodeSeq) {

    lazy val rank: Int = (userXml \ "rank" text) toInt

    lazy val id: String = userXml \ "id" text

    lazy val name: String = userXml \ "name" text

    lazy val affiliation: String = userXml \ "affiliation" text

    lazy val solved: Int = (userXml \ "solved" text) toInt

    lazy val rating: Float = (userXml \ "rating" text) toFloat
  }

  lazy val user = user_listXml \ "user" map (x => UserStruct(x))
}
