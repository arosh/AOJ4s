package com.github.arosh.aoj4s

import java.net.URLEncoder

import scala.xml.Elem
import scala.xml.XML

import dispatch._

object Commons {
  /* 
   * Type Safe Builder Pattern で書いてみた。
   * 
   * 「Scala実践プログラミング」や
   * http://d.hatena.ne.jp/tototoshi/20120602/1338624041
   * を参照して下さい
   * 
   * def instance() を定義したAbstraceBuilderを定義するつもりだったが、
   * implicit parameter部の定義が違うとコンパイルエラーになった。
   * 
   * 仕様上これを避ける事はできないため、AbstractBuilderの定義を断念した。
   * 
   */

  trait On
  trait Off

  val BASE_URL = "http://judge.u-aizu.ac.jp/onlinejudge/webservice/"

  def encode(src: String): String = URLEncoder.encode(src, "UTF-8")

  def loadXML(path: String, query: Map[String, String]): Elem = {
    val req = url(BASE_URL + path) <<? query
    val str = Http(req as_str).replaceAllLiterally("\n", "")
    XML.loadString(str)
  }
}