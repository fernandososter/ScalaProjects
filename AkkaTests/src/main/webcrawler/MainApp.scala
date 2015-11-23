package webcrawler

import akka.actor.Props
import akka.actor.ActorSystem

object MainApp extends App{
 
  
  val dbWriterSupervisor = Props(new WebDbWriterSupervisor())
  
  val processorSupervisor = Props(new WebProcessorSupervisor(dbWriterSupervisor))
  
  val crawlerSupervisor = Props(new WebCrawlerSupervisor(processorSupervisor))
  
  val system = ActorSystem("crawler")
  
  val crawlerRef = system.actorOf(crawlerSupervisor)
  crawlerRef ! "init"
  
  
}