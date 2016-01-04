package com.mogoroom.tasktracker.task;

import com.mogoroom.facade.ICmsFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 官网社区文章浏览量从缓存中同步到数据库.
 *
 * <p>
 *      官网每篇文章的浏览量是存储在缓存中，需每天定时将数据落地。
 * </p>
 *
 * 实时性要求：<b>低</b>
 * 执行周期：<b>每天半夜执行一次即可</b>
 *
 * Author: jilinqiang
 * Date: 2015/12/29
 * Time: 18:03
 */
@Component
public class SyncWebPagePVTask implements Task {
    private static Logger log = Logger.getLogger(SyncWebPagePVTask.class);

    @Autowired
    private ICmsFacade cmsFacade;

    @Override
    public void run(Map<String, String> params) throws Throwable {
        log.info("开始同步官网文章浏览量到数据库的定时任务");
//        cmsFacade.syncPageviewFromCacheToDB();
        log.info("完成同步官网文章浏览量到数据库的定时任务");
    }
}
