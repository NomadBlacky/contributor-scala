package org.nomadblacky.contributor

import java.util.logging.Logger

import net.ruippeixotog.scalascraper.browser.JsoupBrowser

/**
 * Created by blacky on 17/05/29.
 */
object Contributor {
  import com.github.nscala_time.time.Imports._
  import net.ruippeixotog.scalascraper.dsl.DSL._
  import Extract._

  val logger: Logger = Logger.getLogger(this.getClass.getName)

  def getDataCount(userName: String, now: DateTime): Option[Int] = {
    val url = s"https://github.com/users/$userName/contributions"
    logger.info(s"HTTP GET: $url")
    val doc = JsoupBrowser().get(url)
    val rects = doc >> elements("rect")
    rects
      .find(_.attr("data-date") == now.toString("yyyy-MM-dd"))
      .map(_.attr("data-count"))
      .map(_.toInt)
  }
}
