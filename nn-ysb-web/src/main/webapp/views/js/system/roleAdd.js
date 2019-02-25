$(document).ready(function() {
	
	(function() {
		$('#add').height(document.documentElement.clientHeight)
	})();
	
	var vue = new Vue({
		el: '#add',
		
		data: {
			roleName: ''
		},
		methods: {
			init: function() {
				QueryMain.loadData({
					argUrl: '/res/listTreeRes?1=1',
					paramdata: {
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
						}, response);
						vue.ztree = zTreeObj;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			add: function() {
				var checkNodes = this.ztree.getCheckedNodes(true);
				var resCodes = [];
				$.each(checkNodes, function(index, item) {
					resCodes.push(item.resourceCode);
				});
				QueryMain.loadData({
					argUrl: '/role/addRole?1=1',
					paramdata: {
						roleName : vue.roleName,
						resCodes : resCodes
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

