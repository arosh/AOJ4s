package com.github.arosh.aoj4s
package info

import scala.xml.Elem

case class Source(sourceXml: Elem) {

  lazy val id: String = sourceXml \ "id" text

  lazy val title: String = sourceXml \ "title" text

  lazy val subtitle: String = sourceXml \ "subtitle" text

  lazy val place: String = sourceXml \ "place" text

  lazy val abbr: String = sourceXml \ "abbr" text

  lazy val author1: String = sourceXml \ "author1" text

  lazy val author2: String = sourceXml \ "author2" text

  lazy val year: String = sourceXml \ "year" text

  lazy val month: String = sourceXml \ "month" text

  lazy val day: String = sourceXml \ "day" text

  lazy val note: String = sourceXml \ "note" text

  lazy val url: String = sourceXml \ "url" text

  lazy val judge: String = sourceXml \ "judge" text
}