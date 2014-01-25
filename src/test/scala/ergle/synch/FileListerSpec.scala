package ergle.synch

import org.specs2.mutable.Specification
import java.io.File
import org.specs2.mock.Mockito
import org.specs2.specification.Scope

class FileListerSpec extends Specification with Mockito {
  "FileLister" should {
    "list new files" in new FileListerImpl with Scope {
      val directoryFile = mock[File]
      directoryFile.listFiles() returns Array(new File("file1"), new File("file2"))

      list("directory")

      there was one(directoryFile).listFiles()

      override def file(path: String): File = {
        directoryFile
      }
    }
  }
}
