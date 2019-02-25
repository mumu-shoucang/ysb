    function ifDigit(obj){
        slen=obj.length;
        for (i=0; i<slen; i++){
            cc = obj.charAt(i);
            if (cc <"0" || cc >"9"){
                return false;
            }
        }
        return true;
    }
	 function checkPage(pageno,totalPage){
        if(!ifDigit(pageno)){
            return false;
        }
        if(pageno == "" || pageno > totalPage*1 || pageno < 1){
            return false;
        }
        return true;
    }
    
	function submit_click(pageno,pageCountNum){
		  if(!checkPage(pageno,pageCountNum)){
	             alert("\u6ce8\u610f\uff1a\u8bf7\u8f93\u5165\u5408\u6cd5\u9875\u7801\u6570\uff01");
	             return;
	       }else{
				document.getElementById("pageCurrentGO").value=pageno;
				document.pageform.submit(); 
		   }
	}
	
	function pageOnSubmit(pageCountNum){
			return true;
	}