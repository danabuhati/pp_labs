import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.parser.Parser

//clase pentru adt


data class feed_item(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String
)

data class feed(
    val title: String,
    val items: List<feed_item>
)

fun main() {
    val url = "http://rss.cnn.com/rss/edition.rss" //exemplu hardcodat da

    try {

        val doc: Document = Jsoup.connect(url)
            .parser(Parser.xmlParser())
            .get()

        val feed_title = doc.selectFirst("channel > title")?.text() ?: "unknown link"



        val items_list = doc.select("item").map { element ->
            feed_item( //construire clasa item
                title = element.selectFirst("title")?.text() ?: "",
                link = element.selectFirst("link")?.text() ?: "",
                description = element.selectFirst("description")?.text() ?: "",
                pubDate = element.selectFirst("pubDate")?.text() ?: ""
            )
        }

        val feed = feed(feed_title,items_list) //construire clasa feed
        println("Feed: ${feed.title}")

        //test
        feed.items.forEach { item ->
            println("Title: ${item.title}")
            println("Date: ${item.pubDate}")
            println("Description: ${item.description}")
            println("Link:  ${item.link}")
            println()
        }

    } catch (e: Exception) {
        println("processing error")
    }
}