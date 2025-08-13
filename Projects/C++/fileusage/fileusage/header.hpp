#include <iostream>

struct FileInfo {
public:
	std::string ext;
	int count;
	uintmax_t size;

	bool operator !() {
		return count == 0 && size == 0;
	}
};

struct FileInfoDisplay {
public:
	std::string ext, count,  size;
};