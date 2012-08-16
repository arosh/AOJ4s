package com.github.arosh.aoj4s.builder

import scala.util.control.Exception.allCatch

import com.github.arosh.aoj4s.Commons._
import com.github.arosh.aoj4s.info.problem.Problem

import scalaxb.fromXML

object ProblemBuilder {
  def apply(): ProblemBuilder[Off] = new ProblemBuilder()
}

class ProblemBuilder[ID_S] private (
  val param: Map[String, String] = Map.empty)
    extends AbstractBuilder {

  type Self = ProblemBuilder[ID_S]
  type Target = Problem

  val path = "problem"

  def id(id: String)(
    implicit HaveID_S: ID_S =:= Off): ProblemBuilder[On] = {

    val id_E = encode(id)

    new ProblemBuilder(param.updated("id", id_E))
  }

  def status(status: Boolean): Self = {

    val status_s = if (status) "true" else "false"

    val status_E = encode(status_s)

    new ProblemBuilder(param.updated("status", status_E))
  }

  def instance()(implicit HaveID_S: ID_S =:= On): Target = {

    val xml = loadXML(path, param)

    fromXML[Target](xml)
  }
}