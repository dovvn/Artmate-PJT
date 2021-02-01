package com.ssafy.artmate.mapper;

import com.ssafy.artmate.Dto.MyFileDto;

public interface FileMapper {
	int saveFile(MyFileDto dto);
	MyFileDto searchFile(int fno);
}
