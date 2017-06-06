package org.nomadblacky.contributor.aws

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import org.nomadblacky.contributor.Contributor

import scala.beans.BeanProperty

/**
 * Created by blacky on 17/05/29.
 */
class Handler extends RequestHandler[Request, Response] {
  import com.github.nscala_time.time.Imports._

  override def handleRequest(input: Request, context: Context): Response = {
    val now = DateTime.now
    val msg =
      Contributor.getDataCount(input.userName, now)
        .map(c => s"$c")
        .getOrElse(s"Not found: ${now.toString("yyyy-MM-dd")}")
    Response(msg)
  }
}

case class Request(@BeanProperty userName: String) {
  def this() = this(userName = "")
}

case class Response(@BeanProperty msg: String) {
  def this() = this(msg = "")
}