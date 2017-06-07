package org.nomadblacky.contributor.aws

import java.util.logging.Logger

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }
import org.nomadblacky.contributor.Contributor

import scala.beans.BeanProperty
import scalaj.http.Http

/**
 * Created by blacky on 17/05/29.
 */
class Handler extends RequestHandler[Request, Response] {
  import com.github.nscala_time.time.Imports._

  val logger: Logger = Logger.getLogger(getClass.getName)

  val envName: String = "SLACK_WEBHOOK_URL"

  lazy val webHookUrl: String =
    sys.env.getOrElse(envName, throw new IllegalStateException(s"Environment variable `$envName` is not found"))

  override def handleRequest(input: Request, context: Context): Response = {
    val now = DateTime.now
    val count =
      Contributor.getDataCount(input.userName, now)
        .getOrElse(throw new IllegalStateException(s"Not found: ${now.toString("yyyy-MM-dd")}"))

    logger.info(s"Today's contribute counts: $count")

    if (count < 1) {
      postToSlack(":warning:", "You have not contributed GitHub today!")
    }

    Response(msg = s"OK: $count")
  }

  def postToSlack(iconEmoji: String, msg: String, webHookUrl: String = webHookUrl): Unit = {
    val json = s"""{"icon_emoji": $iconEmoji, "text": "$msg"}"""
    logger.info(s"HTTP POST: $webHookUrl")
    logger.info(s"Params: $json")
    Http(webHookUrl).postForm(Seq("payload" → json)).asString
  }
}

case class Request(@BeanProperty var userName: String) {
  def this() = this(userName = "")
}

case class Response(@BeanProperty var msg: String) {
  def this() = this(msg = "")
}