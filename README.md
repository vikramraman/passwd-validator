PasswordValidator is a simple password validation service written in Java. It uses Spring Boot and is configurable via IoC using the Spring Framework. 

The service is meant to check a text string for compliance to a number of password validation rules.

The rules currently supported are:

1. Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
2. Must be between 5 and 12 characters in length.
3. Must not contain any sequence of characters immediately followed by the same sequence.

## Build the source code

1. Export JAVA_HOME:
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_65.jdk/Contents/Home/

2. Run maven at the toplevel directory: (this will download the necessary packages the first time):
./mvnw package && java -jar target/passwordValidator-0.0.1-SNAPSHOT.jar

## Test the code

1. Run step 2 above and then use the curl command line tool as follows:

curl -X POST -d "password=foobar" http://localhost:8080/validate

## Future work

The framework can be enhanced further in the following aspects:

1. http://docs.oracle.com/javase/8/docs/technotes/guides/security/crypto/CryptoSpec.html#PBEEx

For security purposes, the Java Cryptography Architecture reference guide recommends using 
character arrays instead of String classes for storing (and erasing) passwords in memory.

The current implementation uses Strings and can be enhanced to use character arrays.

2. Unicode characters/Internationalization:

The current implementation uses regular expressions to enforce password policies. The regexes are tied to english
characters and do not support other unicode characters. The validators can be enhanced to support other character sets.
