# JVMFLAGS JVM参数可以在这里设置
JVMFLAGS=-Dfile.encoding=UTF-8

if [ "$JAVA_HOME" != "" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  JAVA=java
fi

#把lib下的所有jar都加入到classpath中
for i in lib/*.jar
do
        CLASSPATH="$i:$CLASSPATH"
done
echo $CLASSPATH

CLASS_MAIN="com.mogoroom.tasktracker.Main"
$JAVA -cp "$CLASSPATH" $JVMFLAGS $CLASS_MAIN  &
