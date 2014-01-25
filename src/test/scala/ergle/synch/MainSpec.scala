package ergle.synch

import org.specs2.mutable.Specification
import ergle.PathsConfigExample
import scala.collection.mutable
import org.specs2.mock.Mockito
import org.specs2.matcher.ThrownExpectations
import scala.concurrent.ExecutionContext.Implicits.global

class MainSpec extends Specification {
  "Main" should {
    "start synching files" in new PathsConfigExample2 {

      val testValue: String = "testvalue"
      val testValue2: String = "testvalue2"

      save(mutable.Buffer(testValue,testValue2))
      val main: Main = new Main
      val syncher: FolderSyncher = mock[FolderSyncher]
      main.folderSyncher = syncher

      main.start

      there were one(syncher).synch(testValue) andThen one(syncher).synch(testValue2)
    }
  }

  class PathsConfigExample2 extends PathsConfigExample with Mockito with ThrownExpectations

}


