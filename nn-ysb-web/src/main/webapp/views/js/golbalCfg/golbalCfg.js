$(document).ready(function() {
	var vue = new Vue({
		el : "#globalCfgApp",
		data : {
			globalCfg : {}
		},
		
		methods : {
			query : function() {
				QueryMain.loadData({
					argUrl: "/globalCfg/getGlobalCfg?1=1",
					callback: function(response) {
						if(response!=null){
							vue.globalCfg = response;
						}
					},
					error : function(data) {	
						DialogTemplate.showInfoMsg('查询失败!','');
					}
				});	
			},
			add : function(){
				QueryMain.loadData({
					argUrl: "/globalCfg/saveOrUpdateGlobalCfg?1=1",
					paramdata: this.globalCfg,
					callback: function(response) {
						if(response.code && response.code == "01"){
							DialogTemplate.showInfoMsg(response.desc,'');
						}else{
							DialogTemplate.showInfoMsg('更新成功!','');
						}
					},
					error : function(data) {	
						DialogTemplate.showInfoMsg('更新失败!','');
					}
				});	
			}
		},
		
		mounted: function() {
			this.query();
		}
	});
});