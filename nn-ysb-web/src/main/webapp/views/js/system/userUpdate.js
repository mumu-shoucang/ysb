$(document).ready(function() {
	
	(function() {
		$('#app').height(document.documentElement.clientHeight)
	})();
	
	var vue = new Vue({
		el: '#app',
		data: {
			user: {
				loginName: '',
				userName: ''
			}
		},
		methods: {
			init: function() {
				
				var par = QueryMain.getPars();
				this.userCode = par['userCode'];
				
				QueryMain.loadData({
					argUrl: '/user/getUserByCode?1=1',
					paramdata: {
						userCode: this.userCode
					},
					callback: function(response) {
						var zTreeObj = $.fn.zTree.init($('#roleTree'), {
							data: {
								simpleData: {
									enable: true,
									idKey: 'roleCode'
								}
							},
							view: {
								showIcon: true,
								showLine: true
							},
							check: {
								enable: true
							}
						}, response.tree);
						vue.ztree = zTreeObj;
						vue.user = response.user;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			update: function() {
				var checkNodes = this.ztree.getCheckedNodes(true);
				var roleCodes = [];
				$.each(checkNodes, function(index, item) {
					roleCodes.push(item.roleCode);
				});
				QueryMain.loadData({
					argUrl: '/user/update?1=1',
					paramdata: {
						userCode: vue.userCode,
						userName: vue.user.userName,
						roleCodes: roleCodes
					},
					callback: function(response) {
						var fn = function(){
							parent.window.location.reload();
						};
						if(response.isSuc) {
							DialogTemplate.showInfoMsg('成功!', fn);
						} else {
							DialogTemplate.showInfoMsg('失败!','');
						}
					},
					error: function(data) {	
						DialogTemplate.showInfoMsg('失败!','');
					}
				});	
			},
			cancle: function() {
				parent.window.cd.close();
			}
		},
		mounted: function() {
			this.init();
		}
	});
});

