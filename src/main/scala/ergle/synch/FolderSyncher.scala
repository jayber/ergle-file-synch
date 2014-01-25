package ergle.synch

import javax.inject.{Inject, Named, Singleton}
import scala.concurrent.ExecutionContext

trait FolderSyncher {
  def synch(path: String)(implicit ec: ExecutionContext)
}

@Named
@Singleton
class FolderSyncherImpl extends FolderSyncher {

  @Inject
  var fileSender: FileSender = null
  @Inject
  var fileLister: FileLister = null

  def synch(path: String)(implicit ec: ExecutionContext) {
    val fileList = fileLister.list(path)

    fileList.foreach {
      fileSender.send
    }
  }
}
