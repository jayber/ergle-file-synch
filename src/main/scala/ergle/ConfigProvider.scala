package ergle

import javax.inject.{Named, Singleton}
import com.typesafe.config.{ConfigFactory, Config}
import java.io.File


object ConfigProvider {
  val apiUrlKey: String = "api.url"
}

trait ConfigProvider {
  def config: Config
}

@Named
@Singleton
class ConfigProviderImpl extends ConfigProvider{
  def config: Config = {
    val configFile: File = new File(PathsConfig.parent, "application.properties")
    ConfigFactory.parseFile(configFile)
  }
}
