call mvn dependency:sources
call mvn dependency:resolve -Dclassifier=javadoc

call mvn -f %~dp0pom.xml clean install -U -e -Dmaven.test.skip=true

echo %~n0 success
