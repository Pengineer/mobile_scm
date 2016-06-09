/**
 * 常量: 菜单类型,3为分隔线
 */
var MENUTYPE_LINE = 3;

$().ready(function(){
    //设置容器表格、IFRAME高度
    $("#treeContainer, #ifrm").css("height", $(window).height() - 22);
    $(window).resize(function(){
        $("#treeContainer, #ifrm").css("height", $(window).height() - 22);
    });
    
    //加载树
    $.getJSON("dev/menuManage_menuTree.action", {
        ts: new Date().getTime() // 避免浏览器缓存，保证获取最新数据
    }, function(data){ // data为返回的json格式数据
    
        var treeData = [data.menuTree];
        
        function addUrlToMenu(dataList){
            for (var i = 0; i < dataList.length; i++) {
                var menu = dataList[i];
                if (menu.type != MENUTYPE_LINE) {
                    menu.url = "dev/menuManage_menuDetail.action?menu.id=" + menu.id + "&menu.type=" + menu.type;
                    if (menu.subMenuList && menu.subMenuList.length > 0) {
                        addUrlToMenu(menu.subMenuList);
                    }
                }
            }
        }
        
        addUrlToMenu(treeData);
        
        var props = ObjUtils.deepClone(treeDefaultProps);
        props.fieldNameMap.id = "id";
        props.fieldNameMap.label = "name";
        props.fieldNameMap.subTree = "subMenuList";
        props.urlTarget = "menuDetail";
        
        $("#treeContainer").tree(treeData, props);
    });
});


