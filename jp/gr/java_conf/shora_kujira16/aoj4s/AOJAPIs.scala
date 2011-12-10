package jp.gr.java_conf.shora_kujira16.aoj4s

import java.net.URL
import java.net.URLEncoder
import xml.XML

object AOJAPIs extends QueryWrapper {
  var basedURL = "http://judge.u-aizu.ac.jp/onlinejudge/webservice/"
  var SLEEP_TIME = 10000

  def joinQuery[T <: Query[Any]](queries: T*): String = {
    queries.withFilter(null !=).map(p => "%s=%s".format(p.field, p.value)).mkString("?", "&", "")
  }

  /**
   * Provides detailed information of the specific user.
   *
   * @param id User ID.
   */
  def user(id: String): User = {
    // check
    require(id.length >= 1, "User ID is invalid.")

    // get
    val userXml = XML.load(new URL(basedURL + "user?id=" + id))
    Thread.sleep(SLEEP_TIME)

    User(userXml)
  }

  /**
   * Provides detailed information of the specific problem.
   *
   * @param id Problem ID.
   */
  def problem(id: String): Problem = {
    // check
    require(4 <= id.length() && id.length() <= 5 && id.forall(_.isDigit), "Problem ID is invalid.")

    // get
    val problemXml = XML.load(new URL(basedURL + "problem?id=" + id))
    Thread.sleep(SLEEP_TIME)

    Problem(problemXml)
  }

  /**
   * Provides a list of problems in the specific volume.
   *
   * @param volume Volume No.
   */
  def problem_list(volume: Int): ProblemList = {
    // check
    require(0 <= volume && volume <= 100, "Volume No is invalid.")

    // get
    val problem_listXml = XML.load(new URL(basedURL + "problem_list?volume=" + volume))
    Thread.sleep(SLEEP_TIME)

    ProblemList(problem_listXml)
  }

  /**
   * Provides a list of users meeting specified criteria.
   *
   * @param criteria Criteria for sotring. 0 = order by solved, 1 = order by rating.
   * @param affiliation A substring in the affiliation.
   * @param solved_min The service returns a list of users who solved at least solved_min problems.
   * @param solved_max The service returns a list of users who solved at most solved_max problems.
   */
  def user_list(criteria: Criteria = null, affiliation: Affiliation = null, solved_min: SolvedMin = null, solved_max: SolvedMax = null): UserList = {
    // check
    if (criteria != null) {
      require(criteria.value == 0 || criteria.value == 1)
    }

    // get
    val encoded_affiliation = if(affiliation == null) null else Affiliation(URLEncoder.encode(affiliation.value, "UTF-8"))
    val query = joinQuery(criteria, encoded_affiliation, solved_min, solved_max)

    val user_listXml = XML.load(new URL(basedURL + "user_list" + query))
    Thread.sleep(SLEEP_TIME)

    UserList(user_listXml)
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
  def solved_record(user_id: UserID = null, problem_id: ProblemID = null, language: Language = null, date_begin: DateBegin = null, date_end: DateEnd = null): SolvedRecord = {
    // check
    require(user_id != null || problem_id != null, "User ID or Problem ID should be specified.")
    if (problem_id != null) {
      require(problem_id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(user_id, problem_id, language, date_begin, date_end)

    val solved_recordXml = XML.load(new URL(basedURL + "solved_record" + query))
    Thread.sleep(SLEEP_TIME)

    SolvedRecord(solved_recordXml)
  }

  /**
   * Provides a list of submission records meeting specified criteria.
   *
   * @param user_id User ID.
   * @param problem_id Problem ID.
   * @param start Start position.
   * @param limit The number of records. (1 ≤ limit ≤ 20)
   */
  def status_log(user_id: UserID = null, problem_id: ProblemID = null, start: Start = null, limit: Limit = null): StatusLog = {
    // check
    if (problem_id != null) {
      require(problem_id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(user_id, problem_id, start, limit)

    val status_logXml = XML.load(new URL(basedURL + "status_log" + query))
    Thread.sleep(SLEEP_TIME)

    StatusLog(status_logXml)
  }

  object categories {
    val SF = "straight"
    val DM = "datamanipu"
    val SM = "simulation"
    val ST = "string"
    val PR = "parsing"
    val PZ = "puzzle"
    val CG = "geometry"
    val GR = "graph"
    val NS = "numeric"
    val CP = "combinatorial"
    val NT = "number"
    val PB = "probability"
  }

  /**
   * Provides categories and the corresponding scores of all problems or the specific problem.
   * Please note that, the scores have been assigned subjectively
   * and there are problems which we have not assigned the score yet.
   *
   * @param id Problem ID.
   * @param category Category Name.
   */
  def problem_category(id: ID = null, category: Category = null): ProblemCategory = {
    // check
    if (id != null) {
      require(id.value.forall(_.isDigit), "Problem ID is invalid.")
    }

    // get
    val query = joinQuery(id, category)

    val problem_categoryXml = XML.load(new URL(basedURL + "problem_category" + query))
    Thread.sleep(SLEEP_TIME)

    ProblemCategory(problem_categoryXml)
  }

  /**
   * Provides detailed information of the source of a specific problem.
   *
   * @param id Problem ID.
   */
  def source(id: String): Source = {
    // check
    require(id.forall(_.isDigit), "Problem ID is invalid.")

    // get
    val sourceXml = XML.load(new URL(basedURL + "source?id=" + id))
    Thread.sleep(SLEEP_TIME)

    Source(sourceXml)
  }
}
