all: clean build war
clean:
	rm -rf WEB-INF/classes
build:
	mkdir -p WEB-INF/classes/
	javac -d WEB-INF/classes/ src/com/flywheel/cashiering/api/*.java
war:
	jar cvf HelloWorld.war WEB-INF/
