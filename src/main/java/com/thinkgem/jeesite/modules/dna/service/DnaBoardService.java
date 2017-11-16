/**
\ * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoard;
import com.thinkgem.jeesite.modules.dna.dao.DnaBoardDao;
import com.thinkgem.jeesite.modules.dna.entity.DnaBoardJgg;
import com.thinkgem.jeesite.modules.sys.service.SysCodeRuleService;
import com.thinkgem.jeesite.modules.dna.dao.DnaBoardJggDao;

/**
 * 电泳分型板Service
 * @author zhuguli
 * @version 2017-06-08
 */
@Service
@Transactional(readOnly = true)
public class DnaBoardService extends CrudService<DnaBoardDao, DnaBoard> {

	@Autowired
	private DnaBoardJggDao dnaBoardJggDao;
	@Autowired
	private SysCodeRuleService codeRuleService;
	public DnaBoard get(String id) {
		DnaBoard dnaBoard = super.get(id);
		dnaBoard.setDnaBoardJggList(dnaBoardJggDao.findList(new DnaBoardJgg(dnaBoard)));
		return dnaBoard;
	}

	public List<DnaBoard> findList(DnaBoard dnaBoard) {
		return super.findList(dnaBoard);
	}

	public Page<DnaBoard> findPage(Page<DnaBoard> page, DnaBoard dnaBoard) {
		return super.findPage(page, dnaBoard);
	}

	@Transactional(readOnly = false)
	public void save(DnaBoard dnaBoard) {
		super.save(dnaBoard);
		for (DnaBoardJgg dnaBoardJgg : dnaBoard.getDnaBoardJggList()){

			if (DnaBoardJgg.DEL_FLAG_NORMAL.equals(dnaBoardJgg.getDelFlag())){
				if (StringUtils.isBlank(dnaBoardJgg.getId())){
					dnaBoardJgg.setBoard(dnaBoard);
					dnaBoardJgg.preInsert();
					dnaBoardJggDao.insert(dnaBoardJgg);
				}else{
					dnaBoardJgg.preUpdate();
					dnaBoardJggDao.update(dnaBoardJgg);
				}
			}else{
				dnaBoardJggDao.delete(dnaBoardJgg);
			}
		}
	}

	@Transactional(readOnly = false)
	public void delete(DnaBoard dnaBoard) {
		super.delete(dnaBoard);
		dnaBoardJggDao.delete(new DnaBoardJgg(dnaBoard));
	}
	@Transactional(readOnly = false)
	public DnaBoard autoMakeBoard(String code) {
		 if(StringUtils.isEmpty(code)){
			 code = codeRuleService.generateCode("board_code");
		 }
		DnaBoard board =  new DnaBoard();
		board.setCode(code);
		List<DnaBoardJgg> dnaBoardJggList = new ArrayList<DnaBoardJgg>();
		for(int row=1;row<=8;row++){
			for(int column=1;column<=12;column++){
				DnaBoardJgg jgg = new DnaBoardJgg();
				jgg.setHang(row);
				jgg.setLie(column);
				dnaBoardJggList.add(jgg);
			}
		}
		board.setDnaBoardJggList(dnaBoardJggList );
		save(board);
		return board;
	}

}