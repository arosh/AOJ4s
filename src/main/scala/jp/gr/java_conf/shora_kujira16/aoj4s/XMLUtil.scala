package jp.gr.java_conf.shora_kujira16.aoj4s

import scala.xml.NodeSeq

protected object XMLUtil {

  protected class AOJXML(data: NodeSeq) {
    def content(): String = data.text.replaceAll("\n", "")
  }

  implicit def NodeSeq2AOJXML(x: NodeSeq) = new AOJXML(x)
}
