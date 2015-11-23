package logProcessing

import akka.actor.ActorSystem
import akka.actor.Props

object LogProcessingApp extends App{
  
  val sources = Vector("file://c:/temp","file:///source2")
  val system = ActorSystem("logprocessing")
  
  def createCon() = new DbCon("http://dbtest")
  
  val writerProps = Props(new DbWriter(createCon()))
  
  val dbSuperProps = Props(new DbSupervisor(writerProps))
  
  val logProcSuperProps = Props(new LogProcSupervisor(dbSuperProps))
  
  val topLevelProps = Props(new FileWatchingSupervisor(sources,logProcSuperProps))
  
  system.actorOf(topLevelProps)

}