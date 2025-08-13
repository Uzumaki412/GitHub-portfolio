// nbstats.cpp : This file contains the 'main' function. Program execution begins and ends there
/**
 * Program Name: nbstats.cpp
 * Purpose: to output userinput or file data: mean, median, mode, varancie, STD devition, and newcomb law
 * Coder: Zaid Abu Shawarib
 * Date: March 9, 2024
 */
#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <map>
#include <sstream>
#include "header.hpp"
using namespace std;

// this function calculates the number of hastags 
void drawFreq(long double freq) {
	for (int i = 0; i < (int)(freq * 100); ++i)
		cout << "#";
	cout << endl;
}

int main(int argc, char* argv[]) 
{
	//data members
	vector<string> args;
	long double nbVar, stdDev;
	vector<long double> data;
	string userInput , fileName;
	bool skipBad =false;

	// check how many args user inputed
	for (size_t i = 1; i < argc; i++)
		args.push_back(argv[i]);

	vector<string>::iterator iter = find(args.begin(), args.end(), "--help");
	
	// outputs help option 
	if (iter != args.end()) 
	{
		args.erase(iter);
		cout << "usage: " << endl;
		cout << "stats [--(help|skipbad|version)]* [filename]\n" << endl;
		cout << setw(13) << left << "        --help          " << "display instructions" << endl;
		cout << setw(13) << left << "        --skipbad       " << "skips bad input values" << endl;
		return 0;
	}
	// checks if user puts skipbad
	iter = find(args.begin(), args.end(), "--skipbad");
	if (iter != args.end()) {
		skipBad = true;
		args.erase(iter);
	}
	if (!args.empty() && args.size() == 1) {
		fileName = args.front();
		args.pop_back();
	}

	cout << "Nbstats v1, Zaid Abu Shawarib" << endl ;
	cout << "=========================================================" << endl << "Enter whitspace-separated real numbers. Terminate input with ^Z" << endl;

	ifstream infile;// reads in file 

	//checks if file is not empty 
	if (!fileName.empty()) 
	{
		infile.open(fileName);
		if (!infile) // checks if file exits
		{
			cerr << "Error: Bad file name:  \" " << fileName << "\"" << endl;
			return 0;
		}
		//takes in the data from the file
		while (infile >> userInput) {
			// try catch to check the data for non-numbers, negative numbers, zero numbers
			try{
				if (skipBad && stold(userInput) <= 0) {
					cout << "Error: Invalid Input <" << userInput << "> Rejected." << endl;
				}
				else {
					data.push_back(stold(userInput));
				}
			}
			catch (...) {
				cout << "Error: invalid input <" << userInput << "> rejected" << endl;
				break;
			}
		}
	}
	// checks if it takes userinput or commad line arguments
	while (fileName.empty())
	{
		cin >> userInput;// takes user input
		
		if (cin.eof()) // if cntrl + z is 
			break;

		// try catch to check the data for non-numbers, negative numbers, zero numbers
		try {
			if (stold(userInput) <= 0)
				cout << "Error: invalid input <" << userInput << "> rejected" << endl;
			
			else
				data.push_back(stold(userInput));
		}
		catch (...) {
			cout << "Error: invalid input <" << userInput << "> rejected" << endl;
			break;
		}
	}
	//checks if data is empty
	if (data.size() == 0) {
		cout << "Data set is empty!" << endl;
		return 0;
	}


	stdDev = nbDeviation(data);// call in the newcomb Deviation method in MathFunction.cpp
	cout << "Standard Analysis" << endl << "================================================" << endl;
	cout << "# elements = " << data.size() << endl;
	cout << "Range = ";
	findRange(vector<long double>(data));  // call in the range in MathFunction.cpp
	cout << "Arithmetic mean = " << findMean(vector<long double>(data)) << endl; // call in mean in MathFunction.cpp
	cout << "Arithmetic median = " << findMedian(vector<long double>(data)) << endl; // call in median in MathFunction.cpp
	cout << "Variance = " << findVariance(vector<long double>(data)) << endl; // call in Variance in MathFunction.cpp
	cout << "Standard Deviation = " << findSTD(vector<long double>(data)) << endl << endl; // call in standard Deviation in MathFunction.cpp
	findMode(vector<long double>(data)); // call in mode in MathFunction.cpp
	cout << endl;
	cout << endl;

	map <int, long double> actFeq = frequencyTable(data); // find actFeq by calling frequencyTable  in MathFunction.cpp
	//checks if it 100% graph or 50% graph
	if (actFeq[1] >= 0.5 || actFeq[2] >= 0.5 || actFeq[3] >= 0.5 || actFeq[4] >= 0.5 || actFeq[5] >= 0.5 || actFeq[6] >= 0.5 || actFeq[7] >= 0.5 || actFeq[8] >= 0.5 || actFeq[9] >= 0.5) {
		cout << "Newcomb-Benford's Law Analysis" << endl << "============================================================================================================================" << endl;
		cout << "    exp digit   freq  0        10        20        30        40        50        60        70        80        90        100" << endl;
		cout << "-------------------- -+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+----+" << endl;
		cout << "30.10%  [1] = " << fixed << setprecision(2) << (actFeq[1] * 100) << "%  |";
		drawFreq(actFeq[1]);
		cout << "17.61%  [2] = " << (actFeq[2] * 100) << "%  |";
		drawFreq(actFeq[2]);
		cout << "12.49%  [3] = " << (actFeq[3] * 100) << "%  |";
		drawFreq(actFeq[3]);
		cout << " 9.69%  [4] = " << (actFeq[4] * 100) << "%  |";
		drawFreq(actFeq[4]);
		cout << " 7.92%  [5] = " << (actFeq[5] * 100) << "%  |";
		drawFreq(actFeq[5]);
		cout << " 6.69%  [6] = " << (actFeq[6] * 100) << "%  |";
		drawFreq(actFeq[6]);
		cout << " 5.80%  [7] = " << (actFeq[7] * 100) << "%  |";
		drawFreq(actFeq[7]);
		cout << " 5.12%  [8] = " << (actFeq[8] * 100) << "%  |";
		drawFreq(actFeq[8]);
		cout << " 4.58%  [9] = " << (actFeq[9] * 100) << "%  |";
		drawFreq(actFeq[9]);
		cout << "-------------------- -+----+----+----+----+----+----+----+----+----+----+" << endl;
	}
	else {
		cout << "Newcomb-Benford's Law Analysis" << endl << "=========================================================================" << endl;
		cout << "    exp digit   freq  0        10        20        30         40       50" << endl;
		cout << "-------------------- -+----+----+----+----+----+----+----+----+----+----+" << endl;
		cout << "30.10%  [1] = " << fixed << setprecision(2) << (actFeq[1] * 100) << "%  |"			;
		drawFreq(actFeq[1]);
		cout << "17.61%  [2] = " << (actFeq[2] * 100) << "%  |";
		drawFreq(actFeq[2]);
		cout << "12.49%  [3] = " << (actFeq[3] * 100) << "%  |";
		drawFreq(actFeq[3]);
		cout << " 9.69%  [4] = " << (actFeq[4] * 100) << "%  |";
		drawFreq(actFeq[4]);
		cout << " 7.92%  [5] = " << (actFeq[5] * 100) << "%  |";
		drawFreq(actFeq[5]);
		cout << " 6.69%  [6] = " << (actFeq[6] * 100) << "%  |";
		drawFreq(actFeq[6]);
		cout << " 5.80%  [7] = " << (actFeq[7] * 100) << "%  |";
		drawFreq(actFeq[7]);
		cout << " 5.12%  [8] = " << (actFeq[8] * 100) << "%  |";
		drawFreq(actFeq[8]);
		cout << " 4.58%  [9] = " << (actFeq[9] * 100) << "%  |";
		drawFreq(actFeq[9]);
		cout << "-------------------- -+----+----+----+----+----+----+----+----+----+----+" << endl;
	}
	
	nbVar = nbVariance(vector<long double>(data)); // gets newcombs variance from MathFunction.cpp
	cout << "Variance = " << fixed << setprecision(5) << (nbVar * 100) << "%"  << endl;
	cout << "Std. Dev. = " << (stdDev * 100) << "%" << endl; // gets newcombs Std varaince from MathFunction.cpp
	long double dev = stdDev;
	realationship(dev);// checks the realationship of the data
	cout << endl;
	cout << "=============================================================================";
}