package ReaderWriter

object ReaderWriterProblemDriver extends App {

  private val readWriteData = new ReadWriteData

  // Creation of the Threads (Read/Write)
  private val readerThreads = (1 to 5).map(_ => new ReadThread(readWriteData))
  private val writerThreads = (1 to 5).map(_ => new WriteThread(readWriteData))

  // Threads starts Executing using .start
  readerThreads.foreach(_.start())
  writerThreads.foreach(_.start())

  //.join is used for thread to wait until the current execution is finish.
  readerThreads.foreach(_.join())
  writerThreads.foreach(_.join())

  private class ReadThread(readWriteData: ReadWriteData) extends Thread {
    override def run(): Unit = {
      try {
        val value = readWriteData.readData()
        println(s"Thread ${Thread.currentThread().getName} is Reading $value")
      } catch {
        case e: InterruptedException => println(e.getMessage)
      }
    }
  }

  private class WriteThread(readWriteData: ReadWriteData) extends Thread {
    override def run(): Unit = {
      try {
        val value = (Math.random() * 100).toInt
        readWriteData.writeData(value)
        println(s"Writer ${Thread.currentThread().getName} wrote data: $value")
        Thread.sleep(1000)
      } catch {
        case e: InterruptedException => println(e.getMessage)
      }
    }
  }
}