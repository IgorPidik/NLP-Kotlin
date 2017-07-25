/**
 * Created by igor on 25.7.2017.
 */

fun main(args: Array<String>) {
    val hmm = HMM_Tagger()
    hmm.initCounts("gene.counts")
    hmm.tagFile("gene.dev")

}