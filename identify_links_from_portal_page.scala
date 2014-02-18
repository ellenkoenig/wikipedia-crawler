import scala.io._
import scala.actors._
import Actor._
import scala.xml.XML
import scala.xml.parsing.NoBindingFactoryAdapter
import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl
import org.xml.sax.InputSource
import java.io.StringReader

object HomePageLoader {
	
	val portalUrl = "http://de.wikipedia.org/wiki/Portal:Geist_und_Gehirn"

	lazy val adapter = new NoBindingFactoryAdapter
  	lazy val parser = (new SAXFactoryImpl).newSAXParser

	def extractUrls(source: String): String = {
    	val xml = adapter.loadXML(new InputSource(new StringReader(source)), parser)
	val href = (xml \\ "a" \\ "@href")
	return href.text //todo/next step Convert this into an array of URLs rather than one long String
	}

	def fetchPortalPage() = {
		val caller = self

		actor { caller ! (Source.fromURL(portalUrl).mkString)}

		receive {
			case (source) =>
				println(extractUrls(source.asInstanceOf[String]))
				//todo/next step: Filter out all external links (we don't need them) 
				// and prepend wikipedia.org to all internal links
		}
	}
}	

HomePageLoader.fetchPortalPage()
