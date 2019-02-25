/**
 * ���������ռ�.
 */
(function(sino) {
	// namespace
	sino.ns('tool');
})(sino);

/**
 * ����Base���߷���
 */
(function(sino) {
	/**
	 * ����Base����.
	 */
	sino.tool.Base = function() {
	};

	/**
	 * Returns true if the passed value is not undefined.
	 */
	sino.tool.Base.isDefined = function(v) {
		return typeof v !== 'undefined';
	};

	/**
	 * �Ƿ�Ϊ��.
	 * <p>
	 * ��������������Ϊ�ǿ�:
	 * <ul>
	 * <li>Ϊnull</li>
	 * <li>Ϊundefined</li>
	 * </ul>
	 */
	sino.tool.Base.isEmpty = function(obj) {
		return v === null || v === undefined;
	};
})(sino);

/**
 * Uri����
 */
(function(sino) {
	/**
	 * ����URLUtil����.
	 */
	sino.tool.URLUtil = function() {
	};

})(sino);

/**
 * �ַ�������.
 */
(function(sino) {
	/**
	 * ����StringUtil����.
	 */
	sino.tool.StringUtil = function() {
	};

	/**
	 * �Ƿ�Ϊ��.
	 * 
	 * <p>
	 * ���sourceStr�ַ���Ϊnull,undefined,''������.
	 * 
	 * @param sourceStr
	 *            Դ�ַ���
	 * @returns true if blank
	 */
	sino.tool.StringUtil.isBlank = function(sourceStr) {
		if (sino.isString(sourceStr) && '' != sourceStr) {
			return false;
		}

		return true;
	};

	/**
	 * ȥ���ַ�����ĩ���˿ո�.
	 * <p>
	 * ������,����''.
	 */
	sino.tool.StringUtil.trim = function(sourceStr) {
		if (!sino.tool.Base.isEmpty(sourceStr)) {
			var trimLeft = /^[\s]+/;
			var trimRight = /[\s]+$/;
			sourceStr.toString().replace(trimLeft, "").replace(trimRight, "");
		}
		return '';
	};
})(sino);

/**
 * ��֤����
 */
