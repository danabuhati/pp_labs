import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.parser.Parser as JsoupParser
import org.yaml.snakeyaml.Yaml
import java.io.IOException




interface parser{
    fun parse(nume: String) : Map<String, Any>;
}

class crawler(private val url: String){
    private val client = OkHttpClient()

    fun getResources() : Response {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            return client.newCall(request).execute()
    }

    fun processContent(parseResult: parser): Map<String, Any>{
        val response = getResources()
        return response.use{
            val bodyText = it.body?.string()?: ""
            parseResult.parse(sbodyText)
        }

    }
}


class JSONParser : parser{
    override fun parse(nume: String): Map<String, Any> {
        val JsonObject = JSONObject(nume)
        return JsonObject.toMap()
    }
}

class XMLParser : parser{
    override fun parse(nume: String): Map<String, Any>
    {
        val XMLResult = Jsoup.parse(nume, "", JsoupParser.xmlParser())
        val result= mutableMapOf<String, Any>()
        XMLResult.children().forEach{
            element -> result[element.tagName()] = element.text()
        }
        return result
    }
}

class YAMLParser : parser{
    override fun parse(nume: String): Map<String, Any> {
        val yamlResult: Yaml = Yaml()
        val inputStream = this.javaClass
            .getClassLoader()
            .getResourceAsStream("customer.yaml")
        val obj: Map<String, Any> = yamlResult.load(inputStream)
        return obj
    }
}


fun main() {
    // 1. Testing the JSON Parser
    println("--- Testing JSON ---")
    val jsonUrl = "https://jsonplaceholder.typicode.com/todos/1"
    val jsonCrawler = crawler(jsonUrl)
    val jsonResult = jsonCrawler.processContent(JSONParser())
    println("Parsed JSON Map: $jsonResult")

    // 2. Testing the XML Parser
    println("\n--- Testing XML ---")
    val xmlUrl = "https://www.w3schools.com/xml/note.xml"
    val xmlCrawler = crawler(xmlUrl)
    val xmlResult = xmlCrawler.processContent(XMLParser())
    println("Parsed XML Map: $xmlResult")

    // 3. Testing the YAML Parser
    // Note: This relies on src/main/resources/customer.yaml existing
    println("\n--- Testing YAML ---")
    try {
        val yamlParser = YAMLParser()
        // We pass an empty string because your YAMLParser implementation
        // currently ignores the input string and reads from a file instead.
        val yamlResult = yamlParser.parse("")
        println("Parsed YAML Map: $yamlResult")
    } catch (e: Exception) {
        println("YAML Error: ${e.message} (Make sure customer.yaml exists in resources)")
    }
}