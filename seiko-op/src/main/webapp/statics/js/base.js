// 保存排序
$(function () {
    //高级查询按钮
    $("#aAdvanced").click(function () {
        if ($("#Advanced").val() == "0") {
            $("#Advanced").val(1);
            $("#simpleSearch").hide();
            $("#aAdvanced").addClass("searchAdvancedS");
        } else {
            $("#Advanced").val(0);
            $("#simpleSearch").show();
            $("#aAdvanced").removeClass("searchAdvancedS");
        }
        $(".searchAdvanced").slideToggle("slow");
    });
})


function doSearch(){
    var data = $("#searchForm").serialize();
    $('#datagrid').datagrid('load', data);
}

/**
 * Name 打开添加窗口
 */
function openAdd(map){
    clearForm(map);
    $(map["dialog"]).dialog({
        closed: false,
        modal:true,
        title: map["title"],
        buttons: [{
            text: '确定',
            iconCls: 'icon-ok',
            handler: function () {
                var savebtn = $(this);
                var disabled = savebtn.hasClass("l-btn-disabled");
                if (!disabled) {
                    submitForm(map, savebtn, $(map["formId"]).serialize());
                }
            }
        }, {
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function () {
                $(map["dialog"]).dialog('close');
            }
        }]
    });
}

/**
 * Name 打开修改窗口
 */
function openEdit(map){
    clearForm(map);
    $.ajax({
        url:map["edit"],
        data:{"id":map["id"]},
        success:function(data){
            var result = data.result;
            if(result){
                $(map["dialog"]).dialog({
                    closed: false,
                    modal:true,
                    title: map["title"],
                    buttons: [{
                        text: '确定',
                        iconCls: 'icon-ok',
                        handler: function () {
                            var savebtn = $(this);
                            var disabled = savebtn.hasClass("l-btn-disabled");
                            if (!disabled) {
                                submitForm(map, savebtn, $(map["formId"]).serialize());
                            }
                        }
                    }, {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            $(map["dialog"]).dialog('close');
                        }
                    }]
                });
                $(map["formId"]).form('load', data.data);
            }else{
                $.Loading.error("获取信息失败");
            }
        }
    });
}

function submitForm(map, savebtn, data) {
    var formflag = $(map["formId"]).form().form('validate');
    if (formflag) {
        $.Loading.show("正在保存请稍后...");
        savebtn.linkbutton("disable");
        var options = {
            url: map["url"],
            data: data,
            type: 'post',
            dataType: 'json',
            async: false,
            success: function (result) {
                if (result.result == 1) {
                    $(map["dialog"]).dialog('close');
                    $(map["gridreload"]).datagrid('reload');
                    $.Loading.success("操作成功");
                } else {
                    $.Loading.error("操作失败");
                }
                savebtn.linkbutton("enable");
            },
            error: function (e) {
                $.Loading.error("出现错误 ，请重试");
                savebtn.linkbutton("enable");
            }
        };
        $(map["formId"]).ajaxSubmit(options);
    } else {
        savebtn.linkbutton("enable");
        $.Loading.hide();
    }
}

function clearForm(map) {
    $(map["formId"]).form('clear');
}

/**
 * 获取页面对象值
 *
 * @param obj 传入对象
 * @param prex 参数前缀
 * @param className 对象搜索内容的class标识
 * @returns {{}}
 */
function generateFormData(obj, prex, className) {
    var formdata = {};
    if (typeof prex == "undefined" || prex == null) {
        prex = "";
    }
    var conditions = null;
    if (typeof className == "undefined" || className == null) {
        conditions = obj.find("input[name],select[name],textarea[name]");
    } else {
        conditions = obj.find("." + className);
    }

    conditions.each(function () {
        var name = $(this).attr("name");
        var val = $(this).val();
        if ($(this).attr("type") == "radio") {
            if ($(this).prop("checked")) {
                formdata[prex + name] = val;
            }
        } else {
            formdata[prex + name] = val;
        }
    });
    return formdata;
}