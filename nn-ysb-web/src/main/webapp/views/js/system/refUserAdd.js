$(document).ready(function() {
	
	(function() {
		$('#add').height(document.documentElement.clientHeight)
	})();
	
	var vue = new Vue({
		el: '#add',
		data: {
			userName: '',
			loginName: '',
			loginPwd: '',
			refList: [],
			refId: '',
			refSign: ''
		},
		methods: {
			roleInit: function() {
				QueryMain.loadData({
					argUrl: '/role/listTreeRole?1=1',
					paramdata: {
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
						}, response);
						vue.ztree = zTreeObj;
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			refInit: function() {
				QueryMain.loadData({
					argUrl: '/refUser/listActiveRef?1=1',
					paramdata: {
					},
					callback: function(response) {
						vue.refList = response;
						vue.refId = response ? response[0].id : '';
					},
					error: function(data) {
						// do nothing
					}
				});	
			},
			add: function() {
				var checkNodes = this.ztree.getCheckedNodes(true);
				var roleCodes = [];
				$.each(checkNodes, function(index, item) {
					roleCodes.push(item.roleCode);
				});
				QueryMain.loadData({
					argUrl: '/user/add?1=1',
					paramdata: {
						userName: vue.userName,
						loginName: vue.loginName,
						loginPwd: vue.loginPwd,
						refSign: vue.refSign,
						roleCodes : roleCodes
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
		watch: {
			refId: function(newId, oldId) {
				var ref = {};
				$.each(vue.refList, function(index, item) {
					if(item.id == newId) {
						ref = item;
					}
				})
				this.userName = ref.name;
				this.loginName = ref.mobile;
				this.loginPwd = ref.mobile;
				this.refId = ref.id;
				this.refSign = ref.refSign;
			}
		},
		mounted: function() {
			this.roleInit();
			this.refInit();
		}
	});
});

