import scala.io._
import scala.actors._
import Actor._

val portal_url = 'http://de.wikipedia.org/wiki/Portal:Geist_und_Gehirn'

def fetchPortalPage() = {
	val caller = self

	actor { caller ! (portal_url, Source.fromURL(portal_url))}

	receive {
		case (url, text) =>
			println(url)
			println(text)
	}
}