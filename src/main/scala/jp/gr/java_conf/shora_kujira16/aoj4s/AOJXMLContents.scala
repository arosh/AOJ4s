package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.NodeSeq

object AOJXMLContents {

  class AOJXML(data: NodeSeq) {
    def content() = data.text.replaceAll("\n", "")
  }

  implicit def NodeSeq2AOJXML(x: NodeSeq) = new AOJXML(x)
}
