#include "header.hpp"
#include <string> 
#include <sstream>
#include <iomanip>
#include <locale>
#include <regex>
#include <inttypes.h>
#include <stdlib.h>
#include <stdio.h>



template<class T>
std::string FormatWithCommas(T value) {
	std::stringstream ss;
	ss.imbue(std::locale(""));
	ss << std::fixed << value;
	return ss.str();
}

int static intFromCommas(std::string str) {
	const std::regex commas(",");
	std::stringstream temp;

	std::regex_replace(std::ostream_iterator<char>(temp), str.begin(), str.end(), commas, "");
	return std::stoi(temp.str());
}

uintmax_t static intmaxFromCommas(std::string str) {
	const std::regex commas(",");
	std::stringstream tem;
	char* endptr;

	std::regex_replace(std::ostream_iterator<char>(tem), str.begin(), str.end(), commas, "");
	return strtoumax(tem.str().c_str(), &endptr, 10);
}

bool static compareSize (const FileInfoDisplay& lhs, const FileInfoDisplay& rhs) {
	return intmaxFromCommas(lhs.size) < intmaxFromCommas(rhs.size);
}

