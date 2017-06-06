package org.nomadblacky.contributor

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import org.scalatest.{FunSuite, Matchers}

import scala.io.Source

/**
 * Created by blacky on 17/06/06.
 */
class ScalaScraparTest extends FunSuite with Matchers {
  import net.ruippeixotog.scalascraper.dsl.DSL._
  import Extract._

  lazy val html =
    Source.fromURL(getClass.getResource("test.html"))
      .getLines()
      .mkString("\n")

  lazy val doc = JsoupBrowser().parseString(html)

  test("Extractor") {
    val result = doc >> elements("rect") >> attrs("data-date")
    result.take(3) shouldBe List("2016-06-05", "2016-06-06", "2016-06-07")
  }

}
