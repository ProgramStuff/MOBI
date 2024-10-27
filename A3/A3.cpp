/*
    Name: Jordan Kelsey
    Module Code: PROG 2100
    Purpose: Assignment 3 | Student Grades Loader
    Date: 2024-10-20
 */

#include <iostream>
#include <fstream>
#include <exception>


using namespace std;

// emum for error codes
enum ErrorCodes {
    NO_FILE = 404,
    EMPTY_FILE = 204,
    MAX_SIZE = 413
};

// HandleError class to create and display errors
class HandleError{
    public:
        enum ErrorCodes code;

    HandleError(enum ErrorCodes code) : code(code){};
    // Throws an error depending on the code
    string displayError(){
        switch (code) {
            case 204:
                return "Error: The file is empty";
                break;
            case 404:
                return "Error: File not found.";
                break;
            case 413:
                return "Error: Maximum data size exceeded";
                break;
            default:
                return "";
        };
    }

    ~HandleError(){
    // Complier calls the destructor automatically.
        cout << "Destructor called for HandleError object" << endl;
    }

};

void displayGrades(string* gradeData, int arraySize){
    // Display the data
    // Every fifth line will be the students name
    for (int i = 0; i < arraySize; i++){
        if(i == 0 || i % 5 == 0){
            cout << "\t*** Grades ***" << "\n" << endl;
            cout << gradeData[i] << "\n" << endl;
        } else{
            cout << gradeData[i] << "\n" << endl;
        }
    }
};

int main(){
    string gradeData[20];
    string data;
    ifstream reader;
    int lineCount = 0;

    // Open text file
    try{
        reader.open("grades.txt");
        // Check for file existence
        if (!reader.is_open()){
            enum ErrorCodes error = NO_FILE;
            // Use HandleError class to throw errors
            throw HandleError(error);
        }
        //Seek to the end of the file with no offset
        //If the file is empty the end and start of the file are the same
        reader.seekg(0, ios::end);
        // If the file is empty tellg will return a value of 0
        if (reader.tellg() < 1){
            // Throw error
            enum ErrorCodes error = EMPTY_FILE;
            throw HandleError(error);
        }else{
            // Return to the beginning of the file. Otherwise program will start reading from end of file
            reader.seekg(0, ios::beg);
        }
            // Add each line from file to an array
        while (getline(reader, data)){
                // Check if file can fit within array
            if (lineCount >= 20){
                enum ErrorCodes error = MAX_SIZE;
                // Throw error
                throw HandleError(error);
            }else{
                // Update array with each line from txt file
                gradeData[lineCount] = data;
                lineCount++;
            }
        }
        reader.close();
        // Catch errors thrown by HandleError class
        }catch (HandleError& e){
            cout << e.displayError() << endl;
            return 1;
        }
        catch (exception) {
            // Handle all other errors
            cout << "An unexpected error occurred" << endl;
            return 1;
        }
    // Display all of the grades
    displayGrades(gradeData, lineCount);

    return 0;
}