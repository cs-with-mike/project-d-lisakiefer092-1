javac -d test -cp junit-platform-console-standalone-1.10.1.jar \
  ./edu/westmont/cs030/MazeDriver.java \
  ./edu/westmont/cs030/maze/*.java \
  ./edu/westmont/cs030/tests/*.java

java -jar junit-platform-console-standalone-1.10.1.jar execute \
  --classpath test \
  --scan-class-path