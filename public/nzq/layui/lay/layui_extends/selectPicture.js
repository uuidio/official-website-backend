layui.define(['jquery','upload','laypage','layer'],function(exports){
    var MOD_NAME = 'selectPicture';
    var $=layui.$;
    var layer=layui.layer;
    var laypage = layui.laypage;
    var upload = layui.upload;
    var Picture = function(config){
        this.mainPluginClass = '.picturePlugin',
            this.initPageFirst = true,
            this.selectGroup = '';
        this.config = {
            elem: '',
            page: 1,
            limit: 8,
            multiSelect: false,
            pictureDataUrl: '/bg/picture/r/all.json',
            uploadUrl: '/bg/picture/c/upload',
            removePictureUrl: '/bg/picture/d/'
        };
        this.config = $.extend(this.config,config);
    };

    Picture.prototype = {
        init: function () {
            this.bindClickEven();
        },
        bindClickEven:function () {
            var _this = this;
            $(this.config.elem).on("click", function (e) {
                _this.show();
            });
        },
        show:function () {
            var _this = this;

            _this.render();
            _this.initCloseEven();
            _this.initGroupEven();
            _this.initUpload();
            _this.initSelectPictureEven();
            _this.removePictureEven();
            _this.initSubmitEven();
            _this.initPageFirst = true;
            function success(result) {
                var groupingList = result.pictureGroupingList;
                var pictureList = result.pictureList;
                var total = result.total;

                _this.addPictureHtml(pictureList);
                _this.addGroupHtml(groupingList);
                _this.initPageBar(total);

                _this.popup();
            }
            _this.getGroupAndPictureData(_this.config.page,_this.config.limit,_this.selectGroup, success);

            $(_this.mainPluginClass).removeClass("layui-hide");
        },
        initCloseEven:function () {
            var _this = this;
            $(this.mainPluginClass).find('.mark_head .close').click(function () {
                _this.close();
            });
        },
        popup: function () {
            $(this.mainPluginClass).removeClass("layui-hide");
        },
        close: function () {
            $(this.mainPluginClass).remove();
        },
        getGroupAndPictureData:function (page,limit,group,callback) {
            var _this =this;
            var url = '/bg/picture/r/pictureOrGrouping?page='+page+'&limit='+limit+'&pictureGroupingId='+group;
            $.ajax({
                type: 'get',
                url: url,
                dateType: 'json',
                success: function (result) {
                    if ( callback != undefined && callback != null && typeof callback == "function" ){
                        callback(result);
                    }
                }
            });

        },
        addPictureHtml:function (pictureList) {

            var arr = []
            layui.each(pictureList, function (index, item) {
                // arr.push('<li>'+ item +'</li>');
                // arr.push("<li><div class='img' data-imgurl='" + item.imagePath + "' data-id='"+item.id+"' data-name='"+item.newName+"'><img src=" + item.imagePath + "/></div><p>" + item.newName + "</p></li>")
                arr.push('<li>'
                    + '<div class="img" data-imgurl='+ item.imagePath + ' data-id="'+item.id+'">'
                    + '<img src=' + item.cndImagePath + '/>'
                    // + '<p>160*160</p>'
                    + '</div>'
                    + '<div class="edit_btn">'
                    + '<span>...</span>'
                    + '<div class="operation">'
                    // + '<a href="javascript:;" id="updateName" class="second_name">改名</a>'
                    + '<a id="delete" data-deleteId="'+item.id+'">删除</a>'
                    + '</div>'
                    + '</div>'
                    + '<p>' + item.newName + '</p>'
                    + '</li>'
                )
            });
            $(this.mainPluginClass).find('.img_items').html(arr);

        },
        addGroupHtml:function (groupingList) {
            var _this = this;
            var groupingHtml = '';
            layui.each(groupingList, function (index, item) {
                if (index == 0) {
                    _this.selectGroup = 0;
                    groupingHtml += '<li style="width: 180px;" data-id="0" class ="on">'+
                        '<span>未分组</span>'+
                        '</li>'+
                        '  <li style="width: 180px;" data-id=' + item.id + '>' +
                        '    <span>' + item.pictureOwnership + '</span>' +
                        '<span class=""></span>' +
                        '   </li>';

                } else {
                    groupingHtml +=
                        '  <li style="width: 180px;" data-id=' + item.id + '>' +
                        '    <span>' + item.pictureOwnership + '</span>' +
                        '    <span class="num"></span>' +
                        '   </li>';
                }
            });
            $(this.mainPluginClass).find('.mark_left ul').html(groupingHtml);
        },
        initPageBar:function (total) {
            var _this = this;
            laypage.render({
                elem: $(_this.mainPluginClass).find(".page")
                ,count:total
                ,layout: ['count', 'prev', 'page', 'next', 'skip']
                ,limit:_this.config.limit
                ,groups:3
                ,jump: function(obj){
                    if(_this.initPageFirst) {
                        _this.initPageFirst = false;
                        return;
                    }

                    var page = obj.curr;
                    function pictureSuccess(result) {
                        var  pictureList = result.data;
                        _this.addPictureHtml(pictureList);
                    }
                    _this.getPictureData(page, _this.config.limit,null, pictureSuccess);
                }
            });
        },
        getPictureData:function (page,limit,group,callback) {
            var _this =this;
            if(page==null || page.length<=0) {
                page = _this.config.page;
            }

            if(limit==null || limit.length<=0) {
                limit = _this.config.limit;
            }
            var url = _this.config.pictureDataUrl+"?page="+page+"&limit="+limit;
            if(group==null) {
                group = _this.selectGroup;
            }

            if(group!=null) {
                url = url + "&pictureGroupingId=" + group;
            }
            $.ajax({
                type: 'get',
                url: url,
                dateType: 'json',
                success: function (result) {
                    if ( callback != undefined && callback != null && typeof callback == "function" ){
                        callback(result);
                    }
                }
            });
        },
        initGroupEven:function () {
            var _this = this;
            $(this.mainPluginClass).find('.mark_left').on('click','ul li',function () {
                $(this).addClass('on').siblings().removeClass('on');
                _this.selectGroup = $(this).data("id");
                _this.initPicture(_this.config.page, _this.config.limit, _this.selectGroup);
            })
        },
        initPicture:function (page,limit,group) {
            var _this = this;
            function success(result) {
                $(_this.mainPluginClass).find('.img_items').html('');
                _this.addPictureHtml(result.data);
                _this.initPageFirst = true;
                _this.initPageBar(result.count);
            }
            _this.getPictureData(page, limit,group,success);
        },initUpload:function () {
            var _this = this;
            upload.render({
                elem:  $(_this.mainPluginClass).find(".up_img")
                ,url: _this.config.uploadUrl
                ,multiple: true
                ,before: function(obj){
                    this.url = _this.config.uploadUrl+'/' + _this.selectGroup;
                }
                ,done: function(res){
                    if(res == 3) {
                        layer.msg('上传失败');
                    }else {
                        layer.msg('上传成功');
                    }
                    $(_this.mainPluginClass).find('.mark_left ul').find("li.on").click();

                }

            });
        },
        initSelectPictureEven:function () {
            var _this = this;
            $(this.mainPluginClass).find('.img_items').on('click','li div',function () {
                if (!_this.config.multiSelect) {
                    $(this).closest(".img_items").find("li").removeClass("img_on");
                }

                if($(this).parent().hasClass("img_on"))
                {
                    $(this).parent().removeClass("img_on");
                }
                else {
                    $(this).parent().addClass('img_on');
                }

            })
        },
        removePictureEven:function () {
            var _this = this;
            $(this.mainPluginClass).find('.img_items').on('click','#delete',function () {
                var id = $(this).data('deleteid');
                $.ajax({
                    type: "delete",
                    url: _this.config.removePictureUrl+id,
                    dataType:'json',
                }).success(function(message) {
                    if(message.code == 0) {
                        layer.msg('删除成功');
                        _this.initPicture(_this.config.page, _this.config.limit, _this.selectGroup);
                    }else {
                        layer.msg('删除失败');
                    }
                }).fail(function(err){

                })
            })
        },
        initSubmitEven:function () {
            var _this = this;
            $(this.mainPluginClass).find('.popup-submit').on('click',function () {
                var callback = _this.config.callback;
                if ( callback != undefined && callback != null && typeof callback == "function" ){
                    if (!_this.config.multiSelect) {
                        var selectPicturePar = $(_this.mainPluginClass).find('.img_items').find("li.img_on");
                        var pic ={};
                        var selectPicture = $(selectPicturePar).find(".img");
                        pic.id = selectPicture.data("id");
                        pic.url = selectPicture.data("imgurl");
                        pic.name = selectPicture.data("name");
                        callback(pic);
                    }else {
                        var pictures=[];
                        $(_this.mainPluginClass).find('.img_items').find("li.img_on").each(function (index) {
                            var pic ={};
                            var div = $(this).find(".img");
                            pic.id = div.data("id");
                            pic.url = div.data("imgurl");
                            pic.name = div.data("name");
                            pictures.push(pic);
                        });
                        callback(pictures);
                    }



                    _this.close();
                }
            });

        },
        render: function () {
            if ($(this.mainPluginClass) > 0) {
                $(this.mainPluginClass).remove();
            }
            $(document.body).append('<div class="picturePlugin layui-hide"><div class="picture-popup "><div class="mark_img">' +
                '<!--弹窗头部-->' +
                '    <div class="mark_head">' +
                '        <span>图片选择</span>' +
                '        <span class="close">' +
                '                <img src="/img/icon/close.png"' +
                '            </span>' +
                '    </div>' +
                '    <!--弹窗内容-->' +
                '    <div class="mark_body">' +
                '        <!--分组-->' +
                '        <div class="mark_left">' +
                '            <ul>' +
                '            </ul>' +
                '        </div>' +
                '        <!--图片展示-->' +
                '        <div class="mark_right" style="padding-right: 0px;">' +
                '            <ul class="img_box img_items">' +
                '            </ul>' +
                '            <div class="mark_right_bottom">' +
                '                <div class="layui-uploads upbtn">' +
                '                    <button type="button" class="layui-btn layui-btn-sm up_img disabled" >上传图片</button>' +
                '                </div>' +
                '                <div class="page_box page">' +
                '                </div>' +
                '            </div>' +
                '        </div>' +
                '    </div>' +
                '    <!--弹窗低部-->' +
                '    <div class="mark_footer">' +
                '        <button class="layui-btn layui-btn-sm popup-submit">确认</button>' +
                '    </div></div></div></div>');
        }
    };

    exports(MOD_NAME, function (config) {
        var _this = new Picture(config);
        _this.init();
        return _this;
    });

});