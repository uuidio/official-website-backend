/**
 * Created by fai on 2018/2/1.
 * 注：
 */

layui.use(['element','table','laydate','jquery','tree','form','upload'],function(){
    //引入jquery
    var $=layui.$;//得到jquer对象
    var element=layui.element;
    var laydate=layui.laydate;
    var form=layui.form;
    var upload=layui.upload;
    var tableHeaderData;
    var tableName;
    var oilCardUrl = "/bg/oilCard/record.json";
    var deliverUrl = "/bg/oilCard/deliver";
    var chargeUrl = "/bg/oilCard/charge";
    var oilCardRechargeUrl = "/bg/oilCard/rechargerecord.json";
    var oilStationUrl = "/bg/oilStation/all.json";
    var repairStationUrl = "/bg/repairStation/all.json";
    var agentUrl = "/bg/agent/all.json";
    var selectLeftItem = "";

    var table=layui.table;
    var getTable=function(hData,bPath,callback){  //表格渲染方法
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

    var header={
        oilCard:function () {
            tHeader('/bg/data/list/tHeader/buyCard.json',oilCardUrl,function (tableData) {
                var deliver=$('.bDeliver');
                for(var i=0;i<deliver.length;i++){
                    var obj=tableData.data[i];
                    if(obj.status=='已发货'){
                        $(deliver[i]).addClass('layui-btn-disabled');
                    }
                }
            });
        },oilRecharge:function () {
            tHeader('/bg/data/list/tHeader/oilRecharge.json',oilCardRechargeUrl,function (tableData) {
                var deliver=$('.oilRechargeChargeBtn');
                for(var i=0;i<deliver.length;i++){
                    var obj=tableData.data[i];
                    if(obj.status=='已充值'){
                        $(deliver[i]).addClass('layui-btn-disabled');
                    }
                }
            });
        },oilStation:function () {
            tHeader('/bg/data/list/tHeader/oilStation.json',oilStationUrl);
        },repairStation:function () {
            tHeader('/bg/data/list/tHeader/repairStation.json',repairStationUrl);
        },agentUrl:function () {
            tHeader('/bg/data/list/tHeader/agent.json',agentUrl);
        }
    }

    table.on('tool(lTable)', function(obj){ //监听button工具条执行查看 添加删除等功能
        if(obj.event === 'deliver'){//发货按钮
            var that=this;
            var tData=obj.data;

            if(!$(that).hasClass('layui-btn-disabled')){//发货按钮如果被禁用则不能点击
                var url = deliverUrl + "/"+tData.orderNo;
                $.ajax({
                    url:url,
                    success:function(data){//这里以后要改成success
                        if(data.code==0) {
                            layer.alert('发货成功',function(index){
                                layer.close(index);
                                header.oilCard();
                            });
                        }else
                        {
                            layer.alert('发货失败',function(index){
                                layer.close(index);
                            });
                        }

                    },error:function(data){
                        layer.alert('发货失败',function(index){
                            layer.close(index);
                        });
                    }
                })
            }

        }

        else if(obj.event === 'del'){//删除按钮
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
                $.ajax({
                    url:'地址',//传data.id
                    success:function(data){
                        layer.alert('删除成功',function(index){
                            layer.close(index)
                        })
                    }
                })
            });
        }

        else if(obj.event === 'edit'){//编辑按钮
            var data = obj.data;//获得当前行的编辑数据
            var tr = obj.tr;//获得当前行
            $('#edit').css('display','block');

            //编辑页面节点数据操作（prefect）
            var nameObj= $('.put');
            for(var i=0,j=0;i<nameObj.length;i++){//1.得到页面中的input 2.将其name属性添加值
                $( nameObj[i]).attr('name','${tableHeaderData[0][i].field}');
            }
            for(var i=0;i<tableHeaderData[0].length;i++){//1.根据表格的body中的field找到对应的input中的name元素 2.为lable添加值
                var obj=tableHeaderData[0][i];
                var fd=obj.field;
                var title=obj.title;
                $('input[name=${fd}]').val(data[fd]).parent().prev().html(title);
            }
            $('input[name=status]').attr('disabled','');//设置订单状态不能改
        }

        else if(obj.event === 'oilStationDelete') {
            var data = obj.data;
            $.ajax({
                url:'/bg/oilStation/delete/'+data.id,//传data.id
                type:'get',
                success:function(data){
                    if(data.code==0) {
                        header.oilStation();
                    }
                }
            })
        }

        else if(obj.event === 'oilStationEdit') {
            var data = obj.data;
            getPopUpHtml('/bg/oilStation/edit/'+data.id);
        }

        else if(obj.event === 'repairStationDelete') {
            var data = obj.data;
            $.ajax({
                url:'/bg/repairStation/delete/'+data.id,
                type:'get',
                success:function(data){
                    if(data.code==0) {
                        header.repairStation();
                    }
                }
            })
        }
        else if(obj.event === 'repairStationEdit') {
            var data = obj.data;
            getPopUpHtml('/bg/repairStation/edit/'+data.id);
        }

        else if(obj.event === 'oilRechargeCharge') {
            var that=this;
            var tData=obj.data;
            var url = chargeUrl + "/"+tData.orderNo;
            if($(that).hasClass('layui-btn-disabled')){
                return;
            }
            $.ajax({
                url:url,
                success:function(data){//这里以后要改成success
                    if(data.code==0) {
                        layer.alert('充值成功',function(index){
                            layer.close(index);
                            header.oilRecharge();
                        });
                    }else
                    {
                        layer.alert('充值失败',function(index){
                            layer.close(index);
                        });
                    }

                },error:function(data){
                    layer.alert('充值失败',function(index){
                        layer.close(index);
                    });
                }
            })
        }

        else if(obj.event === 'agentEdit') {
            var data = obj.data;
            getPopUpHtml('/bg/agent/edit/'+data.id);
        }

        else if(obj.event === 'agentDelete') {
            var data = obj.data;
            $.ajax({
                url:'/bg/agent/delete/'+data.id,
                type:'get',
                success:function(data){
                    if(data.code==0) {
                        header.agentUrl();
                    }
                }
            })
        }

        else if(obj.event === 'agentUrl') {
            var data = obj.data;
            var code = data.code;
            //获取当前网址
            var curWwwPath=window.document.location.href;
            //获取主机地址之后的目录
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            var localhostPaht="" ;
            if(pathName=="/")
            {
                localhostPaht = curWwwPath;
            }else
            {
                localhostPaht = curWwwPath.substring(0,pos);
            }

            var url = localhostPaht + "wx/oilCard/recharge?fromagent=" + code;
            layer.alert(url,function(index){
                layer.close(index);
            });
        }
    });



    var tHeader=function(hPath,bPath,callback){//定义一个请求表头数据的tHeader方法 path:tHeader的地址 url时tbody地址
        $.ajax({
            url:hPath,
            //此处通过error实现回调
            success:function(data){
                getTable(data,bPath,callback);
            }
        });
    };


    //油卡业务部分
    $('.left-item').on('click','a',function(){//利用事件委托监听a 的点击
        var cont=$(this).html();
        $('#tContent').html(cont);
        if(cont=='油卡充值'){
            selectLeftItem="oilCardCharge";
            header.oilRecharge();
        }
        else if(cont=="油卡购买"){
            selectLeftItem="oilCardBuy";
            header.oilCard();

        }else if(cont=="油站"){
            selectLeftItem="oilStation";
            header.oilStation();

        }else if(cont=='汽修站')
        {
            selectLeftItem="repairStation";
            header.repairStation();
        }else if(cont=='代理商')
        {
            selectLeftItem="agent";
            header.agentUrl();
        }
        else if(cont=="微信用户"){
            tHeader('/bg/data/list/tHeader/oilRecharge.json','/bg/data/list/tBody/oilRecharge.json');
        }
        showBtns(cont);
    });


    function showBtns(cont) {//左边导航按钮操作是控制右边tab上的按钮 来显示和隐藏
        if(cont=="油站" || cont=="汽修站" || cont=="代理商")
        {
            $("#newBtn").show();
        }else
        {
            $("#newBtn").hide();
        }


        if(cont=="油卡购买" || cont=="油卡充值") {
            $("#export").show();
            $('#DRecharge').show();
        }else
        {
            $("#export").hide();
            $('#DRecharge').hide();
            $('#import1').hide();
            $('#import2').hide();
        }
    }

    //初始化渲染
    header.oilCard();

    laydate.render({//渲染时间
        elem:'#startTime'
    });
    laydate.render({
        elem:'#endTime'
    });


    //复选框部分
    active = {
        getCheckData: function(){ //获取选中数据
            var checkStatus = table.checkStatus('dataTable')
                ,data = checkStatus.data;

            //解决CSRF问题
            var token = $('#_csrf').attr('content');
            var header = $('#_csrf_header').attr('content');

            //批量发货或充值，传数据给后台，批量修改
            if(data.length!=0 && tableName=="油卡购买"){
                //向后台发送勾选数据
                $.ajax({
                    url:"/bg/table/buy",
                    beforeSend:function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    type:"post",
                    contentType: "application/json;charset=utf-8",
                    data:JSON.stringify(data),
                    success:function () {
                        //重新渲染表格
                        tHeader('/bg/data/list/tHeader/buyCard.json',oilCardUrl,function (tableData) {
                            var deliver=$('.bDeliver');
                            for(var i=0;i<deliver.length;i++){
                                var obj=tableData.data[i];
                                if(obj.status=='已发货'){
                                    $(deliver[i]).addClass('layui-btn-disabled');
                                }
                            }
                        });

                        layer.alert("批量发货成功！");
                    }
                });
            }else if(data.length!=0 && tableName=="油卡充值"){
                //向后台发送勾选数据
                $.ajax({
                    url:"/bg/table/re",
                    beforeSend:function (xhr) {
                        xhr.setRequestHeader(header, token);
                    },
                    type:"post",
                    contentType: "application/json;charset=utf-8",
                    data:JSON.stringify(data),
                    success:function () {
                        //重新渲染表格
                        tHeader('/bg/data/list/tHeader/oilRecharge.json',oilCardRechargeUrl,function (tableData) {
                            var deliver=$('.oilRechargeChargeBtn');
                            for(var i=0;i<deliver.length;i++){
                                var obj=tableData.data[i];
                                if(obj.status=='已充值'){
                                    $(deliver[i]).addClass('layui-btn-disabled');
                                }
                            }
                        });
                        layer.alert("批量充值成功！");
                    }
                });
            }else {
                layer.alert("请勾选记录！");
            }

        }

    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //（编辑页面部分）
    form.on('submit(demo2)', function(data){//监听提交
        var formData=JSON.stringify(data.field);
        //发送ajax请求更新数据
        $.ajax({
            //url需要替换
            url:'data/data.json'
            ,data:formData
            ,error:function(data){//代码测试 请改成success
                layer.alert('提交成功', {
                    title: '提交信息'
                },function(index){
                    //发送消息渲染表单
                    layer.close(index);
                    $('#edit').css('display','none');

                })
            }
        });
        return false;//阻止表单提交的默认行为
    });

    form.on('submit(demo1)', function(data){//监听提交
        layer.alert('你确定要关闭吗', {
            title: '关闭'
        },function(index){
            layer.close(index);
            $('#edit').css('display','none');
        });
        return false;//阻止表单提交的默认行为
    });

    $('div.close').click(function(){   //编辑页面中的关闭按钮
        layer.alert('你确定要关闭吗', {
            title: '关闭'
        },function(index){
            layer.close(index);
            $('#edit').css('display','none');
        });
    });

    $(".popupHtml").on('click',".popup-submit",function () {
        var form = $(this).closest(".submit-form");

        if(selectLeftItem=='oilStation')
        {
            $.ajax({
                url:'/bg/oilStation/add',
                type:'post',
                data:$(form).serialize(),
                success:function(data){
                    if(data!=null) {
                        if(data.code==0) {
                            $(".popupHtml").hide();
                            header.oilStation();
                        }else
                        {
                            alert("新增失败");
                        }
                    }
                }
            })
        }

        else if(selectLeftItem=='repairStation'){
            $.ajax({
                url:'/bg/repairStation/add',
                type:'post',
                data:$(form).serialize(),
                success:function(data){
                    if(data!=null) {
                        if(data.code==0) {
                            $(".popupHtml").hide();
                            header.repairStation();
                        }else
                        {
                            alert("新增失败");
                        }
                    }
                }
            })
        }

        else if(selectLeftItem=='agent'){
            $.ajax({
                url:'/bg/agent/add',
                type:'post',
                data:$(form).serialize(),
                success:function(data){
                    if(data!=null) {
                        if(data.code==0) {
                            $(".popupHtml").hide();
                            header.agentUrl();
                        }else
                        {
                            alert("新增失败");
                        }
                    }
                }
            })
        }


        return false;
    });

    $(".popupHtml").on('click',"div.close",function () {

        $(".popupHtml").hide();
    });


    //excel表格的输出
    $('#export').click(function(){
        if(tableName){//tableName有值则可以点击
            layer.alert('你确定要导出表格吗', {
                title: 'Excel表格导出'
            },function(index){
                layer.close(index);
                debugger;
                if(tableName=="油卡充值"){//通过tableName判断是哪个table发送相应的请求
                    window.open("/bg/oilCard/recharge/exportExcel");
                }
                else if(tableName=="油卡购买"){
                    /* $.ajax({
                         url:'data/data.json'//url需要替换
                         ,data:{tableName:tableName}
                         ,error:function(data){
                             layer.alert('导出成功', {
                                 title: '导出数据'
                             },function(index){
                                 layer.close(index);
                             })
                         }
                     });*/
                    window.open("/bg/oilCard/exportExcel");
                }
            });
        }
    })

    $('#newBtn').on('click', function () {
        if(selectLeftItem=='oilStation')
        {
            getPopUpHtml('/bg/oilStation/add.html');
        }else if(selectLeftItem=='repairStation')
        {
            getPopUpHtml('/bg/repairStation/add.html');
        }else if(selectLeftItem=='agent')
        {
            getPopUpHtml('/bg/agent/add.html');
        }
    });

    $('#logout').on('click', function () {
        $(this).closest("form").submit();
    });

    function getPopUpHtml(url) {
        $.ajax({
            url:url,
            type:'get',
            dataType:'html',
            success:function(htmlData){
                $(".popupHtml").html('');
                $(".popupHtml").html($(htmlData).find(".content").html());
                $(".popupHtml").show();
            }
        })
    }

    //解决CSRF问题
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });


    // 文件上传
    upload.render({//油卡
        elem: '#import1' //绑定元素
        ,url: '/excel/' //上传接口
        ,accept:'file'
        ,exts:'xls'
        ,done: function(res){
            //上传完毕回调
            getPopUpHtml("/bg/import/rs");
        }
        ,error: function(){
            //请求异常回调
        }
    });

    //执行实例
    upload.render({//油卡充值
        elem: '#import2' //绑定元素
        ,url: '/excel/' //上传接口
        ,accept:'file'
        ,exts:'xls'
        ,done: function(res){
            //上传完毕回调
            getPopUpHtml("/bg/import/rs");
        }
        ,error: function(){
            //请求异常回调
        }
    });

    $('dd.btnOilBuy').on('click',function(){//油卡充值按钮
        $('#import1').css('display','block').siblings('.uploadBtn').css('display','none');
    });
    $('dd.btnOilRecharge').on('click',function(){//油卡购买按钮
        $('#import2').css('display','block').siblings('.uploadBtn').css('display','none');
    });


});