package com.github.arosh.aoj4s
package info

import scala.xml.NodeSeq

protected object XMLUtils {
  implicit def NodeSeq2AOJXML(x: NodeSeq) = new {
    def content(): String = x.text.replaceAll("\n", "")
  }
}
