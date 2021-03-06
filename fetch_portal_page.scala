import scala.io._
import scala.actors._
import Actor._

object HomePageLoader {
	
	val portalUrl = "http://de.wikipedia.org/wiki/Portal:Geist_und_Gehirn"

	def fetchPortalPage() = {
		val caller = self

		actor { caller ! (portalUrl, Source.fromURL(portalUrl).mkString)}

		receive {
			case (portalUrl, text) =>
				println(portalUrl)
				print(text)
		}
	}
}	

HomePageLoader.fetchPortalPage()