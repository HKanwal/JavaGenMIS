default:
	javac -g -d bin/ -cp .:lib/gson-2.8.6.jar src/Compiler.java

clean:
	rm bin/src/*
