/**
 * Program Name: MathFunction.cpp
 * Purpose: class to hold all math method and that will be called to the main class
 * Coder: Zaid Abu Shawarib
 * Date: March 9, 2024
 */
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include "header.hpp"	
using namespace std;

long double findMean(vector<long double> myVector)
{
	long double num{};
	size_t size = myVector.size();

	for (long double numV : myVector)
	{	
		num += numV;
	}
	return num / size;
}

long double findMedian(vector<long double> myVector) 
{
	size_t size = myVector.size();
	long double num{};
	sort(myVector.begin(), myVector.end());
	
	if (size % 2 == 0) 
	{
		return (myVector[size / 2 - 1] + myVector[size / 2]) / 2;
	}
	else
		return myVector[size / 2];
}

void findRange(vector<long double> myVector)
{
	vector<long double>::iterator min = min_element(myVector.begin(), myVector.end());
	vector<long double>::iterator max = max_element(myVector.begin(), myVector.end());

	cout << "Range = [" << *min << "..." << *max << "]" << endl;
}

long double findVariance(vector<long double> myVector) 
{
	long double mean = findMean(myVector);
	long double squardDiff = 0;
	for (const auto num : myVector) {
		long double diff = num - mean;
		squardDiff += diff * diff;
	}
	return squardDiff / myVector.size();
}

long double findSTD(vector<long double> myVector) 
{
	return sqrt(findVariance(myVector));
}

void findMode(vector<long double> myVector)
{
	map<long double, int> count;
	map<int, vector<long double>> mode;

	for (const auto num : myVector) {
		if (!count[num]) 
			count[num] = 1;
		else 
			count[num] = ++count[num];
	}

	for (const auto& [key, value] : count)
		mode[value].push_back(key);

	if (mode.size() < 2) {
		cout << "Mode = no mode" << endl;
		return;
	}


	auto it = mode.end();
	it--;
	cout << "Mode = { ";
	for (const auto num : it->second) {
		cout << num;
		if (num != it->second.back()) 
			cout << ", ";
	}
	cout << " }x" << it->first << endl;

}

vector<int> firstDigits(vector<long double> myVector)
{
	vector<int> firstDigit;
	for (const auto num : myVector) 
	{
		firstDigit.push_back((int)(num / pow(10, (int)log10(num))));
	}
	return firstDigit;
}

map<int, long double> frequencyTable(vector<long double> myVector)
{
	const int N = myVector.size();
	vector<int> firstDigit = firstDigits(myVector);
	map<int, long double> freqTable;

	for (const auto dig : firstDigit) {
		if (!freqTable[dig]) 
			freqTable[dig] = 1;
		else 
			freqTable[dig] = ++freqTable[dig];
	}

	for (auto& freq : freqTable) 
		freq.second /= N;

	return freqTable;
}

long double nbVariance(vector<long double> myVector) 
{
	map<int, long double> expectedFreqTable;
	for (int i = 1; i <= 9; ++i)
		expectedFreqTable[i] = log10(1 + (1 / (long double)i));
	
	map <int, long double> actFeq = frequencyTable(myVector);

	long double var = 0;

	for (int d = 1; d <= 9; ++d)
		var += pow((actFeq[d] / expectedFreqTable[d]) - 1, 2);

	var /= 9;
	return var;
}

long double nbDeviation(vector<long double> myVector) 
{
	long double var = nbVariance(myVector);
	long double dev = sqrt(var);
	return dev;
}

void realationship(long double dev)
{
	
	if (dev >= 0 && dev < 0.1) 
	{
		cout << "Very strong realationship";
	}
	else if (dev >= 0.1 && dev < 0.2) 
	{
		cout << "Strong realationship";
	}
	else if (dev >= 0.2 && dev < 0.35) 
	{
		cout << "Moderate realationship";
	}
	else if (dev >= 0.35 && dev < 0.5) 
	{
		cout << "weak realationship";
	}
	else 
	{
		cout << "There is not a Benford relationship.";
	}
}