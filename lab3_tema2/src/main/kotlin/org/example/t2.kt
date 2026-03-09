package org.example
import java.io.File;

import  jdk.internal.joptsimple.util.RegexMatcher.regex

fun main(args: Array<String>) {
    val match_page = Regex("[\\s]{2,}[0-9]+[\\s]{2,}")
    val match_multiple_spaces = Regex("[\\r\\t\\v\\f\\n]{2,}")

    fun readFile(file_name: String)
            = File(file_name).inputStream().readBytes().toString(Charsets.UTF_8)


    val content: String = File("book.txt").readText()
    //replacements
     val rm_pages = match_page.replace(content, "") //se elimina complet
     val rm_spaces = match_multiple_spaces.replace(rm_pages, " "); //se inlocuieste cu un singur spatiu

    File("out.txt").writeText(rm_spaces)

}
