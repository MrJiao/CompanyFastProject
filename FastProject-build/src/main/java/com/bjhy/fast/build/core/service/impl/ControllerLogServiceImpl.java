package com.bjhy.fast.build.core.service.impl;


import com.bjhy.fast.build.core.domain.ControllerLog;
import com.bjhy.fast.build.core.service.ControllerLogService;
import com.bjhy.tlevel.datax.common.infrastructure.AbstractBizCommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by: Jackson
 */
@Service
@Transactional
public class ControllerLogServiceImpl extends AbstractBizCommonService<ControllerLog, String> implements ControllerLogService {

}
