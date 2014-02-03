import scala.io._
import scala.actors._
import Actor._

object  HomePageLoader {
	
	val portalUrl = "http://de.wikipedia.org/wiki/Portal:Geist_und_Gehirn"

	def fetchPortalPage() = {
		val caller = self

		actor { caller ! (portalUrl, Source.fromURL(portalUrl))}

		receive {
			case (portalUrl, text) =>
				println(portalUrl)
				println(text)
		}
	}
}

HomePageLoader.fetchPortalPage()