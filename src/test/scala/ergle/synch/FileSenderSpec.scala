package ergle.synch

import org.specs2.mutable.Specification
import java.io.File
import org.specs2.mock.Mockito
import org.specs2.specification.Scope
import play.api.libs.ws.WS.WSRequestHolder
import ergle.ConfigProvider
import com.typesafe.config.Config
import org.specs2.matcher.ThrownExpectations
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.api.libs.ws.Response

class FileSenderSpec extends Specification with Mockito {
  "FileSender" should {
    "send files to web api" in new FileSenderImpl with Scope with ThrownExpectations {
      configProvider = mock[ConfigProvider]
      private val config = mock[Config]
      configProvider.config returns config
      private val testApiUrl = "testApiUrl"
      config.getString(ConfigProvider.apiUrlKey) returns testApiUrl

      private val file = new File("file")
      val requestHolder = mock[WSRequestHolder]
      requestHolder.post(file) returns mock[Future[Response]]

      send(file)

      there was one(requestHolder).post(file)

      override def url(url: String) = {
        url === testApiUrl
        requestHolder
      }
    }
  }
}
