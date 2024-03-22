# Java news from 8 to 22

## maven
```
mvn clean
mvn compile
mvn test
mvn test -Dtest=news
mvn test -Dtest=FirstTest
mvn test -Dtest=news.FirstTest
mvn test -Dtest=FirstTest#oneTest
```

## News Java 8
- streams
- functional interface
- function reference
- lambda function
- optionals
- modern temporal types (java.time)

## News Java 11
https://docs.oracle.com/en/java/javase/11/language/java-language-changes.html
- var keyword
- simplified collection construction for tests: List.of, Set.of, Map.of

## News Java 17
https://docs.oracle.com/en/java/javase/17/language/java-language-changes.html
- sealed classes
- multilines
https://docs.oracle.com/en/java/javase/17/text-blocks/index.html
- records
https://docs.oracle.com/en/java/javase/17/language/records.html
- stream collect shortcut: toList 
- downcasting shortcut
- pattern matching (preview but stable)
https://docs.oracle.com/en/java/javase/17/language/pattern-matching.html
https://docs.oracle.com/en/java/javase/17/language/switch-expressions.html

## Java 21
https://docs.oracle.com/en/java/javase/21/language/java-language-changes.html
- pattern matching finalized + record pattern
- template strings (1st preview)
https://docs.oracle.com/en/java/javase/21/language/string-templates.html

## Java 22
- keyword _ for template matching
- second preview template strings
- preview implicit class + main method (like C#)
- foreign functions (instead of JNI)
- preview stream gatherer
- incubator: Vector API (7th version)