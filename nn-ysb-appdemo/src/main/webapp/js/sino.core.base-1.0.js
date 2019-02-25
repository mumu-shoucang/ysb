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
 * 创建命名空间sino.base,为sino添加一些语言功能.
 * 对于一些非常常用,且通用功能函数的包含(打破模块化职责划分).
 */
(function(window, undefined) {

	// for old browsers,the code of next line references Ext js SRC.
	window.undefined = window.undefined;
	
	var DOC = window.document;
	
	var ID_REG = /#\w+/;
	
	/**
	 * 实现继承.
	 * <p>
	 * 本功能函数来源于Crockford的实现的修改.只做对内建Function扩展.
	 * 
	 * @param parent {Function} 父类对象.
	 */
	Function.prototype.inherits = function(parent) {
        this.prototype = new parent();
        this.prototype.constructor = this;
        this.prototype.uber = function(name) {
            var f = parent.prototype[name];
            return f.apply(this, Array.prototype.slice.call(arguments, 1));
        };
    };

	//扩充sino下的功能
	sino.apply(sino,{
		/**
		 * 定义命空间功能函数.
		 * 
		 * <p>
		 * 调用例子:
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
		 *            string type,以句点号分隔,如果不为string则不创建(如'ns1.ns2')
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
		 * 获取DOM元素,返回DOM元素的原生对象，并不做任何的封装.
		 * <p>
		 * 本操作本应放置到DOM相关模块中，考虑到使用的便捷性，且单向依赖，把功能方法定义在
		 * 这里.函数实现不要去依赖其它库.
		 * 
		 * @param elestr 元素字符串,ID,name
		 * @param opt_context 可选,默认是document
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
		 * 添加事件监听.
		 */
		addEventListener: function(elem,type,eventHandle){
			if ( elem.addEventListener ) {
				elem.addEventListener( type, eventHandle, false );
			} else if ( elem.attachEvent ) {
				elem.attachEvent( "on" + type, eventHandle );
			}
		},
		
		/**
		 * 删除事件监听.
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
		 * 触发事件.
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
		 * 判断是否是字符串类型.
		 * 
		 * <p>
		 * null or undefined 不是字符串类型.
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
		 * 是否是函数.
		 * @param fun
		 * @returns {Boolean}
		 */
		isFunction: function(fun){
			return Object.prototype.toString.apply(fun) === '[object Function]';
		},
		
		/**
		 * 是否是数组.
		 * 
		 * @param array
		 */
		isArray: function(array){
			return Object.prototype.toString.apply(array) === '[object Array]';
		}
	});
	
	sino.ns = sino.namespace;
})(window);