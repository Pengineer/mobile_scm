/*
 * 当文档加载完后触发jquery中的ready事件，执行ready方法：
 * (1)为登录图片绑定鼠标进出事件
 * (1.1)ready接受一个匿名回调函数
 * (1.2)根据id匹配元素：格式为#id，等价于DOM中document.getElementById(id)
 * (1.3)然后为该元素绑定一个鼠标进入事件
 * (1.4)this表示触发事件的对象，替换它的src属性值，也就完成了图片的更换。
 * (1.5)同时为该标签绑定一个鼠标离开的方法。
 * 
 * (2)获取验证码图片相对其输入框的绝对位置
 * (2.1)取出输入框控件：查找input标签中name属性值为vcode的那一个，格式为：对象[属性名=属性值]
 * (2.2)获取控件的绝对位置
 * (2.3)定义验证码图片的绝对位置
 * (2.4)找到class为validateCodeDiv的div层，并设置css样式：格式为，对象.class名。为一个页面设置背景$(document.body).css( "background", "black" );
 * 
 * (3)设置验证码图片框显隐：
 * (3.1)当鼠标点击被绑定对象获取有tab键使得控件获得焦点则触发隐藏事件
 * (3.2)当鼠标点击验证码输入框获取它获取到焦点则触发显示事件
 * 
 * (4)绑定验证码图片框鼠标单击事件实现跟换验证码
 */
$(document).ready(function() {
	$("#submitBtn").mouseover(function() {
		this.src = "images/login_submitBtn2.gif";
	}).mouseout(function() {
		this.src = "images/login_submitBtn1.gif";
	});
	
	var vcode = $("input[name=vcode]");
	var pos = getAbsPosition(vcode);
	// alert(pos.top + ", " + pos.left); 调试
	var validateLeft = pos.left;
	var validateTop = pos.top + vcode[0].offsetHeight + 2;
	// var validateTop = pos.top + vcode.height() + 2; 此种方式获取的高度有误差，还是上面原生的准确
	$("div.validateCodeDiv").css("top", validateTop).css("left", validateLeft);
	
	showHideVcodeImg();
	
	$("div.validateCodeDiv").click(function(event) {
		changeCode();
		event.stopPropagation();
	});
});

function showHideVcodeImg() {
	$("input[name=account], input[name=passwd], #submitBtn").click(function() {
		$("div.validateCodeDiv").css("display", "none");
	}).focus(function() {
		$("div.validateCodeDiv").css("display", "none");
	});
	
	$("input[name=vcode]").click(function(event) {
		$("div.validateCodeDiv").css("display", "block");
		event.stopPropagation(); // 防止事件冒泡，因为点击验证码还符合下面定义的顶级的document事件，这种方式可以防止事件在DOM结构中向上传递
	}).focus(function() {
		$("div.validateCodeDiv").css("display", "block");
	});
	
	$(document).click(function() {
		$("div.validateCodeDiv").css("display", "none");
	}); 
}

function changeCode() {
	
}