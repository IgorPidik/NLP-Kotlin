import java.io.File

/**
 * Created by igor on 25.7.2017.
 */

class WordsAndTagsHolder {

    private enum class RareWordCategory {
        _NUMERIC_, _ALL_CAPITALS_, _LAST_CAPITAL_, _RARE_
    }

    private val tagWordCount = HashMap<Pair<String, String>, Int>()
    private val nGramArray = arrayOf(HashMap<String, Int>(), HashMap<Pair<String, String>, Int>(), HashMap<Triple<String, String, String>, Int>())

    fun getEmissionProbability(word: String, tag: String): Double {
        return getTagWordCount(tag, word)/get1GramHashMap().getOrDefault(tag, 0).toDouble()
    }

    fun getQTrigram(tag1: String, tag2: String, tag3: String):Double {
        return get3GramHashMap().getOrDefault(Triple(tag1, tag2, tag3), 0)/get2GramHashMap().getOrDefault(Pair(tag1, tag2), 0).toDouble()
    }

    fun processCountFile(fileName: String) {
        File(fileName).forEachLine { processLine(it) }
    }

    private fun processLine(line: String) {
        val data = line.split(" ")
        val type = data[1]

        if (type == "WORDTAG") {
            processWordTag(data)
        } else {
            processNGram(data)
        }
    }

    fun getTags(): Array<String> {
        return get1GramHashMap().keys.toTypedArray()
    }

    private fun processWordTag(data: List<String>) {
        val count = data[0].toInt()
        val tag = data[2]
        var word = data[3]

        if (count < 5) word = getRareWordCategory(word)
        val key = Pair(tag, word)
        tagWordCount[key] = count

    }

    private fun getRareWordCategory(word: String): String {
        val category = when {
            word.any { it.isDigit() } -> RareWordCategory._NUMERIC_
            word.all { it.isUpperCase() } -> RareWordCategory._ALL_CAPITALS_
            word.last().isUpperCase() -> RareWordCategory._LAST_CAPITAL_
            else -> RareWordCategory._RARE_
        }
        return category.toString()
    }

    private fun getTagWordCount(tag: String, word: String): Int {
        //return tag -> word count, if not in hashTable try return tag -> RareWordCategory, else return 0
        return tagWordCount.getOrDefault(Pair(tag, word), tagWordCount.getOrDefault(Pair(tag, getRareWordCategory(word)), 0))
    }

    private fun processNGram(data: List<String>) {
        val count = data[0].toInt()
        val nGram = data[1][0]

        when (nGram) {
            '1' -> {
                get1GramHashMap().put(data[2], count)
            }
            '2' -> {
                get2GramHashMap().put(Pair(data[2], data[3]), count)
            }
            '3' -> {
                get3GramHashMap().put(Triple(data[2], data[3], data[4]), count)
            }
            else -> {
                throw IllegalArgumentException("${nGram}-GRAM not supported")
            }
        }
    }

    private fun get1GramHashMap(): HashMap<String, Int> {
        return nGramArray[0] as HashMap<String, Int>
    }

    private fun get2GramHashMap(): HashMap<Pair<String, String>, Int> {
        return nGramArray[1] as HashMap<Pair<String, String>, Int>
    }

    private fun get3GramHashMap(): HashMap<Triple<String, String, String>, Int> {
        return nGramArray[2] as HashMap<Triple<String, String, String>, Int>
    }
}