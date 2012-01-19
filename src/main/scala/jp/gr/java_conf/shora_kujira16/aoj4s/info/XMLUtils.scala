package jp.gr.java_conf.shora_kujira16.aoj4s.info

import scala.xml.NodeSeq

protected object XMLUtils {
  implicit def NodeSeq2AOJXML(x: NodeSeq) = new {
    def content(): String = x.text.replaceAll("\n", "")
  }
}
