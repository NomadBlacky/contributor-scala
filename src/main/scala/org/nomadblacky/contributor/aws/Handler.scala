package org.nomadblacky.contributor.aws

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

/**
  * Created by blacky on 17/05/29.
  */
class Handler extends RequestHandler[Request, Response] {
  override def handleRequest(input: Request, context: Context): Response = ???
}
