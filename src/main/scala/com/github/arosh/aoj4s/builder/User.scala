package com.github.arosh.aoj4s.builder

import scala.util.control.Exception.allCatch

import com.github.arosh.aoj4s.Commons._
import com.github.arosh.aoj4s.info.user.User
import com.github.arosh.aoj4s.info.XMLProtocol

import scalaxb.fromXML

object UserBuilder {
  def apply() = new UserBuilder[Off]
}

class UserBuilder[ID_S] private (
  val param: Map[String, String] = Map.empty)
    extends AbstractBuilder {

  type Self = UserBuilder[ID_S]
  type Target = User

  val path = "user"

  def id(id: String)(
    implicit NotHaveID_S: ID_S =:= Off): UserBuilder[On] = {

    val id_E = encode(id)

    new UserBuilder(param.updated("id", id_E))
  }

  /**
   * ネットワーク周りの例外はそのまま投げる
   * userIdが存在しない時はNoneを返す
   */
  def instance()(
    implicit HaveID_S: ID_S =:= On): Target = {

    val xml = loadXML(path, param)

    fromXML[Target](xml)

  }
}