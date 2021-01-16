
layui.use(['element','table','jquery','form','upload'],function(){
    //引入jquery
    var $=layui.$;
    var element=layui.element;
    var laydate=layui.laydate;
    var form=layui.form;
    var upload=layui.upload;

    var tableHeaderData;
    var tableName;
    var table=layui.table;
    window.getTable=function(hData,bPath,callback){  //表格渲染方法
        tableHeaderData=hData;
        table.render({//渲染表格
            elem:'#dataTable'
            ,url:bPath
            ,page:true//后端分页默认一次请求10条数据
            ,cols:hData
            ,cellMinWidth:120
            ,done: function(res, curr, count){//表格渲染完成后回调
                tableName=res.tableName;//得到当前表的名字

                if ( callback != undefined && callback != null && typeof callback == "function" ){
                    callback(res);
                }
            }
        });
    };
    window.tHeader=function(hPath,bPath,callback){//定义一个请求表头数据的tHeader方法 path:tHeader的地址 url时tbody地址
        $.ajax({
            url:hPath,
            //此处通过error实现回调
            success:function(data){
                getTable(data,bPath,callback);
            }
        });
    };


    //添加弹窗内容方法
    window.getPopUpHtml= function (url,callback) {
        $.ajax({
            url:url,
            type:'get',
            dataType:'html',
            success:function(htmlData){
                $(".popupHtml").html('');
                $(".popupHtml").html($(htmlData).find(".content").html());
                $(".popupHtml").show();
                form.render();
                if ( callback != undefined && callback != null && typeof callback == "function" ){
                    callback();
                }
            }
        })
    }



});