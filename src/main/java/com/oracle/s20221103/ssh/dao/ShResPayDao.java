package com.oracle.s20221103.ssh.dao;

import java.util.List;

import com.oracle.s20221103.dto.ResPayDTO;

public interface ShResPayDao {

	List<ResPayDTO> resPayList(ResPayDTO resPayDTO);

	int totalResPay();

}
