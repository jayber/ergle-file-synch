package ergle.synch

import javax.inject.{Inject, Named, Singleton}
import java.io.File
import play.api.libs.ws.WS
import ergle.ConfigProvider
import scala.concurrent.ExecutionContext

trait FileSender {
  def send(file: File)(implicit ec: ExecutionContext)
}

@Named
@Singleton
class FileSenderImpl extends FileSender {

  @Inject
  var configProvider: ConfigProvider = null

  def send(file: File)(implicit ec: ExecutionContext) {
    val apiUrl = configProvider.config.getString(ConfigProvider.apiUrlKey)
    val requestHolder = url(apiUrl)

    requestHolder.post(file).map { response =>
      println( response.statusText)
    }
  }

  def url(url: String) = {
    WS.url(url).withRequestTimeout(100)
  }
}
