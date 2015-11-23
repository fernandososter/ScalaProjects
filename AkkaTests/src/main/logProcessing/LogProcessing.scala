package logProcessing

import java.io.File

@SerialVersionUID(1L) 
class DiskError(msg: String) extends Error(msg) with Serializable

@SerialVersionUID(1L) 
class CorruptedFileException(msg: String, val file: File) extends Exception(msg) 
with Serializable


@SerialVersionUID(1L) 
class DbBrokenconnectionException(msg: String)  extends Exception(msg) 
with Serializable

object LogProcessingProtocol {
  case class LogFile(file: File) 
  case class Line(time: Long, message: String, messageType: String)
}

object FileWatcherProtocol {
  case class NewFile(file: File, timeAdded: Long)
  case class SourceAbandoned(uri: String)
}
trait FileWatchingAbilities {
  def register(uri: String) {
    
  }
}


class DbCon(url: String) {
  /**
   * Writes a map to a database.
   * @param map the map to write to the database.
   * @throws DbBrokenConnectionException when the connection is broken. It might be back later
   * @throws DbNodeDownException when the database Node has been removed from the database cluster. It will never work again.
   */
  def write(map: Map[Symbol, Any]) {
    //
  }
}



trait LogParsing {
  import LogProcessingProtocol._
  // Parses log files. creates line objects from the lines in the log file.
  // If the file is corrupt a CorruptedFileException is thrown
  def parse(file: File): Seq[Line] = {
    Nil
  }
}
