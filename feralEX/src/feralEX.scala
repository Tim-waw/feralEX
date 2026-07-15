package feralEX

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.http4s.HttpApp
import org.http4s.HttpRoutes
import org.http4s.dsl.io._

import cats.effect.IO
import cats.effect.kernel.Resource

import feral.functions.IOAzureHttpFunction
import feral.functions.facade.InvocationContext

object feralEX3 extends IOAzureHttpFunction {
  override def buildHttpApp(context: InvocationContext): Resource[IO, HttpApp[IO]] = 
    val app = HttpRoutes
      .of[IO] {
        case GET -> Root / "hello" / name =>
          Ok(s"Hello, $name.")
      }
      .orNotFound

    Resource.pure(app)
}

// zip -r functionapp.zip \
//   host.json \
//   package.json \
//   package-lock.json \
//   src \
//   node_modules \
//   -x "local.settings.json" ".vscode/*" ".git/*"