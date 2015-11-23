package logProcessing

import akka.actor.Props
import akka.actor.Actor
import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy.{ Stop, Resume, Restart }
import scala.concurrent.duration._

class DbImpatientSupervisor(writerProps: Props) extends Actor {
  override def supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 5, withinTimeRange = 60 seconds) {
    case _: DbBrokenconnectionException => Restart
  }
  
  val writer = context.actorOf(writerProps)
  def receive = {
    case m => writer forward(m)
  }
}