package org.nomadblacky.contributor.aws

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }

import scala.beans.BeanProperty

/**
 * Created by blacky on 17/05/29.
 */
class Handler extends RequestHandler[Request, Response] {
  override def handleRequest(input: Request, context: Context): Response = {
    new Response()
  }
}

case class Request(@BeanProperty userName: String) {
  def this() = this(userName = "")
}

case class Response(@BeanProperty msg: String) {
  def this() = this(msg = "")
}