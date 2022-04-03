# BSPQ22-E1
BSPQ22-E1

First, compile the whole code:
1. mvn compile

Then, in three separate cmd windows, run:

1. mvn jetty:run
2. mvn exec:java -Pmanager
3. mvn exec:java -Pclient