(function(sino) {
	/**
	 * ����Val����.
	 */
	sino.tool.Val = function(config) {
	};

	/**
	 * �ж��Ƿ����ֻ���.
	 * 
	 * @param phoneNo
	 *            �ֻ���
	 * @returns boolean true if is mobilePhone
	 */
	sino.tool.Val.isMobilePhone = function(phoneNo) {
		return /(13\d{9})|(15\d{9})|(18\d{9})/.test(phoneNo);
	};

	/**
	 * �ж��Ƿ���������.
	 * 
	 * @param phoneNo
	 *            ������
	 * @returns boolean true if is telephone
	 */
	sino.tool.Val.isTelPhone = function(phoneNo) {
		return /(\d+)*-?\d{5,8}(-?\d+)*/.test(phoneNo);
	};

	/**
	 * �ж��Ƿ��Ǻ��ֻ�����ĸ.
	 * 
	 * <p>
	 * unallowLetter Ϊ trueʱ��ʾ������Ϊ��ĸ
	 * 
	 * @param text
	 *            �ı�
	 * @returns boolean true if is Cn
	 */
	sino.tool.Val.isCnLetter = function(text, unallowLetter) {
		if (unallowLetter) {
			return /^[\u4E00-\u9FA5]+$/.test(text);
		}
		return /^[A-Za-z\u4E00-\u9FA5]+$/.test(text);
	};

	/**
	 * �ж��Ƿ�Ϊ��ĸ����.
	 * 
	 * <p>
	 * unallowDigit Ϊ trueʱ��ʾֻ������ĸ.
	 * 
	 * @param text
	 *            �ı�
	 */
	sino.tool.Val.isLetterDigit = function(text, unallowDigit) {
		if (unallowDigit) {
			return /^[A-Za-z]+$/.test(text);
		}
		return /^[A-Za-z\d]+$/.test(text);
	};
	/**
	 * ��֤�Ƿ��Ǹ�����
	 * <p>
	 * @param v
	 * 		Ҫ��֤��ֵ��
	 */
	sino.tool.Val.isNegative = function (v){
		return /^-(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(v);
	};
	/**
	 * �ж�text�ı�ȡֵ��Χ�Ƿ���ָ����ģʽ��.
	 * 
	 * <p>
	 * options {[CN|EN|en|DI]:[true|flash],other:[\.|-|\\]}.
	 * ex:{CN:true,EN:true,DI:true,en:true,other:'-_'}
	 * ��ʾtext��ȡֵ��Χ�ǣ������ַ���Ӣ�ģ���Сд���ַ������֡�'-' �Լ� '_'
	 * 
	 * @param text
	 *            �ı�
	 */
	sino.tool.Val.textSeleRange = function(text, options) {
		var s = '^[';
		if(options.EN){
			s = s + 'A-Z';
		}
		if(options.en){
			s = s + 'a-z';
		}
		if(options.CN){
			s = s + '\\u4E00-\\u9FA5';
		}
		if(options.DI){
			s = s + '\\d';
		}
		if(options.other){
			s = s + options.other;
		}
		s = s + ']+$';
		if(s == '^[]+$'){
			return true;
		}
		var rg = new RegExp(s,'g');
		return rg.test(text);
	};
	
	/**
	 * ��֤���֤��Ϣ.
	 * 
	 * <p>
	 * @param v
	 *           ���֤����.
	 */
	sino.tool.Val.isCNID = function (v){
		if(!(/^\d{17}(\d|x)$/i.test(v) || /^\d{15}$/i.test(v))) return false;
		var provinceCode = parseInt(v.substr(0,2));
		if((provinceCode < 11) || (provinceCode > 91)) return false;
		var forTestDate = v.length == 18 ? v : v.substr(0,6)+"19"+v.substr(6,15);
		var birthday = forTestDate.substr(6,8);
		if(!sino.tool.Val.isDate(birthday,'yyyyMMdd')) return false;
		if(v.length == 18) {
			v = v.replace(/x$/i,"a");
			var verifyCode = 0;
			for(var i = 17;i >= 0;i--)   
            	verifyCode += (Math.pow(2,i) % 11) * parseInt(v.charAt(17 - i),11);
            if(verifyCode % 11 != 1) return false;
		}
		return true;
	};
	
	/**
	 * ��֤�����Ƿ���ϸ�ʽ.
	 * 
	 * <p>
	 * @param v
	 *            Ϊ�����ı�����2011-02-03.
	 * @param dateFormat
	 * 			  Ϊ����Ҫ��֤�ĸ�ʽ����ʽ��������(yyyy)��(MM)��(dd)���ߵ���ϣ�Ŀǰ���д˹��ܡ�
	 */
	sino.tool.Val.isDate = function(v,dateFormat) {
		var MONTH = "MM";
	   	var DAY = "dd";
	   	var YEAR = "yyyy";
		var regex = '^'+dateFormat.replace(YEAR,'\\d{4}').replace(MONTH,'\\d{2}').replace(DAY,'\\d{2}')+'$';
		if(!new RegExp(regex).test(v)) return false;
		var year = v.substr(dateFormat.indexOf(YEAR),4);
		var month = v.substr(dateFormat.indexOf(MONTH),2);
		var day = v.substr(dateFormat.indexOf(DAY),2);
		
		var d = new Date(year+"/"+month+"/"+day);
		return ( parseInt(month, 10) == (1+d.getMonth()) ) && 
					(parseInt(day, 10) == d.getDate()) && 
					(parseInt(year, 10) == d.getFullYear() );		
	};
	
	/**
	 * �����ı���Ŀ���ı��������ڵıȽ�.
	 * 
	 * <p>
	 * @param v
	 *            Ϊ�����ı�����'2011-02-03'.
	 * @param options
	 * 			  options.p : Ϊ����Ҫ��֤�ĸ�ʽ����ʽ��������(yyyy)��(MM)��(dd)���ߵ���ϣ�(yyyy[-/]MM[-/]dd)Ŀǰ���д˹��ܡ�
	 * 			  options.src : Ϊ���Ƚϵ������ı�,��'2011-02-05'��
	 * 			  options.type : case >> {'lt' <;'gt' >;'leq' <=;geq >=;eq ==} 
	 */
	sino.tool.Val.isDateGThan = function(v,options) {
		var MONTH = "MM";
	   	var DAY = "dd";
	   	var YEAR = "yyyy";
		var dateFormat = options.p ? options.p : 'yyyy-MM-dd';
		var regex = '^'+dateFormat.replace(YEAR,'\\d{4}').replace(MONTH,'\\d{2}').replace(DAY,'\\d{2}')+'$';
		var re = new RegExp(regex);
		if(!re.test(v)) return false;
		if(!options.src || !re.test(options.src)) return false;
		switch (options.type){
			case 'lt' : return v < options.src;
			case 'leq' : return v <= options.src;
			case 'gt' : return v > options.src;
			case 'geq' : return v >= options.src;
			default : return v == options.src;
		}
		return false;
	};
	
	/**
	 * ��֤�Ƿ���С����С��������Ƿ�С�ڵ���ָ��ֵ��
	 * �������ֵ�����ڻ��߸���ֵ����number���͵ģ�ֻ��֤v�Ƿ���С����
	 * <p>
	 * @param v
	 * 		Ҫ��֤��ֵ��
	 * @param o
	 * 		{num:5} o.num��С�����������С�ڵ���o.num��
	 */
	sino.tool.Val.isDecimal = function (v,o){
		if(!/^(([1-9]\d*)|(\d))([\.]\d+)?$/.test(v))return false;
		if(o && o.num && typeof o.num=='number'){
			v = v + '';
			if(v.indexOf('.')==-1)return true;
			var s = v.substring(v.indexOf('.')+1);
			return s.length > 0 && s.length <= o.num;
		}
		return true;
	};
	/**
	 * ��֤�Ƿ���6λ�����ʱ����
	 */
	sino.tool.Val.isPostcode = function(v){
		return (!isNaN(v) && !/^\s+$/.test(v)) && (v.length==6);
	};
})(sino);