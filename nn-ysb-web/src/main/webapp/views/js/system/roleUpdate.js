$(document).ready(function() {
	
	(function() {
		$('#app').height(document.documentElement.clientHeight)
	})();
	
	
	var vue = new Vue({
		el: '#app',
		
		data: {
			role: {
				roleCode: '',
				roleName: ''
			}
		},
		methods: {
			init: function() {
				
				var par = QueryMain.getPars();
				this.roleCode = par['roleCode'];
				
				QueryMain.loadData({
					argUrl: '/role/getRoleByCode?1=1',
					paramdata: {
						roleCode: this.roleCode
					},
					callback: function(response) {
						var zTreeObj = $.fn.zTree.init($('#resTree'), {
							data: {
								simpleData: {
									enable: true,
									idKey: 'resourceCode',
									pIdKey: 'parentCode'
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
						vue.role = response.role;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			update: function() {
				var checkNodes = this.ztree.getCheckedNodes(true);
				var resCodes = [];
				$.each(checkNodes, function(index, item) {
					resCodes.push(item.resourceCode);
				});
				QueryMain.loadData({
					argUrl: '/role/updateRole?1=1',
					paramdata: {
						roleCode: vue.roleCode,
						roleName: vue.role.roleName,
						resCodes: resCodes
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

