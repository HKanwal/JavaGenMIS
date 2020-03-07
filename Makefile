file = StartServer

all: build run open

build:
	javac -g -d bin/ -cp .:lib/gson-2.8.6.jar src/$(file).java

run:
	java -cp 'bin/:lib/gson-2.8.6.jar' src/$(file)

open:
	cd tex && pdflatex MIS.tex
	okular tex/MIS.pdf

clean:
	rm -r bin/*
	rm tex/*
