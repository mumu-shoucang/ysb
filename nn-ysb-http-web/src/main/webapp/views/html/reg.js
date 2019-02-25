$(document).ready(function() {
	//用户协议选择
	$(".check").click(function(){
		var _this = $(this);
		if(_this.attr('src')=='img/check.png'){  
	        _this.attr('src','img/checked.png');  
	    }else{  
	        _this.attr('src','img/check.png');
    	}
	});
	
	//用户协议弹出框
	$(".rule span").click(function(){
		layer.open({
		  type: 1,
		  title: '用户协议',
		  skin: 'layui-layer-rim', //加上边框
		  area: ['80%', '60%'], //宽高
		  content: $("#rules")
		});
	});
	
	//验证码
	var validCode=true;
	$(".getCode").click(function(){
		if($(".check").attr('src')=='img/check.png'){
			layer.msg("请查看用户协议并同意", {
				icon : 2
			});
			return false;
		}
		
		var merchantMobile = $("#merchantMobile").val();
		if(!merchantMobile){
			layer.msg("请输入注册手机号", {
				icon : 2
			});
			return false;
		}
		
		var telReg = /^1[0-9]{10}$/;
		if(!telReg.test(merchantMobile)){
			layer.msg("请输入正确格式注册手机号", {
				icon : 2
			});
			return false;
		}
		
		var $code = $(this);
		var time=60;
	    if (validCode) {
	    	validCode=false;
	    	var t=setInterval(function  () {
	    		time--;
	    	    $code.html(time+"s后重发");
	    	    $code.css("background","#dbdbdb").attr("disabled","disabled");
	    	    if (time==0) {
	    	        clearInterval(t);
	    	        $code.html("重新获取");
	    	        $code.css("background","#028760").removeAttr("disabled");
	    	        validCode=true;
	    	    }
	    	},1000)
	    }
		
		QueryMain.loadData({
			argUrl: "/register/sendSMSCode?1=1",
			paramdata : {merchantMobile : merchantMobile} ,
			callback: function(response) {
				if(response.code == 0000){
					layer.msg("验证码已发送到手机，请注意查收", {
						icon : 1
					});
				}else{
					layer.msg(response.desc, {
						icon : 2
					});
				}
			},
			error : function(data) {
				layer.msg("验证码已发失败，请稍后重试", {
					icon : 2
				});
			}
		});
	});
	
	//注册
	var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$/;
	$('.sure button').click(function(){
		var pwd = $("#pwd").val();
		var rePwd = $("#rePwd").val();
		
		if($(".check").attr('src')=='img/check.png'){
			layer.msg("请查看用户协议并同意", {
				icon : 2
			});
			return false;
		}
		
		var merchantMobile = $("#merchantMobile").val();
		if(!merchantMobile){
			layer.msg("请输入注册手机号", {
				icon : 2
			});
			return false;
		}
		
		var telReg = /^1[0-9]{10}$/;
		if(!telReg.test(merchantMobile)){
			layer.msg("请输入正确格式注册手机号", {
				icon : 2
			});
			return false;
		}
		
		var verificateCode = $("#verificateCode").val();
		if(!verificateCode){
			layer.msg("请输入验证码", {
				icon : 2
			});
			return false;
		}
		
		var refCode = $("#refCode").val();
		if(!refCode){
			layer.msg("请输入邀请码", {
				icon : 2
			});
			return false;
		}
		
		if(pwd == "" || rePwd == "" ){
			layer.msg("请输入密码", {
				icon : 2
			});
		}else if(!pwdReg.test(pwd) || !pwdReg.test(rePwd)){
			layer.msg("请输入正确格式密码", {
				icon: 2
	        });
		}else if(pwd != rePwd){
			layer.msg("两次密码不相同", {
				icon: 2
	        });				
		}else{
			QueryMain.loadData({
				argUrl: "/register/register?1=1",
				paramdata : $('#regForm').serializeArray(),
				callback: function(response) {
					if(response.code == ("0000")){
						layer.msg('用户注册成功',{
	                          offset:['50%']
	                    },function(){
//	                    	window.location.href="https://www.pgyer.com/ysb_android";
	                    	window.location.href="download.html";
	                    });
					}else{
						layer.msg(response.desc, {
							icon : 2
						});
					}
					
				},
				error : function(data) {
					layer.msg("用户注册失败,"+data.desc, {
						icon : 2
					});
				}
			});
		}
	});
	
});