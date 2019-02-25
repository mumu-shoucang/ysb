$(document).ready(function() {
	var vue = new Vue({
		el : "#calendarApp",
		data : {
			calendarList:[],
			cldstatus  : calendarStatus,
			inputtext:{
				status : '',
				nonworkday: '',
				workday: '',
			},
		},
		methods : {
			query : function() {
				initPagination({
					url : "/calendar/getCalendarPage.controller?1=1",
					formData : this.inputtext,
					callback : function(response) {
						vue.calendarList = response;
					}
				});
			},
			reset : function(){
				this.inputtext = {
					status : '',
					nonworkday: '',
					workday: '',
				};
				this.query();
			},
			addFestival : function(){
				if(vue.inputtext.nonworkday==null||vue.inputtext.nonworkday==''){
					DialogTemplate.showInfoMsg('请选择要添加的日期','');
				}else{
					var secondRecharge = function(){
						QueryMain.loadData({
							argUrl: "/calendar/addCalendar?1=1",
							paramdata: {
								calendar : vue.inputtext.nonworkday,
								status : 1
							},
							callback: function(response) {
								var fn = function(){
									window.location.reload();
								};
								DialogTemplate.showInfoMsg('添加成功!',fn);
								
							},error : function(data) {
								DialogTemplate.showInfoMsg('添加失败!','');
							}
						});
					};
					DialogTemplate.showConfirm('是否进行添加操作',secondRecharge,'');
				}
			},
			addWork : function(){
				if(vue.inputtext.workday==null||vue.inputtext.workday==''){
					DialogTemplate.showInfoMsg('请选择要添加的日期','');
				}else{
					var secondRecharge = function(){
						QueryMain.loadData({
							argUrl: "/calendar/addCalendar?1=1",
							paramdata: {
								calendar : vue.inputtext.workday,
								status : 0
							},
							callback: function(response) {
								var fn = function(){
									window.location.reload();
								};
								DialogTemplate.showInfoMsg('添加成功!',fn);
								
							},error : function(data) {
								DialogTemplate.showInfoMsg('添加失败!','');
							}
						});
					};
					DialogTemplate.showConfirm('是否进行添加操作',secondRecharge,'');
				}
			},
			getByValue : function(status){
				var index = status;
				if(this.cldstatus[index].key==status){
					return this.cldstatus[index].value;
				}
			},
			deleteDate : function(calendar){
				var secondRecharge = function(){
					QueryMain.loadData({
						argUrl: "/calendar/deleteCalendar?1=1",
						paramdata: calendar,
						callback: function(response) {
							var fn = function(){
								window.location.reload();
							};
							DialogTemplate.showInfoMsg('删除成功!',fn);
							
						},error : function(data) {
							DialogTemplate.showInfoMsg('删除失败!','');
						}
					});
				};
				DialogTemplate.showConfirm('是否删除当前日期',secondRecharge,'');
			}
			
			
		},
		mounted: function() {
			this.query();
		}
	});
});

