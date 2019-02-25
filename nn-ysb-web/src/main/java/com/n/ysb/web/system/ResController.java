package com.n.ysb.web.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.system.IResService;
import com.n.ysb.service.system.po.NnResource;
import com.n.ysb.service.system.vo.NnResourceVo;
import com.n.ysb.service.system.vo.ResTreeVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("res")
public class ResController extends BaseController {

	@Autowired
	private IResService resService;
	
	@RequestMapping("listModulePage")
	@ResponseBody
	public SimpleResponse listModulePage(NnResourceVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      ) {
		PageInfo<NnResource> ret = resService.listModulePage(vo, pageNo, limit);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("listAllModules")
	@ResponseBody
	public SimpleResponse listAllModules(NnResourceVo vo) {
		PageInfo<NnResource> ret = resService.listModulePage(vo, 0, 0);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("listResPage")
	@ResponseBody
	public SimpleResponse listResPage(NnResourceVo vo,
		  @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
          @RequestParam(required = false, defaultValue = "10", value = "limit") int limit
	      ) {
		PageInfo<NnResource> ret = resService.listResPage(vo, pageNo, limit);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("listTreeRes")
	@ResponseBody
	public SimpleResponse listTreeRes() {
		List<ResTreeVo> ret = resService.listTreeRes();
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("getByCode")
	@ResponseBody
	public SimpleResponse getByCode(String resCode) {
		NnResource res = resService.getByCode(resCode);
		PageInfo<NnResource> pageModule = resService.listModulePage(new NnResourceVo(), 0, 0);
		Map<String, Object> ret = new HashMap<>();
		ret.put("modules", pageModule.getList());
		ret.put("res", res);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("addModule")
	@ResponseBody
	public SimpleResponse addModule(NnResourceVo vo) {
		vo.setParentCode(null);
		vo.setResourceUrl("null");
		boolean isSuc = resService.addRes(vo);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("addRes")
	@ResponseBody
	public SimpleResponse addRes(NnResourceVo vo) {
		boolean isSuc = resService.addRes(vo);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("updateModule")
	@ResponseBody
	public SimpleResponse updateModule(NnResourceVo vo) {
		vo.setParentCode(null);
		vo.setResourceUrl("null");
		boolean isSuc = resService.updateRes(vo);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("updateRes")
	@ResponseBody
	public SimpleResponse updateRes(NnResourceVo vo) {
		boolean isSuc = resService.updateRes(vo);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("enableRes")
	@ResponseBody
	public SimpleResponse enableRes(String resCode, String parentCode) {
		boolean isSuc = resService.enableRes(resCode, parentCode);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("disableRes")
	@ResponseBody
	public SimpleResponse disableRes(String resCode) {
		boolean isSuc = resService.disableRes(resCode);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("enableModule")
	@ResponseBody
	public SimpleResponse enableModule(String resCode) {
		boolean isSuc = resService.enableModule(resCode);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
	@RequestMapping("disableModule")
	@ResponseBody
	public SimpleResponse disableModule(String resCode) {
		boolean isSuc = resService.disableModule(resCode);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("isSuc", isSuc);
		return SimpleResponse.suc(ret);
	}
	
}
