/**
 * 提示信息.
 */
jQuery.extend(jQuery.validator.messages, {
	mobilephone : "请输入正确的手机号",
	telphone : "请输入正确的座机号",
	cnletter: '只能输入汉字字母',
	textSeleRange:'输入文本不符合要求',
	requiredBy:'该字段不能为空',
	isDateFormat:'日期格式不正确',
	isDateGThan : '不能小于前一个文本框中的日期值',
	isDateLThan : '不能大于前一个文本框中的日期值',
	isDecimalMax :'小数位长度不能超过{0}位',
	isDecimal : '不是合法小数',
	isNegative:'请输入一个负数',
	isCNID : '请输入正确的身份证号码',
	isPostcode:'请输入6位数的邮编号码'
});

/**
 * 手机
 */
jQuery.validator.addMethod("mobilephone", function(value, element) {
	return this.optional(element)
			|| sino.tool.Val.isMobilePhone(value);
}, jQuery.format(jQuery.validator.messages["mobilephone"]));
/**
 * 座机(区号-电话号码-分机号)
 */
jQuery.validator.addMethod("telphone", function(value, element) {
	return this.optional(element) || sino.tool.Val.isTelPhone(value);
}, jQuery.format(jQuery.validator.messages["telphone"]));

/**
 * 请输入正确的身份证号码
 */
jQuery.validator.addMethod("isCNID", function(value, element) {
	return this.optional(element) || sino.tool.Val.isCNID(value);
}, jQuery.format(jQuery.validator.messages["isCNID"]));

/**
 * 只能输入汉字字母.
 */
jQuery.validator.addMethod("cnletter", function(value, element) {
	return this.optional(element) || sino.tool.Val.isCnLetter(value);
}, jQuery.format(jQuery.validator.messages["cnletter"]));

/**
 * 匹配文本取值范围.
 * options {[CN|EN|en|DI]:[true|flash],other:[\.|-|\\|......]}.
 * ex:{CN:true,EN:true,DI:true,en:true,other:'-_'}
 * 表示text的取值范围是：中文字符、英文（大小写）字符、数字、'-' 以及 '_'
 */
jQuery.validator.addMethod("textSeleRange", function(value, element,options) {
	return this.optional(element) || sino.tool.Val.textSeleRange(jQuery.trim(value),options);
}, jQuery.format(jQuery.validator.messages["textSeleRange"]));

/**
 * 根据指定的字段来判断当前字段是否可为空
 * <p>
 * @param value
 * @param element
 * @param options 
 * 		  带一些参数。例如：{src:'#select_id',srccheck:'check',self:'check'} 表示
 * 		  要验证的字段是根据id为select_id的控件是否被选中来判断的，如果src表示的控件
 * 		  处于被选中的状态，当前字段为必填，否则相反。如果options.check 为空表示src表示的控件
 * 		  可以根据value值来判断，如果value值不为空则表示当前字段为必填字段。
 * 		  options.self='check' 表示判断当前控件是checkbox 或者 radio。
 */
jQuery.validator.addMethod("requiredBy", function(value, element,options) {
	if(options.src){
		var src = $(options.src);
		if(src){
			if(options.srccheck=='check'){
				src = $(options.src+":checked");
				if(src && src.length>=1){
					return _requiredBy(value, element,options);
				}
			}else{
				if($(src[0]).val() && jQuery.trim($(src[0]).val())!=''){
					return _requiredBy(value, element,options);
				}
			}
		}
	}
	return true;
}, jQuery.format(jQuery.validator.messages["requiredBy"]));

function _requiredBy(value, element,options){
	if(options.self=='check'){
		return $("[name="+$(element).attr('name')+"]:checked").length>0;
	}else if(value==null || jQuery.trim(value)==''){
		return false;
	}
	return true;
}

/**
 * 验证日期格式是否正确
 *  <p>
 * @param v
 *            为日期文本，如2011-02-03.
 * @param dateFormat
 * 			  为日期要验证的格式。格式中须是年(yyyy)月(MM)日(dd)三者的组合，目前仅有此功能。
 */
jQuery.validator.addMethod("isDateFormat", function(value, element,dateFormat) {
	return this.optional(element) || sino.tool.Val.isDate(jQuery.trim(value),dateFormat);
}, jQuery.format(jQuery.validator.messages["isDateFormat"]));


/**
 * 验证日期是否小于目标文本框中的日期.
 * <p>
 * @param v
 *            为日期文本，如2011-02-03.
 * @param options
 * 			  options.p : 为日期要验证的格式。格式中须是年(yyyy)月(MM)日(dd)三者的组合，(yyyy[-/]MM[-/]dd)目前仅有此功能。
 * 			  options.lsrc : 为目标文本的标示，根据该标示获取element的value，进行 v < value的比较。
 */
jQuery.validator.addMethod("isDateLThan", function(value, element,options) {
	if(options.lsrc){
		var lele = $(options.lsrc);
		if(lele.length>0){
			if($(lele[0]).val()){
				return this.optional(element) || sino.tool.Val.isDateGThan(jQuery.trim(value),{p:options.p,type:'lt',src:$(lele[0]).val()});
			}
		}
	}
	return false;
}, jQuery.format(jQuery.validator.messages["isDateLThan"]));

/**
 * 验证日期是否位大于目标文本框中的日期.
 * <p>
 * @param v
 *            为日期文本，如2011-02-03.
 * @param options
 * 			  options.p : 为日期要验证的格式。格式中须是年(yyyy)月(MM)日(dd)三者的组合，(yyyy[-/]MM[-/]dd)目前仅有此功能。
 * 			  options.gsrc : 为目标文本的标示，根据该标示获取element的value，进行 v > value的比较。
 */
jQuery.validator.addMethod("isDateGThan", function(value, element,options) {
	if(options.gsrc){
		var lele = $(options.gsrc);
		if(lele.length>0){
			if($(lele[0]).val()){
				return this.optional(element) || sino.tool.Val.isDateGThan(jQuery.trim(value),{p:options.p,type:'gt',src:$(lele[0]).val()});
			}
		}
	}
	return false;
}, jQuery.format(jQuery.validator.messages["isDateGThan"]));

/**
 * 验证是否是小数。如：0.22;0.00
 *  <p>
 */
jQuery.validator.addMethod("isDecimal", function(value, element,options) {
	return this.optional(element) || sino.tool.Val.isDecimal(jQuery.trim(value),options);
}, jQuery.format(jQuery.validator.messages["isDecimal"]));
/**
 * 验证小数位数是否小于等于指定值。
 *  <p>
 *  @param params 
 *  	 number 类型值
 */
jQuery.validator.addMethod("isDecimalMax", function(value, element,params) {
	return this.optional(element) || sino.tool.Val.isDecimal(jQuery.trim(value),{num:params});
}, jQuery.format(jQuery.validator.messages["isDecimalMax"]));
/**
 * 验证是否是负数。
 *  <p>
 */
jQuery.validator.addMethod("isNegative", function(value, element) {
	return this.optional(element) || sino.tool.Val.isNegative(jQuery.trim(value));
}, jQuery.format(jQuery.validator.messages["isNegative"]));
/**
 * 验证是否邮编
 */
jQuery.validator.addMethod("isPostcode", function(value, element) {
return this.optional(element) || sino.tool.Val.isPostcode(jQuery.trim(value));
}, jQuery.format(jQuery.validator.messages["isPostcode"]));