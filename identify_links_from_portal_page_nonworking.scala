import scala.io._
import scala.actors._
import Actor._
import scala.xml._

object HomePageLoader {
	
	val portalUrl = "http://de.wikipedia.org/wiki/Portal:Geist_und_Gehirn"

	def extractUrls(text: String): String = {
		val xml = XML.loadString(text)
		return (xml \\ "a" \ "@href").mkString
	}

	def fetchPortalPage() = {
		val caller = self

		actor { caller ! (portalUrl, Source.fromURL(portalUrl).mkString)}

		receive {
			case (portalUrl, text) =>
				print(extractUrls((text.asInstanceOf[String])))
		}
	}
}	

HomePageLoader.fetchPortalPage()