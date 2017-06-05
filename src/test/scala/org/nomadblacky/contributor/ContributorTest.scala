package org.nomadblacky.contributor

import org.scalatest.{ FunSuite, Matchers }
import com.github.nscala_time.time.Imports._

/**
 * Created by blacky on 17/06/05.
 */
class ContributorTest extends FunSuite with Matchers {

  test("getDataCount") {
    val count = Contributor.getDataCount("NomadBlacky", DateTime.parse("2017-6-1"))
    println(count)
  }

}
