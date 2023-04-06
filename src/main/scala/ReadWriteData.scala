package ReaderWriter

import java.util.concurrent.locks.ReentrantReadWriteLock

class ReadWriteData {
  private val lock = new ReentrantReadWriteLock()
  private var data = 0

  def readData(): Int = {
    lock.readLock.lock()
    try {
      data
    } finally {
      lock.readLock().unlock()
    }
  }

  def writeData(value: Int): Unit = {
    lock.writeLock().lock() //Acquire Lock
    try {
      data = value
    } finally {
      lock.writeLock().unlock() // As soon as Writing is been done it will unlock
    }
  }
}

