package feralEX

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.http4s.HttpApp
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

import org.http4s.Request

import cats.effect.IO
import cats.effect.kernel.Resource

import feral.functions.IOAzureHttpFunction
import feral.functions.facade.InvocationContext
import org.http4s.Response

object feralEXB8 extends IOAzureHttpFunction {
  override def handler: InvocationContext => Resource[IO,HttpApp[IO]] = {
    context => {
      val app = HttpRoutes
        .of[IO] {
          case GET -> Root / "api" / "hello" / name =>
            Ok(s"Hello, $name.")

          case req @ PUT -> Root / "api" =>
            Ok(req.body)
        }
        .orNotFound

      Resource.pure(app)
    }
  }
}