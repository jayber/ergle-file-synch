package ergle.synch

import javax.inject.{Inject, Named, Singleton}
import java.io.File
import play.api.libs.ws.WS
import ergle.ConfigProvider
import scala.concurrent.ExecutionContext
import com.typesafe.scalalogging.slf4j.{Logging, Logger}

trait FileSender {
  def send(file: File)(implicit ec: ExecutionContext)
}

@Named
@Singleton
class FileSenderImpl extends FileSender with Logging {

  @Inject
  var configProvider: ConfigProvider = null

  def send(file: File)(implicit ec: ExecutionContext) {
    logger.debug (s"sending file: ${file.getName}")

    val apiUrl = configProvider.config.getString(ConfigProvider.apiUrlKey)
    val requestHolder = url(apiUrl)

    requestHolder.withQueryString(("filename",file.getName)).put(file).map { response =>
      logger.debug( s"response: ${response.body}")
    }
  }

  def url(url: String) = {
    WS.url(url).withRequestTimeout(1000)
  }
}
