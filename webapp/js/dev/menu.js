/** 菜单类型：无子菜单*/
var MENUTYPE_NODE = 2;

/**
 * 显示隐藏动作输入框
 * @param {Object} o
 */
function showHideActionRow(o){
    if (o.value == MENUTYPE_NODE) {
        $("#actionRow").css("display", "");
        $("#input[name=menu.action.actionName]").removeAttr("disabled");
    } else {
        $("#actionRow").css("display", "none");
        $("#input[name=menu.action.actionName]").attr("disabled", true);
    }
}

/**
 * 新增菜单表单校验
 */
function validateFrm(){	
    var oMenuName = $("input[name=menu.menuName]");
    if (oMenuName.val().trim().length == 0) {
		oMenuName.focus();
        window.alert("菜单名不能为空！");
        return false;
    }
    return true;
}
