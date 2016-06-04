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