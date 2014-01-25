package ergle.synch

import java.io.File
import javax.inject.{Singleton, Named}

trait FileLister {
  def list(path: String): Array[File]
}

@Named
@Singleton
class FileListerImpl extends FileLister{
  def list(path: String): Array[File] = {
    val directory = file(path)
    val files: Array[File] = directory.listFiles()
    if  (files == null) {
      Array()
    } else {
      files
    }
  }

  def file(path: String) = {
    new File(path)
  }
}
