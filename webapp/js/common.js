/*
 * 查找对象的绝对位置
 */

function getAbsPosition(o) {
	o = $(o); //如果传入的是传统的js对象，就会被包装成jquery对象，如果本身就是jquery对象，则还是jquery对象
	if (o.length == 0) {
		return false;
	}
	o = o[0]; //使用数组可以将jquery对象转换成普通js对象，这样就保证下面的o对象一定是普通的js对象
	
	// 求出到父元素的间距
	var left = o.offsetLeft;
	var top = o.offsetTop;
	
	while (o = o.offsetParent) { // 递归找到当前对象的父对象
		left += o.offsetLeft;
		top += o.offsetTop;
	}
	
	// 返回一个匿名函数，有两个属性：left属性的值为left
	return {
		left: left,
		top: top
	};
}

/*
 * 动态扩展String方法，增加trim方法
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*) | (\s*$)/, "");
};

/**
 * 将表单中各域的值自动封装成参数对象
 * @param oForm 表单对象
 */
function getFormPara(oForm) {
	oForm = $(oForm)[0];
	var len = oForm.elements.length;
	var ret = {};
	for (var i = 0; i < len; i++) {
		var oEle = oForm.elements[i];
		if (oEle.type === "radio") { // 单选框单独处理
			if (oEle.checked) {      // 如果被选中
				ret[oEle.name] = oEle.value.trim();
			}
		} else if (oEle.type === "checkbox") { // 多选框
			var curVal = ret[oEle.name];
			if (curVal === undefined) {        // 如果数组中不存在
				ret[oEle.name] = [];           // 为该参数对象创建一个数组
				if (oEle.checked) {
					ret[oEle.name].push(oEle.value.trim());
				} 
			} else {
				if (oEle.checked) {
					ret[oEle.name].push(oEle.value.trim());
				} 
			}
		} else {
			ret[oEle.name] = oEle.value.trim();
		}
	}
	return ret;
}

var Browser = {
		isIE: function(version){
			if (navigator.userAgent.toLowerCase().indexOf("msie") == -1) {
				return false;
			} else {
				if (version) {
					var regexpr = new RegExp("msie\\s*" + version, "g");
					if (navigator.userAgent.toLowerCase().match(regexpr)) {
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			}
		}
	};