var menuData;
var menuStat = "over";
var ctx;
var skinpath;

$().ready(function(){
	// 获取全局参数
	ctx = $("input:hidden[name=ctx]").val();
	skinpath = $("input:hidden[name=skinpath]").val();
	
	//页面布局
	pageLayout();
	$(window).resize(pageLayout);
	
	//获取菜单数据
	getMenuData(drawMenu);

	$().click(function(){
		hideOtherSubMenu();
		$("div.menuItemBack").css("visibility", "hidden");
		menuStat = "over";
	});
});

function getMenuData(callback){
	var url = "menuData.json";
	$.getJSON(url, {ts: new Date().getTime()}, function(data){
		menuData = data;
		callback(data);
	});
}

function drawMenu(data){
	for (var id in data) {
		var mname = data[id]["name"]
		var back = $("<div></div>").addClass("menuItemBack").appendTo("div.menu");
		$("<div></div>").addClass("menuItem").html(mname).attr("menuId", id).appendTo($("div.menu")).mouseover(function(){
			var left = this.offsetLeft;
			var top = this.offsetTop;
			var width = this.offsetWidth;
			var height = this.offsetHeight;
			back.css("left", left).css("top", top).css("width", width).css("height", height).css("visibility", "visible");
			if (menuStat == "over") {
				back.css("background-image", "url(" + skinpath + "/images/menuItem_over.gif)");
			} else {
				back.css("background-image", "url(" + skinpath + "/images/menuItem_active.gif)");
				//显示下级菜单
				showSubMenu(this);
			}
		}).mouseout(function(){
		}).click(function(event){
			if (menuStat == "over") {
				back.css("background-image", "url(" + skinpath + "/images/menuItem_active.gif)");
				menuStat = "active";
				//显示下级菜单
				showSubMenu(this);
			} else {
				back.css("background-image", "url(" + skinpath + "/images/menuItem_over.gif)");
				menuStat = "over";
				hideOtherSubMenu();
			}
			event.stopPropagation();
		});

	}
}

function showSubMenu(mainMenu){

	hideOtherSubMenu();

	var menuId = $(mainMenu).attr("menuId");
	var subMenuDiv = $("div#sm" + menuId);
	var subMenuBackDiv;

	if (subMenuDiv.length == 0) {
		subMenuDiv = $("<div></div>").addClass("subMenuDiv").appendTo("div.menu").attr("id", "sm" + menuId);
		subMenuBackDiv = $("<div></div>").addClass("subMenuItemBack").appendTo(subMenuDiv);
		subMenuDiv.css("left", mainMenu.offsetLeft).css("top", mainMenu.offsetTop + mainMenu.offsetHeight - 1);
		var subMenuItems = menuData[menuId]["subMenu"];

		for (var subMenuItemId in subMenuItems) {
			var menuItem = $("<div></div>").addClass("subMenuItem").html(subMenuItems[subMenuItemId]["name"]).attr("menuId", subMenuItemId).attr("action", subMenuItems[subMenuItemId]["action"]).appendTo(subMenuDiv);
			menuItem.mousemove(function(){
				subMenuBackDiv.css("top", this.offsetTop + 1).css("left", 1).css("height", this.offsetHeight - 2).css("width", this.offsetWidth - 2);
				subMenuBackDiv.css("display", "block");
			}).mouseout(function(){
				subMenuBackDiv.css("display", "none");
			}).click(function(){
				var action = $(this).attr("action");
				window.frames["content"].location = action;
				hideOtherSubMenu();
				$("div.menuItemBack").css("visibility", "hidden");
				menuStat = "over";
			});
		}
	}

	subMenuDiv.css("visibility", "visible");
	$("div.mask").css("display", "block");

	if (Browser.isIE(6)) {
		$("select", $(window.frames["content"].document.body)).css("visibility", "hidden");
	}
}

function pageLayout(){
	$("div.main").css("height", $(window).height() - $("div.menu").height() - $("div.foot").height());
	$("div.conDiv").css("width", $("div.main").width() - 6).css("height", $("div.main").height() - 8);
}

function hideOtherSubMenu(){
	//alert($("div[id^=sm]").length);
	$("div[id^=sm]").css("visibility", "hidden");
	$("div.mask").css("display", "none");
	if (Browser.isIE(6)) {
		$("select", $(window.frames["content"].document.body)).css("visibility", "visible");
	}
}
