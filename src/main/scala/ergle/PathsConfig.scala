package ergle

import java.io.{PrintWriter, File}

import scala.io.Source
import scala.collection.mutable

object PathsConfig {
  private val parentFile: File = new File(System.getProperty("user.home"), ".ergle")

  def parent: File = {
    parentFile.mkdirs()
    parentFile
  }
}

trait PathsConfig {

  val configFile: File = new File(PathsConfig.parent, "paths.list")


  def read() = {
    if (configFile.exists()) Source.fromFile(configFile).getLines().toList
    else List.empty
  }

  def save(content: mutable.Buffer[String]) {
    val writer = new PrintWriter(configFile)
    try content foreach {
      writer.println
    }
    finally writer.close()
  }
}
