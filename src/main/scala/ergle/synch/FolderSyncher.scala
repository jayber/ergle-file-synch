package ergle.synch

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext
import com.typesafe.scalalogging.slf4j.Logging

trait FolderSyncher {
  def synch(path: String)(implicit ec: ExecutionContext)
}

@Named
@Singleton
class FolderSyncherImpl extends FolderSyncher with Logging {

  @Inject
  var fileSender: FileSender = null
  @Inject
  var fileLister: FileLister = null

  def synch(path: String)(implicit ec: ExecutionContext) {
    logger.debug(s"synching path: $path")
    val fileList = fileLister.list(path)

    fileList.foreach {
      fileSender.send
    }
  }
}
