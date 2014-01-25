package ergle.synch

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import javax.inject.{Named, Singleton, Inject}
import ergle.PathsConfig
import scala.concurrent._
import java.util.concurrent.{TimeUnit, Executors}

object Main extends App {

  val es = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutorService(es)

  var ctx: AnnotationConfigApplicationContext = new AnnotationConfigApplicationContext()
  ctx.scan("ergle")
  ctx.refresh()
  ctx.start()

  val main: Main = ctx.getBean("main").asInstanceOf[Main]
  main.start

  future {
    Thread.sleep(2 * 1000)
    es.shutdown()
    es.awaitTermination(10, TimeUnit.SECONDS)

    println("We're done bro")
    sys.exit()
  }
}

@Named
@Singleton
class Main extends PathsConfig {

  @Inject
  var folderSyncher: FolderSyncher = null

  val paths = read()

  def start(implicit ec: ExecutionContext) {
    paths.map {
      folderSyncher.synch
    }
  }
}