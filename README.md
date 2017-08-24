#Natural Language Processing for Kotlin
===================
This project will contain several NLP algorithms, which I studied during Columbia University's MOOC on Coursera, written in Kotlin.

 - Hidden Markov Models
 - Probabilistic Context-Free Grammars
 - Global linear models

[Hidden Markov Models Tagger](https://github.com/IgorPidik/NLP-Kotlin/tree/master/src/HMM_Tagger)
----------------------------
This is a [Part of Speech](http://en.wikipedia.org/wiki/Part-of-speech_tagging) tagger written in Kotlin. For tagging it uses the [Viterbi algorithm](http://en.wikipedia.org/wiki/Viterbi_algorithm) (an instantiation of [Hidden Markov Models](http://en.wikipedia.org/wiki/Hidden_Markov_model)).

Probabilistic Context-Free Grammars
----------------------------------

A Probabilistic Context-Free Grammar (PCFG) is simply a Context-Free Grammar with probabilities assigned to the rules such that the sum of all probabilities for all rules expanding the same non-terminal is equal to one. Here's a small example:

TP−→1.0DPT′TP→1.0DPT′
DP−→1.0DNPDP→1.0DNP
NP−→0.2NCPNP→0.2NCP
NP−→0.8NNP→0.8N
T′−→1.0TVPT′→1.0TVP
CP−→1.0CTPCP→1.0CTP
D−→1.0theD→1.0the
N−→0.5ideaN→0.5idea
N−→0.5notionN→0.5notion
T−→0.7willT→0.7will
T−→0.1mightT→0.1might
T−→0.2shouldT→0.2should
VP−→1.0sufficeVP→1.0suffice
C−→1.0that
