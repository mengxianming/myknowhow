CWD=$(dirname $(readlink -f $0))
cd $CWD
java -jar lib/jsontool-0.0.1-SNAPSHOT.jar $*