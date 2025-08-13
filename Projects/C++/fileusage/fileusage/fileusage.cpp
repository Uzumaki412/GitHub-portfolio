#include <filesystem>
#include <algorithm>
#include <cstring>
#include <fstream>
#include <vector>
#include <map>
#include "FileStruct.cpp"

using namespace std;
namespace fs = filesystem;

void static printHelp() {
	cout << "fileusage {v4.0.0} (c) 2016-24, Garth Santor" << endl;
	cout << "Usage: fileusage [--help] [-hrs(x regularexpression)] [folder]" << endl;
	cout << "\tswitches:\n\t\th\thelp\n\t\tr\treverse the order of the listing\n\t\ts\tsort by file sizes\n\t\tx\tfilter with a regular expression" << endl;
	cout << "\tfolder\n\t\tstarting folder or current folder if not provided" << endl;
}


int main(int argc, char* argv[])
{
	string path{};
	regex regexPattern{};
	bool reversef{ false }, sortf{ false }, regexf{ false }, pathf{ false };

	if (argc == 1) {
		path = ".";
	}
	else {
		for (int i = 1; i < argc; i++) {
			if (string(argv[i]) == "--help") {
				printHelp();
				return 0;
			}
			else if (argv[i][0] == '-') {
				if (!pathf) path = ".";
				for (int j = 1; j < strlen(argv[i]); j++) {
					switch (argv[i][j]) {
						case 'h':
							printHelp();
							return 0;
						case 'r':
							reversef = true;
							break;
						case 's':
							sortf = true;
							break;
						case 'x':
							if (!regexf) {
								regexf = true;
								if (i + 1 < argc) {
									try {
										regexPattern = regex(string(argv[++i]));
									}
									catch (regex_error& ) {
										cout << "Invalid regex pattern" << endl;
										return 0;
									}
									break;
								}
								else {
									cout << "Please enter a regex pattern after the -x switch" << endl;
									return 0;
								}
							}
							else break;
						default:
							cout << "Invalid switch" << endl;
							break;
					}
					if (regexf) break;
				}
			}
			else {
				if (!pathf) {
					path = string(argv[i]);
					pathf = true;
				}
				else {
					cout << "Enter in only 1 folder path per query" << endl;
					return 0;
				}
			}
		}
	}

	
	map<string, FileInfo> Files;
	int extNum{ 0 }, count{ 0 }, extW{ 0 }, numW{ 0 }, sizeW{ 0 };
	size_t index{};
	uintmax_t size{ 0 };

	fs::path test{ path };
	if (!fs::exists(test)) {
		cout << "Invalid path" << endl;
		return 0;
	}

	if (path == "C:\\" || path == "c:\\") {
		for (const auto& entry : fs::directory_iterator(path, fs::directory_options::skip_permission_denied)) {
			if (entry.is_directory() || !entry.exists() || (regexf && !regex_match(entry.path().extension().string(), regexPattern)))
				continue;

			if (!Files[entry.path().extension().string()]) {
				FileInfo file = { entry.path().extension().string(), 1, fs::file_size(entry.path()) };
				Files[entry.path().extension().string()] = file;
			}
			else {
				Files[entry.path().extension().string()].count++;
				Files[entry.path().extension().string()].size += fs::file_size(entry.path());
			}
		}
	}
	else {
		for (const auto& entry :  fs::recursive_directory_iterator(path, fs::directory_options::skip_permission_denied)) {
			if (entry.is_directory() || !entry.exists() || (regexf && !regex_match(entry.path().extension().string(), regexPattern)))
				continue;

			if (!Files[entry.path().extension().string()]) {
				FileInfo file = { entry.path().extension().string(), 1, fs::file_size(entry.path()) };
				Files[entry.path().extension().string()] = file;
			}
			else {
				Files[entry.path().extension().string()].count++;
				Files[entry.path().extension().string()].size += fs::file_size(entry.path());
			}
		}
	}

	vector<FileInfoDisplay> values;

	for (const auto& file : Files) {
		values.push_back({ file.second.ext, FormatWithCommas(file.second.count), FormatWithCommas(file.second.size) });
	}
	
	for (const auto& value : values) {
		extW = max(extW, (int)value.ext.size());
		numW = max(numW, (int)value.count.size());
		sizeW = max(sizeW, (int)value.size.size());

		extNum++;
		count += intFromCommas(value.count);
		size += intmaxFromCommas(value.size);
	}

	if (sortf) 
		sort(values.begin(), values.end(), compareSize);
		

	extW = max(extW, (int)string("Ext").size());
	numW = max(numW, (int)string("#").size());
	sizeW = max(sizeW, (int)string("Total").size());

	extW = max(extW, (int)to_string(extNum).size());
	numW = max(numW, (int)to_string(count).size());
	sizeW = max(sizeW, (int)to_string(size).size());


	extW++;
	numW +=5;
	sizeW +=5;

	cout << right << setw(extW) << "Ext" << "";
	cout << right << setw(numW) << "#" << "";
	cout << right << setw(sizeW) << "Total" << "\n" << endl;

	for (size_t i = 0; i != values.size(); ++i){
		if (reversef)
			index = values.size() - i - 1;
		else 
			index = i;

		cout << right << setw(extW) << values[index].ext << "";
		cout << right << setw(numW) << values[index].count << "";
		cout << right << setw(sizeW) << values[index].size << "\n";
	}
	cout << endl;
	cout << right << setw(extW) << FormatWithCommas(extNum) << "";
	cout << right << setw(numW) << FormatWithCommas(count) << "";
	cout << right << setw(sizeW) << FormatWithCommas(size) << "\n";
		

	return 0;
}

