import java.io.File

/**
 * Created by igor on 25.7.2017.
 */

class HMM_Tagger() {
    private val dataHolder = WordsAndTagsHolder()

    fun initCounts(fileName: String) {
        dataHolder.processCountFile(fileName)
    }

    fun tagFile(fileName: String) {
        val sentences = mutableListOf<Array<String>>()
        val sentence = mutableListOf<String>()
        File(fileName).forEachLine {
            if (it.isBlank()) {
                sentences.add(sentence.toTypedArray())
                sentence.clear()
                sentence.add("") // shift sentence so that it start at index 1
            } else {
                sentence.add(it.trim())
            }
        }
        sentences.forEach{viterbi(it)}
    }

    private fun viterbi(sentence: Array<String>): Array<String> {
        val pi = HashMap<Triple<Int, String, String>, Int>()
        val bp = HashMap<Triple<Int, String, String>, String>()
        pi.put(Triple(0, "*", "*"), 1)

        for(k in 1..sentence.size-1) {
            for (u in getTags(k-1)) {
                for (v in getTags(k)) {
                    //TODO: Finish viterbi algorithm
                }
            }
        }


        return emptyArray()
    }

    private fun getTags(index: Int): Array<String> {
        if (index <= 0) return arrayOf("*")
        return dataHolder.getTags()
    }
}