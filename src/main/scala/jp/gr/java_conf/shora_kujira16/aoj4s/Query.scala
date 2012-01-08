package jp.gr.java_conf.shora_kujira16.aoj4s

protected sealed abstract class Query[+A](val field: String) {
  def value: A
}

protected case class Criteria(value: Int) extends Query[Int]("criteria")
protected case class Affiliation(value: String) extends Query[String]("affiliation")
protected case class SolvedMin(value: Int) extends Query[Int]("solved_min")
protected case class SolvedMax(value: Int) extends Query[Int]("solved_max")
protected case class UserID(value: String) extends Query[String]("user_id")
protected case class ProblemID(value: String) extends Query[String]("problem_id")
protected case class Language(value: String) extends Query[String]("language")
protected case class DateBegin(value: Long) extends Query[Long]("date_begin")
protected case class DateEnd(value: Long) extends Query[Long]("date_end")
protected case class Start(value: Int) extends Query[Int]("start")
protected case class Limit(value: Int) extends Query[Int]("limit")
protected case class ID(value: String) extends Query[String]("id")
protected case class Category(value: String) extends Query[String]("category")

protected trait QueryWrapper {
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
