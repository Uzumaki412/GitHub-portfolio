/**
 * Program Name: header.hpp
 * Purpose: connect the MathFunction class to nbstats class
 * Coder: Zaid Abu Shawarib
 * Date: March 9, 2024
 */
#pragma
#ifndef HEADER_HPP
#define HEADER_HPP
#include <string>
#include <vector>
#include <map>

long double findMean(std::vector<long double> myVector);

long double findMedian(std::vector<long double> myVector);

void findRange(std::vector<long double> myVector);

long double findVariance(std::vector<long double> myVector);

long double findSTD(std::vector<long double> myVector);

void findMode(std::vector<long double> myVector);

std::vector<int> firstDigits(std::vector<long double> myVector);

std::map<int, long double> frequencyTable(std::vector<long double> myVector);

long double nbVariance(std::vector<long double> myVector);

long double nbDeviation(std::vector<long double> myVector);

void realationship(long double dev);
#endif // !HEADER_HPP
