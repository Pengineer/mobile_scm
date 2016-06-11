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
    
    loadTree();
});

function loadTree() {
	//加载树
   $.getJSON("dev/menuManage_menuTree.action", {
       ts: new Date().getTime()
   }, function(data){
   
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
       props.expandLevel = 2;
       $("#treeContainer").html("");
       $("#treeContainer").tree(treeData, props);
   });
}
