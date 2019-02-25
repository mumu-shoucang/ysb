package com.n.ysb.web.statistics;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.n.ysb.service.statistics.IStatisticsService;
import com.n.ysb.service.statistics.po.NnStatistics;
import com.n.ysb.service.statistics.vo.NnStatisticsVo;
import com.n.ysb.web.base.BaseController;
import com.n.ysb.web.base.SimpleResponse;

@Controller
@RequestMapping("statistics")
public class StatisticsController extends BaseController {
    
    @Autowired
    private IStatisticsService statisticsService;

    @RequestMapping("getStatistics")
    @ResponseBody
    public SimpleResponse getStatistics(NnStatisticsVo statisticsVo,
            @RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit){
        
        PageInfo<NnStatistics> statisticsPage = statisticsService.getStatisticsByType(statisticsVo, pageNo, limit);
        
        return SimpleResponse.suc(statisticsPage);
    }
    
    @RequestMapping("getUntransferStatistics")
    @ResponseBody
    public SimpleResponse getUntransferStatistics(NnStatisticsVo statisticsVo,
    		@RequestParam(required = false, defaultValue = "1", value = "pageNo") int pageNo,
    		@RequestParam(required = false, defaultValue = "10", value = "limit") int limit){
    	
    		PageInfo<NnStatistics> statisticsPage = statisticsService.getUntransferStatistics(statisticsVo, pageNo, limit);
    	
    		return SimpleResponse.suc(statisticsPage);
    }
    
    @RequestMapping("export")
    @ResponseBody
    public SimpleResponse export(NnStatisticsVo statisticsVo,HttpServletResponse response){
        
        Workbook workbook = statisticsService.export(statisticsVo);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = null;
        BufferedOutputStream outputStream = null;
        int bufferSize = 2048;
        try {
            workbook.write(baos);
            byte[] ba = baos.toByteArray();
            bais = new ByteArrayInputStream(ba);
            
            String fileName = "佣金统计_" + new SimpleDateFormat("yyyy-MM-dd").format
                    (new Date()) + ".xlsx";
            String displayFileName = new String(fileName.getBytes("gb2312"), "iso-8859-1");
            
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + displayFileName);
            
            outputStream = new BufferedOutputStream(response.getOutputStream());
            
            byte[] buff = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = bais.read(buff, 0, buff.length)) != -1)

            {
                outputStream.write(buff, 0, bytesRead);
            }
            outputStream.flush();
            return SimpleResponse.suc(true);
        } catch (IOException e) {
            return SimpleResponse.suc(false);
        } finally{
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
