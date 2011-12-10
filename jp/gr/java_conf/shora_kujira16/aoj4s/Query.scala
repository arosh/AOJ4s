package jp.gr.java_conf.shora_kujira16.aoj4s

sealed abstract class Query[+A](val field: String) {
  def value: A
}

case class Criteria(value: Int) extends Query[Int]("criteria")
case class Affiliation(value: String) extends Query[String]("affiliation")
case class SolvedMin(value: Int) extends Query[Int]("solved_min")
case class SolvedMax(value: Int) extends Query[Int]("solved_max")
case class UserID(value: String) extends Query[String]("user_id")
case class ProblemID(value: String) extends Query[String]("problem_id")
case class Language(value: String) extends Query[String]("language")
case class DateBegin(value: Long) extends Query[Long]("date_begin")
case class DateEnd(value: Long) extends Query[Long]("date_end")
case class Start(value: Int) extends Query[Int]("start")
case class Limit(value: Int) extends Query[Int]("limit")
case class ID(value: String) extends Query[String]("id")
case class Category(value: String) extends Query[String]("category")

trait QueryWrapper {
  implicit def WrapCriteria(value: Int) = Criteria(value)
  implicit def WrapAffiliation(value: String) = Affiliation(value)
  implicit def WrapSolvedMin(value: Int) = SolvedMin(value)
  implicit def WrapSolvedMax(value: Int) = SolvedMax(value)
  implicit def WrapUserID(value: String) = UserID(value)
  implicit def WrapProblemID(value: String) = ProblemID(value)
  implicit def WrapLanguage(value: String) = Language(value)
  implicit def WrapDateBegin(value: Long) = DateBegin(value)
  implicit def WrapDateEnd(value: Long) = DateEnd(value)
  implicit def WrapStart(value: Int) = Start(value)
  implicit def WrapLimit(value: Int) = Limit(value)
  implicit def WrapID(value: String) = ID(value)
  implicit def WrapCategory(value: String) = Category(value)
}
