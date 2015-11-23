package logProcessing

import akka.actor.Props
import akka.actor.Actor
import akka.actor.OneForOneStrategy
import akka.dispatch.sysmsg.Resume
import akka.actor.SupervisorStrategy.{ Stop, Resume, Restart }


class LogProcSupervisor(dbSupervisorProps: Props) extends Actor  {
 
  override def supervisorStrategy = OneForOneStrategy() {
    case _: CorruptedFileException => Resume
  }
  
  val dbSupervisor = context.actorOf(dbSupervisorProps)
  val logProcProps = Props(new LogProcessor(dbSupervisor))
  val logProcessor = context.actorOf(logProcProps)
  
  def receive = {
    case m => logProcessor forward (m)
  }
  
}