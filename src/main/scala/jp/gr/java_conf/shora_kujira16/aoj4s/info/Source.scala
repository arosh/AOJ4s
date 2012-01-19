package jp.gr.java_conf.shora_kujira16.aoj4s.info

import scala.xml.Elem

import XMLUtils.NodeSeq2AOJXML

case class Source(sourceXml: Elem) {
  lazy val id: String = sourceXml \ "id" content ()

  lazy val title: String = sourceXml \ "title" content ()

  lazy val subtitle: String = sourceXml \ "subtitle" content ()

  lazy val place: String = sourceXml \ "place" content ()

  lazy val abbr: String = sourceXml \ "abbr" content ()

  lazy val author1: String = sourceXml \ "author1" content ()

  lazy val author2: String = sourceXml \ "author2" content ()

  lazy val year: String = sourceXml \ "year" content ()

  lazy val month: String = sourceXml \ "month" content ()

  lazy val day: String = sourceXml \ "day" content ()

  lazy val note: String = sourceXml \ "note" content ()

  lazy val url: String = sourceXml \ "url" content ()

  lazy val judge: String = sourceXml \ "judge" content ()
}