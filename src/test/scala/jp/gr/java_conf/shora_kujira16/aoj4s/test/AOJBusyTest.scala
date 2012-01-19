package jp.gr.java_conf.shora_kujira16.aoj4s.test

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers
import jp.gr.java_conf.shora_kujira16.aoj4s.AOJAPIs._

class AOJBusyTest extends FunSuite with MustMatchers {
  ignore("AOJに繋がらないとき、Noneを返す") {
    problem("1000") must be === None
    problem_category("1024", categories.CG) must be === None
    problem_list(5) must be === None
    solved_record("sho518", "1024", "JAVA") must be === None
    source("1024") must be === None
    status_log(limit = 20) must be === None
    user("sho518") must be === None
    user_list(affiliation = "Japan NEET University") must be === None
  }
}
