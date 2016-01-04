package com.mogoroom.tasktracker.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.mogoroom.facade.IAsyncOperationFacade;
import com.mogoroom.facade.ICmsFacade;
import com.mogoroom.facade.IKeywordFacade;
import com.mogoroom.facade.vo.HelpIndexContent;
import com.mogoroom.service.base.enums.HelpContentTypeEnum;
import com.mogoroom.util.BaseMogoSystem;

public class HelpContentTask implements Task {

	private Logger log = Logger.getLogger(HelpContentTask.class);
	
	@Autowired
	private ICmsFacade cmsFacadeImpl;
	
	@Autowired
	private IAsyncOperationFacade asyncOperationFacadeImpl;
	
	@Autowired
	private IKeywordFacade keywordFacadeImpl;
	
	@Override
	public void run(Map<String, String> params) throws Throwable {
		
		List<HelpIndexContent> hics = null;
		try {
			hics = cmsFacadeImpl.findHelpOfWebsite();
		} catch (Exception e) {
			log.error("Error when findHelpOfWebsite", e);
			return;
		}
		
		Map<String, Integer> ids = new HashMap<String, Integer>();
		if(null!=hics && 0<hics.size()) {
			asyncOperationFacadeImpl.addCounter(hics.size());
			for(HelpIndexContent hic : hics) {
				try {
					asyncOperationFacadeImpl.addHelpContent(hic);
					ids.put(HelpContentTypeEnum.valueOf(hic.getType()).getDesc() + hic.getId(), 1);
				} catch (Exception e) {
					log.error("Error when addHelpContent for " + hic);
					continue;
				}
			}
		}
		
		int counter = 0;
		while(0 < (counter=asyncOperationFacadeImpl.currentCounter())) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Left " + counter + " rows to operate.");
		}
		
		BaseMogoSystem bms = BaseMogoSystem.getInstance();
		String elasticHelpIndex = bms.getELASTIC_HELP_INDEX();
		
		log.info("Starting to remove redundant data...");
		
		/**
		 * 删除冗余数据
		 */
		String helpType = bms.getELASTIC_HELP_TYPE();
		keywordFacadeImpl.removeRedundantDataForStringKey(ids, elasticHelpIndex, helpType);
		log.info("Redundant help content has removed.");
	}
}
