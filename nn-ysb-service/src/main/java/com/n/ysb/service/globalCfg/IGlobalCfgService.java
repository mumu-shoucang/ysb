package com.n.ysb.service.globalCfg;

import com.n.ysb.service.globalCfg.vo.NnGlobalCfgVo;

public interface IGlobalCfgService {

    NnGlobalCfgVo getGlobalCfg();

    void saveOrUpdateGlobalCfg(NnGlobalCfgVo globalCfgVo);

    NnGlobalCfgVo getRedisGlobalCfgVo();

    void setRedisGlobalCfgVo();

}
