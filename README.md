# OOP-Java
Program that supports operations with context-free grammars, using uppercase Latin letters for variables (non-terminals) and lowercase Latin letters and digits for terminals. Each grammar that is read should receive a unique identifier.


list: List all the identifiers of the grammars that have been read.

print <id>: Print the grammar in a suitable format. Each rule should be printed with a serial number.

save <id> <filename>: Save the grammar to a file.

addRule <id> <rule>: Add rules to the grammar.

removeRule <id> <n>: Remove a rule by its serial number.

union <id1> <id2>: Find the union of two grammars and create a new grammar. Print the identifier of the new grammar.

concat <id1> <id2>: Find the concatenation of two grammars and create a new grammar. Print the identifier of the new grammar.

chomsky <id>: Check if a grammar is in Chomsky Normal Form.

cyk <id>: Check if a word is in the language of a given grammar using the CYK algorithm.

iter <id>: Perform the iteration (Kleene star) operation on a grammar and create a new grammar. Print the identifier of the new grammar.

empty <id>: Check if the language of a given context-free grammar is empty.

chomskify <id>: Convert the grammar to Chomsky Normal Form. Print the identifier of the new grammar.
