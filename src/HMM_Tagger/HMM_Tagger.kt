import java.io.File
import java.io.FileWriter

/**
 * Created by igor on 25.7.2017.
 */

class HMM_Tagger() {
    private val dataHolder = WordsAndTagsHolder()

    fun initCounts(fileName: String) {
        dataHolder.processCountFile(fileName)
    }

    fun tagFile(inputFileName: String, outputFileName: String) {
        val sentences = mutableListOf<Array<String>>()
        val sentence = mutableListOf<String>()
        sentence.add("")
        File(inputFileName).forEachLine {
            if (it.isBlank()) {
                sentences.add(sentence.toTypedArray())
                sentence.clear()
                sentence.add("") // shift sentence so that it start at index 1
            } else {
                sentence.add(it.trim())
            }
        }
        val outputFile = FileWriter(outputFileName)
        sentences.forEach {
            val tagSequence = viterbi(it)
            for (key in 1..it.size - 1) {
                outputFile.appendln(it[key] + " " + tagSequence[key - 1])
            }
            outputFile.appendln()
        }

        outputFile.flush()
        outputFile.close()
    }

    private fun viterbi(sentence: Array<String>): Array<String> {
        val pi = HashMap<Triple<Int, String, String>, Double>()
        val bp = HashMap<Triple<Int, String, String>, String>()
        pi.put(Triple(0, "*", "*"), 1.0)

        val n = sentence.size - 1
        for (k in 1..n) {
            for (u in getTags(k - 1)) {
                for (v in getTags(k)) {
                    //TODO: implement arg max function
                    val possibleValues = HashMap<String, Double>()
                    for (w in getTags(k - 2)) {
                        possibleValues.put(w, pi[Triple(k - 1, w, u)]!! * dataHolder.getQTrigram(w, u, v) * dataHolder.getEmissionProbability(sentence[k], v))
                    }
                    val max = possibleValues.maxBy { it.value }!!
                    pi.put(Triple(k, u, v), max.value)
                    bp.put(Triple(k, u, v), max.key)
                    possibleValues.clear() // clear current hash table just in case
                }
            }
        }

        val ySequence = Array<String>(n + 1, { "" })

        //compute last 2 tags
        val possibleValues = HashMap<Pair<String, String>, Double>()
        for (u in getTags(n - 1)) {
            for (v in getTags(n)) {
                possibleValues.put(Pair(u, v), pi[Triple(n, u, v)]!! * dataHolder.getQTrigram(u, v, "STOP"))
            }
        }
        val max = possibleValues.maxBy { it.value }!!
        ySequence[n - 1] = max.key.first
        ySequence[n] = max.key.second

        for (k in n - 2 downTo 1) {
            ySequence[k] = bp[Triple(k + 2, ySequence[k + 1], ySequence[k + 2])]!!
        }

        return ySequence.sliceArray(1..n)
    }

    private fun getTags(index: Int): Array<String> {
        if (index <= 0) return arrayOf("*")
        return dataHolder.getTags()
    }
}