# DocOrganizer

This program can serve as a starter project for a document organizer, that can serve the purpose of a filing cabinet for the Documents folder.

The program takes in two parameters from the user. A topic and a frequency.

The program will access Word documents, Excel spreadsheets, PDF files and text files and search for the topic provided by the user and count the number of times the topic appears. Then it will compare that count against the frequency provided by the user. If the topic had appeared in the file more than the frequency provided by the user, the program will create a folder with that topic name and move the file to that folder.

Limitations:
The Java libraries used for accessing Word docs, spreadsheets and PDF files have their own limitations in terms the versions of those documents. These libraries are not able to access all the versions. That is why to test this program, please use the test files that are provided along with this program.

How to run:

Click on the green button to download the entire suite. Once downloaded open the folder "Run" and copy the folder "Organizer" into the "Documents" folder of your home directory. Then open windows command line and go to the path of the same "Run" directory.

While in the "Run" directory, type the following command:
../Run:> java -jar DocOrganize <topic> <frequency>

e.g.
..Run:> java -jar DocOrganize John 4
With this command, any file containing the word John more than 4 times will be copied into a folder "John"


Requirements:
This program requires the latest Java SDK and Java RE to be installed and their paths to be added to the PATH in system variables.
