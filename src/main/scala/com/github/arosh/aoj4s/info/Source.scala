package com.github.arosh.aoj4s
package info

import scala.xml.Elem
import scala.xml.NodeSeq

case class Source(sourceXML: Elem) {

  /** Problem ID. */
  lazy val id: String = (sourceXML \ "id").text

  /** Title of the source. */
  lazy val title: String = (sourceXML \ "title").text

  /** Subtitle of the source. */
  lazy val subtitle: String = (sourceXML \ "subtitle").text

  /** The place the corresponding contest. */
  lazy val place: String = (sourceXML \ "place").text

  /** Abbreviation of the title. */
  lazy val abbr: String = (sourceXML \ "abbr").text

  /** Author of the problem. */
  lazy val author1: String = (sourceXML \ "author1").text

  /** Second author of the problem. */
  lazy val author2: String = (sourceXML \ "author2").text

  /** Year */
  lazy val year: String = (sourceXML \ "year").text

  /** Month */
  lazy val month: String = (sourceXML \ "month").text

  /** Day */
  lazy val day: String = (sourceXML \ "day").text

  /** Special notes */
  lazy val note: String = (sourceXML \ "note").text

  /** URL */
  lazy val url: String = (sourceXML \ "url").text

  /** Judge */
  lazy val judge: String = (sourceXML \ "judge").text

}
