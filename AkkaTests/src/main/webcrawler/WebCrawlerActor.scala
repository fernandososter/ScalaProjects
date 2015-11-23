package webcrawler

import akka.actor.ActorRef
import akka.actor.Actor
import scalaj.http.HttpRequest
import scalaj.http.Http

class WebCrawlerActor(nomeFonte: String, url: String , timeToPool: Long ,webProcessorSupervisorRef: ActorRef)
extends Actor
{
  val request: HttpRequest = Http(url) 
  
  def initCrawler() ={ 
    import Mensagens._
    
    while(true) {
      println("pooling")
      webProcessorSupervisorRef ! WebConteudo(request.asString.toString())
      
      Thread.sleep(timeToPool)
      
    }
  }
  
  def receive = {
    case _=> initCrawler()
  }
  
}