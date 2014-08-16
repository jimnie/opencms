
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.ReturnsDao;
import net.opencms.entity.Returns;
import net.opencms.service.ReturnsService;

import org.springframework.stereotype.Service;

@Service("returnsServiceImpl")
public class ReturnsServiceImpl extends BaseServiceImpl<Returns, Long> implements ReturnsService {

	@Resource(name = "returnsDaoImpl")
	public void setBaseDao(ReturnsDao returnsDao) {
		super.setBaseDao(returnsDao);
	}

}