package logProcessing

import akka.actor.Props
import akka.actor.Actor
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy.{ Stop, Resume, Restart }

class DbSupervisor(writeProps: Props) extends Actor {
  
  override def supervisorStrategy = OneForOneStrategy() {
    case _: DbBrokenconnectionException => Restart
  }
  
  val writer = context.actorOf(writeProps)
  def receive={
    case m => writer forward (m)
  }
  
}