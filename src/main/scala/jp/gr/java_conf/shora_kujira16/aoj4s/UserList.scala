package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.Elem
import scala.xml.NodeSeq

import XMLUtil.NodeSeq2AOJXML

protected case class UserList(user_listXml: Elem) {

  protected case class UserStruct(userXml: NodeSeq) {
    lazy val rank: Int = (userXml \ "rank" content ()).toInt

    lazy val id: String = userXml \ "id" content ()

    lazy val name: String = userXml \ "name" content ()

    lazy val affiliation: String = userXml \ "affiliation" content ()

    lazy val solved: Int = (userXml \ "solved" content ()).toInt

    lazy val rating: Float = (userXml \ "rating" content ()).toFloat
  }

  lazy val user = user_listXml \ "user" map (x => UserStruct(x))
}
