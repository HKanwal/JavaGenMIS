testClass = TestClass

all: default run open

default:
	javac -g -d bin/ -cp .:lib/gson-2.8.6.jar src/$(testClass).java

run:
	java -cp 'bin/:lib/gson-2.8.6.jar' src.$(testClass)

open:
	cd tex && pdflatex MIS.tex
	okular tex/MIS.pdf

clean:
	rm -r bin/*
	rm tex/*
