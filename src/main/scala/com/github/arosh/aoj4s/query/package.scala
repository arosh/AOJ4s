package com.github.arosh.aoj4s

package object query {

  sealed abstract class Query[A](val field: String) {
    def value: A
  }

  case class Criteria(value: Int) extends Query[Int]("criteria")

  case class Affiliation(value: String) extends Query[String]("affiliation")

  case class SolvedMin(value: Int) extends Query[Int]("solved_min")

  case class SolvedMax(value: Int) extends Query[Int]("solved_max")

  case class UserId(value: String) extends Query[String]("user_id")

  case class ProblemId(value: String) extends Query[String]("problem_id")

  case class Language(value: String) extends Query[String]("language")

  case class DateBegin(value: Long) extends Query[Long]("date_begin")

  case class DateEnd(value: Long) extends Query[Long]("date_end")

  case class Start(value: Int) extends Query[Int]("start")

  case class Limit(value: Int) extends Query[Int]("limit")

  case class Id(value: String) extends Query[String]("id")

  case class Category(value: String) extends Query[String]("category")

  trait QueryWrapper {

    implicit def WrapCriteria(value: Int) = Criteria(value)

    implicit def WrapAffiliation(value: String) = Affiliation(value)

    implicit def WrapSolvedMin(value: Int) = SolvedMin(value)

    implicit def WrapSolvedMax(value: Int) = SolvedMax(value)

    implicit def WrapUserId(value: String) = UserId(value)

    implicit def WrapProblemId(value: String) = ProblemId(value)

    implicit def WrapLanguage(value: String) = Language(value)

    implicit def WrapDateBegin(value: Long) = DateBegin(value)

    implicit def WrapDateEnd(value: Long) = DateEnd(value)

    implicit def WrapStart(value: Int) = Start(value)

    implicit def WrapLimit(value: Int) = Limit(value)

    implicit def WrapId(value: String) = Id(value)

    implicit def WrapCategory(value: String) = Category(value)

  }
}
