package ergle.synch

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global

class FolderSyncherSpec extends Specification with Mockito {
  "FolderSyncher" should {
    "send new files to ergle" in {
      val path = "testVal"
      val pathFile1 = new File("path1")
      val pathFile2 = new File("path2")

      val lister = mock[FileLister]
      lister.list(path) returns Array(pathFile1,pathFile2)
      val sender = mock[FileSender]

      val folderSyncherImpl: FolderSyncherImpl = new FolderSyncherImpl
      folderSyncherImpl.fileLister = lister
      folderSyncherImpl.fileSender = sender

      folderSyncherImpl.synch(path)

      there was one(lister).list(path) andThen one(sender).send(pathFile1) andThen one(sender).send(pathFile2)
    }
  }
}
