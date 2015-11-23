package logProcessing

import akka.actor.Props
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.AllForOneStrategy
import akka.actor.Terminated
import akka.actor.PoisonPill
import akka.actor.SupervisorStrategy.{ Stop, Resume, Restart }


class FileWatchingSupervisor(sources: Vector[String], logProcSuperProps: Props) 
extends Actor {
  
  var fileWatchers: Vector[ActorRef] = sources.map { source => 
    val logProcSupervisor = context.actorOf(logProcSuperProps)
    val fileWatcher = context.actorOf(Props(
        new FileWatcher(source, logProcSupervisor)))
 
    context.watch(fileWatcher)
    fileWatcher
  }
  
  override def supervisorStrategy = AllForOneStrategy() {
    case _: DiskError => Stop
  }
  
  def receive = {
    
    case Terminated(fileWatcher) => fileWatchers = fileWatchers.filterNot { w => w == fileWatcher }
  
    if (fileWatchers.isEmpty) self ! PoisonPill
  }
  
}