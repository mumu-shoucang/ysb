/**
 * ��ʾ��Ϣ.
 */
jQuery.extend(jQuery.validator.messages, {
	mobilephone : "��������ȷ���ֻ���",
	telphone : "��������ȷ��������",
	cnletter: 'ֻ�����뺺����ĸ',
	textSeleRange:'�����ı�������Ҫ��',
	requiredBy:'���ֶβ���Ϊ��',
	isDateFormat:'���ڸ�ʽ����ȷ',
	isDateGThan : '����С��ǰһ���ı����е�����ֵ',
	isDateLThan : '���ܴ���ǰһ���ı����е�����ֵ',
	isDecimalMax :'С��λ���Ȳ��ܳ���{0}λ',
	isDecimal : '���ǺϷ�С��',
	isNegative:'������һ������',
	isCNID : '��������ȷ�����֤����',
	isPostcode:'������6λ�����ʱ����'
});

/**
 * �ֻ�
 */
jQuery.validator.addMethod("mobilephone", function(value, element) {
	return this.optional(element)
			|| sino.tool.Val.isMobilePhone(value);
}, jQuery.format(jQuery.validator.messages["mobilephone"]));
/**
 * ����(����-�绰����-�ֻ���)
 */
jQuery.validator.addMethod("telphone", function(value, element) {
	return this.optional(element) || sino.tool.Val.isTelPhone(value);
}, jQuery.format(jQuery.validator.messages["telphone"]));

/**
 * ��������ȷ�����֤����
 */
jQuery.validator.addMethod("isCNID", function(value, element) {
	return this.optional(element) || sino.tool.Val.isCNID(value);
}, jQuery.format(jQuery.validator.messages["isCNID"]));

/**
 * ֻ�����뺺����ĸ.
 */
jQuery.validator.addMethod("cnletter", function(value, element) {
	return this.optional(element) || sino.tool.Val.isCnLetter(value);
}, jQuery.format(jQuery.validator.messages["cnletter"]));

/**
 * ƥ���ı�ȡֵ��Χ.
 * options {[CN|EN|en|DI]:[true|flash],other:[\.|-|\\|......]}.
 * ex:{CN:true,EN:true,DI:true,en:true,other:'-_'}
 * ��ʾtext��ȡֵ��Χ�ǣ������ַ���Ӣ�ģ���Сд���ַ������֡�'-' �Լ� '_'
 */
jQuery.validator.addMethod("textSeleRange", function(value, element,options) {
	return this.optional(element) || sino.tool.Val.textSeleRange(jQuery.trim(value),options);
}, jQuery.format(jQuery.validator.messages["textSeleRange"]));

/**
 * ����ָ�����ֶ����жϵ�ǰ�ֶ��Ƿ��Ϊ��
 * <p>
 * @param value
 * @param element
 * @param options 
 * 		  ��һЩ���������磺{src:'#select_id',srccheck:'check',self:'check'} ��ʾ
 * 		  Ҫ��֤���ֶ��Ǹ���idΪselect_id�Ŀؼ��Ƿ�ѡ�����жϵģ����src��ʾ�Ŀؼ�
 * 		  ���ڱ�ѡ�е�״̬����ǰ�ֶ�Ϊ��������෴�����options.check Ϊ�ձ�ʾsrc��ʾ�Ŀؼ�
 * 		  ���Ը���valueֵ���жϣ����valueֵ��Ϊ�����ʾ��ǰ�ֶ�Ϊ�����ֶΡ�
 * 		  options.self='check' ��ʾ�жϵ�ǰ�ؼ���checkbox ���� radio��
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
 * ��֤���ڸ�ʽ�Ƿ���ȷ
 *  <p>
 * @param v
 *            Ϊ�����ı�����2011-02-03.
 * @param dateFormat
 * 			  Ϊ����Ҫ��֤�ĸ�ʽ����ʽ��������(yyyy)��(MM)��(dd)���ߵ���ϣ�Ŀǰ���д˹��ܡ�
 */
jQuery.validator.addMethod("isDateFormat", function(value, element,dateFormat) {
	return this.optional(element) || sino.tool.Val.isDate(jQuery.trim(value),dateFormat);
}, jQuery.format(jQuery.validator.messages["isDateFormat"]));


/**
 * ��֤�����Ƿ�С��Ŀ���ı����е�����.
 * <p>
 * @param v
 *            Ϊ�����ı�����2011-02-03.
 * @param options
 * 			  options.p : Ϊ����Ҫ��֤�ĸ�ʽ����ʽ��������(yyyy)��(MM)��(dd)���ߵ���ϣ�(yyyy[-/]MM[-/]dd)Ŀǰ���д˹��ܡ�
 * 			  options.lsrc : ΪĿ���ı��ı�ʾ�����ݸñ�ʾ��ȡelement��value������ v < value�ıȽϡ�
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
 * ��֤�����Ƿ�λ����Ŀ���ı����е�����.
 * <p>
 * @param v
 *            Ϊ�����ı�����2011-02-03.
 * @param options
 * 			  options.p : Ϊ����Ҫ��֤�ĸ�ʽ����ʽ��������(yyyy)��(MM)��(dd)���ߵ���ϣ�(yyyy[-/]MM[-/]dd)Ŀǰ���д˹��ܡ�
 * 			  options.gsrc : ΪĿ���ı��ı�ʾ�����ݸñ�ʾ��ȡelement��value������ v > value�ıȽϡ�
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
 * ��֤�Ƿ���С�����磺0.22;0.00
 *  <p>
 */
jQuery.validator.addMethod("isDecimal", function(value, element,options) {
	return this.optional(element) || sino.tool.Val.isDecimal(jQuery.trim(value),options);
}, jQuery.format(jQuery.validator.messages["isDecimal"]));
/**
 * ��֤С��λ���Ƿ�С�ڵ���ָ��ֵ��
 *  <p>
 *  @param params 
 *  	 number ����ֵ
 */
jQuery.validator.addMethod("isDecimalMax", function(value, element,params) {
	return this.optional(element) || sino.tool.Val.isDecimal(jQuery.trim(value),{num:params});
}, jQuery.format(jQuery.validator.messages["isDecimalMax"]));
/**
 * ��֤�Ƿ��Ǹ�����
 *  <p>
 */
jQuery.validator.addMethod("isNegative", function(value, element) {
	return this.optional(element) || sino.tool.Val.isNegative(jQuery.trim(value));
}, jQuery.format(jQuery.validator.messages["isNegative"]));
/**
 * ��֤�Ƿ��ʱ�
 */
jQuery.validator.addMethod("isPostcode", function(value, element) {
return this.optional(element) || sino.tool.Val.isPostcode(jQuery.trim(value));
}, jQuery.format(jQuery.validator.messages["isPostcode"]));