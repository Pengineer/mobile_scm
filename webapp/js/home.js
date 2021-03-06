var menuData;
var ctx;
var skinpath;
var menuActived = false;

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
        hideAllOther();
        menuActived = false;
    });
});

function pageLayout(){
    $("div.main").css("height", $(window).height() - $("div.menu").height() - $("div.foot").height());
    $("div.conDiv").css("width", $("div.main").width() - 6).css("height", $("div.main").height() - 8);
}

function getMenuData(callback){
    var url = ctx + "/menuData.json";
    $.getJSON(url, null, function(data){
        menuData = data;
        callback(data);
    });
}

function drawMenu(data){
    for (var menuId in data) {
        var menu = data[menuId];
        //显示菜单
        var menuDiv = $("<div></div>").attr("id", menu.id).html(menu.name).addClass("menuItem").appendTo("div.menu");
        var menuBackDiv = $("<div></div>").addClass("menuItemBack").appendTo("div.menu");
        menuBackDiv.css("width", menuDiv[0].offsetWidth).css("height", menuDiv[0].offsetHeight).css("top", menuDiv.position().top).css("left", menuDiv.position().left).attr("id", "mb" + menu.id);
        menuDiv.mouseover(function(){
            hideAllOther();
            showMenuItemBack(this);
            if (menuActived) {
                showMenu(this);
            }
        }).mouseout(function(){
            if (!menuActived) {
                hideAllOther();
            }
        }).click(function(evt){
            menuActived = !menuActived;
            if (menuActived) {
                showMenu(this);
            } else {
                hideAllOther();
            }
            showMenuItemBack(this);
            evt.stopPropagation();
        });
    }
}

function showMenuItemBack(menuItem){
    menuItem = $(menuItem);
    var relateBackDiv = $("div#mb" + menuItem.attr("id"));
    if (menuActived) {
        relateBackDiv.css("background-image", "url(" + skinpath + "/images/menuItem_active.gif)");
    } else {
        relateBackDiv.css("background-image", "url(" + skinpath + "/images/menuItem_over.gif)");
    }
    relateBackDiv.css("visibility", "visible");
}

function hideMenuItemBack(menuItem){
    menuItem = $(menuItem);
    var relateBackDiv = $("div#mb" + menuItem.attr("id"));
    relateBackDiv.css("visibility", "hidden");
}

function showMenu(rootMenuItem){
    rootMenuItem = $(rootMenuItem);
    var id = rootMenuItem.attr("id");
    var relateBackDiv = $("div#mb" + id);
    
    var subMenuList = menuData[id].subMenu;
    if (subMenuList) {
        var subMenuDiv = $("div#sm" + id);
        if (subMenuDiv.length == 0) { //若菜单不存在，创建菜单
            subMenuDiv = $("<div></div>").attr("id", "sm" + id).addClass("subMenuDiv").appendTo("div.menu");
            var subMenuBackDiv = $("<div></div>").addClass("subMenuItemBack").css("left", "1px").appendTo(subMenuDiv);
            $("<div></div>").addClass("subMenuWhiteItem").appendTo(subMenuDiv);
            for (var subMenuId in subMenuList) {
                if (subMenuId.startWith("sepline_")) {
                    $("<div></div>").addClass("subMenuSeparateLineDiv").html("<div class='subMenuSeparateLine'></div>").appendTo(subMenuDiv);
                } else {
                    var subMenu = subMenuList[subMenuId];
                    tmp = $("<div></div>").addClass("subMenuItem").html(subMenu.name).attr("id", subMenu.id).appendTo(subMenuDiv);
					tmp.attr("action", subMenu.action);
                    tmp.mouseover(function(){
                        if (Browser.isIE()) {
                            itemWidth = this.offsetWidth - 2;
                            itemHeight = this.offsetHeight;
                        } else {
                            itemWidth = this.offsetWidth - 4;
                            itemHeight = this.offsetHeight - 1;
                        }
                        subMenuBackDiv.css("width", itemWidth).css("height", itemHeight).css("top", $(this).position().top).css("display", "block").appendTo(subMenuDiv);
                    }).mouseout(function(){
                        subMenuBackDiv.css("display", "none");
                    }).click(function() {
						frames["content"].location = ctx + "/" + $(this).attr("action");
					});
                }
            }
            $("<div></div>").addClass("subMenuWhiteItem").appendTo(subMenuDiv);
            subMenuDiv.css("top", relateBackDiv.position().top + relateBackDiv[0].offsetHeight - 1).css("left", relateBackDiv.position().left);
        }
        
        if (Browser.isIE(6)) {
            $("select", $(frames["content"].document.body)).css("visibility", "hidden");
        }
        $("div.mask").css("display", "block");
        subMenuDiv.css("visibility", "visible");
    }
}

function hideAllOther(){
    $("div[id^=mb], div[id^=sm]").css("visibility", "hidden");
    $("div.mask").css("display", "none");
    if (Browser.isIE(6)) {
        $("select", $(frames["content"].document.body)).css("visibility", "visible");
    }
}
