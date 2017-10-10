var map={};
$(function () {
    initData();
})

function initData(){
    map["add"] = basePath + "/user/add";
    map["edit"] = basePath + "/user/edit";
    map["formId"] = "#userForm";
    map["url"] = basePath + "/user/saveUser";
    map["title"] = "用户编辑";
    map["dialog"] = "#userDialog";
    map["gridreload"] = "#datagrid";

    $(".addBtn").click(function(){
        openAdd(map);
    });

    $(".editBtn").click(function(){
        var item = $(map["gridreload"]).datagrid('getSelected');
        if(item){
            map["id"] = item.userId;
            openEdit(map);
        }
    })
}
// function formatName(value, row, index) {
//     var val = "<a href=\"javascript:void(0)\"target=\"_blank\">" + row.goodsName + "</a>";
//     return val;
// }


