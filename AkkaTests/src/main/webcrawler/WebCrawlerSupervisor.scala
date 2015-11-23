package webcrawler

import akka.actor.Actor
import akka.actor.Props


// o supervisor recebe o props de quem ele irá instanciar um nivel abaixo: 
class WebCrawlerSupervisor(webProcessorSupervisor: Props) 
extends Actor {
 
  val webProcessorSupervisorRef = context.actorOf(webProcessorSupervisor)
  val webCrawler = Props(new WebCrawlerActor("g1","http://g1.globo.com",5000, webProcessorSupervisorRef))
  val webCrawlerRef = context.actorOf(webCrawler)
  
  def receive = {
    case m => webCrawlerRef forward m
  }
  
}