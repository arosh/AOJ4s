package com.github.arosh.aoj4s

import java.net.URLEncoder
import query._
import info._

object AOJ extends QueryWrapper {
  private val AOJ_URI = "http://judge.u-aizu.ac.jp/onlinejudge/webservice/"

  private type Tagged[U] = { type Tag = U }
  protected class SleepTimeTag
  type SleepTime = Int with Tagged[SleepTimeTag]

  implicit def Int2SleepTime(i: Int): SleepTime = i.asInstanceOf[SleepTime]
  private val DEFAULT_SLEEP_TIME = 10000

  private def joinQuery[T <: Query[_]](queries: T*): String = {
    queries.withFilter(null !=).map(p => "%s=%s".format(p.field, p.value)).mkString("?", "&", "")
  }

  private def loadXML(params: String)(implicit st: SleepTime): Option[xml.Elem] = {
    try {
      val source = scala.io.Source.fromURL(AOJ_URI + params, "UTF-8")
      val str = source.mkString.replaceAll("\n", "")
      source.close()
      Thread.sleep(st)

      val ret = scala.xml.XML.loadString(str)
      Some(ret)
    } catch {
      case e: Exception => None
    }
  }

  /**
   * Provides detailed information of the specific user.
   *
   * @param id User ID.
   */
  def user(id: String)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[User] = {
    // check
    require(id.length >= 1, "User ID is invalid.")

    // get
    val userXml = loadXML("user?id=" + id)(st)

    userXml.map {
      x => User(x)
    }
  }

  /**
   * Provides detailed information of the specific problem.
   *
   * @param id Problem ID.
   */
  def problem(id: String)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[Problem] = {
    // check
    require(4 <= id.length() && id.length() <= 5 && id.forall(_.isDigit), "Problem ID is invalid.")

    // get
    val problemXml = loadXML("problem?id=" + id)(st)

    problemXml filter {
      x =>
        x != <problem></problem>
    } map {
      x => Problem(x)
    }
  }

  /**
   * Provides a list of problems in the specific volume.
   *
   * @param volume Volume No.
   */
  def problem_list(volume: Int)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[ProblemList] = {
    // check
    require(0 <= volume && volume <= 100, "Volume No is invalid.")

    // get
    val problem_listXml = loadXML("problem_list?volume=" + volume)(st)

    problem_listXml.map {
      x => ProblemList(x)
    }
  }

  /**
   * Provides a list of users meeting specified criteria.
   *
   * @param criteria Criteria for sorting. 0 = order by solved, 1 = order by rating.
   * @param affiliation A substring in the affiliation.
   * @param solved_min The service returns a list of users who solved at least solved_min problems.
   * @param solved_max The service returns a list of users who solved at most solved_max problems.
   */
  def user_list(criteria: Criteria = null, affiliation: Affiliation = null, solved_min: SolvedMin = null, solved_max: SolvedMax = null)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[UserList] = {
    // check
    if (criteria != null) {
      require(criteria.value == 0 || criteria.value == 1)
    }

    // get
    val encoded_affiliation = if (affiliation == null) null else Affiliation(URLEncoder.encode(affiliation.value, "UTF-8"))
    val query = joinQuery(criteria, encoded_affiliation, solved_min, solved_max)

    val user_listXml = loadXML("user_list" + query)(st)

    user_listXml.map {
      x => UserList(x)
    }
  }

  /**
   * Provides a list of accepted submission records meeting specified criteria.
   *
   * @param user_id User ID.
   * @param problem_id Problem ID.
   * @param language Programming language.
   * @param date_begin The service returns records after date_begin.
   * @param date_end The service returns records before date_end.
   */
  def solved_record(user_id: UserID = null, problem_id: ProblemID = null, language: Language = null, date_begin: DateBegin = null, date_end: DateEnd = null)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[SolvedRecord] = {
    // check
    require(user_id != null || problem_id != null, "User ID or Problem ID should be specified.")
    if (problem_id != null) {
      require(problem_id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(user_id, problem_id, language, date_begin, date_end)

    val solved_recordXml = loadXML("solved_record" + query)(st)

    solved_recordXml.map {
      x => SolvedRecord(x)
    }
  }

  /**
   * Provides a list of submission records meeting specified criteria.
   *
   * @param user_id User ID.
   * @param problem_id Problem ID.
   * @param start Start position.
   * @param limit The number of records. (1 <= limit <= 20)
   */
  def status_log(user_id: UserID = null, problem_id: ProblemID = null, start: Start = null, limit: Limit = null)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[StatusLog] = {
    // check
    if (problem_id != null) {
      require(problem_id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(user_id, problem_id, start, limit)

    val status_logXml = loadXML("status_log" + query)(st)

    status_logXml.map {
      x => StatusLog(x)
    }
  }

  /**
   * Provides categories and the corresponding scores of all problems or the specific problem.
   * Please note that, the scores have been assigned subjectively
   * and there are problems which we have not assigned the score yet.
   *
   * @param id Problem ID.
   * @param category Category Name.
   */
  def problem_category(id: ID = null, category: Category = null)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[ProblemCategory] = {
    // check
    if (id != null) {
      require(id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(id, category)

    val problem_categoryXml = loadXML("problem_category" + query)(st)

    problem_categoryXml.map {
      x => ProblemCategory(x)
    }
  }

  /**
   * Provides detailed information of the source of a specific problem.
   *
   * @param id Problem ID.
   */
  def source(id: String)(implicit st: SleepTime = DEFAULT_SLEEP_TIME): Option[Source] = {
    // check
    require(id.forall(_.isDigit), "Problem ID is invalid.")

    // get
    val sourceXml = loadXML("source?id=" + id)(st)

    sourceXml.map {
      x => Source(x)
    }
  }
}
