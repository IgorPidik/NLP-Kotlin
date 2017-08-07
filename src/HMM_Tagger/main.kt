/**
 * Created by igor on 25.7.2017.
 */

fun main(args: Array<String>) {
    val hmm = HMM_Tagger()
    hmm.initCounts("/mnt/Data/Projects/Kotlin-Projects/NLP/src/HMM_Tagger/Data/gene.counts")
    hmm.tagFile("/mnt/Data/Projects/Kotlin-Projects/NLP/src/HMM_Tagger/Data/gene.dev", "/mnt/Data/Projects/Kotlin-Projects/NLP/src/HMM_Tagger/Data/gene_dev_test.out")
}