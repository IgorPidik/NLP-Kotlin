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

A Probabilistic Context-Free Grammar (PCFG) is simply a Context-Free Grammar with probabilities assigned to the rules such that the sum of all probabilities for all rules expanding the same non-terminal is equal to one. 
![img](http://conglang.github.io/img/note_nlp_a_probabilistic_context_free_grammar.png)
![img](http://www.pling.org.uk/cs/com6791img/phrasestructuretree.png)
