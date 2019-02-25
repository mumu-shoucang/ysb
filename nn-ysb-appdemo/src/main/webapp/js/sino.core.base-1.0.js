/**
 * Sinvatech base JS.
 * 
 * Copyright (c) 2011 Beijing Sinova Technologies,Inc.All Rights Reserved
 */

// root Namespace define.
var sino = {
	version : '1.0'
};

sino.apply = function(o, c, defaults){
    if(defaults){
    	sino.apply(o, defaults);
    }
    if(o && c && typeof c == 'object'){
        for(var p in c){
            o[p] = c[p];
        }
    }
    return o;
};

/**
 * Do some init Action.
 * <p>
 * ���������ռ�sino.base,Ϊsino���һЩ���Թ���.
 * ����һЩ�ǳ�����,��ͨ�ù��ܺ����İ���(����ģ�黯ְ�𻮷�).
 */
(function(window, undefined) {

	// for old browsers,the code of next line references Ext js SRC.
	window.undefined = window.undefined;
	
	var DOC = window.document;
	
	var ID_REG = /#\w+/;
	
	/**
	 * ʵ�ּ̳�.
	 * <p>
	 * �����ܺ�����Դ��Crockford��ʵ�ֵ��޸�.ֻ�����ڽ�Function��չ.
	 * 
	 * @param parent {Function} �������.
	 */
	Function.prototype.inherits = function(parent) {
        this.prototype = new parent();
        this.prototype.constructor = this;
        this.prototype.uber = function(name) {
            var f = parent.prototype[name];
            return f.apply(this, Array.prototype.slice.call(arguments, 1));
        };
    };

	//����sino�µĹ���
	sino.apply(sino,{
		/**
		 * �������ռ书�ܺ���.
		 * 
		 * <p>
		 * ��������:
		 * <pre>
		 *  sino.ns('ns1');
		 *  sino.ns1.aa = 'ns1';
		 *  
		 *  sino.ns('ns1.ns2');
		 *  sino.ns1.aa = 'ns1.aa';
		 *  sino.ns1.ns2.aa = 'ns1.ns2.aa';
		 * </pre>
		 * 
		 * @param extendNS
		 *            string type,�Ծ��ŷָ�,�����Ϊstring�򲻴���(��'ns1.ns2')
		 */
		namespace : function(extendNS) {
			if (extendNS && typeof extendNS == 'string') {
				d = extendNS.split(".");
				o = sino[d[0]] = sino[d[0]] || {};
				var secondArray = d.slice(1);
				if (secondArray
						&& Object.prototype.toString.apply(secondArray) === '[object Array]'
						&& secondArray.length) {
					for ( var i = 0, len = secondArray.length; i < len; i++) {
						o = o[secondArray[i]] = o[secondArray[i]] || {};
					}
				}
			}
		},
		
		/**
		 * ��ȡDOMԪ��,����DOMԪ�ص�ԭ�����󣬲������κεķ�װ.
		 * <p>
		 * ��������Ӧ���õ�DOM���ģ���У����ǵ�ʹ�õı���ԣ��ҵ����������ѹ��ܷ���������
		 * ����.����ʵ�ֲ�Ҫȥ����������.
		 * 
		 * @param elestr Ԫ���ַ���,ID,name
		 * @param opt_context ��ѡ,Ĭ����document
		 * @return HTMLElement
		 */
		getDom : function(elestr,opt_context) {
			if(!opt_context){
				opt_context = DOC;
			}
			if(ID_REG.test(elestr)){
				return opt_context.getElementById(elestr.replace('#',''));
			}else{
				return opt_context.getElementsByName(elestr)[0];
			}
		},
		
		/**
		 * ����¼�����.
		 */
		addEventListener: function(elem,type,eventHandle){
			if ( elem.addEventListener ) {
				elem.addEventListener( type, eventHandle, false );
			} else if ( elem.attachEvent ) {
				elem.attachEvent( "on" + type, eventHandle );
			}
		},
		
		/**
		 * ɾ���¼�����.
		 * @param elem
		 * @param type
		 * @param eventHandle
		 */
		removeEventListener: function(elem,type,eventHandle){
			if(elem.removeEventListener){
				elem.removeEventListener( type, eventHandle, false );
			} else if (elem.detachEvent) {
				elem.detachEvent("on" + type,eventHandle);
			}
		},
		
		/**
		 * �����¼�.
		 * @param eventType
		 */
		trigger: function(elem,eventType){
			if(document.all){ // IE
				elem.fireEvent('on' + eventType);
			}else{
				var e = document.createEvent('HTMLEvents');
		        e.initEvent(eventType, false, false);
		        elem.dispatchEvent(e);
			}
		},
		
		/**
		 * �ж��Ƿ����ַ�������.
		 * 
		 * <p>
		 * null or undefined �����ַ�������.
		 * 
		 * @returns true if string type
		 */
		isString: function(obj) {
			if (typeof obj == 'string') {
				return true;
			}

			return false;
		},
		
		/**
		 * �Ƿ��Ǻ���.
		 * @param fun
		 * @returns {Boolean}
		 */
		isFunction: function(fun){
			return Object.prototype.toString.apply(fun) === '[object Function]';
		},
		
		/**
		 * �Ƿ�������.
		 * 
		 * @param array
		 */
		isArray: function(array){
			return Object.prototype.toString.apply(array) === '[object Array]';
		}
	});
	
	sino.ns = sino.namespace;
})(window);